package mx.com.gm.kline;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class KlineService {

    List<Kline> klines;
    ObjectMapper mapper = new ObjectMapper();
    File file = new File("src/main/java/mx/com/gm/kline/klines-data.json");

    public List<Kline> get(){
        try{
            klines = Arrays.asList(mapper.readValue(file, Kline[].class));
        }catch(JsonParseException e){
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return klines;
    }
}
