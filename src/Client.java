import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws InterruptedException, IOException {

        String hostname = "Localhost";
        int port = 1111;
        System.out.println("connecting to port: " + port);
        String readedLines[] = new String[10000];

        //  try /catch to close socket safely
        // resources within the parentheses will be automatically closed.
        try (Socket clientSocket = new Socket(hostname, port);
				PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    writer.println(clientSocket.getInetAddress());
                    System.out.println(clientSocket.getInetAddress());
                    Thread.sleep(2000);
                    
                    int i = 1;
				    while (reader.ready()) {
                        if (i < 11) {
                            System.out.println("readed line : " + i + "\n" + reader.readLine() + "\n");
                        }                    
                    readedLines[i] = reader.readLine();                    
                    i++;
                }
                System.out.println("line 10 is: " + readedLines[10] );
        }	        
    }
}

