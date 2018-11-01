/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools - Templates
 * and open the template in the editor.
 */
package Tools;

import Model.Message;

/**
 *
 * @author Marcelo
 */
public class Convert {
    
    public static String MessageToJson(Message message){
        int[] minCost = message.getMinCost();
        String json = Integer.toString(message.getSourceID())+"-"+Integer.toString(minCost[0])+"-"+Integer.toString(minCost[1])+"-"+Integer.toString(minCost[2])+"-"+Integer.toString(minCost[3]);
        return json;
    }
    
    //Obtém o Id da mensagem e seu clock
    public static Message JsonToMessage(String json){
        String[] str = json.split("-");
        int[] minCost = {Integer.parseInt(str[1]), Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4])};
        Message message = new Message();
        
        message.setSourceID(Integer.parseInt(str[0]));
        message.setMinCost(minCost);
                
        return message;
    }
}
