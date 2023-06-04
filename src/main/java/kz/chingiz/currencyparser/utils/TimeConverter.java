package kz.chingiz.currencyparser.utils;


import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TimeConverter {

    private static final String REG_EXP = "(?<hours>\\d{2}):(?<minutes>\\d{2}):(?<seconds>\\d{2})";
    private static final Pattern PATTERN = Pattern.compile(REG_EXP);

    private TimeConverter() {
    }

    public static Optional<LocalTime> convertStringToLocalTime(String time) {
        Matcher matcher = PATTERN.matcher(time);
        int hours, minutes, seconds;
        if (matcher.find()) {
            try {
                hours = Integer.parseInt(matcher.group("hours"));
                minutes = Integer.parseInt(matcher.group("minutes"));
                seconds = Integer.parseInt(matcher.group("seconds"));
            } catch (NumberFormatException exception) {
                return Optional.empty();
            }
            return Optional.of(LocalTime.of(hours, minutes, seconds));
        }
        return Optional.empty();
    }
}
