package pong;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
public class GetIP  
{
    public static void GetIP() 
    {
        int i=0;
        String systemipaddress = "";
        try
        {
        final URL url = new URL("http://www.google.com");                                                                                                                                                                                 
        final URLConnection conn = url.openConnection();                                                                                                                                                                                  
        conn.connect();     
         if (!(systemipaddress.length() > 0)) 
            {
                
                    InetAddress localhost = InetAddress.getLocalHost();
                    System.out.println((localhost.getHostAddress()).trim());
                    systemipaddress = (localhost.getHostAddress()).trim();
               
            }
        }    
        catch (Exception e) 
        {  
        JOptionPane.showMessageDialog(null,"INTERNET CONNECTION ERROR","", JOptionPane.WARNING_MESSAGE);
        i++;  
        }
        if(i==0)
        JOptionPane.showMessageDialog(null,"System's Current IP Address :" +systemipaddress,"", JOptionPane.INFORMATION_MESSAGE);
    }
}

    

