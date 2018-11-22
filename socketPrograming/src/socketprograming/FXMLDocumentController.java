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
    
    
    
    private UDPClient client;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button temp=(Button)event.getSource();
        System.out.println("You clicked me!");
        if(isFirst)temp.setText("O");
        else temp.setText("X");
        temp.setDisable(true);
        
        
    }
    
    public void setText(String msg){
        stat.setText(msg);
    }
    public void setFirst(boolean x){
        this.isFirst=x;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        client=new UDPClient(this);
        try{
            System.out.println("msk");
            client.test();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }    
    
}
