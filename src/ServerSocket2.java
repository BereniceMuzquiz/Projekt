
import java.io.*;
import java.net.*;

public class ServerSocket2 {
    static String endData;
   // static File file = new File(
   //         "C:\\Users\\beren\\Documents\\5_Semester\\PROJEKT\\OpenFace_2.2.0_win_x64\\OpenFace_2.2.0_win_x64\\processed\\webcam_2020-11-13-12-58.csv");
      static File file = new File(
                "C:\\Users\\beren\\Pictures\\openface-AU\\webcam_2020-11-22-00-05.csv"); // C:\Users\beren\Pictures\openface-AU
    public static void main(String[] args) throws Exception {

        int port = 1112;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server ist listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected " + "\nClient Address: " + socket.getInetAddress());

                // Streamas inizializieren
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                // reading CSV-Data
                try {
                    int countLines = 1;
                    FileReader fileReader = new FileReader(file); // reads the file
                    BufferedReader csvReader = new BufferedReader(fileReader);

                    String header= csvReader.readLine();
                        writer.write(header);
                        System.out.println(" Contents of header \n " + header);

                    while (csvReader.readLine() != null) {
                        String linea = csvReader.readLine();
                        if (linea != null) {
                            writer.write(linea);
                            writer.newLine();
                            writer.flush();
                        } else{
                          System.out.println(" data ist empty");
                       }                        
                        if (countLines<11) {
                            System.out.println(" Contents of line " + countLines + "\n" + linea);   
                        }                                      
                        countLines++;
                    }
                    csvReader.close();
                    System.out.println("\nEnd of data Transmision");
                    System.out.println("Data sended to address: " + reader.readLine()); //read Address from Client
                    System.out.println("Sended lines = " + (countLines -1));

                } catch (Exception e) {
                    e.printStackTrace();
                }
               //socket.close();  // !!!!!!!!!!!!!!!
            }

        } catch (Exception ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }                          
    }    
}

