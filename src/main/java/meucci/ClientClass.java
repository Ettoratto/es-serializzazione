package meucci;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ClientClass {

    int serverPort;
    String serverAddress;

    Socket client;
    BufferedReader in;
    PrintWriter out;

    ObjectMapper mapper = new ObjectMapper();

    public ClientClass(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public Socket connect(){

        try {
            client = new Socket(serverAddress, serverPort);
            
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

            System.out.println("Connesso all'host: " + serverAddress + ":" + serverPort);
        } catch (UnknownHostException e) {
            System.err.println("Host is unknown");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Something went wrong, closing client...");
            System.exit(1);
        }

        return client;
    }

    public void communicate(){

        String json;

        try {
            while(!(json = in.readLine()).isEmpty()){
                Persona p = mapper.readValue(json, Persona.class);
                System.out.println("Nome: " + p.nome + ";\nCognome: " + p.cognome + ";\nSesso: " + p.sesso + ";\nEta': " + p.eta + ";");
                closeConnection();
            }
        
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
    }

    public void closeConnection(){

        try {
            out.close();
            in.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Something went wrong...");
        }
    }

}