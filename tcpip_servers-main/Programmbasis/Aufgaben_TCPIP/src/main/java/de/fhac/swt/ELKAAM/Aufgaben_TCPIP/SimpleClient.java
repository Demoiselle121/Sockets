package de.fhac.swt.ELKAAM.Aufgaben_TCPIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;


import java.io.*;                                                               
import java.net.*;                                                              
                   
public class SimpleClient {

	                                                           
	        public static void main(String args[])throws Exception                  
	        {                                                                       
	                String line, newLine;
	                try                                                             
	                {           
	                		String hostname = args[0];
	                		int port = Integer.parseInt(args[1]);
	                		//Der Client stellt eine Verbindung zu dem Server her und fordert einen Dienst an
	                        Scanner scanner = new Scanner(System.in);
	                        System.out.println("Client wurde gestartet ...");
	                        
	                        System.out.println("Client bietet Verbindung an...");  
	                        Socket cs=new Socket(hostname,port);                 
	                        
	                        System.out.println("Client ist Verbunden..."); 
	                        DataInputStream inp=new DataInputStream (cs.getInputStream());
	                        
	                        DataOutputStream out=new DataOutputStream(cs.getOutputStream());
	                         
	                        
	                       while(true) {
	                        
	                            
	                            newLine = scanner.nextLine();
	                            if (newLine.equals("exit")) { 
	                            	
	                               out.writeUTF("exit"); 
	                               line = inp.readUTF();                                 
		                           System.out.println("Received from server: "+line);   
	                               scanner.close();
		   	                       cs.close();
	                               return;                                             
	                            } else { 
	                            	
	                              out.writeUTF(newLine + '\n'); 
	                              line = inp.readUTF();                                 
		                           System.out.println("Received from server: "+line); 
	                            } 
	                            
	                           }
	                       

	                   }                                                               
	                   catch(Exception e)                                              
	                   {        
	                	   e.printStackTrace();
	                   }
	                
	           }                                                                       
	   }     