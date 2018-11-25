/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprograming;

import java.io.*;
import java.net.*;
import javafx.application.Platform;

class UDPClient implements Runnable{
    
    private String answer;
    private String response;
    private boolean isAnswerOut;
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
        System.out.println("ans");
        answer=new String(ans.getData(),0,ans.getLength());
        this.controller.setText(answer);
        this.controller.setFirst(answer.equalsIgnoreCase("Waiting for player2"));
        while(true){
            System.out.println("waiting game");
            DatagramPacket ans2=new DatagramPacket(b2,b2.length);
            ds.receive(ans2);
            answer=new String(ans.getData(),0,ans.getLength());
            System.out.println(answer);
        }
    }

    @Override
    public void run() {
        try{
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
            byte[] b3=new byte[1024];
            boolean firstRun=true;
            while(true){
                System.out.println("waiting");
                ds.receive(ans);
                if(!firstRun)this.controller.setPlayerTurn();
                answer=new String(ans.getData(),0,ans.getLength());
                String[] serverUpdate=answer.split(",");
                System.out.println(answer);
                Platform.runLater(() -> {
                    this.controller.setText(serverUpdate[0]);
                    if(serverUpdate.length>1)this.controller.update(serverUpdate[1]);
                });
                System.out.println("keluar");
                while(!isAnswerOut){
                    System.out.println("mesk");
                   
                }
                System.out.println(response);
                b=response.getBytes();
                dp=new DatagramPacket(b,b.length,ia,9999);
                ds.send(dp);
                this.isAnswerOut=false;
                this.controller.setPlayerTurn();
                firstRun=false;
            }
        }
        catch(Exception e){
            
        }


    }
    public void setResponse(String ans){
        this.response=ans;
        this.isAnswerOut=true;
    }
}
