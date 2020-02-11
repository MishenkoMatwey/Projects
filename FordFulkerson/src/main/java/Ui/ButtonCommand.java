package Ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface ButtonCommand extends ActionListener {
    void actionPerformed(ActionEvent e);
}