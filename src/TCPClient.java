import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

class TCPClient {

    public static HashMap<Integer, ArrayList<String>> hashMap;



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


    public static void main(String argv[]) throws Exception {
        String sentence;
        String modifiedSentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        InputStream inputStream = new URL("http://homes.ieu.edu.tr/eokur/sample0.txt").openStream();

        Files.copy(inputStream, Paths.get("b.txt"), StandardCopyOption.REPLACE_EXISTING);

        File file = new File("b.txt");

        String content = read(file);
        hashMap = new HashMap<>();

        for(int i=0;i<content.length();i++){
            char c = content.charAt(i);
            ArrayList<String> arrayList= new ArrayList<>();
            arrayList.add(Character.toString(c));
            hashMap.put(i,arrayList);
        }

        while(true) {
            Socket clientSocket = new Socket("localhost", 6789);

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            //DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());
            ObjectOutputStream objToServer = new ObjectOutputStream(clientSocket.getOutputStream());

            //sentence = inFromUser.readLine();
            outToServer.writeBytes(hashMap.toString() + '\n');

            //object Output
            //objToServer.writeObject(hashMap );


            //modifiedSentence = inFromServer.readLine();

            //System.out.println("FROM SERVER: " + modifiedSentence);

            //From server bla
            String serverSentence = inFromServer.readLine();
            //outToClient.writeBytes(serverSentence);
            //clientSocket.close();
            System.out.println("From Server: " + serverSentence);
        }
    }
}
