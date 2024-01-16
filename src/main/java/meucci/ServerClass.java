package meucci;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerClass {

    int port;
    ServerSocket server;
    Socket client;
    BufferedReader in;
    PrintWriter out;

    ObjectMapper mapper = new ObjectMapper();

    public ServerClass(int port) {

        this.port = port;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void connect() {

        try {
            System.out.println("Waiting for connection...");
            client = server.accept();

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("Client connesso: " + client.getInetAddress() + ":" + port);

        } catch (IOException e) {

        }
    }
    
    public void communicate() {

        try {

            Persona p = new Persona("Davide", "Guerrieri", "M", 18);
            String json = mapper.writeValueAsString(p);
            System.out.println(json);
            out.println(json);
            closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }
    }

    public void closeConnection() {

        System.out.println("Closing connection...");
        try {
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
       
}
