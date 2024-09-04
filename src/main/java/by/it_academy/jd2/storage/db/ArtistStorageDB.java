package by.it_academy.jd2.storage.db;

import by.it_academy.jd2.entity.Artist;
import by.it_academy.jd2.entity.Genre;
import by.it_academy.jd2.storage.api.IStorageDB;
import by.it_academy.jd2.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ArtistStorageDB implements IStorageDB<Artist> {
    private static final IStorageDB<Artist> INSTANCE = new ArtistStorageDB();
    private static final String SQL_INSERT_ARTIST = "INSERT INTO app.artist (name) VALUES (?) returning id";
    private static final String SQL_GET_ARTIST = "SELECT name FROM app.artist WHERE id = ?";
    private static final String SQL_GET_ALL_ARTIST = "SELECT id, name FROM app.artist";

    private ArtistStorageDB() {
    }

    @Override
    public Long create(Artist artist) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ARTIST)) {
            preparedStatement.setString(1, artist.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            Long id = null;
            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Artist get(Long id) {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ARTIST)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Artist artist = null;
            if (resultSet.next()) {
                artist = new Artist(
                        id,
                        resultSet.getString("name"));
            }
            return artist;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Long, Artist> get() {
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_ARTIST);
        ) {
            Map<Long, Artist> result = new HashMap<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                result.put(id, new Artist(id, name));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static IStorageDB<Artist> getInstance() {
        return INSTANCE;
    }
}
