package com.alexandria;

import com.alexandria.persistence.PersistenceUtils;
import com.alexandria.windows.mainwindow.MainWindow;
import com.bulenkov.darcula.DarculaLaf;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        final Logger logger = LogManager.getLogger(Main.class);

        SwingUtilities.invokeLater(() -> {

            // Look & Feel
            try {
                // TODO : WARNING during runtime : An illegal reflective access operation has occurred
                UIManager.setLookAndFeel(new DarculaLaf());

/*                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }*/
            } catch (UnsupportedLookAndFeelException e) {
                logger.error ("Unable to set Look&Feel");
            }

            // Hibernate init
            PersistenceUtils.init();

            new MainWindow();
        });
    }
}

