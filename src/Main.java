import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Enter type of file(.txt, .json, .xml): ");

        Scanner in = new Scanner(System.in);
        String typeOfFile;
        typeOfFile = in.nextLine();

        ArrayList<String> arr = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();

        if(typeOfFile.equals(".txt")) {
            TxtReadWrite.read(arr);
        }
        else if(typeOfFile.equals(".json")){
            JSONReadWrite.read(arr);
        }
        else if(typeOfFile.equals(".xml"))
        {
            XMLReadWrite.read(arr);
        }

        Parser.parse(arr, result);

        System.out.println("What is format of output file: ");
        typeOfFile = in.nextLine();

        if(typeOfFile.equals(".txt")) {
            TxtReadWrite.write(arr, result);
        }
        else if(typeOfFile.equals(".json")){
            JSONReadWrite.write(arr, result);
        }
        else if(typeOfFile.equals(".xml"))
        {
            XMLReadWrite.write(result);
        }

        //шифрование
        Encrypt cr = new Encrypt();
        BufferedReader r = new BufferedReader(new FileReader("output" + typeOfFile));
        PrintStream f = new PrintStream("encrypted.txt");
        String str = r.readLine();
        while (str != null) {
            String e = cr.encrypt(str);
            f.println(e);
            str = r.readLine();
        }


        //архивация
        Archiver archiver = new Archiver();
        String sourceDir = "output" + typeOfFile;
        String zipFile = "outputZip.zip";
        archiver.createZipArchive(sourceDir, zipFile);
    }

}