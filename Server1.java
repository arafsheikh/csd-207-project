/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author rabil
 */
public class Server1 {

    /**
     * @param args the command line arguments
     */
    private  static Socket socket;
    public static void main(String[] args) {
        // TODO code application logic here
         try
        {
            int port = 5321;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("server connection");
            socket=serverSocket.accept();//single thread as multiple server is not used here
            while(true)//for making the server take part in the process all time
            {
                //getting/reading the message from the client
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader buffer = new BufferedReader(isr);
                String number = buffer.readLine();
                System.out.println("message from the client: "+number);
                 String returnedmessage;
                try
                {
                    int numberInIntFormat = Integer.parseInt(number);
                    int returnedvalue = numberInIntFormat/2;
                    returnedmessage = String.valueOf(returnedvalue)+"\n";
                }
                catch(NumberFormatException e)
                {
                   returnedmessage = "enter a valid number.\n";//checks for the validity of the input for sending proper messsage back to the client.
                }
                OutputStream os = socket.getOutputStream();//responsing back to the client 
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter buffer1 = new BufferedWriter(osw);
                buffer1.write(returnedmessage);
                System.out.println("Message sent to the client is "+returnedmessage);
                
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}
    

