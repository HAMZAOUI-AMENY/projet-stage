import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class MainApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Barcode Scanner App");
        JTextField userIdField = new JTextField(10);
        JTextField operationTypeField = new JTextField(10);
        JTextField locationField = new JTextField(10);
        JButton scanButton = new JButton("Scan Barcode");

        // ActionListener pour le bouton de scan
        scanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Appel de la méthode pour scanner le code-barres
                String barcode = BarcodeScannerApp.getScannedBarcode();

                // Si le code-barres n'est pas vide, créer une nouvelle entrée de journal
                if (!barcode.isEmpty()) {
                    String userId = userIdField.getText();
                    String operationType = operationTypeField.getText();
                    String location = locationField.getText();

                    // Créer une nouvelle entrée de journal avec les données récupérées
                    LogEntry entry = new LogEntry(userId, operationType, barcode, location);

                    // Ajouter l'entrée de journal à la base de données
                    MySQLConnection.insertData(entry.getUserId(), LocalDateTime.now().toString(), entry.getTypeOperation(), entry.getCodeBarres());


                    JOptionPane.showMessageDialog(frame, "Log entry added successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please scan a barcode first.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);
        panel.add(new JLabel("Operation Type:"));
        panel.add(operationTypeField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(scanButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
