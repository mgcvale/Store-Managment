package com.github.mgcvale.storemanagment.gui.crudpanels;

import com.github.mgcvale.storemanagment.data.HashMapChangeListener;

import javax.swing.*;
import java.util.HashMap;

public class UpdatePanel extends JPanel {
    HashMap<Integer, String[]> map;
    HashMapChangeListener hashMapChangeListener;
    public void setMap(HashMap<Integer, String[]> map){
        this.map = map;
    }
    public void addHashMapChangeListener(HashMapChangeListener hashMapChangeListener){
        this.hashMapChangeListener = hashMapChangeListener;
    }
    public UpdatePanel(HashMapChangeListener hashMapChangeListener){
        this.hashMapChangeListener = hashMapChangeListener;
    }
}
