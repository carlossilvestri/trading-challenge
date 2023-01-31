package mx.com.gm.kline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
// import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/api/kline")
@Controller
public class KlineController {
    @Autowired
    KlineService klineService;

    @GetMapping()
    public List<Kline> get(){
        return klineService.get();
    }
}
