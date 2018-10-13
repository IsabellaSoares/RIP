/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public class Estrutura {
    private String id = null; //Id da mensagem e do ACK
    private Integer time = null; //Clock da mensagem e do ACK
    private Message message; //Mensagem
    private List<ACK> ackList; //Lista de ACKs da mensagem
    
    public Estrutura(){
        this.ackList = new ArrayList<>();
    }

    public Message getMessage() {
        return message;
    }
    
    public void setMessage(Message message) {
        this.message = message;
        
        if(this.id==null){
            this.id = message.getId();
        }
        if(this.time==null){
            this.time = message.getTime();
        }
    }

    public Integer getTime() {
        return time;
    }

    public String getId() {
        return id;
    }
    
    public void addACK(ACK ack){
        this.ackList.add(ack);
        
        if(this.id==null){
            this.id = ack.getId();
        }
        if(this.time==null){
            this.time = ack.getTime();
        }
    }
    
    public int getNumbersOfACK(){
        return this.ackList.size();
    }
}
