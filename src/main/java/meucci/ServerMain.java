package meucci;

public class ServerMain {
    public static void main(String[] args) {

        ServerClass server = new ServerClass(6789);

        server.connect(); 
        server.communicate();
    }


}