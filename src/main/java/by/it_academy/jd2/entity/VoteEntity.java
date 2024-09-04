package by.it_academy.jd2.entity;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

public class VoteEntity {
    private Long id;
    private List<Long> genresId;
    private final Long artistId;
    private final String info;
    private final OffsetDateTime create_at;

    private VoteEntity(List<Long> genresId, Long artistId, String info, OffsetDateTime create_at, Long id ) {
        this.genresId = genresId;
        this.artistId = artistId;
        this.info = info;
        this.create_at = create_at;
        this.id = id;
    }

    public static VoteEntityBuilder builder() {
        return new VoteEntityBuilder();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGenresId(List<Long> genresId) {
        this.genresId = genresId;
    }

    public List<Long> getGenresId() {
        return genresId;
    }

    public Long getArtistId() {
        return artistId;
    }

    public String getInfo() {
        return info;
    }

    public OffsetDateTime getCreate_at() {
        return create_at;
    }

    public Long getId() {
        return id;
    }

    public static class VoteEntityBuilder {
        private Long id;
        private List<Long> genresId;
        private Long artistId;
        private String info;
        private OffsetDateTime create_at;

        private VoteEntityBuilder() {
        }

        public VoteEntityBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public VoteEntityBuilder setGenres(List<Long> genresId) {
            this.genresId = genresId;
            return this;
        }

        public VoteEntityBuilder setArtist(Long artistId) {
            this.artistId = artistId;
            return this;
        }

        public VoteEntityBuilder setInfo(String info) {
            this.info = info;
            return this;
        }

        public VoteEntityBuilder setCreate_at(OffsetDateTime create_at) {
            this.create_at = create_at;
            return this;
        }

        public VoteEntity build() {
            return new VoteEntity(genresId, artistId, info, create_at, id);
        }
    }
}
