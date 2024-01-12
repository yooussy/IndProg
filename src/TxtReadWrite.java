import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TxtReadWrite
{
    public static void read(ArrayList<String> arr)
    {
        try{
            BufferedReader br = new BufferedReader(new FileReader("input.txt"));
            String line;

            while((line = br.readLine()) != null)
            {
                arr.add(line);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error " + e);
        }
    }
    public static void write(ArrayList<String> arr, ArrayList<Integer> result)
    {
        try(FileWriter writer = new FileWriter("output.txt", false))
        {
            Iterator<String> iter1 = arr.iterator();
            Iterator<Integer> iter2 = result.iterator();
            while(iter1.hasNext()){
                String arr1 = iter1.next();
                Integer int1 = iter2.next();
                writer.write(arr1 + ": " + int1 + '\n');
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
