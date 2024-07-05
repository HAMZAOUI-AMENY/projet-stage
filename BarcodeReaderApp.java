import java.util.Scanner;

public class BarcodeReaderApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please scan a barcode:");

        while (scanner.hasNextLine()) {
            String barcode = scanner.nextLine();
            if (!barcode.isEmpty()) {
                System.out.println("Barcode scanned: " + barcode);
                // Vous pouvez maintenant utiliser la variable `barcode` pour toute logique nécessaire,
                // comme l'enregistrement dans une base de données ou un traitement supplémentaire.
            }
        }

        scanner.close();
    }
}
