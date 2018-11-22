/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprograming;

import java.io.*;
import java.net.*;

class UDPClient {
    
    private String answer;
    private FXMLDocumentController controller;
    public UDPClient(FXMLDocumentController controller){
        this.controller=controller;
    }
    
    public void test() throws Exception {
        /*DatagramSocket ds =new DatagramSocket();
        int i=9;
        byte[] b=(i+"").getBytes();
        InetAddress ia=InetAddress.getLocalHost();
        DatagramPacket dp=new DatagramPacket(b,b.length,ia,9999);
        ds.send(dp);
        
        byte[] b2=new byte[1024];
        DatagramPacket ans=new DatagramPacket(b2,b2.length);
        ds.receive(ans);
        String temp=new String(ans.getData(),0,ans.getLength());
        System.out.println(temp);
        //tes
        DatagramPacket dp2=new DatagramPacket(b,b.length,ia,9999);
        ds.send(dp2);
        DatagramPacket ans2=new DatagramPacket(b2,b2.length);
        ds.receive(ans2);
        String temp2=new String(ans.getData(),0,ans.getLength());
        System.out.println(temp2);*/
        
        DatagramSocket ds =new DatagramSocket();
        String msg="ok";
        byte[] b=msg.getBytes();
        InetAddress ia=InetAddress.getLocalHost();
        DatagramPacket dp=new DatagramPacket(b,b.length,ia,9999);
        ds.send(dp);
        
        //get server response
        byte[] b2=new byte[1024];
        DatagramPacket ans=new DatagramPacket(b2,b2.length);
        ds.receive(ans);
        answer=new String(ans.getData(),0,ans.getLength());
        this.controller.setText(answer);
        this.controller.setFirst(answer.equalsIgnoreCase("Waiting for player2"));
    }
}
