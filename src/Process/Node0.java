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
public class Node0 {

    public static void main(String[] args) {
        int serverPort = 2000;
        int[] connectionPorts = {2001, 2002, 2003};
        int[] costs = {0, 1, 3, 7};
        int table[][] = {{0, 999, 999, 999}, {999, 1, 999, 999}, {999, 999, 3, 999}, {999, 999, 999, 7}};
        
        Process p = new Process();
        p.exec(0, serverPort, connectionPorts, table);
        //exec(pid do processo, porta do servidor, vetor de conexões)
    }
}
