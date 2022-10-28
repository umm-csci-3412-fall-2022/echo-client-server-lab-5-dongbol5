package echoserver;

import java.io.*;
import java.net.*;

public class EchoClient {

    // The port number we are connecting to
    public static final int PORT_NUMBER = 6013;
    public static void main(String[] args) {

        String server;

        // If no server is specified on the command line, use the local host
        if (args.length == 0) {
            server = "localhost";
        } else {
            server = args[0];
        }

        // Try to open a socket on the specified server and port
        try {

          // Connect to the server
          Socket socketServe = new Socket(server, PORT_NUMBER);

          // Setup Input and Output Streams
          InputStream inFromServer = socketServe.getInputStream();
          OutputStream outToServer = socketServe.getOutputStream();

          // Read from the keyboard and send to the server
          int inputByte;
          while ((inputByte = System.in.read()) != -1) {
            outToServer.write(inputByte);
          }

          // Close the socket
          outToServer.flush();
          socketServe.close();
          System.out.println(inFromServer.readAllBytes());

          // Provide some basic error handling
        } catch (ConnectException ce) {
            System.out.println("Unable connect to: " + server);
            System.out.println("Please check your server, and try again.");
        } catch (IOException ioe) {
            System.out.println("An unexpected exception happened. Please refer to the following error message and try again:");
            System.err.println(ioe);
        }

    }

}