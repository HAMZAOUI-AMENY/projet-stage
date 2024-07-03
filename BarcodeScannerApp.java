import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarcodeScannerApp {

    private static JTextField barcodeField; // Déclarer barcodeField en tant que champ statique pour y accéder dans la méthode statique

    public static void main(String[] args) {
        JFrame frame = new JFrame("Barcode Scanner App");
        barcodeField = new JTextField(30);
        JButton sendButton = new JButton("Send to Server");

        // ActionListener pour envoyer le code-barres au serveur
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barcode = getScannedBarcode(); // Utilisation de la méthode pour récupérer le code-barres
                if (!barcode.isEmpty()) {
                    String userId = "1234"; // Remplacez par la logique pour récupérer l'ID de l'utilisateur
                    String typeOperation = "entrée"; // Remplacez par la logique pour déterminer le type d'opération
                    String location = "Magasin"; // Remplacez par la logique pour déterminer la localisation

                    // Création d'une nouvelle entrée de journal avec les données scannées
                    LogEntry entry = new LogEntry(userId, typeOperation, barcode, location);

                    // Envoyer l'entrée de journal au serveur (exemple avec une méthode fictive)
                    sendLogEntryToServer(entry);
                } else {
                    JOptionPane.showMessageDialog(frame, "Please scan a barcode first.");
                }
            }
        });

        frame.setLayout(new java.awt.FlowLayout());
        frame.add(barcodeField);
        frame.add(sendButton);

        frame.setSize(400, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Méthode statique pour récupérer le code-barres lu
    public static String getScannedBarcode() {
        return barcodeField.getText();
    }

    // Méthode fictive pour envoyer l'entrée de journal au serveur
    private static void sendLogEntryToServer(LogEntry entry) {
        // Placez ici la logique pour envoyer l'entrée de journal au serveur
        // Par exemple, vous pouvez utiliser JDBC pour insérer les données dans une base de données
        // Ou vous pouvez envoyer les données à un service web, etc.
        System.out.println("Log Entry Sent to Server: " + entry.getUserId() + ", " + entry.getDateOperation() + ", " +
                entry.getTypeOperation() + ", " + entry.getCodeBarres() + ", " + entry.getLocation());
    }
}
