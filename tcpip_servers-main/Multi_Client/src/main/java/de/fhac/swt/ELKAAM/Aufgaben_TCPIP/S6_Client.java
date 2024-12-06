package de.fhac.swt.ELKAAM.Aufgaben_TCPIP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class S6_Client {
	
	
    public static void main(String args[]) throws Exception
	{
    	if (args.length < 2) return ;
    	 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
        
        System.out.println("Client wurde gestartet ...");
		System.out.println("Client bietet Verbindung an...");  

		Socket socket=new Socket(hostname,port);
        System.out.println("Client ist Verbunden..."); 
        
		DataInputStream inStream=new DataInputStream(socket.getInputStream());
	    DataOutputStream outStream=new DataOutputStream(socket.getOutputStream());
	    
		Scanner scanner = new Scanner(System.in);
		String s;
		while (  true )
		{
			System.out.print("Client : ");
			s = scanner.nextLine();
			outStream.writeUTF(s);
	
            if ( s.equalsIgnoreCase("exit") )
            {
               System.out.println("Client beendet Verbindung");
               s = inStream.readUTF();
               System.out.println("echo server"+s);
 			   break;
                        }
			s=inStream.readUTF();
			System.out.print("Server : "+s+"\n");
  			
		}
		 socket.close();
		 inStream.close();
		 outStream.close();
 		scanner.close();
	}
    
    

	
}
