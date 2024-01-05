package com.github.mgcvale.storemanagment.gui.crudpanels;

import com.github.mgcvale.storemanagment.data.HashMapChangeListener;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class DeletePanel extends JPanel {
    HashMapChangeListener hashMapChangeListener;
    HashMap<Integer, String[]> map;
    public void setMap(HashMap<Integer, String[]> map){
        this.map = map;
    }
    public void addHashMapChangeListener(HashMapChangeListener hashMapChangeListener){
        this.hashMapChangeListener = hashMapChangeListener;
    }
    public DeletePanel(HashMapChangeListener hashMapChangeListener){
        this.hashMapChangeListener = hashMapChangeListener;
        setLayout(new GridLayout());
    }
}
