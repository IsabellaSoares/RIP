/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import Listener.ServerManagerListener;
import Manager.ConnectionManager;
import Model.ACK;
import Model.Estrutura;
import Model.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class Process {
    private static boolean updated = false;
    
    public void exec(int pid, int serverPort, int[] connectionPorts, int[][] table, int[] costs) {
        
        Scanner keyboard = new Scanner(System.in);
        List<Estrutura> messageList = new ArrayList<Estrutura>(); //Lista de mensagens
                
        ConnectionManager manager = new ConnectionManager(serverPort); //Gerenciador de conexão
        
        manager.setServerManagerListener(new ServerManagerListener() {
            @Override
            public void messageReceived(Message message) {
                // recebeu uma mensagem
                System.out.println("\n[Log] Recebeu o vetor de custo mínimo do nó " + message.getSourceID());
                tableUpdate(costs, table, message);
            }
        });
        
        //Inicia o servidor do processo e aguarda que todos os outros processos sejam iniciados
        System.out.println("Iniciou nó "+pid+" na porta "+serverPort);
        System.out.print("Pressione 1 para iniciar as conexões:\n>> ");
        keyboard.next();
        keyboard.nextLine();
        
        //Cria conexão com os outros processos e com ele mesmo
        for (int i = 0; i < connectionPorts.length; i++) {
            manager.addConnection(connectionPorts[i]);
            System.out.println("Criou conexão com o nó "+ (connectionPorts[i] % 2000));
        }
        
        System.out.println("Inicializando a tabela de distâncias do nó "+pid);
        System.out.print("Pressione 1 para iniciar a atualização das tabelas de distância:\n>>");
        
        int option = 0;
        
        do {
            option = keyboard.nextInt();
            
            if (option != 0) {
                try {
                    Message msg = new Message(pid, costs);
                    manager.sendMessageToServer(msg);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } while (option != 0);
        
        manager.close();
        return;
    }
    
    private static void printdt (int table[][]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(String.format("%03d ", table[i][j]));
            }
            
            System.out.println("");
        }
    }
    
    private synchronized static void tableUpdate (int[] destCosts, int[][] destTable, Message msg) {
        int i = 0;
        int[] sourceCosts = msg.getMinCost();
        int sourceID = 0;
        int destID = 0;
        boolean updated = false;
        
        for (i = 0; i < destCosts.length; i++) {            
            if (sourceCosts[i] == 0) {
                sourceID = i;
            }
            
            if (destCosts[i] == 0) {
                destID = i;
            }
            
            if ((sourceCosts[i] != 0) && (destCosts[i] != 0)) {
                if (sourceCosts[i] < destCosts[i]) {
                    updated = true;
                    destCosts[i] = sourceCosts[i] + destCosts[sourceID];
                    destTable[i][sourceID] = destCosts[i];                    
                }
            }
        }
        
        if (updated) {
            System.out.println("A tabela do nó "+destID+" foi alterada");
            printdt(destTable);
        }
    }            
}
