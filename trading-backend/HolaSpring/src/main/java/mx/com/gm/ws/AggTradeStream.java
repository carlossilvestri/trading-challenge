package mx.com.gm.ws;

import com.binance.connector.client.impl.WebsocketClientImpl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class AggTradeStream {
    WebsocketClientImpl wsClient = new WebsocketClientImpl();
    Client client =  new Client();
    int streamId1;
    String symbol = "btcusdt";

    public AggTradeStream(String symbol) {
        this.symbol = symbol;
    }


    public void run() throws ExecutionException, InterruptedException, TimeoutException {
        client.run();
        streamId1 = wsClient.aggTradeStream(symbol, ((event) -> {
            client.send(event);
        }));
    }
    public void close(){
        wsClient.closeConnection(streamId1);
    }
}
