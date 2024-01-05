package com.github.mgcvale.storemanagment.gui.crudpanels.create;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ControlsPanel extends JPanel {
    public JButton add, clear;
    public ControlsPanel(){
        add = new JButton("Adicionar");
        clear = new JButton("Limpar dados");
        GridLayout gl = new GridLayout(1, 2, 100, 0);

        setBorder(new EmptyBorder(0, getWidth()/5, 0, getWidth()/5));
        setLayout(gl);
        add(add);
        add(clear);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                setBorder(new EmptyBorder(0, getWidth()/5, 0, getWidth()/5));
                gl.setHgap(getWidth()/5);
                setLayout(gl);
                add(add);
                add(clear);
                repaint();
            }

            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                setBorder(new EmptyBorder(0, getWidth()/5, 0, getWidth()/5));
                gl.setHgap(getWidth()/5);
                setLayout(gl);
                add(add);
                add(clear);
                repaint();
            }
        });

        setVisible(true);
    }

}
