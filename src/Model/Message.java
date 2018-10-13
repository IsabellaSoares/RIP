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
public class Message {
    private int sourceID;
    private int destID;
    private int[] minCost = new int[4];

    public Message(int sourceID, int destID, int[] minCost){
        this.sourceID = sourceID;
        this.destID = destID;
        this.minCost = minCost;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public int getDestID() {
        return destID;
    }

    public void setDestID(int destID) {
        this.destID = destID;
    }

    public int[] getMinCost() {
        return minCost;
    }

    public void setMinCost(int[] minCost) {
        this.minCost = minCost;
    }   
}
