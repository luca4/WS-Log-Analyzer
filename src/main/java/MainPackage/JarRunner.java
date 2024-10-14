package MainPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//Execute jar file
public class JarRunner {

    private String pathToDataset;

    public JarRunner(String pathToDataset)
    {
        this.pathToDataset = pathToDataset;
    }

    public void run() throws FileNotFoundException{
        if(pathToDataset == null) return;

        if(!new File(pathToDataset).exists())
        {
            throw new FileNotFoundException();
        }

        try {
            Process process = Runtime.getRuntime().exec("java -jar ./jawk.1_02.jar -f ./script.awk " + pathToDataset);

            // Wait until jawk finish executing
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
