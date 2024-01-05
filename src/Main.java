import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.github.mgcvale.storemanagment.gui.MainFrame;

import javax.swing.*;

public class Main{
    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        MainFrame mainFrame = new MainFrame();
    }
}