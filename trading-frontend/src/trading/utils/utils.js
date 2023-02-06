/**
 * Connect to web sockets.
 * @param url: string
 * @returns Object
 */
export const Stream = (url) => {
  var ws = new WebSocket(url);
  var cb = () => {};

  ws.onopen = function () {
    console.log("Websocket is opened");
  };

  ws.onmessage = function (data) {
    try {
      data = JSON.parse(data.data);
      cb(data);
    } catch (e) {
      console.log(e);
    }
  };

  return {
    set ontrades(val) {
      cb = val;
    },
    off() {
      ws.close(1000);
    },
  };
};
/**
 * Format data.
 * @param data: Array
 * @returns Object
 */
export const format = (data) => {
  // Each query sets data to a corresponding overlay
  return {
    "chart.data": data,
    // other onchart/offchart overlays can be added here,
    // but we are using Script Engine to calculate some:
    // see EMAx6 & BuySellBalance
  };
};
// Parse a specific exchange format
export const parse_binance = (data) => {
  if (!Array.isArray(data)) return [];
  return data.map((x) => {
    for (var i = 0; i < x.length; i++) {
      x[i] = parseFloat(x[i]);
    }
    return x.slice(0, 6);
  });
};
