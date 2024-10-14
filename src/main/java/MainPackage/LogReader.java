package MainPackage;

import java.io.File;

//Start two threads in order to read sanitized file a wait until reading finish
public class LogReader {

    private Thread th1;
    private Thread th2;

    public LogReader(File file) {

        th1 = new ThreadReader(file, true);
        th2 = new ThreadReader(file, false);
    }

    public void start()
    {
        th1.start();
        th2.start();

        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
