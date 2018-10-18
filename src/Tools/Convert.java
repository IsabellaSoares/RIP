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
        int[] minCost = message.getMinCost();
        String json = message.getSourceID()+"|"+minCost[0]+"|"+minCost[1]+"|"+minCost[2]+"|"+minCost[3];
        return json;
    }
    
    //Obtém o Id da mensagem e seu clock
    public static Message JsonToMessage(String json){
        String[] str = json.split("|");        
        int[] minCost = {Integer.parseInt(str[2]), Integer.parseInt(str[4]), Integer.parseInt(str[6]), Integer.parseInt(str[8])};
        Message message = new Message();
        
        message.setSourceID(Integer.parseInt(str[0]));
        message.setMinCost(minCost);
                
        return message;
    }
    
//    //Cria a mensagem de ACK
//    public static String ACKToJson(ACK ack){
//        String json = ack.getId()+"|"+ack.getTime()+"|"+ack.getProcess();
//        return json;
//    }
//    
//    //Obtém Id, clock e processo que enviou o ACK
//    public static ACK JsonToACK(String json){
//        String[] str = json.split("|");
//        ACK ack = new ACK();
//        ack.setId(str[0]);
//        ack.setTime(Integer.parseInt(str[2]));
//        ack.setProcess(Integer.parseInt(str[4]));
//        return ack;
//    }
//    
//    //Identifica se é uma mensagem de ACK
//    public static boolean isACK(String json){
//        String[] str = json.split("|");
//        if(str.length>3){
//            return true;
//        }
//        return false;
//    }
}
