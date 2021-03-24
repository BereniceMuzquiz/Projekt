import java.io.*;
import java.net.*;
import java.nio.file.Path;

public class ServerThread extends Thread {
    private final Socket socket;

    public ServerThread(final Socket socket) { // inizializiert socket
        this.socket = socket;
    }

    static String command;
    static File file = new File("C:\\Users\\beren\\Documents\\5_Semester");
    static File file2;
    static int cd = 0; // to geth subpath
    static int cdIndex = 0;
    static String myPath; // path to string to send it to client
    static Path path = file.toPath();
    static int dirIndex = 0; // to get the current directory

    public void run() {
        try {
            // get message from client
            final InputStream input = socket.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // send data (directory list) to client
            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("the current path is: " + file.toPath());

            // Wait for incoming messages
            while ((command = reader.readLine()) != null) {
                command = reader.readLine(); // read command from client
                System.out.println("received command: " + command);
                switch (command) {
                    case "cd":
                        get_Subpath();
                        // sleep(20);
                        out.writeUTF(myPath); // sending Path to the client
                        break;
                    case "ls":
                        get_File(); // get de current directory
                        String[] directory;
                        directory = file2.list();
                        for (String directori : directory) {
                            try {
                                out.writeUTF("<" + directori + ">\n" ); // sending list to the client
                            
                            } catch (IOException e) {
                                System.out.println("error by creating directory ");
                                e.printStackTrace();
                            }
                        }
                        ;
                        break;
                    default:
                        System.out.println("invalid command");
                }

                out.flush();
                Thread.sleep(100);

            }
        } catch (final IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
            e.printStackTrace();
        }
    }

    // Method to get the Subpath
    private synchronized static void get_Subpath() {
        // Users/beren/Documents/5_Semester
        cd = path.getNameCount() - 1;
        if (cd < 1) {
            cd = 1;
        }
        path = path.subpath(0, cd);
        myPath = path.toString();
        dirIndex++;
    }

    // method to get the corrent directory
    private synchronized static void get_File() {
        if (!file.exists()) {
            file = file.getParentFile();
        }
        if (dirIndex == 0) {
            file2 = file;
        }
        if (dirIndex == 1) {
            get_file3();
        }
        if (dirIndex == 2) {
            get_File4();
        }

    }

       private static void get_file3() {
        File file3 = file2.getParentFile();
        file2 = file3;
    }

    private static void get_File4() {
        File file4 = file2.getParentFile();
        file2 = file4;
    }
}
