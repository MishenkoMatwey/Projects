package my_projects.search_engine.view;

import javax.swing.*;
import java.awt.*;

public class PanelForText extends JPanel {

    private JScrollPane scrollForTextArea;

    private JTextArea textArea = new JTextArea();

    public JTextArea getTextArea() {
        return textArea;
    }

    public JScrollPane getScrollForTextArea() {
        return scrollForTextArea;
    }

    PanelForText() {
        setLayout(new BorderLayout());
        scrollForTextArea = new JScrollPane(textArea);
        add(scrollForTextArea, BorderLayout.CENTER);
    }
}
