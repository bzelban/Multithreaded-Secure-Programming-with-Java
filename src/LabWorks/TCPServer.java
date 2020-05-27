package LabWorks;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

class TCPServer {

    public static HashMap<Integer, ArrayList<String>> hashMap;


    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        String serverSentence;

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        ServerSocket welcomeSocket = new ServerSocket(6789);
        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            BufferedReader objFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            //clientSentence = inFromClient.readLine();
            //hashMap = (HashMap) inFromClient.readLine();
            //capitalizedSentence = clientSentence.toUpperCase() + '\n';
            //outToClient.writeBytes(capitalizedSentence); // outToClient.writeBytes(capitalizedSentence);������

            //System.out.println("From LabWorks.Client: " + clientSentence);


            /*
            for(int i=0;i<clientSentence.length();i++){
                char c = clientSentence.charAt(i);
                ArrayList<String> arrayList= new ArrayList<>();
                arrayList.add(Character.toString(c));
                hashMap.put(i,arrayList);
            }
            */


            serverSentence = inFromUser.readLine();
            outToClient.writeBytes(serverSentence);



            //System.out.println("From Server:");
        }
    }
}
