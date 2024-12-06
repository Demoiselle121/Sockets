package de.fhac.swt.ELKAAM.Aufgaben_TCPIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class SimpleServer 
{
	                                                           
	                                                                  
	   public static void main(String args[])throws Exception                       
	   {                                                                            
	        try                                                                     
	        {       
	        		//SimpleServer s = new SimpleServer();
	        		
	                String line, newLine;
	                boolean running =true;
	                ServerSocket ss=new ServerSocket(6789);
	                System.out.println("Server wurde gestartet ...");
	                System.out.println("Client IP adresse & hostname: "+InetAddress.getLocalHost());
	                System.out.println("server is listenning on port: "+ss.getLocalPort());
	                
	                // Communication Endpoint for the client and server.            
	                while(true)                                                     
	                {                                                               
	                  // Warten auf Verbindung
		              System.out.println("Server wartet auf Verbindungen ...");

	                  Socket s=ss.accept();                                         
	                  System.out.println("Neuer Client verbunden ...");
	                  //DataInputStream in=new DataInputStream(System.in);
	                  Scanner scanner = new Scanner(System.in);
	                  // DataInputStream to read data from input stream             
	                  DataInputStream inp=new DataInputStream(s.getInputStream());  
	                  // DataOutputStream to write data on outut stream             
	                  DataOutputStream out = new DataOutputStream(s.getOutputStream());
	                              
	                   //read a line text                                      
	                  while(true) {                                                 
	                     
	                   line = inp.readUTF();                                       
	                                               
	                   System.out.println("Received from client: " + line);
	                   if (line.equals("exit")) {
	                	   System.out.println("***");
	                	   newLine = scanner.nextLine();
	                	                                                                         
	                	   out.writeUTF(newLine + '\n'); 
	                	   
	                	   running = false;
	                	   scanner.close();
	                	   ss.close();
	                	   
	                	   return;
	                   }
	                   else {
                         
	                   //newLine = in.readLine();
	                   newLine = scanner.nextLine();
;	                                                                      
	                     out.writeUTF(newLine + '\n'); 
	                   }               
		                   
	                   }  
	                  
	                  }                                                             
	              
	                
	        }                                                                
	        catch(Exception e)                                                      
	        {                                                                       
	        	e.printStackTrace();                                                                     
	        }                                                                       
	   }                                                                            
}