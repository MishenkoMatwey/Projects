package Controller;

import Model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

final class InitNode extends JDialog {

    InitNode(GraphDrawer graphDrawer, MouseEvent e) {
        this.setVisible(true);
        add(new JLabel("<html><h1><i>Task</i></h1><hr>" + "InitNode"),
                BorderLayout.CENTER);
        this.setLocation(e.getX(), e.getY());
        JTextField textField = new JTextField();
        add(textField);
        // При активизации кнопки ОК диалогове окно закрывается.
        JButton ok = new JButton("ok");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                if (textField.getText().length() > 0) {
                    graphDrawer.getGraph().addNode(new Node(textField.getText(), e.getX(), e.getY()));
                    graphDrawer.repaint();
                }
            }
        });

        // Кнопка ОК помещается в нижнюю часть окна.
        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(100, 100);
    }
}
