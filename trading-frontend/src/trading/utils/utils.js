/**
 * Convert to array, so can be used on tradingcomponent.
 * @param payload: Kline[]
 * @returns [ string[] | number[] ]
 */
export const convertToArray = (payload) => {
  if (!payload) return [];
  const data = payload.map((item) => {
    return [
      item.timestamp,
      Number(item.open),
      Number(item.high),
      Number(item.low),
      Number(item.close),
      Number(item.volume),
    ];
  });
  return data;
};
