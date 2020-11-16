import java.io.IOException;

public class RunOpenFace {
    public static void main(String[] args) {

       
        try {
            Runtime runTime = Runtime.getRuntime();
            String ruta = "C:\\Users\\beren\\Documents\\5_Semester\\PROJEKT\\OpenFace_2.2.0_win_x64\\OpenFace_2.2.0_win_x64\\OpenFaceOffline.exe";
            runTime.exec(ruta);
        } catch (IOException e) {
            System.out.println("programa no arranca + ");
            e.printStackTrace();
        }

    }
}
