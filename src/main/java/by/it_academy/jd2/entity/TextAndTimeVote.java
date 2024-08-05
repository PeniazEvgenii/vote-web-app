package by.it_academy.jd2.entity;

import java.time.LocalDateTime;

public class TextAndTimeVote {
    private final String textInfo;
    private final LocalDateTime time;

    public TextAndTimeVote(String textInfo, LocalDateTime time) {
        this.textInfo = textInfo;
        this.time = time;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
