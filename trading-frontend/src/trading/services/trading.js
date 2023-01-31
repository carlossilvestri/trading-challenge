import axiosInstance from "@/common/plugins/axios";

export default {
  /**
   * This function calls /kline GET endpoint.
   * Used when user want to see the klines (trading lines).
   * @returns KLine[]
   */
  async get() {
    try {
      const { data } = await axiosInstance.get(`/api/kline`);
      return data;
    } catch (error) {
      console.log(error);
    }
  },
};
