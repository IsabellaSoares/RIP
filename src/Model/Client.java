/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public class Client {
//    public static void main (String args[]) throws Exception {
//        Socket client = new Socket("localhost", 6789);
//        DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
//        String msg = "mensagem do cliente!";
//        outToServer.writeBytes(msg);
//        client.close();
//    }
    
    private List<Socket> socketList = null;
    
    private int port;
    public Client(){
        socketList = new ArrayList<>();
    }
    
    public void addConnection (int port) {
        try{
            Socket client = new Socket("localhost", port);
            socketList.add(client);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void outToServer (String json) throws Exception {
        for(Socket client : socketList){
            DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());
            if(client!=null && outToServer!=null){
                try{
                    outToServer.writeBytes(json + 'n');
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            /*for(Socket s:socketList){
                ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                out.writeObject(json);
            }*/
        }
    }
    
    public void close() throws Exception {
        for(Socket client : socketList){
            client.close();
        }
    }
}