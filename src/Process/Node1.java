/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;
import Listener.ServerManagerListener;
import Manager.ConnectionManager;
import Manager.MessageManager;
import Model.Message;
import java.util.Scanner;

/**
 *
 * @author Marcelo
 */
public class Node1 {
    
    static int nodeID = 1;

    public static void main(String[] args) throws InterruptedException {
        Scanner keyboard = new Scanner(System.in);
        int serverPort = 2001;
        int[] connectionPorts = {2000, 2002};
        int[] costs = {1, 0, 1, 999};

        MessageManager msgManager = new MessageManager(nodeID);
        
        ConnectionManager manager = new ConnectionManager(serverPort);
        
        manager.setServerManagerListener(new ServerManagerListener() {
            boolean updated = false;
            
            @Override
            public void messageReceived(Message message) {
                // recebeu uma mensagem
                System.out.println("\n[Log] Recebeu o vetor de custo mínimo do nó " + message.getSourceID());
                msgManager.addMessage(message);
                
                do {
                    updated = tableUpdate(costs, msgManager.getMessage());
                    msgManager.remove();
                    
                    if (updated) {
                        updated = false;

                        try {
                            Message msg = new Message(nodeID, costs);
                            manager.sendMessageToServer(msg);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } while (!msgManager.isEmpty());
            }
        });
        
        System.out.println("Iniciou nó 1 na porta "+serverPort);
        System.out.print("Pressione 1 para iniciar as conexões:\n>> ");
        keyboard.nextInt();
     
        for (int i = 0; i < connectionPorts.length; i++) {
            manager.addConnection(connectionPorts[i]);
            System.out.println("Criou conexão com o nó "+ (connectionPorts[i] % 2000));
        }
        
        System.out.println("Inicializando a tabela de distâncias do nó 1.");
        System.out.print("Pressione 1 para iniciar a atualização das tabelas de distância:\n>> ");
        
        keyboard.nextInt();

        System.out.println("["+costs[0]+" "+costs[1]+" "+costs[2]+" "+costs[3]+"]");
        
        manager.close();
    }
    
    private static boolean tableUpdate (int[] destCosts, Message msg) {
        int[] sourceCosts = msg.getMinCost();
        int sourceID = msg.getSourceID();
        boolean up = false;
        
        for (int i = 0; i < destCosts.length; i++) {            
          if ((sourceCosts[i] != 0) && (destCosts[i] != 0)) {
                if ((sourceCosts[i]+destCosts[sourceID]) < destCosts[i]) {
                    up = true;
                    destCosts[i] = sourceCosts[i] + destCosts[sourceID];                
                }
            }
        }
        
        if (up) {
            System.out.println("A tabela do nó "+nodeID+" foi alterada.");
        } else {
            System.out.println("A tabela do nó "+nodeID+" não foi alterada.");
        }
        
        return up;
    }
}