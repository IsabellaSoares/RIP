/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Marcelo
 */
public class ACK {
    private String id; //ID da mensagem a qual se refere o ACK
    private int time; //Clock
    private int process; //Processo que está enviando o ACK

    //Retorna ID da mensagem
    public String getId() {
        return id;
    }

    //Define ID da mensagem
    public void setId(String id) {
        this.id = id;
    }

    //Retorna clock do ACK
    public int getTime() {
        return time;
    }

    //Define clock do ACK
    public void setTime(int time) {
        this.time = time;
    }

    //Retorna o processo que está enviando o ACK
    public int getProcess() {
        return process;
    }

    //Define o processo que está enviando o ACK
    public void setProcess(int process) {
        this.process = process;
    }
    
}
