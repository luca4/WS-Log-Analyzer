package MainPackage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

//Thread that read half of the sanitized file
public class ThreadReader extends Thread{
    private File file;
    private boolean isFirstThread = false;
    private Data data;

    public ThreadReader(File file, boolean isFirstThread)
    {
        this.isFirstThread = isFirstThread;
        this.file = file;
        data = Data.getInstance();
    }

    @Override
    public void run() {
        super.run();
        try {
            long nLines = Files.lines(file.toPath()).count();

            if(isFirstThread)
            {
                Object[] lines = Files.lines(file.toPath())
                        .skip(1)  //skip column description
                        .toArray();
                for(int i=0; i<(int)(nLines/2)-1; i++)
                {
                    data.add(lines[i].toString());
                }
            }
            else
            {
                Object[] lines = Files.lines(file.toPath()).skip((int)(nLines/2)).toArray();
                for(Object obj : lines)
                {
                     data.add(obj.toString());
                }
            }
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
