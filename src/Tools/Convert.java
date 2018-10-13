/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Model.ACK;
import Model.Message;

/**
 *
 * @author Marcelo
 */
public class Convert {
    
    public static String MessageToJson(Message message){
        String json = message.getId()+"|"+message.getTime();
        return json;
    }
    
    //Obtém o Id da mensagem e seu clock
    public static Message JsonToMessage(String json){
        String[] str = json.split("|");
        Message message = new Message();
        message.setId(str[0]);
        message.setTime(Integer.parseInt(str[2]));
        return message;
    }
    
    //Cria a mensagem de ACK
    public static String ACKToJson(ACK ack){
        String json = ack.getId()+"|"+ack.getTime()+"|"+ack.getProcess();
        return json;
    }
    
    //Obtém Id, clock e processo que enviou o ACK
    public static ACK JsonToACK(String json){
        String[] str = json.split("|");
        ACK ack = new ACK();
        ack.setId(str[0]);
        ack.setTime(Integer.parseInt(str[2]));
        ack.setProcess(Integer.parseInt(str[4]));
        return ack;
    }
    
    //Identifica se é uma mensagem de ACK
    public static boolean isACK(String json){
        String[] str = json.split("|");
        if(str.length>3){
            return true;
        }
        return false;
    }
}
