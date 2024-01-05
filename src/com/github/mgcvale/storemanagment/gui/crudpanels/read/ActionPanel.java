package com.github.mgcvale.storemanagment.gui.crudpanels.read;

import com.github.mgcvale.storemanagment.data.HashMapModificationRequestListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPanel extends JPanel {
    JButton delete, edit, sell, updateStock;
    HashMapModificationRequestListener hashMapModificationRequestListener;

    public ActionPanel(){
        delete = new JButton("Deletar");
        edit = new JButton("Editar");
        sell = new JButton("Vender");
        updateStock = new JButton("Atualizar Estoque");

        setLayout(new GridLayout(1, 3, 5, 0));
        add(delete);
        add(edit);
        add(sell);
        add(updateStock);

        //action listeners
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hashMapModificationRequestListener.modificationRequested(HashMapModificationRequestListener.DELETE_REQUESTED);
            }
        });
        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hashMapModificationRequestListener.modificationRequested(HashMapModificationRequestListener.SELL_REQUESTED);
            }
        });
        updateStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                hashMapModificationRequestListener.modificationRequested(HashMapModificationRequestListener.STOCK_UPDATE_REQUESTED);
            }
        });
    }

    public void addHashMapModificationRequestListener(HashMapModificationRequestListener hashMapModificationRequestListener) {
        this.hashMapModificationRequestListener = hashMapModificationRequestListener;
    }
}
