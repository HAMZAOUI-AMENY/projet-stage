import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BarcodeSender {
    public static void sendBarcodeToServer(String barcode) {
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


