package analytics.config;

import java.time.Duration;

import static java.time.Duration.between;
import static java.time.LocalTime.parse;

public class Decorator {

    protected Duration convert(String item) {
        return between(parse("00:00:00"), parse(item.split(" ")[1]));
    }
}

