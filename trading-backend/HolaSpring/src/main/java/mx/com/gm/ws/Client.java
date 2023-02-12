package mx.com.gm.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Client {
    @Autowired
    private WebSocketStompClient stompClient;
    private ClientOneSessionHandler clientOneSessionHandler;

    String payload;
    StompSession session;
    StompSession stompSession;
    public void run() throws InterruptedException, ExecutionException, TimeoutException {
        /*
        ********************************
        CLIENT CONNECTION WITHOUT SOCKJS
        ********************************
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        ClientOneSessionHandler clientOneSessionHandler = new ClientOneSessionHandler();
        ListenableFuture<StompSession> sessionAsync =
                stompClient.connect("ws://localhost:9090/ws", clientOneSessionHandler);
        session = sessionAsync.get();
        session.subscribe("/topic/messages", clientOneSessionHandler);
         */
        // CLIENT CONNECTION WITH SOCKJS
        final String url = "http://localhost:9090/ws";
        clientOneSessionHandler = new ClientOneSessionHandler();
        stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        stompSession = stompClient.connect(url, clientOneSessionHandler).get(10, TimeUnit.SECONDS);

        stompSession.subscribe("/topic/messages", clientOneSessionHandler);


    }
    public void send(String payload){
        stompSession.send("/app/btcusdt@aggTrade", payload);
    }
    private static List<Transport> createTransportClient() {
        final List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }
}
