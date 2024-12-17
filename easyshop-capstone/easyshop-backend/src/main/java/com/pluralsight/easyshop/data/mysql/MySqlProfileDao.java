package com.pluralsight.easyshop.data.mysql;

import JavaHelpers.ColorCodes;
import org.springframework.stereotype.Component;
import com.pluralsight.easyshop.models.Profile;
import com.pluralsight.easyshop.data.ProfileDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao {
    public MySqlProfileDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<Profile> profiles = new ArrayList<>();

        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM profiles
                    """);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Profile profile = mapRow(rs);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return profiles;
    }

    @Override
    public Profile getByUserId(int id) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    SELECT * FROM profiles
                    WHERE user_id = ?
                    """);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Profile create(Profile profile) {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("""
                    INSERT INTO profiles (user_id, first_name, last_name, phone, email, address, city, state, zip) VALUES
                    (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, profile.getUserId());
            ps.setString(2, profile.getFirstName());
            ps.setString(3, profile.getLastName());
            ps.setString(4, profile.getPhone());
            ps.setString(5, profile.getEmail());
            ps.setString(6, profile.getAddress());
            ps.setString(7, profile.getCity());
            ps.setString(8, profile.getState());
            ps.setString(9, profile.getZip());

            int rows = ps.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            // Retrieve the generated keys
            ResultSet genKeys = ps.getGeneratedKeys();

            if (genKeys.next()) {
                // Retrieve the auto-incremented ID
                int userId = genKeys.getInt(1);

                return new Profile(userId, profile.getFirstName(), profile.getLastName(), profile.getPhone(), profile.getEmail(), profile.getAddress(), profile.getCity(), profile.getState(), profile.getZip());
            }

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Profile was created!" + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void update(int id, Profile profile) {
        try (Connection conn = getConnection()) {
            PreparedStatement statement = conn.prepareStatement("""
                    UPDATE profiles
                    SET first_name = ?, last_name = ?, phone = ?, email = ?, address = ?, city = ?, state = ?, zip = ?
                    WHERE user_id = ?
                    """);
            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getEmail());
            statement.setString(5, profile.getAddress());
            statement.setString(6, profile.getCity());
            statement.setString(7, profile.getState());
            statement.setString(8, profile.getZip());
            statement.setInt(9, profile.getUserId());

            int rows = statement.executeUpdate();
            System.out.printf("Rows updated: %d\n", rows);

            //Confirmation message
            System.out.println(ColorCodes.SUCCESS + ColorCodes.ITALIC + "Profile was updated." + ColorCodes.RESET);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Profile mapRow(ResultSet row) throws SQLException {
        int userId = row.getInt("user_id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String phone = row.getString("phone");
        String email = row.getString("email");
        String address = row.getString("address");
        String city = row.getString("city");
        String state = row.getString("state");
        String zip = row.getString("zip");

        return new Profile(userId, firstName, lastName, phone, email, address, city, state, zip);
    }

}
