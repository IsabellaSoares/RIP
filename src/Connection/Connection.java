/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Listener.ServerListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Marcelo
 */
public class Connection {
    
    private Socket connectionSocket;
    private Thread thread;
    private ServerListener serverListener;
    
    public Connection(Socket socket, ServerListener listener){
        this.connectionSocket = socket;
        this.serverListener = listener;
    }
    
    public void start() throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try{
                    while(true){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                        String json = reader.readLine();
                        if(json==null || json.length()==0){
                            close();
                            return;
                        }
                        if(serverListener!=null){
                            serverListener.receivePacket(json);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        thread = new Thread(runnable);
        thread.start();
    }
    
    public void close() throws Exception{
        if(thread!=null){
            thread.interrupt();
            thread.stop();
            connectionSocket.close();
        }
    }
}
