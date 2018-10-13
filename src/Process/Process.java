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

/**
 *
 * @author Marcelo
 */
public class Process {
    private static int clock = 1;
    //private static int table[][];
    
    public void exec(int pid, int serverPort, int[] connectionPorts, int table[][]) {
        
        Scanner keyboard = new Scanner(System.in);
        List<Estrutura> messageList = new ArrayList<Estrutura>(); //Lista de mensagens
                
        ConnectionManager manager = new ConnectionManager(serverPort); //Gerenciador de conexão
        
//        manager.setServerManagerListener(new ServerManagerListener() {
//            @Override
//            public void messageReceived(Message message) {
//                // recebeu uma mensagem
//                System.out.println("[Log] P"+pid+" - recebeu uma mensagem: " + message.getId());
//                addMessagem(messageList, message); //Adiociona mensagem na lista
//                sendACK(manager, pid, message); //Envia ACK
//            }
//
//            @Override
//            public void ACKReceived(ACK ack) {
//                //Recebeu um ACK
//                System.out.println("[Log] P"+pid+" - recebeu um ack de P" + ack.getProcess());
//                addACK(messageList, ack); //Adiciona ACK na lista
//                updateList(messageList);
//            }
//        });
        
        //Inicia o servidor do processo e aguarda que todos os outros processos sejam iniciados
        System.out.println("Iniciou nó "+pid+" na porta "+serverPort);
        System.out.print("Pressione 1 para iniciar as conexões:\n>> ");
        keyboard.next();
        
        //Cria conexão com os outros processos e com ele mesmo
        for (int i = 0; i < connectionPorts.length; i++) {
            manager.addConnection(connectionPorts[i]);
            System.out.println("Criou conexão com o nó "+ (connectionPorts[i] % 2000));
        }
        
        printdt(table);
        
//        manager.addConnection(connectionPorts[0]);
//        System.out.println("Criou conexão com a porta "+ connectionPorts[0]);
//        manager.addConnection(connectionPorts[1]);
//        System.out.println("Criou conexão com a porta " + connectionPorts[1]);
//        manager.addConnection(serverPort);
//        System.out.println("Criou conexão com a porta " + serverPort); 
       
//        System.out.print("\nMenu:\n 0 - Finaliza Processo\n 1 - Envia Mensagem\n 2 - Ver Clock\n");
        
//        int option = 0;
//        
//        do{
//            option = keyboard.nextInt();
//            if(option!=0){
//                switch(option){
//                    case 1 : 
//                        //Envia mensagem para o servidor
//                        try{
//                            Message m = new Message(String.valueOf(pid), ++clock);
//                            manager.sendMessageToServer(m);
//                        } catch (Exception e){
//                            e.printStackTrace();
//                        }
//                        break;
//                    case 2:
//                        //Exibe o valor de clock
//                        System.out.println("Clock: " + clock);
//                        break;
//                }
//            }
//        } while(option!=0);
//        
//        manager.close();
//        System.out.println("Finalizou P"+pid);
//        return;
//    }
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
                System.out.print(String.format("%0.2d ", table[i][j]));
            }
            
            System.out.println("");
        }
    }
}
