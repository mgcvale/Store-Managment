package com.github.mgcvale.storemanagment.gui.crudpanels.read;

import javax.swing.*;
import java.awt.*;

public class InformationPanel extends JPanel {
    JTextArea location, description, quantity, serialnumber;
    JPanel labelPanel;
    public ActionPanel actionPanel;
    JLabel name;

    public InformationPanel(){
        name = new JLabel("Nenhuma peca selecionada!");
        location = new JTextArea("");
        description = new JTextArea("");
        quantity = new JTextArea("");
        serialnumber = new JTextArea("");
        labelPanel = new JPanel(new GridLayout(5, 1, 15, 20));
        actionPanel = new ActionPanel();

        // set up the components
        name.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        name.setHorizontalAlignment(JLabel.CENTER);
        location.setEditable(false);
        location.setBorder(null);
        location.setOpaque(false);
        location.setLineWrap(true);
        location.setWrapStyleWord(true);
        location.setAlignmentY(Component.CENTER_ALIGNMENT);
        location.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setEditable(false);
        description.setBorder(null);
        description.setOpaque(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        quantity.setEditable(false);
        quantity.setBorder(null);
        quantity.setOpaque(false);
        quantity.setLineWrap(true);
        quantity.setWrapStyleWord(true);
        serialnumber.setEditable(false);
        serialnumber.setBorder(null);
        serialnumber.setOpaque(false);
        serialnumber.setLineWrap(true);
        serialnumber.setWrapStyleWord(true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //lay components into the labelpanel
        labelPanel.add(name);
        labelPanel.add(description);
        labelPanel.add(location);
        labelPanel.add(quantity);
        labelPanel.add(serialnumber);

        //lay components out in this panel
        gbc.gridx=0; gbc.gridy=0;
        gbc.fill=GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx=1.0f;
        gbc.weighty=1.0f;
        gbc.gridheight=3;
        add(labelPanel, gbc);

        gbc.gridy=3;
        gbc.gridheight=1;
        gbc.weighty=0.15f;
        gbc.ipady=0;
        add(actionPanel, gbc);
    }

    public void updateInformation(String [] info){
        name.setText(info[0]);
        location.setText(("Localizacao da peca: " + info[1]));
        quantity.setText("Quantidade de pecas: " + info[4]);
        description.setText(info[2].isEmpty() ? "Não há descricão para esta peca." : "Descricao da peca: " + info[2]);
        serialnumber.setText(info[3].isEmpty() ? "Não há número de série cadastrado para esta peca." : "S/N da peca: " + info[3]);
    }
    public void deselect(){
        name.setText("");
        location.setText("");
        quantity.setText("");
        description.setText("");
        serialnumber.setText("");
    }

}
