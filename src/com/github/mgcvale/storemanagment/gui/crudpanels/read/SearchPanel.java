package com.github.mgcvale.storemanagment.gui.crudpanels.read;

import com.github.mgcvale.storemanagment.data.ItemSelectedListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SearchPanel extends JPanel {
    JPanel searchPanel;
    JPanel comboPanel;
    JScrollPane scrollPane;
    JTextField searchTextField;
    JButton searchBtn;
    JComboBox sortBycb;
    JComboBox searchBycb;
    HashMap<Integer, String[]> map;
    JTable table;
    DefaultTableModel model;
    GridBagConstraints gbc;
    ItemSelectedListener itemSelectedListener;

    public SearchPanel(HashMap<Integer, String[]> map){
        this.map = map;
        JPanel thisPanel = this;
        gbc = new GridBagConstraints();
        searchPanel = new JPanel();
        comboPanel = new JPanel();
        searchTextField = new JTextField("");
        searchBtn = new JButton("Buscar");
        sortBycb = new JComboBox();
        searchBycb = new JComboBox();
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //set up all the components
        sortBycb.addItem("Ordenar por: ID");
        searchBycb.addItem("Buscar por: Nome");
        searchBycb.addItem("Buscar por: ID");
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Quantidade");
        for(Map.Entry<Integer, String[]> entry : map.entrySet()){
            model.addRow(new String[]{entry.getKey().toString(), entry.getValue()[0], entry.getValue()[4]});
            System.out.println(Arrays.toString(map.get(entry.getKey())));
        }
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane = new JScrollPane(table);

        //lay components out
        setLayout(new GridBagLayout());
        searchPanel.setLayout(new GridBagLayout());
        comboPanel.setLayout(new GridBagLayout());

        //utils panel
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx=0; gbc.gridy=0;
        gbc.weightx=0.75f;
        gbc.gridwidth=3; gbc.gridheight=1;
        searchPanel.add(searchTextField, gbc);

        gbc.insets.left=0;
        gbc.weightx=0.25f;
        gbc.gridx=3;
        gbc.gridwidth=1;
        searchPanel.add(searchBtn, gbc);

        gbc.insets.left=5;
        gbc.insets.top=0;
        gbc.weightx=1;
        gbc.gridx=0; gbc.gridy=0;
        comboPanel.add(searchBycb, gbc);
        gbc.insets.left=0;
        gbc.gridx=1;
        comboPanel.add(sortBycb, gbc);

        //main panel (this)
        gbc.insets.left=0;
        gbc.gridx=0; gbc.gridy=0;
        gbc.weighty = 0.1f;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(searchPanel, gbc);
        gbc.gridy=1; gbc.weighty=0.1f;
        add(comboPanel, gbc);
        gbc.gridy=2; gbc.weighty=0.8f;
        add(scrollPane, gbc);

        //action listeners
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                search(searchTextField.getText());
            }
        });
        searchTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                searchTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if(table.getSelectedRow()>-1) {
                    itemSelectedListener.itemSelected(Integer.parseInt((table.getValueAt(table.getSelectedRow(), 0)).toString()));
                }
            }
        });
    }

    public void search(String search){
        model.setRowCount(0);
        for(Map.Entry<Integer, String[]> entry : map.entrySet()){
            if(searchBycb.getSelectedItem()=="Buscar por: Nome") {
                if (entry.getValue()[0].contains(search)) {
                    model.addRow(new String[]{entry.getKey().toString(), entry.getValue()[0], entry.getValue()[4]});
                } else {
                    System.out.println("o item " + entry.getKey().toString() + " nao contem!");
                }
            }else{
                if (entry.getKey()==Integer.parseInt(searchTextField.getText())) {
                    model.addRow(new String[]{entry.getKey().toString(), entry.getValue()[0], entry.getValue()[4]});
                }
            }
        }
        table.repaint();
        scrollPane.repaint();
    }
    public void sort(){

    }
    public void setMap(HashMap<Integer, String[]> map){
        this.map = map;
        model.setRowCount(0);
        for(Map.Entry<Integer, String[]> entry : map.entrySet()){
            model.addRow(new String[]{
                    entry.getKey().toString(), entry.getValue()[0], entry.getValue()[4]
            });
        }
    }
    public void addItemSelectedListener(ItemSelectedListener itemSelectedListener){
        this.itemSelectedListener = itemSelectedListener;
    }
    public int getSelectedItemID(){
        return table.getSelectedRow()+1;
    }
}
