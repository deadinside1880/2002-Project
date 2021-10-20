import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    
    File file;
    Scanner AT;

    public ArrayList<String> readFile(String path) throws FileNotFoundException{
        ArrayList<String> data = new ArrayList<>();
        try{
            file = new File(path);
            AT = new Scanner(file);

            while(AT.hasNextLine()){
                String[] arr = AT.nextLine().split(",");
                for(String x : arr)
                    data.add(x);
            }
            AT.close();
        }catch(Error e){
            System.out.println(e);
        }

        return data;
    }
    

    public void writeFile(ArrayList<String> data, String path) throws FileNotFoundException{
        try{
            FileWriter fw = new FileWriter(new File(path));
            StringBuilder sb = new StringBuilder();

            for(String x : data){
                sb.append(x);
                sb.append(",");
            }
            
            fw.write(sb.toString());
            fw.close();
        }catch(IOException ioe){
            System.out.println(ioe);
        }catch(Error e){
            System.out.println(e);
        }
    }
}
