/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Tools.Convert;
import Listener.ServerListener;
import Listener.ServerManagerListener;
import Connection.Client;
import Model.Message;
import Connection.Server;

/**
 *
 * @author Isabella
 */
public class ConnectionManager {
    private Client client = null;
    private Server server;
    private ServerManagerListener serverManagerListener;
    
    public ConnectionManager(int serverPort){
        //Cria cliente
        client = new Client();
        
        //Cria servidor
        this.server = new Server(serverPort);
        
        this.server.setServerListener(new ServerListener() {
            @Override
            public void receivePacket(String json) {
                serverManagerListener.messageReceived(Convert.JsonToMessage(json));
            }
        });
    }

    public void setServerManagerListener(ServerManagerListener serverManagerListener) {
        this.serverManagerListener = serverManagerListener;
    }
    
    //Adiciona as conexões aos outros processos
    public void addConnection(int port){
        client.addConnection(port);
    }
    
    //Envia mensagem para o servidor
    public void sendMessageToServer(Message message) throws Exception{
        client.outToServer(Convert.MessageToJson(message));
    }
    
    //Encerra o processo e fecha as conexões
    public void close(){
        try {
            this.client.close();
            this.server.close();
        } catch (Exception e) {
        }
    }
}
