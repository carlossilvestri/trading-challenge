<template>
  <trading-vue
    class="m-auto"
    :data="chart"
    :width="width"
    :height="height"
    ref="tvjs"
  >
  </trading-vue>
</template>
<script>
import TradingVue, { Constants, DataCube, Utils } from "trading-vue-js";
import { Stream, format, parse_binance } from "@/trading/utils/utils.js";
import tradingService from "@/trading/services/trading.js";

export default {
  name: "TradingChart",
  components: { TradingVue },
  data() {
    return {
      chart: {},
      width: window.innerWidth - 370,
      height: window.innerHeight - 100,
      ohlcv: [],
      WSS: `${process.env.VUE_APP_WSS_BINANCE}`,
      URL: `${process.env.VUE_APP_BACKEND_URL}/api/kline`,
      stompClient: null,
    };
  },
  methods: {
    /**
     * Load lines and handle them.
     * @returns void.
     */
    async load_lines() {
      // Load the last data chunk & init DataCube:
      let now = Utils.now();
      const data = await this.get_lines([now - Constants.HOUR4, now]);
      this.handle_get_lines(data);
    },
    /**
     * Get lines
     * @param range : [timestamp, timestamp]
     * @returns Object : { "chart.data": number[]  }
     */
    async get_lines(range) {
      let [t1, t2] = range;
      let symbol = "BTCUSDT";
      const payload = { symbol, t1, t2 };
      const data = await tradingService.get(payload);
      return format(parse_binance(data));
    },
    on_trades(trade) {
      this.chart.update({
        t: trade.T, // Exchange time (optional)
        price: parseFloat(trade.p), // Trade price
        volume: parseFloat(trade.q), // Trade amount
        "datasets.binance-btcusdt": [
          // Update dataset
          trade.T,
          trade.m ? 0 : 1, // Sell or Buy
          parseFloat(trade.q),
          parseFloat(trade.p),
        ],
        // ... other onchart/offchart updates
      });
    },
    /**
     * Init DataCube:
     * @param data: Object { "chart.data": number[]  }
     */
    handle_get_lines(data) {
      this.chart = new DataCube(
        {
          ohlcv: data["chart.data"],
        },
        { aggregation: 100 }
      );
      // Register a stream of trades
      this.$refs.tvjs.resetChart();
      this.stream = new Stream(this.WSS);
      this.stream.ontrades = this.on_trades;
    },
    /**
     * Create resize event and calls on_resize.
     * @returns void
     */
    resize_event() {
      window.addEventListener("resize", this.on_resize);
      this.on_resize();
    },
    /**
     * Destroy resize event.
     */
    destroy_resize_event() {
      window.removeEventListener("resize", this.on_resize);
      if (this.stream) this.stream.off();
    },
    /**
     * Adapt screen to be responsive.
     * @returns void
     */
    on_resize() {
      this.width = window.innerWidth - 370;
      this.height = window.innerHeight - 100;
    },
    // Functions to load when the component is mounted.
    async init() {
      this.resize_event();
      await Promise.all([this.load_lines()]);
    },
  },
  beforeDestroy() {
    this.destroy_resize_event();
  },
  async mounted() {
    await this.init();
  },
};
</script>
