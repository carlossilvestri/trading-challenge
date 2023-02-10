package mx.com.gm.kline;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


@Service
public class KlineService {


    ObjectMapper mapper = new ObjectMapper();
    URL url = null;
    BufferedReader bufferReader = null;
    URLConnection urlConnection = null;

    public String get(String symbol, Long startTime, Long endTime) throws IOException {
        url = new URL("https://api.binance.com/api/v3/klines?symbol="+ symbol + "&interval=1m&startTime=" + startTime+ "&endTime=" + endTime);
        // System.out.println(url);
        urlConnection = url.openConnection();
        // System.out.println("Connected to Binance endpoint");

        bufferReader = new BufferedReader(
                new InputStreamReader(
                        urlConnection.getInputStream()
                )
        );

        String inputLine, dataString = null;
        while((inputLine = bufferReader.readLine()) != null){
            dataString = inputLine;
        }
        bufferReader.close();

        return dataString;
    }
}
