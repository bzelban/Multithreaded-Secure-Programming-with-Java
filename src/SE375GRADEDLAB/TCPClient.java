package SE375GRADEDLAB;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

class TCPClient {

    static String read(File path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

        String data = br.readLine();

        String content="";
        while (data!=null) {
            content+=data;
            data = br.readLine();
        }
        return content;
    }

    
    public static void main(String [] args) throws Exception {
        String sentence;
        String modifiedSentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket("localhost", 3334);
        Scanner sc = new Scanner(System.in);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //String tempURL = "";
        //tempURL = sc.next();
        InputStream inputStream = new URL("http://homes.ieu.edu.tr/eokur/sample0.txt").openStream();
        //InputStream inputStream = new URL(tempURL).openStream();
        Files.copy(inputStream, Paths.get("b.txt"), StandardCopyOption.REPLACE_EXISTING);
        File file = new File("b.txt");
        String content = read(file);


        //sentence = inFromUser.readLine();
        outToServer.writeBytes(content + '\n');

        //From Server
        modifiedSentence = inFromServer.readLine();

        System.out.println("Message FROM SERVER: " + modifiedSentence);
        //clientSocket.close();



    }
}