package com.company;


import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] b = {"1", " ", "3", "4", "2", "5", "7", "8", "6"};
        String[] g = {"1","2","3","4","5","6","7","8",""};
        Board init = new Board(b);
        Board goal = new Board(g);
        System.out.println(init.getLength());
        Solver s = new Solver(init, goal);
        s.BuildTree();

//        Graph g = new Graph(5);
//        g.addEdge(b0, b1,0,1);
//        g.addEdge(b0, b4,0,4);
//        g.addEdge(b1, b3,1,3);
//        g.addEdge(b2,b3,2,3);

        //System.out.println(g.toString());
    }
}
