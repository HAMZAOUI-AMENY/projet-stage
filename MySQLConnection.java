import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnection {
    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/evenements";
    private static final String USER = "root";
    private static final String PASSWORD = "Mysql24";

    // JDBC variables for opening and managing connection
    private static Connection connection;

    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Example: Insert data
            insertData("1234", "2024-06-18 14:52", "entrée", "ABC123", "Magasin");
            insertData("3427", "2023-06-18", "SORTIE", "23456463543", "DIV INFO");

            // Example: Retrieve data
            retrieveData();

            // Example: Insert user
            int username = 124232;
            String nom = "ameny";
            String profil = "admin";
            String plainPassword = "EFS342";
            String hashedPassword = PasswordUtil.hashPassword(plainPassword);
            insertUser(username, nom, profil, hashedPassword);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void insertData(String userId, String date, String operationType, String barcode, String location) {
        String insertQuery = "INSERT INTO operations (User_Id, DateOperation, TypeOperation, code_barres, Location) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, date);
            preparedStatement.setString(3, operationType);
            preparedStatement.setString(4, barcode);
            preparedStatement.setString(5, location);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertUser(int username, String nom, String profil, String hashedPassword) {
        String insertQuery = "INSERT INTO User (matricule, nom, profil, motdepasse) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, username);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3, profil);
            preparedStatement.setString(4, hashedPassword);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void retrieveData() {
        String selectQuery = "SELECT * FROM operations";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userId = resultSet.getString("User_Id");
                String date = resultSet.getString("DateOperation");
                String operationType = resultSet.getString("TypeOperation");
                String barcode = resultSet.getString("code_barres");
                String location = resultSet.getString("Location");

                System.out.println(userId + ", " + date + ", " + operationType + ", " + barcode + ", " + location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
