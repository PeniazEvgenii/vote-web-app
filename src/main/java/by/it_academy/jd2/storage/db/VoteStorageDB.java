package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.entity.VoteEntity;
import by.it_academy.jd2.storage.api.IStorageDB;
import by.it_academy.jd2.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteStorageDB implements IStorageDB<VoteEntity> {
    private static final IStorageDB<VoteEntity> INSTANCE = new VoteStorageDB();

    private static final String INSERT_VOICE_SQL = "INSERT INTO app.vote (artist_id, about, create_at) VALUES (?, ?, ?) returning id";
    private static final String INSERT_VOICE_GENRE_SQL = "INSERT INTO app.cross_vote_genre (vote_id, genre_id) VALUES (?, ?)";
    private static final String SELECT_VOICE_SQL = "SELECT vote.id, about, artist_id, create_at FROM app.vote";
    private static final String SELECT_VOICE_BY_ID_SQL = "SELECT id, about, artist_id, create_at FROM app.vote WHERE id = ?";
    private static final String SELECT_GENRE_BY_VOICE_ID = "SELECT genre_id FROM app.cross_vote_genre WHERE vote_id = ?";


    private VoteStorageDB() {
    }


    @Override
    public Long create(VoteEntity voteEntity) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VOICE_SQL);
             PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_VOICE_GENRE_SQL)) {

            preparedStatement.setLong(1, voteEntity.getArtistId());
            preparedStatement.setString(2, voteEntity.getInfo());
            preparedStatement.setObject(3, voteEntity.getCreate_at());

            ResultSet resultSet = preparedStatement.executeQuery();
            Long vote_id = null;
            while (resultSet.next()) {
                vote_id = resultSet.getLong("id");
            }

            List<Long> genresId = voteEntity.getGenresId();
            for (Long genreId : genresId) {
                preparedStatement2.setLong(1, vote_id);
                preparedStatement2.setLong(2, genreId);
                preparedStatement2.executeUpdate();
            }

            return vote_id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public VoteEntity get(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOICE_BY_ID_SQL)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            VoteEntity voteEntity = null;
            if (resultSet.next()) {
               voteEntity = buildVoteEntity(resultSet);
            }

            List<Long> genresByVoice = getGenresByVoice(voteEntity.getId(), connection);;
            voteEntity.setGenresId(genresByVoice);

            return voteEntity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> getGenresByVoice(Long voice_id) {
        try (Connection connection = ConnectionManager.open()) {
            return getGenresByVoice(voice_id, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Long> getGenresByVoice(Long voice_id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GENRE_BY_VOICE_ID)) {
            preparedStatement.setLong(1, voice_id);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            return getListGenres(resultSet2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Map<Long, VoteEntity> get() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VOICE_SQL)
             ) {

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<Long, VoteEntity> mapVotes = new HashMap<>();
            while (resultSet.next()) {
                VoteEntity voteEntity = buildVoteEntity(resultSet);
                Long id = voteEntity.getId();
                List<Long> genresByVoice = getGenresByVoice(id, connection);
                voteEntity.setGenresId(genresByVoice);

                mapVotes.put(id, voteEntity);
            }

            return mapVotes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Long> getListGenres(ResultSet resultSet2) throws SQLException {
        List<Long> genreId = new ArrayList<>();
        while(resultSet2.next()) {
            genreId.add(resultSet2.getLong("genre_id"));
        }
        return genreId;
    }

    private VoteEntity buildVoteEntity(ResultSet resultSet) throws SQLException {
        return VoteEntity.builder()
                .setId(resultSet.getLong("id"))
                .setArtist(resultSet.getLong("artist_id"))
                .setInfo(resultSet.getString("about"))
                .setCreate_at(resultSet.getObject("create_at", OffsetDateTime.class))
                .build();
    }


    public static IStorageDB<VoteEntity> getInstance() {
        return INSTANCE;
    }
}
