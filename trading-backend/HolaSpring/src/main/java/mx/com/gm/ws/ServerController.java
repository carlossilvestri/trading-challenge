package mx.com.gm.ws;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = { "*" })
@Controller
public class ServerController {

    @MessageMapping("/btcusdt@aggTrade")
    @SendTo("/topic/messages")
    public String processMessage(@Payload String message) throws Exception{
        // System.out.println(message);

        return message;
    }
}
