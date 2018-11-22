/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprograming;

import java.io.*;
import java.net.*;

public class UDPServer {


    public static void main(String args[]) throws Exception {
        /*DatagramSocket ds=new DatagramSocket(9999);
        byte[] b1=new byte[1024];
        DatagramPacket dp=new DatagramPacket(b1,b1.length);
        ds.receive(dp);
        String str =new String(dp.getData(),0,dp.getLength());
        int num=Integer.parseInt(str);
        int res=num+10;
        byte[] b2=(res+"").getBytes();
        InetAddress ia=InetAddress.getByName("192.168.100.25");
        DatagramPacket reply=new DatagramPacket(b2,b2.length,ia,dp.getPort());
        ds.send(reply);
        
        //tes
        DatagramPacket dp2=new DatagramPacket(b1,b1.length);
        ds.receive(dp);
        DatagramPacket reply2=new DatagramPacket(b2,b2.length,ia,dp.getPort());
        ds.send(reply);
        */
        DatagramSocket ds=new DatagramSocket(9999);
        byte[] b1=new byte[1024];
        DatagramPacket player1=new DatagramPacket(b1,b1.length);
        DatagramPacket player2=new DatagramPacket(b1,b1.length);
        ds.receive(player1);
        reply("Waiting for player2",player1.getPort(),ds);
        ds.receive(player2);
        reply("Waiting for player1 to move",player2.getPort(),ds);
    }
    
    public static void reply(String msg,int port,DatagramSocket ds) throws Exception{
        InetAddress address=InetAddress.getByName("192.168.100.25");
        byte[] temp=msg.getBytes();
        DatagramPacket reply=new DatagramPacket(temp,temp.length,address,port);
        ds.send(reply);
    }
}
