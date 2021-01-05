import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
     // Open CSV File from Open Face
    public File file = new File("C:\\Users\\beren\\Documents\\5_Semester\\PROJEKT\\webcam_2020-12-08-20-24.csv");

    private final Socket socket;

    public ServerThread(final Socket socket) { // inizializiert socket
        this.socket = socket;
    }

    static String clientConnected;
    static String myPath; // path to string to send it to client 
    static String trackedData;
    public BufferedWriter writer;

    public void run() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.println("the current path is: " + file.toPath());

            // Wait for incoming messages
            while (reader.ready()) {              
                clientConnected = reader.readLine();
                System.out.println("connected to Addresse: " + clientConnected);  
                getTrackedData(); // Call Method to read CSV File
                Thread.sleep(1000);
            }
        } catch (final IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            e.printStackTrace();
        }        
    }

    // Method to get tracking-data 
    public void getTrackedData() {
        int i = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String linea;
            System.out.println("Contents of File: ");
            while ((linea = br.readLine()) != null) {
                writer.write(linea);
                writer.newLine();
                try{ Thread.sleep(1000); } Catch(InterruptedException e ) { System.out.println("Interrupted"); }
                writer.flush();
                System.out.println("Linea: " + i + "\n" + linea + "\n");
                i++;
            }
            br.close();          
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
