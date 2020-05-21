package analytics.config;

import java.time.Duration;

import static java.time.Duration.between;
import static java.time.LocalTime.parse;

public class Decorator {

    protected Duration getDuration(String item) {
        long count = Long.parseLong(item.split(" ")[0]);
        Duration duration = between(parse("00:00:00"), parse(item.split(" ")[1]));
        duration = duration.plusHours(24 * count);
        return duration;
    }
}
