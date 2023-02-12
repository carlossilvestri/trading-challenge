import SockJS from "sockjs-client";
import { over } from "stompjs";

/**
 * Connect to web sockets.
 * @param url: string
 * @returns Object
 */
let stompClient = null;
export const Stream = (url) => {
  // wss://stream.binance.com:9443/ws/btcusdt@aggTrade
  // ws://localhost:9090/ws
  // stream.binance.com/ws/btcusdt@aggTrade
  let ws = new SockJS(url);
  stompClient = over(ws);
  stompClient.connect({}, onConnected, onError);
  setTimeout(() => {
    onConnected();
  }, 500);
  let cb = () => {};
  const onConnected = () => {
    console.log("SE CONECTO");
    let data = null;
    stompClient.subscribe("/topic/messages", (response) => {
      try {
        data = JSON.parse(response.body);
        cb(data);
      } catch (e) {
        console.log(e);
      }
    });
  };
  const onError = (err) => {
    console.log(err);
  };

  return {
    set ontrades(val) {
      cb = val;
    },
    off() {
      stompClient.disconnect();
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
    for (let i = 0; i < x.length; i++) {
      x[i] = parseFloat(x[i]);
    }
    return x.slice(0, 6);
  });
};
