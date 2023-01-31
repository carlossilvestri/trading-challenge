<template>
  <trading-vue class="m-auto" :data="$data" :width="width" :height="height">
  </trading-vue>
</template>
<script>
import TradingVue from "trading-vue-js";
import tradingService from "@/trading/services/trading.js";
import { convertToArray } from "@/trading/utils/utils.js";
export default {
  name: "app",
  components: { TradingVue },
  data() {
    return {
      width: window.innerWidth - 370,
      height: window.innerHeight - 100,
      ohlcv: [],
    };
  },
  methods: {
    async getLines() {
      const data = await tradingService.get();
      const lines = convertToArray(data);
      this.ohlcv = JSON.parse(JSON.stringify(lines));
    },
    async init() {
      await Promise.all([this.getLines()]);
    },
  },
  async mounted() {
    await this.init();
  },
};
</script>
