import java.io.*;
import java.net.*;

/* this class creat instanz of socket server and Threads for connection 
with multiples client socket is listening in port 1111 */
public class Server extends Thread {  

    public static void main(String[] args) {

        int port = 1111;
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {  //creat a server socket in port 1111
 
            System.out.println("Server is listening on port " + port);
 
            while (true) { // wait continously for clients
                Socket socket = serverSocket.accept(); // listen for incomming client request 
                System.out.println("New client connected  " + Thread.currentThread().getName());
 
                new ServerThread(socket).start(); // creat instanz of Thread (for multi clients)
                                                    //and start it
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
