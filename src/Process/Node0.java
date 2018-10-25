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
public class Node0 {

    public static void main(String[] args) throws InterruptedException {
        Scanner keyboard = new Scanner(System.in);
        int serverPort = 2000;
        int[] connectionPorts = {2001, 2002, 2003};
        int[] costs = {0, 1, 3, 7};
        int table[][] = {{0, 999, 999, 999}, {999, 1, 999, 999}, {999, 999, 3, 999}, {999, 999, 999, 7}};
        MessageManager msgManager = new MessageManager(0);
        
        ConnectionManager manager = new ConnectionManager(serverPort);
        
        manager.setServerManagerListener(new ServerManagerListener() {
            boolean updated = false;
            
            @Override
            public void messageReceived(Message message) {
                // recebeu uma mensagem
                System.out.println("\n[Log] Recebeu o vetor de custo mínimo do nó " + message.getSourceID());
                msgManager.addMessage(message);
                
                do {
                    while (manager.getUpdating() == true) {}
                    manager.setUpdating(true);
                    updated = tableUpdate(costs, table, msgManager.getMessage());
                    manager.setUpdating(false);
                    msgManager.remove();
                    
                    if (updated) {
                        updated = false;

                        try {
                            Message msg = new Message(0, costs);
                            manager.sendMessageToServer(msg);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } while (!msgManager.isEmpty());
            }
        });
        
        System.out.println("Iniciou nó 0 na porta "+serverPort);
        System.out.print("Pressione 1 para iniciar as conexões:\n>> ");
        keyboard.nextInt();
     
        for (int i = 0; i < connectionPorts.length; i++) {
            manager.addConnection(connectionPorts[i]);
            System.out.println("Criou conexão com o nó "+ (connectionPorts[i] % 2000));
        }
        
        System.out.println("Inicializando a tabela de distâncias do nó 0.");
        System.out.print("Pressione 1 para iniciar a atualização das tabelas de distância:\n>> ");
        
        keyboard.nextInt();
        
        try {
            Message msg = new Message(0, costs);
            manager.sendMessageToServer(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            System.out.println("Deu erro!");
        }
        msgManager.printList();
        manager.close();
    }
    
    private static void printdt (int table[][]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(String.format("%03d ", table[i][j]));
            }
            
            System.out.println("");
        }
    }
    
    private static boolean tableUpdate (int[] destCosts, int[][] destTable, Message msg) {
        int i = 0;
        int[] sourceCosts = msg.getMinCost();
        int sourceID = 0;
        int destID = 0;
        boolean up = false;
        
        for (i = 0; i < destCosts.length; i++) {            
            if (sourceCosts[i] == 0) {
                sourceID = i;
            }
            
            if (destCosts[i] == 0) {
                destID = i;
            }
            
            if ((sourceCosts[i] != 0) && (destCosts[i] != 0)) {
                if (sourceCosts[i] < destCosts[i]) {
                    up = true;
                    destCosts[i] = sourceCosts[i] + destCosts[sourceID];
                    destTable[i][sourceID] = destCosts[i];                    
                }
            }
        }
        
        if (up) {
            System.out.println("A tabela do nó "+destID+" foi alterada.");
            //printdt(destTable);
        } else {
            System.out.println("A tabela do nó "+destID+" não foi alterada.");
        }
        
        return up;
    }
}
