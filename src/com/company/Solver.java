package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by ryan on 12/20/16.
 */
public class Solver {
    private boolean[] marked;
    public Board init, goal;
    public Graph g = new Graph();
    public LinkedList<Board> visited = new LinkedList<Board>();
    public Solver(Board initial, Board goal){
        this.init = initial;
        this.goal = goal;
    }



    public void BuildTree(Board board, Board goal, int count){
        int counter = count;
        System.out.println(counter);
        if(counter==0) {
            visited.add(board);
            g.addFirst(board, 0);
        }

        if(board.compare(goal)==true){
            System.out.println(board.printBoard());
            for(int i=0;i<visited.size();i++){
                System.out.println(visited.get(i).printBoard());
            }
            return;
        }
        else {
            Board left = getLeft(board);
            Board right = getRight(board);
            Board up = getUp(board);
            Board down = getDown(board);
            counter++;

            if (left!=null) {
                //if (!hasBoard(left)){
                    visited.add(left);
                    g.addEdge(left, board, counter, count);
                    BuildTree(left, goal, counter);
               // }
            }
            if(right!=null){
                //if(!hasBoard(right)) {
                    visited.add(right);
                    g.addEdge(right, board, counter, count);
                    BuildTree(right, goal, counter);
                //}
            }

            if(up!=null) {
                //if (!hasBoard(up)) {
                    visited.add(up);
                    g.addEdge(up, board, counter, count);
                    BuildTree(up, goal, counter);
               // }
            }


            if(down!=null) {
                //if (!hasBoard(down)) {
                    visited.add(down);
                    g.addEdge(down, board, counter, count);
                    BuildTree(down, goal, counter);
                //}
            }

        }
    }

    public boolean hasBoard(Board b1){
        for(int i=0;i<visited.size();i++){
            if(visited.get(i).compare(b1)==true) return true;
        }
        return false;
    }

    public int randGen(Board b){
        int twoChoice[] = new int[2];
        int fourChoice[] = new int[4];
        int rTwo = ThreadLocalRandom.current().nextInt(0, 2);
        int rFour = ThreadLocalRandom.current().nextInt(0, 4);
        //TOP-LEFT
        if(b.empJ!=0 && b.empI!=0) {
            if (b.empI == 0 && b.empJ == 0) {
                twoChoice[0] = 1;
                twoChoice[1] = 3;
            }
            //TOP-RIGHT
            else if (b.empJ == b.getLength() - 1 && b.empI == 0) {
                twoChoice[0] = 1;
                twoChoice[1] = 2;
            }
            //BOTTOM-RIGHT
            else if (b.empI == b.getLength() - 1 && b.empJ == b.getLength() - 1) {
                twoChoice[0] = 0;
                twoChoice[1] = 2;
            }
            //BOTTOM-lEFT
            else if (b.empJ == 0 && b.empI == b.getLength() - 1) {
                twoChoice[0] = 0;
                twoChoice[1] = 3;
            }
            //UP OR DOWN
            else if (b.empJ == 0) {
                twoChoice[0] = 0;
                twoChoice[1] = 1;
            }
            //LEFT OR RIGHT
            else if (b.empI == 0) {
                twoChoice[0] = 2;
                twoChoice[1] = 3;
            }

            return twoChoice[rTwo];
        }
        else{
            fourChoice[0]= 0;
            fourChoice[1] = 1;
            fourChoice[2] = 2;
            fourChoice[3] = 3;
            return fourChoice[rFour];
        }
    }


    public Board getLeft(Board b){
        if(b.empJ==0){
            return null;
        }
        else{
           return shiftTiles(b,0);
        }
    }

    public Board getRight(Board b){
        if(b.empJ==b.getLength()-1){
            return null;
        }
        else{
            return shiftTiles(b,1);
        }
    }

    public Board getDown(Board b){
        if(b.empI==b.getLength()-1){
            return null;
        }
        else{
            return shiftTiles(b,2);
        }
    }

    public Board getUp(Board b){
        if(b.empI==0){
            return null;
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
        newBoard.getEmpty();
        return newBoard;
    }


}
