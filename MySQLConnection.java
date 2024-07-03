import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/evenements";
    private static final String USER = "root";
    private static final String PASSWORD = "Mysql24";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

    public static void main(String[] args) {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = getConnection();
            UserService userService = new UserService();
            
            if (userService.authenticateUser("omarr", "EFS342")) {
                String username = "omarr";  // Doit être récupéré dynamiquement après l'authentification
                UserSession session = new UserSession(username);
                System.out.println("User " + session.getUsername() + " is logged in.");
                System.out.println("User " + UserSession.getInstance().getUsername() + " is logged in.");

                // Example: Insert data
                insertData("2024-04-18 14:52", "entrée", "ABC123", "Magasin");
                insertData("2023-05-18", "SORTIE", "23456463543", "DIV INFO");

                // Example: Retrieve data
                retrieveData();

                // Example: Insert user
                int matricule = 124232;
                String nom = "omarr";
                String profil = "admin";
                String plainPassword = "EFS342";
                String hashedPassword = PasswordUtil.hashPassword(plainPassword);
                insertUser(matricule, nom, profil, hashedPassword);

                // Log out
                UserSession.getInstance().clearSession();
                System.out.println("User session cleared.");
            } else {
                System.out.println("Authentication failed.");
            }
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

    public static void insertData(String date, String operationType, String barcode, String location) {
        String insertQuery = "INSERT INTO operations (User_Id, DateOperation, TypeOperation, code_barres, Location) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            String userId = UserSession.getInstance().getUsername();
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
