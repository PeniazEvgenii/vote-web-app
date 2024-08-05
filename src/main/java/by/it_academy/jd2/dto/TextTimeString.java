package by.it_academy.jd2.dto;

public class TextTimeString {
    private final String textInfo;
    private final String time;

    public TextTimeString(String textInfo, String time) {
        this.textInfo = textInfo;
        this.time = time;
    }

    public String getTextInfo() {
        return textInfo;
    }

    public String getTime() {
        return time;
    }
}
