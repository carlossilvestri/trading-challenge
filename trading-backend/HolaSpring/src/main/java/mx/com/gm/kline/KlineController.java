package mx.com.gm.kline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/api/kline")
@Controller
public class KlineController {
    @Autowired
    KlineService klineService;

    @GetMapping()
    public String get(
            @RequestParam(required = true) String symbol,
            @RequestParam(required = true) Long startTime,
            @RequestParam(required = true) Long endTime
    ) throws IOException {
        return klineService.get(symbol, startTime, endTime);
    }
}
