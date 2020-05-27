import java.io.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class clientDownloader {

    static String read(File path) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String data = br.readLine();

        String content="";
        while (data!=null) {
            content+=data;
            data = br.readLine();
        }
        return content;
    }

    public static void main(String[] args) throws Exception {

        //This URL can be changeable(with switch or another)
        InputStream inputStream = new URL("http://homes.ieu.edu.tr/eokur/sample0.txt").openStream();
        Files.copy(inputStream, Paths.get("b.txt"), StandardCopyOption.REPLACE_EXISTING);
        File file = new File("b.txt");
        String content = read(file);
        //System.out.println(content);


        String sentence;
        String modifiedSentence;
        String key;

        //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        Reader fromFileString = new StringReader(content);
        BufferedReader inFromFile = new BufferedReader(fromFileString);

        Socket clientSocket = new Socket("localhost", 6789);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //sentence = inFromUser.readLine();
        sentence = inFromFile.readLine();
        outToServer.writeBytes(sentence + '\n');

        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: " + modifiedSentence);

        clientSocket.close();


    }
}



