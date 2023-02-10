import axiosInstance from "@/common/plugins/axios";

export default {
  /**
   * This function calls /kline GET endpoint.
   * Used when user want to see the klines (trading lines).
   * @returns KLine[]
   */
  async get(payload) {
    const { symbol, t1, t2 } = payload;
    try {
      const { data } = await axiosInstance.get(
        `/api/kline?symbol=${symbol}&interval=1m&startTime=${t1}&endTime=${t2}`
      );
      return data;
    } catch (error) {
      console.log(error);
    }
  },
};
