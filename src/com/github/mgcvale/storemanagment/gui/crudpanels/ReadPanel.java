package com.github.mgcvale.storemanagment.gui.crudpanels;

import com.github.mgcvale.storemanagment.data.HashMapChangeListener;
import com.github.mgcvale.storemanagment.data.HashMapModificationRequestListener;
import com.github.mgcvale.storemanagment.data.ItemSelectedListener;
import com.github.mgcvale.storemanagment.gui.crudpanels.read.InformationPanel;
import com.github.mgcvale.storemanagment.gui.crudpanels.read.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ReadPanel extends JPanel {
    HashMap<Integer, String[]> map;
    public SearchPanel searchPanel;
    InformationPanel infoPanel;
    HashMapChangeListener hashMapChangeListener;


    public void setMap(HashMap<Integer, String[]> map){
        this.map = map;
        searchPanel.setMap(map);
    }
    public void addHashMapChangeListener(HashMapChangeListener hashMapChangeListener){
        this.hashMapChangeListener = hashMapChangeListener;
    }
    public ReadPanel(HashMapChangeListener hashMapChangeListener, HashMap<Integer, String[]> map){
        this.map = map;
        this.hashMapChangeListener = hashMapChangeListener;
        searchPanel = new SearchPanel(map);
        infoPanel = new InformationPanel();
        ReadPanel thisPanel = this;

        setLayout(new GridLayout(1, 2, 10, 10));
        add(searchPanel);
        add(infoPanel);
        searchPanel.addItemSelectedListener(new ItemSelectedListener() {
            @Override
            public void itemSelected(int hashMapKey) {
                if(map.get(hashMapKey)!=null)
                    infoPanel.updateInformation(map.get(hashMapKey));
                else
                    infoPanel.deselect();
            }
        });
        infoPanel.actionPanel.addHashMapModificationRequestListener(new HashMapModificationRequestListener() {
            @Override
            public void modificationRequested(int type) {
                int result, id;
                switch(type){
                    case DELETE_REQUESTED:
                        result = JOptionPane.showConfirmDialog(thisPanel, "Você realmente deseja deletar esta peca?", "Confirmacão", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION) {
                            id = searchPanel.getSelectedItemID();
                            map.remove(id);
                            String[] pastElement;
                            if(map.size()>1)
                                for(int i=id; i<map.size()+1; i++){
                                    pastElement = map.get(i+1);
                                    map.remove(i+1);
                                    map.put(i, pastElement);
                                }
                            hashMapChangeListener.hashMapUpdated(map, "ReadPanel");
                        }
                        break;
                    case SELL_REQUESTED:
                        result = JOptionPane.showConfirmDialog(thisPanel, "Você realmente deseja vender essa peca?", "Conrifmacão", JOptionPane.YES_NO_OPTION);
                        if(result == JOptionPane.YES_OPTION){
                            id = searchPanel.getSelectedItemID();
                            if(map.get(id)!=null) {
                                String[] oldString = map.get(id);
                                Integer quantity = Integer.parseInt(oldString[4]) - 1;
                                if (quantity >= 0) {
                                    map.put(id, new String[]{oldString[0], oldString[1], oldString[2], oldString[3], quantity.toString()});
                                    hashMapChangeListener.hashMapUpdated(map, "ReadPanel");
                                }
                                else
                                    JOptionPane.showMessageDialog(thisPanel, "Nao foi possível realizar venda: não há estoque!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(thisPanel, "Item inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case STOCK_UPDATE_REQUESTED:
                        String value = JOptionPane.showInputDialog(thisPanel, "Digite a nova quantidade.", "0", JOptionPane.QUESTION_MESSAGE);
                        if(value.matches("^?\\d+$")){
                            id = searchPanel.getSelectedItemID();
                            if(map.get(id)!=null){
                                String[] oldString = map.get(id);
                                map.put(id, new String[]{oldString[0], oldString[1], oldString[2], oldString[3], value});
                                hashMapChangeListener.hashMapUpdated(map, "Read Panel");
                            }
                        }else{
                            JOptionPane.showMessageDialog(thisPanel, "Quantidade fornecida inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                }
            }
        });
    }
}
