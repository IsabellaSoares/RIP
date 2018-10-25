/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Model.Message;
//import Model.Structure;
//import static Process.Process.clock;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Isabella
 */
public class MessageManager {
    
    private int pid;
    private List<Message> messageList; //Lista de mensagens
    
    public MessageManager(int pid){
        this.pid = pid;
        this.messageList = new ArrayList<Message>();
    }
    
    public synchronized void addMessage(Message message){
        messageList.add(message);
    }
    
    public synchronized void printList(){
        for(int i=0; i<messageList.size(); i++){
            Message structure = messageList.get(i);
            System.out.println("["+i+"] pid: "+structure.getSourceID());
        }
    }
    
    public Message getMessage () {
        return messageList.get(0);
    }
    
    public boolean isEmpty () {
        if (messageList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public void remove () {
        messageList.remove(0);
    }
}
