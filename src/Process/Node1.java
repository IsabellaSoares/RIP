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
public class Node1 {

    public static void main(String[] args) {
        int serverPort = 2001;
        int[] connectionPorts = {2000, 2002};
        int[] costs = {1, 0, 1, 999};
        int table[][] = {{1, 999, 999, 999}, {999, 0, 999, 999}, {999, 999, 1, 999}, {999, 999, 999, 999}};
        
        Process p = new Process();
        p.exec(1, serverPort, connectionPorts, table, costs);
        //exec(pid do processo, porta do servidor, vetor de conexões)
    }
}
