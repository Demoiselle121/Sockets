package de.fhac.swt.ELKAAM.SSL_TLSEchoServer;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Provider;
import java.security.Security;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TLSEchoServer1 {
	 public static void main(String[] args) throws IOException {

	        // TODO: Set keystore and password
	       System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\user\\OneDrive\\Documents\\JEE\\SSL-SST_EchoClient\\rn-fhac-de.jks");
	       System.setProperty("javax.net.ssl.keyStorePassword", "ElkaamHiba2001");
	       System.out.println("server starts");
	       ServerSocket socketserver=  SSLServerSocketFactory.getDefault().createServerSocket(39999) ;

	        // TODO: Create SSLServerSocket
	        Socket sslSocket = (SSLSocket) socketserver.accept();
	        
			// ((SSLSocket) sslSocket).startHandshake();


	        DataInputStream dataInputStream = new DataInputStream(sslSocket.getInputStream());
	        DataOutputStream dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());
	        while (true) {
	            dataOutputStream.writeUTF(dataInputStream.readUTF().toUpperCase());
	            System.out.println("Received message and send back");
	        }
	    }

}

