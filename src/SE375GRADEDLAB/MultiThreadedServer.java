package SE375GRADEDLAB;

import java.net.*;
import java.io.*;

public class MultiThreadedServer {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static boolean closed = false;
    String tempCase;
    String tempShift;
    String tempColor;

    // To only Lower Case
    public String caseMethod(String _case)
    {
        tempCase = _case.toLowerCase();
        return tempCase;
    }

    // To Only Shift by 3
    public String shiftMethod(String _shift)
    {
        int c = 3;
        StringBuffer result= new StringBuffer();

        for (int i=0; i<_shift.length(); i++)
        {
            if (Character.isUpperCase(_shift.charAt(i)))
            {
                char ch = (char)(((int)_shift.charAt(i) +
                        c - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)_shift.charAt(i) +
                        c - 97) % 26 + 97);
                result.append(ch);
            }
        }
        tempShift = result.toString();
        return tempShift;
    }

    // To Only Coloring Red
    public String colorMethod(String _color)
    {
        return ANSI_RED+_color;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(3334);
        } catch (IOException ioe) {
            System.out.println("Could not create server socket on port 3334. Quitting.");
            System.exit(-1);
        }

        while (!closed) {
            Socket s = ss.accept();
            Thread clientThread = new Thread (new MultiThreadedServer().new ClientServiceThread(s));
            clientThread.start();
        }
        ss.close();
        System.out.println("Server Stopped");
    }

    public class ClientServiceThread implements Runnable {
        Socket s;
        boolean clientStop = false;
        ClientServiceThread(Socket s) {
            this.s = s;
        }

        public void run() {
            BufferedReader din = null;
            PrintWriter dout = null;
            System.out.println("Accepted Client Address - " + s.getInetAddress().getHostName());



            try {
                din = new BufferedReader(new InputStreamReader(s.getInputStream()));
                dout = new PrintWriter(s.getOutputStream(), true);

                while (! clientStop) {
                    String clientCommand = din.readLine();
                    //
                    clientCommand = caseMethod(clientCommand);
                    clientCommand = shiftMethod(clientCommand);
                    clientCommand = colorMethod(clientCommand);

                    //
                    System.out.println("Client Says :" + clientCommand);

                    if (clientCommand.equalsIgnoreCase("quit")) {
                        clientStop = true;
                        System.out.print("Stopping client thread for client : ");
                    } else if (clientCommand.equalsIgnoreCase("end")) {
                        clientStop = true;
                        System.out.print("Stopping client thread for client : ");
                        closed = true;
                    } else {
                        dout.println("Server Says : " + clientCommand);
                        dout.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    din.close();
                    dout.close();
                    s.close();
                    System.out.println("...Stopped");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}