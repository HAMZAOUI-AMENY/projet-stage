import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BarcodeScannerApp {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Barcode Scanner App");
        JTextField barcodeField = new JTextField(30);
        JButton sendButton = new JButton("Send to Server");

        // ActionListener pour envoyer le code-barres au serveur
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barcode = barcodeField.getText();
                if (!barcode.isEmpty()) {
                    sendBarcodeToServer(barcode);
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

    // Méthode pour envoyer le code-barres au serveur
    private static void sendBarcodeToServer(String barcode) {
        try {
            URL url = new URL("https://your_web_application_url/endpoint");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            String postData = "barcode=" + barcode;
            OutputStream os = conn.getOutputStream();
            os.write(postData.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

