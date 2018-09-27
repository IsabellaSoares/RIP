/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Listener.ServerListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Marcelo
 */
public class Server {
    
//    public static void main (String Args[]) throws Exception {
//        ServerSocket server = new ServerSocket(6789);
//        while(true){
//            Socket socket = server.accept();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            String msg = reader.readLine();
//            System.out.println("msg: " + msg);
//        }
//    }
    
    private ServerSocket server;
    private int port;
    private ServerListener serverListener;
    
    public Server(int port){
        this.port = port;
        startServer();
    }
    
    private void startServer(){
        try{
            server = new ServerSocket(port);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        while(true){
                            Socket socket = server.accept();
                            execute(socket);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {
            server = null;
            e.printStackTrace();
        }
    }

    public void setServerListener(ServerListener serverListener) {
        this.serverListener = serverListener;
    }
    
    public void close() throws Exception{
        server.close();
    }
    
    public void execute(Socket socket){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    while(true){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        //String json = reader.readLine();
                        System.out.println("[server] antes: " + socket.getPort());
                        String json = reader.readLine();
                        System.out.println("[server] received: " + json);
                        if(serverListener!=null){
                            serverListener.receivePacket(json);
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
