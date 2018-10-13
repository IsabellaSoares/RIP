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
public class Node3 {
    public static void main(String[] args) {
        int serverPort = 2003;
        int[] connectionPorts = {2000, 2002};
        int[] costs = {7, 999, 2, 0};
        int table[][] = {{7, 999, 999, 999}, {999, 999, 999, 999}, {999, 999, 2, 999}, {999, 999, 999, 0}};
        
        Process p = new Process();
        p.exec(3, serverPort, connectionPorts, table);
        //exec(pid do processo, porta do servidor, vetor de conexões)
    }
}
