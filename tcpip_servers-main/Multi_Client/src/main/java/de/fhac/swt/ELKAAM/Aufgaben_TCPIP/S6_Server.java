package de.fhac.swt.ELKAAM.Aufgaben_TCPIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class S6_Server {
	


    int port;
    ServerSocket server=null;
    Socket client=null;
    ExecutorService pool = null;
    int clientcount=0;
    
    public static void main(String[] args) throws IOException {
        S6_Server serverobj=new S6_Server(5000);
        
        
        serverobj.startServer();
    }
    
    S6_Server(int port){
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
            ServerThread runnable= new ServerThread(client,clientcount,this);
            System.out.println("////////////////:"+runnable);
            pool.execute(runnable);
        }
        
    }

    private static class ServerThread implements Runnable {
        
        S6_Server ServerSocket=null;
        Socket client=null;
        DataInputStream inStream;
        DataOutputStream outStream;
        Scanner sc=new Scanner(System.in);
        int id;
        String s;
		byte[] buf;
        
        
       ServerThread(Socket client, int count ,S6_Server ServerSocket ) throws IOException {
            
            this.client=client;
    	    
            this.ServerSocket=ServerSocket;
            this.id=count;
            System.out.println("Connection no"+id+"=====> Neuer Client mit Ip @ "+client.getLocalAddress()+" und port:"+client.getLocalPort());
            
            inStream=new DataInputStream(client.getInputStream());
            outStream=new DataOutputStream(client.getOutputStream());
        
       }
       
        @Override
        public void run() {
            int x=1;
         try{
         while(true){
               s=inStream.readUTF();
  			 if ( s.equals("exit")) {
  				 System.out.println("Client"+id+" schlisst die Verbindung an !");
  				 //outStream.writeUTF("Client"+id+" schlisst die Verbindung an !");
  				 outStream.writeUTF("BYE");
  				inStream.close();
                client.close();
                outStream.close();
  				 break;
  			 }
  			 
			System. out.print("echo Client("+id+") :"+s+"\n");
			System.out.print("echo Server : ");
			
                            s=sc.nextLine();
                            //buf = s.getBytes();
                            //InetAddress group = InetAddress.getByName("255.255.255.255");
                           
                            //DatagramSocket packet = new DatagramSocket(5000,group);
                           // ((DatagramSocket) ServerSocket).send(packet);
                        outStream.writeUTF(s);
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
    }
}
        
    
