package by.it_academy.jd2.dto;


import java.time.LocalDateTime;
import java.util.Arrays;

public class InfoFromUserDTO {
    private String[] janres;
    private String singer;
    private String info;
    private LocalDateTime dateTime;


    private InfoFromUserDTO(String[] janres, String singer, LocalDateTime dateTime, String info) {
        this.janres = janres;
        this.singer = singer;
        this.dateTime = dateTime;
        this.info = info;
    }

    public static BuilderInfoFromUser builder() {
        return new BuilderInfoFromUser();
    }

    public String[] getJanres() {
        return janres;
    }

    public String getSinger() {
        return singer;
    }

    public String getInfo() {
        return info;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public static class BuilderInfoFromUser {
        private String[] janres;
        private String singer;
        private String info;
        private LocalDateTime dateTime;

        public BuilderInfoFromUser setJanres(String[] janres) {
            this.janres = janres;
            return this;
        }

        public BuilderInfoFromUser setSinger(String singer) {
            this.singer = singer;
            return this;
        }

        public BuilderInfoFromUser setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public BuilderInfoFromUser setInfo(String info) {
            this.info = info;
            return this;
        }

        public InfoFromUserDTO build() {
            return new InfoFromUserDTO(janres, singer, dateTime, info);
        }
    }

    @Override
    public String toString() {
        return "InfoFromUser{" +
                "janres=" + Arrays.toString(janres) +
                ", singer=" + singer +
                ", date='" + dateTime + '\'' +
                '}';
    }
}
