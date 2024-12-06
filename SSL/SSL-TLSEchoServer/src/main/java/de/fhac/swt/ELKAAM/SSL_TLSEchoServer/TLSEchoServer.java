package de.fhac.swt.ELKAAM.SSL_TLSEchoServer;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
// TODO: import SSL-Classes
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;


public class TLSEchoServer {
    public static void main(String[] args) throws IOException {

        // TODO: Set keystore and password
    	
        System.out.println("server starts");

        // TODO: Create SSLServerSocket
        // ??? sslSocket = sslServerSocket.accept();
        System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\user\\OneDrive\\Documents\\JEE\\SSL-TLSEchoServer\\rn-ssl.jks");
	    System.setProperty("javax.net.ssl.keyStorePassword", "geheim");
	    
        ServerSocketFactory ssocketFactory = SSLServerSocketFactory.getDefault();
        ServerSocket ssocket = ssocketFactory.createServerSocket(1234);

        Socket socket = ssocket.accept();
        
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        while (true) {
            dataOutputStream.writeUTF(dataInputStream.readUTF().toUpperCase());
            System.out.println("Received message and send back");
        }
    }
}

