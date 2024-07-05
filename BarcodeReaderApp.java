import java.util.Scanner;

public class BarcodeReaderApp {

    public static void main(String[] args) {
        String barcode = readBarcode();
        
        // Utilisez le code-barres scanné pour toute logique nécessaire
        if (barcode != null && !barcode.isEmpty()) {
            System.out.println("Processing barcode: " + barcode);
            // Vous pouvez maintenant utiliser la variable `barcode` pour toute logique nécessaire,
            // comme l'enregistrement dans une base de données ou un traitement supplémentaire.
        } else {
            System.out.println("No barcode scanned.");
        }
    }

    // Méthode pour lire un code-barres depuis le scanner
    public static String readBarcode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please scan a barcode:");

        if (scanner.hasNextLine()) {
            String barcode = scanner.nextLine();
            if (!barcode.isEmpty()) {
                System.out.println("Barcode scanned: " + barcode);
                scanner.close(); // Ferme le scanner après utilisation
                return barcode;
            }
        }
        
        scanner.close(); // Ferme le scanner si aucune entrée n'est lue
        return null;
    }
}
