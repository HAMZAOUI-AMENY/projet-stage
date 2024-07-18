import java.util.Scanner;

public class AdminPage {

    public void display() {
        System.out.println("Welcome to the Admin Page!");
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Select an option:");
            System.out.println("1. View all operations");
            System.out.println("2. Search by code_barres");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    viewAllOperations();
                    break;
                case 2:
                    System.out.println("Enter code_barres to search:");
                    String codeBarres = scanner.nextLine();
                    searchByCodeBarres(codeBarres);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }

        scanner.close();
    }

    private void viewAllOperations() {
        System.out.println("All Operations:");
        MySQLConnection.retrieveData();
    }

    private void searchByCodeBarres(String codeBarres) {
        System.out.println("Searching by code_barres: " + codeBarres);
        MySQLConnection.retrieveDataByCodeBarres(codeBarres);
    }
    
}
