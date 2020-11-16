import java.io.File;

public class FindOpenFaceDirectory {
    public static String fileString;
    public static String trackingfile;
    public static File trackedFile;
    public static int ready;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ready = 0;
        fileString = "C:\\";
        
        find_Directory(fileString);
        System.out.println(ready); 
        System.out.println("trackingFile  dir: " + trackingfile);
        System.out.println("the tracked file is: " + trackedFile); 

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");      
    }

    private static void find_Directory(String fileString) {

        ready = 0;
        String searchedFile = "OpenFace_2.2.0_win_x64";
        String currentFile = fileString;
        File file = new File(currentFile);
        String[] names = file.list();
              
        try {
                for(String name : names){
                    if (ready != 1) {
                        if (new File(currentFile + name).isDirectory()){
            
                            System.out.println(name);
            
                            file = new File(currentFile + name);
                            fileString = (file.toString() + "\\");
                        
        
                            System.out.println("fr: " + fileString);
                            boolean flag = name.equals(searchedFile);
                            System.out.println(flag);
                            
                            if (name.equals(searchedFile)){
                                ready = 1;
                                trackingfile = fileString;
                                trackedFile = file;  
                            }
                            else{
                                find_SubDirectory(fileString);
                            }
                        }
                    }
                    
                } 
                    
        } catch (Exception e) {
            System.out.println("directory is empty  ");
        }
    }




    private static void find_SubDirectory(String fileString) {
        ready = 0;
        String searchedFile = "OpenFace_2.2.0_win_x64";
        String currentFile = fileString;
        File file = new File(fileString);
        String[] names = file.list();
                       
        try {
            for(String name : names){
                if (ready != 1) { 
                    if (new File(currentFile + name).isDirectory()){
    
                    System.out.println(name);
    
                    file = new File(currentFile + name);
                    fileString = (file.toString() + "\\");

                    System.out.println("fr: " + fileString);
                    boolean flag = name.equals(searchedFile);
                    System.out.println(flag);
                        if (name.equals(searchedFile)){
                            ready = 1;
                            trackingfile = fileString;
                            trackedFile = file;  
                        }else{
                            find_Directory(fileString);
                        }
                    }
                }
            }        
                
        } catch (Exception e) {
            System.out.println("directory is empty  ");
        }
    }    
}
