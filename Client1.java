/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
/**
 *
 * @author rabil
 */
public class Client1 {
      /**
     * @param args the command line arguments
     */
     private static Socket socket;
    public static void main(String[] args) {
        // TODO code application logic here
        try
        {
            String host = "localhost";
            int port = 5321;
            InetAddress address = InetAddress.getByName(host);// returns a string representing host name connected to InetAddress object, wrapping up ip addressses and domain methods of the host name
            socket = new Socket(address, port);
            OutputStream os = socket.getOutputStream();//sending of the message to the server end
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter buffer = new BufferedWriter(osw);
            String no ="20";
            String sentmessage =no + "\n";
            buffer.write(sentmessage);
            buffer.flush();//Flushes the output stream and forces any buffered output bytes to be written out. 
            System.out.println("message sent to the server: "+sentmessage);
            InputStream is = socket.getInputStream();//getting back the return message from the server
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buffer1 = new BufferedReader(isr);
            String receivedmessage = buffer1.readLine();
            System.out.println("message received back from the server:"+receivedmessage);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();// IOException object in the catch block.
        }
        finally
        {
         try
            {
                socket.close();//indicates the end or the closing of the socket.
            }
            catch(Exception e)
            {
                e.printStackTrace();//helps in clearing/sorting out the exception and allowing us to know how and where it happened
            }
        }
    }
 }
