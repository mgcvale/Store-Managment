package com.github.mgcvale.storemanagment.gui.crudpanels.create;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InformationPanel extends JPanel {
    GridBagConstraints gbc;
    public JTextField pieceName, pieceLocation, pieceDescription, pieceSerialNumber, pieceQuantity;
    JLabel pieceNameLbl, pieceLocationLbl, pieceDescriptionLbl, pieceSerialNumberLbl, pieceQuantityLbl;

    public InformationPanel(){
        setLayout(new GridLayout(2, 5, 20, 2));

        pieceName = new JTextField("nome");
        pieceNameLbl = new JLabel("Nome da peça: (*)");
        pieceLocation = new JTextField("localização");
        pieceLocationLbl = new JLabel("Localização da peça: (*)");
        pieceDescription = new JTextField("descrição");
        pieceDescriptionLbl = new JLabel("Descrição da peça: ");
        pieceSerialNumber = new JTextField("número de série");
        pieceSerialNumberLbl = new JLabel("Número de série da peça: ");
        pieceQuantity = new JTextField("quantidade");
        pieceQuantityLbl = new JLabel("Quantidade de pecas (*)");

        add(pieceNameLbl);
        add(pieceLocationLbl);
        add(pieceQuantityLbl);
        add(pieceDescriptionLbl);
        add(pieceSerialNumberLbl);
        add(pieceName);
        add(pieceLocation);
        add(pieceQuantity);
        add(pieceDescription);
        add(pieceSerialNumber);

        pieceName.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(pieceName.getText().equals("nome"))
                    pieceName.setText("");
            }
            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(pieceName.getText().isEmpty())
                    pieceName.setText("nome");
            }
        });
        pieceLocation.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(pieceLocation.getText().equals("localização"))
                    pieceLocation.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(pieceLocation.getText().isEmpty())
                    pieceLocation.setText("localização");
            }
        });
        pieceQuantity.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(pieceQuantity.getText().equals("quantidade"))
                    pieceQuantity.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                if(pieceQuantity.getText().isEmpty())
                    pieceQuantity.setText("quantidade");
            }
        });
        pieceDescription.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(pieceDescription.getText().equals("descrição"))
                    pieceDescription.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
            }
        });
        pieceSerialNumber.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                if(pieceSerialNumber.getText().equals("número de série"))
                    pieceSerialNumber.setText("");
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
            }
        });

        setVisible(true);
    }

    public String checkInfo(){
        if(pieceName.getText().isEmpty() || pieceLocation.getText().isEmpty() || pieceQuantity.getText().isEmpty())
            return "Você deixou algum campo obrigatório vazio!";
        if(pieceName.getText().equals("nome"))
            return "O nome \"nome\" não é válido!";
        if(pieceLocation.getText().equals("localização"))
            return "A localização \"localização\" não é válida!";
        if(!pieceQuantity.getText().matches("^?\\d+$")){
            return "Quantidade inválida!";
        }
        return "";
    }
    public String[] getValues(){
        String descriptionText = pieceDescription.getText().equals("descrição") ? "" : pieceDescription.getText();
        String serialNumberText = pieceSerialNumber.getText().equals("número de série") ? "" : pieceSerialNumber.getText();
        String[] returnValue = new String[]{pieceName.getText(), pieceLocation.getText(), descriptionText, serialNumberText, pieceQuantity.getText()};
        pieceName.setText(""); pieceLocation.setText("");
        pieceDescription.setText(""); pieceSerialNumber.setText("");
        pieceQuantity.setText("");
        return returnValue;
    }

    public void clear(){
        pieceName.setText(""); pieceLocation.setText("");
        pieceDescription.setText(""); pieceSerialNumber.setText("");
        pieceQuantity.setText("");
    }


}
