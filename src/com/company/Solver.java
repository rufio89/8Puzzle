package com.company;

/**
 * Created by ryan on 12/20/16.
 */
public class Solver {
    public Board init, goal;
    public Graph g = new Graph();
    public Solver(Board initial, Board goal){
        this.init = initial;
        this.goal = goal;
    }



    public void BuildTree(){
        Board a = getLeft(this.init);
        Board b = getRight(this.init);
        Board c = getDown(this.init);
        Board d = getUp(this.init);
        System.out.println(a.printBoard());
        System.out.println(b.printBoard());
        System.out.println(c.printBoard());
        System.out.println(d.printBoard());
    }

    public Board getLeft(Board b){
        if(b.empJ==0){
            return b;
        }
        else{
           return shiftTiles(b,0);
        }
    }

    public Board getRight(Board b){
        if(b.empJ==b.getLength()-1){
            return b;
        }
        else{
            return shiftTiles(b,1);
        }
    }

    public Board getDown(Board b){
        if(b.empI==b.getLength()-1){
            return b;
        }
        else{
            return shiftTiles(b,2);
        }
    }

    public Board getUp(Board b){
        if(b.empI==0){
            return b;
        }
        else{
            return shiftTiles(b,3);
        }
    }

    public Board shiftTiles(Board b, int direction){
        Board newBoard = new Board();
        String[][] s = new String[b.getLength()][b.getLength()];
        int newEmpI, newEmpJ;
        String oldVal;
        //LEFT
        if(direction==0){
            newEmpI = b.empI;
            newEmpJ = b.empJ-1;
            oldVal = b.getTiles()[b.empI][b.empJ-1];
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i==b.empI && j==b.empJ){
                        s[i][j] = oldVal;
                    }
                    else if(b.getTiles()[i][j]==oldVal){
                        s[i][j] = " ";
                    }
                    else {
                        s[i][j] = b.getTiles()[i][j];
                    }
                }
            }
        }
        //RIGHT
        else if(direction==1){
            newEmpI = b.empI;
            newEmpJ = b.empJ+1;
            oldVal = b.getTiles()[b.empI][b.empJ+1];
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i==b.empI && j==b.empJ){
                        s[i][j] = oldVal;
                    }
                    else if(b.getTiles()[i][j]==oldVal){
                        s[i][j] = " ";
                    }
                    else {
                        s[i][j] = b.getTiles()[i][j];
                    }
                }
            }
        }
        //DOWN
        else if(direction==2){
            newEmpI = b.empI+1;
            newEmpJ = b.empJ;
            oldVal = b.getTiles()[b.empI+1][b.empJ];
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i==b.empI && j==b.empJ){
                        s[i][j] = oldVal;
                    }
                    else if(b.getTiles()[i][j]==oldVal){
                        s[i][j] = " ";
                    }
                    else {
                        s[i][j] = b.getTiles()[i][j];
                    }
                }
            }
        }
        //UP
        else if(direction==3){
            newEmpI = b.empI-1;
            newEmpJ = b.empJ;
            oldVal = b.getTiles()[b.empI-1][b.empJ];
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(i==b.empI && j==b.empJ){
                        s[i][j] = oldVal;
                    }
                    else if(b.getTiles()[i][j]==oldVal){
                        s[i][j] = " ";
                    }
                    else {
                        s[i][j] = b.getTiles()[i][j];
                    }
                }
            }
        }


        newBoard.setTiles(s);
        return newBoard;
    }


}
