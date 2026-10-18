package com.mashrafi.smartstudent;

import com.formdev.flatlaf.FlatLightLaf;
import com.mashrafi.smartstudent.data.Database;
import com.mashrafi.smartstudent.ui.MainFrame;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatLightLaf.setup();
            UIManager.put("Component.arc", 12);
            UIManager.put("Button.arc", 12);
            try {
                Database.initialize();
                new MainFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not start application: " + e.getMessage(),
                        "Startup error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
