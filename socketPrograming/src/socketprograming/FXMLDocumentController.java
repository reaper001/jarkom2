/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprograming;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 *
 * @author Feb
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Button arr0;
    @FXML private Button arr1;
    @FXML private Button arr2;
    @FXML private Button arr3;
    @FXML private Button arr4;
    @FXML private Button arr5;
    @FXML private Button arr6;
    @FXML private Button arr7;
    @FXML private Button arr8;
    
    @FXML
    private Button cobaBut;
    
    @FXML
    private Label stat;
    
    private boolean isFirst;
    private boolean isFirstPlayerTurn;
    

    
    
    
    private UDPClient client;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button temp=(Button)event.getSource();
        String tmp=temp.getId();
        tmp=tmp.substring(3);
        //System.out.println("You clicked me!");
        System.out.println("turn:"+isFirstPlayerTurn);
        if(isFirst && isFirstPlayerTurn){
            temp.setText("O");
            temp.setDisable(true);
            this.client.setResponse(tmp);
        }
        else if(!isFirst && !isFirstPlayerTurn){
            temp.setText("X");
            temp.setDisable(true);
            this.client.setResponse(tmp);
        }
    }
    
    @FXML
    public void update(String update){
        int temp=Integer.parseInt(update);
        String temp2="";
        if(isFirst && isFirstPlayerTurn){
            temp2="X";
        }
        else if(!isFirst && !isFirstPlayerTurn){
            temp2="O";
        }
        if(temp==0){
            arr0.setText(temp2);
            arr0.setDisable(true);
        }
        else if(temp==1){
            arr1.setText(temp2);
            arr1.setDisable(true);
        }
        else if(temp==2){
            arr2.setText(temp2);
            arr2.setDisable(true);
        }
        else if(temp==3){
            arr3.setText(temp2);
            arr3.setDisable(true);
        }
        else if(temp==4){
            arr4.setText(temp2);
            arr4.setDisable(true);
        }
        else if(temp==5){
            arr5.setText(temp2);
            arr5.setDisable(true);
        }
        else if(temp==6){
            arr6.setText(temp2);
            arr6.setDisable(true);
        }
        else if(temp==7){
            arr7.setText(temp2);
            arr7.setDisable(true);
        }
        else if(temp==8){
            arr8.setText(temp2);
            arr8.setDisable(true);
        }
    }
    
    public void setText(String msg){
        stat.setText(msg);
    }
    public void setFirst(boolean x){
        this.isFirst=x;
        this.isFirstPlayerTurn=x;
    }
    
    public void setPlayerTurn(){
        this.isFirstPlayerTurn=!isFirstPlayerTurn;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.client=new UDPClient(this);
        Thread clientThread=new Thread(this.client);
        try{
            //System.out.println("msk");
            clientThread.start();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }    
    
}
