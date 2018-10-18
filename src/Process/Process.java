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
    //private static int clock = 1;
    //private static int table[][];
    
    public void exec(int pid, int serverPort, int[] connectionPorts, int[][] table, int[] costs) {
        
        Scanner keyboard = new Scanner(System.in);
        List<Estrutura> messageList = new ArrayList<Estrutura>(); //Lista de mensagens
                
        ConnectionManager manager = new ConnectionManager(serverPort); //Gerenciador de conexão
        
        manager.setServerManagerListener(new ServerManagerListener() {
            @Override
            public void messageReceived(Message message) {
                // recebeu uma mensagem
                System.out.println("[Log] Recebeu o vetor de custo mínimo do nó " + message.getSourceID());
                tableUpdate(costs, table, message);
//                addMessagem(messageList, message); //Adiociona mensagem na lista
//                sendACK(manager, pid, message); //Envia ACK
            }

//            @Override
//            public void ACKReceived(ACK ack) {
//                //Recebeu um ACK
//                System.out.println("[Log] P"+pid+" - recebeu um ack de P" + ack.getProcess());
//                addACK(messageList, ack); //Adiciona ACK na lista
//                updateList(messageList);
//            }
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
        
//        printdt(table);
        System.out.println("Inicializando a tabela de distâncias do nó "+pid);       
        System.out.print("Pressione ENTER para iniciar a atualização das tabelas de distância: ");
        
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
        
        //keyboard.nextLine();
//        System.out.print("Supimpa\n");
        
        //Aqui troca as mensagens ;D
        
//        Message msg = new Message(pid, costs);
//        try {
//            manager.sendMessageToServer(msg);
//        } catch (Exception ex) {
//            Logger.getLogger(Process.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        manager.close();
//        System.out.println("Finalizou P"+pid);
        return;
    //}
//    
//    public static void sendACK(ConnectionManager cm, int pid, Message message){
//        try {
//            ACK ack = new ACK();
//            ack.setId(message.getId());
//            ack.setTime(message.getTime());
//            ack.setProcess(pid);
//            cm.sendACKToServer(ack);
//        } catch (Exception e) {
//        }
//    }
//    
//    public static synchronized void addMessagem(List<Estrutura> lista, Message message){
//        boolean addedToList = false;
//        
//        for(int i=0; i<lista.size() && !addedToList; i++){
//            Estrutura e = lista.get(i);
//            if(e.getTime()!=null && message.getTime() > e.getTime()){
//                Estrutura estrutura = new Estrutura();
//                estrutura.setMessage(message);
//                lista.add(i, estrutura);
//                addedToList = true;
//                System.out.println("[Log] addMessagem - criou novo item ("+message.getId()+", "+message.getTime()+") na posicao " + i);
//            } else if (e.getTime()!=null && e.getId()!=null && e.getId().equals(message.getId()) && message.getTime()==e.getTime()){
//                e.setMessage(message);
//                addedToList = true;
//                System.out.println("[Log] addMessagem - setou mensagem ("+message.getId()+", "+message.getTime()+") na posicao " + i);
//            }
//        }
//        
//        if(!addedToList){
//            Estrutura e = new Estrutura();
//            e.setMessage(message);
//            lista.add(e);
//            System.out.println("[Log] addMessagem - criou novo item ("+message.getId()+", "+message.getTime()+") no final");
//        }
//    }
//    
//    public static synchronized void addACK(List<Estrutura> lista, ACK ack){
//        boolean addedToList = false;
//        
//        for(int i=0; i<lista.size() && !addedToList; i++){
//            Estrutura e = lista.get(i);
//            if(e.getTime()!=null && ack.getTime() > e.getTime()){
//                Estrutura estrutura = new Estrutura();
//                estrutura.addACK(ack);
//                lista.add(i, estrutura);
//                addedToList = true;
//                System.out.println("[Log] addACK - criou novo item ("+ack.getId()+", "+ack.getTime()+") na posicao " + i);
//            } else if (e.getTime()!=null && e.getId()!=null && e.getId().equals(ack.getId()) && ack.getTime()==e.getTime()){
//                e.addACK(ack);
//                addedToList = true;
//                System.out.println("[Log] addACK - adicionou Ack ("+ack.getId()+", "+ack.getTime()+") na posicao " + i);
//            }
//        }
//        
//        if(!addedToList){
//            Estrutura e = new Estrutura();
//            e.addACK(ack);
//            lista.add(e);
//            System.out.println("[Log] addACK - criou novo item ("+ack.getId()+", "+ack.getTime()+") no final");
//        }
//    }
//    
//    public static synchronized void updateList(List<Estrutura> lista){
//        try{
//            boolean removed = true;
//            for(int i=(lista.size()-1); i>=0 && removed; i--){
//                Estrutura e = lista.get(i);
//                if(e.getMessage()!=null && e.getNumbersOfACK()==3){
//                    // remove message
//                    clock = e.getTime();
//                    System.out.println("Removeu o elemento "+i+" da lista");
//                    lista.remove(i);
//                } else {
//                    removed = false;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Deu erro updateList()!");
//        }
    }
    
    private static void printdt (int table[][]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //String.format("%0.2d", table[i][j]);
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
        
        for (i = 0; i < destCosts.length; i++) {
            if (sourceCosts[i] == 0) {
                sourceID = i;
            }
            
            if (destCosts[i] == 0) {
                destID = i;
            }
            
            if ((sourceCosts[i] != 0) && (destCosts[i] != 0)) {
                if (sourceCosts[i] < destCosts[i]) {
                    destCosts[i] = sourceCosts[i] + destCosts[sourceID];
                    destTable[i][sourceID] = destCosts[i];
                    System.out.println("A tabela do nó "+destID+" foi alterada");
                    printdt(destTable);
                }
            }
        }
    }
            
}
