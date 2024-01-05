package com.github.mgcvale.storemanagment.gui;

import com.github.mgcvale.storemanagment.data.HashMapChangeListener;
import com.github.mgcvale.storemanagment.data.JsonHandler;
import com.github.mgcvale.storemanagment.gui.crudpanels.CreatePanel;
import com.github.mgcvale.storemanagment.gui.crudpanels.DeletePanel;
import com.github.mgcvale.storemanagment.gui.crudpanels.ReadPanel;
import com.github.mgcvale.storemanagment.gui.crudpanels.UpdatePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame implements HashMapChangeListener {
    JTabbedPane tabbedPane;
    CreatePanel createPanel;
    DeletePanel deletePanel;
    UpdatePanel updatePanel;
    ReadPanel readPanel;
    GridBagConstraints gbc;
    HashMap<Integer, String[]> map;
    File jsonDir = new File(System.getProperty("user.dir") + "/data.json");
    JsonHandler jsonHandler = new JsonHandler();

    public MainFrame(){
        super("Store managment");
        //init variables
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx=0; gbc.gridy=0; gbc.weighty=1; gbc.weightx=1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        try {
            jsonDir.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            map = jsonHandler.getHashMapFromJson(jsonDir);
            if(map==null){
                map = new HashMap<>();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tabbedPane = new JTabbedPane();
        createPanel = new CreatePanel(this);
        updatePanel = new UpdatePanel(this);
        deletePanel = new DeletePanel(this);
        readPanel = new ReadPanel(this, map);
        createPanel.setMap(map);
        updatePanel.setMap(map);
        deletePanel.setMap(map);
        readPanel.setMap(map);

        tabbedPane.addTab("Buscar Peças", readPanel);
        tabbedPane.addTab("Adicionar Peça", createPanel);
        tabbedPane.addTab("Remover Peça", deletePanel);
        tabbedPane.addTab("Editar Peça", updatePanel);

        tabbedPane.setSelectedComponent(createPanel);

        //add action listeners
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    jsonHandler.exportHashMap(jsonDir, map);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                System.exit(0);
            }
        });

        //init window
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        add(tabbedPane, gbc);
        pack();
        setVisible(true);
    }

    @Override
    public void hashMapUpdated(HashMap<Integer, String[]> map, String source) {
        this.map = map;
        createPanel.setMap(map);
        readPanel.setMap(map);
        updatePanel.setMap(map);
        deletePanel.setMap(map);
        System.out.println("hash map updated, source: "+source+" : ");
        for(Map.Entry<Integer, String[]> entry : map.entrySet()){
            System.out.println(entry.getKey() + ": " + Arrays.toString(entry.getValue()));
        }
    }
}
