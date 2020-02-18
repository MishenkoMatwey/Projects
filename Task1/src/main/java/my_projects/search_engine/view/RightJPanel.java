package my_projects.search_engine.view;

import my_projects.search_engine.controller.NavigationController;

import javax.swing.*;
import java.awt.*;

class RightJPanel extends JPanel {


    RightJPanel(NavigationController controller) {
        setLayout(new BorderLayout());

        JButton buttonTop = new JButton(new ImageIcon(getClass().getResource("/top.png")));
        add(buttonTop, BorderLayout.BEFORE_FIRST_LINE);
        JButton buttonCopy = new JButton(new ImageIcon(getClass().getResource("/copy.png")));
        add(buttonCopy, BorderLayout.CENTER);
        JButton buttonBottom = new JButton(new ImageIcon(getClass().getResource("/bottom.png")));
        add(buttonBottom, BorderLayout.AFTER_LAST_LINE);

        buttonBottom.addActionListener(controller);
        buttonCopy.addActionListener(controller);
        buttonTop.addActionListener(controller);

        buttonBottom.setName("buttonBottom");
        buttonCopy.setName("buttonCopy");
        buttonTop.setName("buttonTop");
    }

}
