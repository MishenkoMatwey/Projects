package my_projects.search_engine.controller;

import my_projects.search_engine.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationController implements ActionListener {
    private MainView mainView;

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton) e.getSource()).getName()) {
            case ("buttonCopy"): {
                if (mainView.getPanelForText().getTextArea().getSelectedText() == null) {
                    mainView.getPanelForText().getTextArea().selectAll();
                }
                StringSelection selection = new StringSelection(mainView.getPanelForText().getTextArea().getSelectedText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, selection);
                break;
            }
            case ("buttonTop"): {
                int position = mainView.getPanelForText().getScrollForTextArea().getVerticalScrollBar().getValue();
                if (position >= 10) {
                    mainView.getPanelForText().getScrollForTextArea().getVerticalScrollBar().setValue(position - 10);
                }
                break;
            }
            case ("buttonBottom"): {
                int maxPosition = mainView.getPanelForText().getScrollForTextArea().getVerticalScrollBar().getMaximum();
                int position = mainView.getPanelForText().getScrollForTextArea().getVerticalScrollBar().getValue();
                if (maxPosition - position >= 10) {
                    mainView.getPanelForText().getScrollForTextArea().getVerticalScrollBar().setValue(position + 10);
                }
                break;
            }
            default:
                break;
        }
    }
}
