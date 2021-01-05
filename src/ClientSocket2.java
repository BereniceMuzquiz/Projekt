import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ClientSocket2 {
    private static final String SEPARATOR = ","; // to creat the Output csv line
    public static String csvInput; // csv line received from client
    public static String csvOutput; // csv line send to nex component(StringSpliter)

    public static void main(String[] args) throws UnknownHostException, IOException {
        String hostname = "Localhost";
        int port = 1111;        
   
        System.out.println("connecting to port: " + port);

        //  try /catch to close socket safely
        // resources within the parentheses will be automatically closed.
        try (Socket clientSocket = new Socket(hostname, port);
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
                    writer.println(clientSocket.getInetAddress());
                    writer.flush();
                    System.out.println(clientSocket.getInetAddress());                  
                    int linesCounter = 1;
                    do {
                            String csvInput = reader.readLine();
                            //System.out.println("readed line : " + linesCounter + "\n" + reader.readLine() + "\n");
                            linesCounter++;
                            csvOutput = getShortCsv(csvInput); //creat a shorter csv line
                            System.out.println("readed line : " + linesCounter + "\n" +"csv line Output =" + csvOutput);                           
                    } while (reader.ready());
                    System.out.println("\nEnd of data Transmision");
                    System.out.println("Received lines from server = " + linesCounter);				   
        }	         
    }
        // this method creats a new csv line with 16 AU values needed for 
        //the modell, delete the unusual AU values
    public static String getShortCsv(String csvInput) {
        String csvInputNoSpace= csvInput.replace(" ",""); //delate space from the String
        String[] AU = csvInputNoSpace.split(",");  //Split the String
        List<String> list = new ArrayList<String>(); // creat a list with the separate AU-values
        for (int i = 22; i < AU.length; i++) {
            list.add(AU[i]);     
        }
        list.remove(10);
        list.remove(11);
        System.out.println("elements on string " + list.size());    //BORRAR!
        StringBuilder csvBuilder = new StringBuilder(); //create a new csv line
        for (Object object : list) {
           csvBuilder.append(object);
           csvBuilder.append(SEPARATOR);
        }        
        String csv = csvBuilder.toString();
        csvOutput = csv.substring(0, csv.length() - SEPARATOR.length());//remove last coma
        return csvOutput;
    }

}
