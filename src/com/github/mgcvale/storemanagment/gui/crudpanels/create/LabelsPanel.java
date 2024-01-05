package com.github.mgcvale.storemanagment.gui.crudpanels.create;

import javax.swing.*;
import java.awt.*;

public class LabelsPanel extends JPanel {
    public JLabel warning;
    JLabel info;

    public LabelsPanel(){
        warning = new JLabel("");
        warning.setForeground(new Color(200, 80, 80));
        info = new JLabel("(*): Itens obrigatórios");
        info.setHorizontalAlignment(JLabel.CENTER);
        warning.setHorizontalAlignment(JLabel.CENTER);
        setLayout(new GridLayout(2, 1));

        add(info);
        add(warning);
        warning.setVisible(false);

        setVisible(true);
    }


}
