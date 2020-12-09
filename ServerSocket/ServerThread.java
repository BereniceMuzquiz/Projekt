import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(final Socket socket) { // inizializiert socket
        this.socket = socket;
    }

    static String clientConnected;
    //static File file = new File("C:\\Users\\beren\\Documents\\5_Semester\\PROJEKT\\OpenFace_2.2.0_win_x64\\OpenFace_2.2.0_win_x64\\processed\\webcam_2020-11-13-12-58.csv");
    static File file = new File("C:\\Desktop\\webcam_2020-12-08-20-24.csv");
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

                getTrackedData();
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
        int i = 1;
        try {
            File file = new File("C:\\Users\\beren\\Pictures\\openface-AU\\webcam_2020-11-22-00-05.csv");

            FileReader fr = new FileReader(file); // reads the file
            BufferedReader br = new BufferedReader(fr); // creates a buffering character input stream

            System.out.println("Contents of File: ");
            while (br.readLine() != null) {
                String linea = br.readLine();
                writer.write(linea);
                writer.newLine();
                writer.flush();
                //if (i < 6){
                    System.out.println("Linea: " + i + "\n" + linea + "\n");
                //}
                i++;
            }
            fr.close(); // closes the stream and release the resources
                        
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}
