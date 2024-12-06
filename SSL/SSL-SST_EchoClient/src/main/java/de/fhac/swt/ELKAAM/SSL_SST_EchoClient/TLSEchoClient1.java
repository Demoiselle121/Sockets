package de.fhac.swt.ELKAAM.SSL_SST_EchoClient;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TLSEchoClient1 {
	
	  public static void main(String[] args) throws UnknownHostException, IOException {
	        // TODO: Setup truststore to verify server-certificate

	        System.out.println("client starts");
	        
	        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\user\\OneDrive\\Documents\\JEE\\SSL-SST_EchoClient\\truststore.jks");
		    System.setProperty("javax.net.ssl.trustStorePassword", "geheim");


	        SSLSocketFactory factory =(SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket)factory.createSocket(InetAddress.getLocalHost(), 39999);
	
            sslSocket.startHandshake();

	        Scanner scanner = new Scanner(System.in);
	        DataInputStream dataInputStream = new DataInputStream(sslSocket.getInputStream());
	        DataOutputStream dataOutputStream = new DataOutputStream(sslSocket.getOutputStream());

	        System.out.print("Next message: ");
	        while (scanner.hasNextLine()) {
	            dataOutputStream.writeUTF(scanner.nextLine());
	            System.out.print("Answer: ");
	            System.out.println(dataInputStream.readUTF());
	            System.out.print("Next message: ");
	        }
	        scanner.close();
	    }

	
	
	

}
