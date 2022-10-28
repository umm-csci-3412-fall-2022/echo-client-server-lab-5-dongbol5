package echoserver;

import java.io.*;
import java.net.*;

public class EchoServer {

    // The port number we are listening on
    public static final int PORT_NUMBER = 6013;

    public static void main (String[] args) {

        try {

            // Start listening on the specified port
            ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);

            // Run forever, which is common for server style services
            while (true) {

                // Wait for the next client connection
                Socket socketServe = serverSocket.accept();

                InputStream inFromClient = socketServe.getInputStream();
                OutputStream outToClient = socketServe.getOutputStream();

                // Create a new thread to handle the client
                inFromClient.transferTo(outToClient);

                // Close the socket
                inFromClient.close();
                outToClient.flush();
                outToClient.close();
                socketServe.close();

            }

        // Provide some basic error handling
        } catch (IOException ioe) {
            System.out.println("An unexpected exception happened. Please refer to the following error message and try again:");
            System.err.println(ioe);
        }

    }

}
