/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketprograming;

/**
 *
 * @author Feb
 */
public class GameLogic {
    
    private int[][] board;
    
    public GameLogic(){
        this.board=new int[3][3];
    }
    
    public boolean checkRes(int x,int y){
        int lineX=0;
        int lineY=0;
        int check=board[x][y];
        for (int i = 0; i < board.length; i++) {
            if(check==board[x][i])lineX++;
            if(check==board[i][y])lineY++;
        }
        int diagonal1=0;
        int diagonal2=0;
        if(x==1 && y==1){
            for (int i = 0; i < board.length; i++) {
                if(check==board[i][i])diagonal1++;
                if(check==board[board.length-i][board.length-i])diagonal2++;
                
            }
        }
        if(lineX==3 || lineY==3 ||diagonal1==3 || diagonal2==3)return true;
        else return false;
    }
    
    public void move(int player,int x,int y){
        if(player==1)board[x][y]=0;
        else board[x][y]=1;
    }
}
