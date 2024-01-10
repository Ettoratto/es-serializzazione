package meucci;

import java.io.*;
import java.net.*;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ClientClass {

    int serverPort;
    String serverAddress;

    Socket client;
    BufferedReader in;
    PrintWriter out;

    XmlMapper mapper = new XmlMapper();

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

        String xml;

        try {
            while(!(xml = in.readLine()).isEmpty()){
                Persona p = mapper.readValue(xml, Persona.class);
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