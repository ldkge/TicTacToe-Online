package com.ldkge.ttt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class TTTServer {

	public static void main(String[] args) {
		int portNumber = 8081;

        try {
        	 ServerSocket serverSocket = new ServerSocket(portNumber);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out =
                 new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                 new InputStreamReader(clientSocket.getInputStream()));
        
            String inputLine, outputLine;
            
            // Initiate conversation with client
           

           
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }

	}

}
