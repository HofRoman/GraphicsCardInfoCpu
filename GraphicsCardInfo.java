import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GraphicsCardInfo {
    public static void main(String[] args) {
        try {
            // Informationen zur Grafikkarte abrufen
            Process gpuProcess = Runtime.getRuntime().exec("wmic path win32_videocontroller get caption");
            BufferedReader gpuReader = new BufferedReader(new InputStreamReader(gpuProcess.getInputStream()));
            String gpuLine;

            System.out.println("Grafikkarte:");
            while ((gpuLine = gpuReader.readLine()) != null) {
                System.out.println(gpuLine.trim());
            }

            // Informationen zur CPU abrufen
            Process cpuProcess = Runtime.getRuntime().exec("wmic cpu get caption");
            BufferedReader cpuReader = new BufferedReader(new InputStreamReader(cpuProcess.getInputStream()));
            String cpuLine;

            System.out.println("\nCPU:");
            while ((cpuLine = cpuReader.readLine()) != null) {
                System.out.println(cpuLine.trim());
            }

            // Schließen Sie die Reader und warten Sie auf das Ende der Prozesse
            gpuReader.close();
            cpuReader.close();
            gpuProcess.waitFor();
            cpuProcess.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
