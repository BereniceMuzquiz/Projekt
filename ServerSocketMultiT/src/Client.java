import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        String hostname = "Localhost";
        int port = 1111;
        System.out.println("connecting to port: " + port);

        //  try /catch to close socket safely
        // resources within the parentheses will be automatically closed.
        try (Socket socket = new Socket(hostname, port);
            // send data to server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
         
            // read data from the server (string and list)
            DataInputStream in = new DataInputStream(socket.getInputStream()); 

            BufferedReader readCons = new BufferedReader(new InputStreamReader(System.in));) {

            System.out.println("Supported commands: 'ls', 'cd' and 'exit' ");
            System.out.println("Enter a command:");

            String command;

            // wait for entry (command)
            while ((command = readCons.readLine()) != null) {
                System.out.println("sending command '"  + command + "' to server ...");
                // sending command to server
                writer.println(command);
                writer.flush();
                Thread.sleep(1000);

                // check if there are bytes to be readed
                //and read message from server
                while (in.available() > 0) {                    
                    System.out.println(in.readUTF());
                    
                }
            }

        } catch (UnknownHostException ex) {

            System.out.println("Server not found: " + ex.getMessage());

        } catch (IOException ex) {

            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
