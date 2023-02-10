package mx.com.gm;

import mx.com.gm.ws.AggTradeStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
@EnableScheduling
public class HolaSpringApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
		SpringApplication.run(HolaSpringApplication.class, args);
		new AggTradeStream("btcusdt").run();
	}

}
