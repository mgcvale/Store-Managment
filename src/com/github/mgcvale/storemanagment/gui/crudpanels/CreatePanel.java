package com.github.mgcvale.storemanagment.gui.crudpanels;

import com.github.mgcvale.storemanagment.data.HashMapChangeListener;
import com.github.mgcvale.storemanagment.gui.crudpanels.create.ControlsPanel;
import com.github.mgcvale.storemanagment.gui.crudpanels.create.InformationPanel;
import com.github.mgcvale.storemanagment.gui.crudpanels.create.LabelsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class CreatePanel extends JPanel {
    HashMapChangeListener hashMapChangeListener;
    HashMap<Integer, String[]> map;

    com.github.mgcvale.storemanagment.gui.crudpanels.create.InformationPanel infoPanel;
    ControlsPanel controlPanel;
    LabelsPanel labelsPanel;

    public CreatePanel(HashMapChangeListener hashMapChangeListener){
        this.hashMapChangeListener = hashMapChangeListener;
        infoPanel = new InformationPanel();
        controlPanel = new ControlsPanel();
        labelsPanel = new LabelsPanel();
        map = new HashMap<Integer, String[]>();
        GridBagConstraints gbc  = new GridBagConstraints();

        //lay components
        setLayout(new GridBagLayout());
        gbc.gridx=0; gbc.gridy=0;
        gbc.ipadx=20; gbc.ipady=20;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx=1; gbc.weighty=1;
        gbc.insets = new Insets(20, 40, 30, 40);
        add(infoPanel, gbc);
        gbc.gridy=1;
        add(controlPanel, gbc);
        gbc.insets = new Insets(5, 40, 30, 40);
        gbc.gridy=2;
        add(labelsPanel, gbc);

        //action listeners
        controlPanel.add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                labelsPanel.warning.setVisible(false);
                String status = infoPanel.checkInfo();
                if(status.isEmpty()){
                    map.put(map.size()+1, infoPanel.getValues());
                hashMapChangeListener.hashMapUpdated(map, "Create panel");
                }else{
                    labelsPanel.warning.setVisible(true);
                    labelsPanel.warning.setText(status);
                }
            }
        });
        controlPanel.clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                infoPanel.clear();
            }
        });
    }

    public void setMap(HashMap<Integer, String[]> map){
        this.map = map;
    }
    public void addHashMapChangeListener(HashMapChangeListener hashMapChangeListener){
        this.hashMapChangeListener = hashMapChangeListener;
    }

}
