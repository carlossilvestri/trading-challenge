package mx.com.gm.kline;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Kline {
    Long timestamp;
    String open;
    String high;
    String low;
    String close;
    String volume;
}

