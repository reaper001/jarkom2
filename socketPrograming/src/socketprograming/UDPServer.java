/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprograming;

import java.io.*;
import java.net.*;


public class UDPServer {

    
    public static void main(String args[])  {
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
        Thread serverThread=new Thread(new UDP());
        serverThread.start();

    }
    

    
}
class UDP implements Runnable{
    DatagramSocket ds;
    int port1,port2;
    DatagramPacket player1,player2;
    @Override
    public void run() {
        try{
            ds=new DatagramSocket(9999);
            byte[] b1=new byte[1024];
            player1=new DatagramPacket(b1,b1.length);
            player2=new DatagramPacket(b1,b1.length);
            waiting();
            boolean finish=false;
            boolean isPlayer1=true;
            System.out.println("game starting");
            String lastChange="";
            while(!finish){
                System.out.println("waiting for player");
                int port;
                if(isPlayer1)port=port1;
                else port=port2;
                reply("Your Turn!"+lastChange,port,ds);
                if(!isPlayer1)ds.receive(player2);
                else ds.receive(player1);
                String temp=new String(player1.getData(),0,player1.getLength());
                System.out.println("isiTemp="+temp);
                lastChange=","+temp;
                isPlayer1=!isPlayer1;
            }
        }
        catch(Exception e){
        
        }
    }
    public void reply(String msg,int port,DatagramSocket ds) throws Exception{
        InetAddress address=InetAddress.getByName("192.168.100.25");
        byte[] temp=msg.getBytes();
        DatagramPacket reply=new DatagramPacket(temp,temp.length,address,port);
        ds.send(reply);
    }
    
    public void waiting()throws Exception{
        ds.receive(player1);
        port1=player1.getPort();
        System.out.println("player 1 connected");
        reply("Waiting for player2",port1,ds);
        ds.receive(player2);
        port2=player2.getPort();
        System.out.println("player 2 connected");
        reply("Waiting for player1 to move",port2,ds);
    }
    
}
