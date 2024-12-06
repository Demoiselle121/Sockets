package de.fhac.swt.ELKAAM.Aufgaben_TCPIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;



public class BroadCastetServer {
	

	int port;
    ServerSocket server=null;
    Socket client=null;
    ExecutorService pool = null;
    int clientcount=0;
    private static ArrayList<BroadServerThread> clientList;
    private static  LinkedBlockingQueue<Object> messages;
    public static void main(String[] args) throws IOException {
    	BroadCastetServer serverobj=new BroadCastetServer(5000);
        
        
        serverobj.startServer();
    }
    
    BroadCastetServer(int port){
    	clientList = new ArrayList<BroadServerThread>();
    	messages = new LinkedBlockingQueue<Object>();
        this.port=port;
        pool = Executors.newFixedThreadPool(5);
    }

    public void startServer() throws IOException {
        
        server=new ServerSocket(5000);
        
        System.out.println("Server wurde gestartet ...");
        System.out.println("Um den Programm zu beenden , bitte exit schreiben");
        System.out.println("Server wartet auf Verbindungen ...");
        while(true)
        {	
        	
            client=server.accept();
            
            clientcount++;
            BroadServerThread runnable= new BroadServerThread(client,clientcount,this);
            //activeClients.add(runnable);
            System.out.println("////////////////:"+runnable);
            pool.execute(runnable);
            //activeClients.add(runnable);
            clientList.add(new BroadServerThread(client));
           
        }
        
      
        
    }

    private static class BroadServerThread implements Runnable {
        
        BroadCastetServer ServerSocket=null;
        Socket client=null;
        DataInputStream inStream;
        DataOutputStream outStream;
        Scanner sc=new Scanner(System.in);
        int id;
        String s;
		byte[] buf;
        
        
		BroadServerThread(Socket client, int count ,BroadCastetServer ServerSocket ) throws IOException {
            
            this.client=client;
    	    
            this.ServerSocket=ServerSocket;
            this.id=count;
            System.out.println("Connection no"+id+"=====> Neuer Client mit Ip @ "+client.getLocalAddress()+" und port:"+client.getLocalPort());
            
            inStream=new DataInputStream(client.getInputStream());
            outStream=new DataOutputStream(client.getOutputStream());
        
       }
		
       
        public BroadServerThread(Socket client) throws IOException  {
        	this.client= client;
            inStream = new DataInputStream(client.getInputStream());
            outStream = new DataOutputStream(client.getOutputStream());
            }


		@Override
        public void run() {
			 for (int i = 0; i < clientList.size(); i++) {
			  	 
			     // Printing elements one by one
			     System.out.print("*****active:***** "+clientList.get(i) + " ");
	       }
            int x=1;
            try{
            while(true){
             s=inStream.readUTF();
             if ( s.equals("exit")) {
   				 System.out.println("Client"+id+" schlisst die Verbindung an !");
   				 outStream.writeUTF("BYE");
   				 inStream.close();
                 client.close();
                 outStream.close();
   				 break;
   			 }
           	 System. out.print("echo Client("+id+") :"+s+"\n");
 			System.out.print("echo Server : ");
 							
                             s=sc.nextLine();
                             System.out.println(client.isConnected());
                             if(s.startsWith("broad")) {
                            	 
                            	 String[] arrOfStr = s.split("broad");
                            	 
                                     System.out.println("******den eigentlichen Nachrichtentext*******"+arrOfStr[1]);
                            	 sendToAll(arrOfStr[1]);
                             }
                             else {
                            	
                            	 outStream.writeUTF(s);
                            	 }
                             
                         
                         System.out.println("Client IP adresse : "+client.getInetAddress());
     	                System.out.println("server is listenning on port: "+client.getLocalPort());
 		}
 		
             
                 inStream.close();
                 client.close();
                 outStream.close();
            } 
            catch(IOException ex){
                System.out.println("Error : "+ex);
            }
                
        
		
            
        }
		
		
		public void sendToAll(String message){
			
	        for(BroadServerThread client : clientList)
	            client.write(message);
	    }
		 public void write(String obj) {
	            try{
	            	System.out.println("lmen tayseft"+outStream);
	                outStream.writeUTF(obj);
	            }
	            catch(IOException e){ e.printStackTrace(); }
	        }
	    }
    }
