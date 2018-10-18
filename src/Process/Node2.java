/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

/**
 *
 * @author Marcelo
 */
public class Node2 {
    public static void main(String[] args) {
        int serverPort = 2002;
        int[] connectionPorts = {2000, 2001, 2003};
        int[] costs = {3, 1, 0, 2};
        int table[][] = {{3, 999, 999, 999}, {999, 1, 999, 999}, {999, 999, 0, 999}, {999, 999, 999, 2}};
        
        Process p = new Process();
        p.exec(2, serverPort, connectionPorts, table, costs);
        //exec(pid do processo, porta do servidor, vetor de conexões)
    }
}
