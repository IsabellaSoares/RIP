/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Listener.ServerListener;
import Listener.ServerManagerListener;
import Model.ACK;
import Model.Client;
import Model.Message;
import Model.Server;

/**
 *
 * @author Isabella
 */
public class ConnectionManager {
    private Client client = null;
    private Server server;
    private ServerManagerListener serverManagerListener;
    
    public ConnectionManager(int serverPort){
        // cria cliente
        client = new Client();
        
        // cria servidor
        this.server = new Server(serverPort);
        this.server.setServerListener(new ServerListener() {
            @Override
            public void receivePacket(String json) {
               if(Convert.isACK(json)){
                   serverManagerListener.ACKReceived(Convert.JsonToACK(json));
               } else {
                   serverManagerListener.messageReceived(Convert.JsonToMessage(json));
               }
            }
        });
    }

    public void setServerManagerListener(ServerManagerListener serverManagerListener) {
        this.serverManagerListener = serverManagerListener;
    }
    
    public void addConnection(int port){
        client.addConnection(port);
    }
    
    public void sendMessageToServer(Message message) throws Exception{
        client.outToServer(Convert.MessageToJson(message));
    }
    
    public void sendACKToServer(ACK ack){
    
    }
    
    public void close(){
        try {
            this.client.close();
            this.server.close();
        } catch (Exception e) {
        }
    }
}
