package com.company;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Stack;

/**
 * Created by ryan on 12/11/16.
 */
public class Board {
    private static final String NEWLINE = System.getProperty("line.separator");
    String[][] tiles;
    Stack<String> nums = new Stack<String>();
    int[]  numsHit;
    public int empI;
    public int empJ;

    public Board(int n1, int n2){
        tiles = new String[n1][n2];
        numsHit = new int[(n2*n2)-1];
        generateNums(n1,n2);
        populateBoard();
    }

    public Board(String[] n){
        tiles = new String[3][3];
        int count = 0;
        for(int i =0;i<3;i++){
            for(int j=0;j<3;j++){
                tiles[i][j] = n[count].toString();
                count++;
            }

        }

        getEmpty(this);
    }

    public Board(){

    }

    public void setTiles(String[][] s){
        tiles = s;
    }



    public int getNonHitNum(){
        for(int i=0;i<numsHit.length;i++){
            if(numsHit[i]==0){
                return i;
            }
        }
        return -1;
    }



    public boolean checkContains(int num){
        if(numsHit[num]==1){
            return true;
        }

        return false;
    }

    public void updateContains(int num){
        numsHit[num] = 1;
    }

    public int randomNum(int max){
        return ThreadLocalRandom.current().nextInt(0, max + 0);
    }

    public void generateNums(int n1,int n2){
        int max = (n1*n2) -1;
        int randNum;
        for(int i=0;i<max;i++) {
            randNum = randomNum(max);
            boolean containsNum = checkContains(randNum);
            while(containsNum==true){
                randNum = getNonHitNum();
                containsNum = checkContains(randNum);
            }

            nums.push(Integer.toString(randNum+1));
            updateContains(randNum);
        }
    }

    public void populateBoard(){
        for(int i=0;i< tiles.length;i++){
            int k=0;
            for(int j=0;j<tiles.length;j++){
                if(i==0 && j==0){tiles[0][0]= " ";}
                else{tiles[i][j] = nums.pop();}
                k++;
            }
        }
    }

    public String[][] getTiles(){
        return tiles;
    }

    public int getLength(){
        return tiles.length;
    }

    public boolean compare(Board b){
        String[][] compT = b.getTiles();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(compT[i][j]!=this.tiles[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public StringBuilder printBoard(){
        StringBuilder s = new StringBuilder();
        for(int i=0;i<tiles.length;i++){
            for(int j=0;j<tiles.length;j++){
                s.append("["+tiles[i][j]+"]");
            }
            s.append(NEWLINE);
        }
        s.append(NEWLINE);
        return s;
    }

    public void getEmpty(Board b){
        for(int i=0;i<b.getLength();i++) {
            for (int j = 0; j < b.getLength(); j++) {
                if (b.getTiles()[i][j] == " ") {
                    this.empI = i;
                    this.empJ = j;
                }
            }
        }
    }




}
