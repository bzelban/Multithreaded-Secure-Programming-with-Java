package LabWorks;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Client {
    private static Socket clientSocket = null; // The client socket
    private static PrintWriter os = null; // The output stream
    private static BufferedReader is = null; // The input stream
    private static BufferedReader inputLine = null;

    public static void main(String[] args) throws Exception {
        int portNumber = 6789;
        String host = "localhost";

        InputStream inputStream = new URL("http://homes.ieu.edu.tr/eokur/sample0.txt").openStream();

        Files.copy(inputStream, Paths.get("b.txt"), StandardCopyOption.REPLACE_EXISTING);

        File file = new File("b.txt");

        // Open a socket on a given host and port. Open input and output streams.
        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host " + host);
        }

        /*
         * If everything has been initialized then we want to write some data to
         * the socket we have opened a connection to on the port portNumber.
         */
        if (clientSocket != null && os != null && is != null) {
            try {
                while (true) {
                    String str = inputLine.readLine().trim();
                    os.println(str);
                    if (str.equals("end") || str.equals("quit") )
                        break;
                }
                // Close the output stream, close the input stream, close the socket
                os.close();
                is.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
}