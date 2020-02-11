package Controller;

import javax.swing.*;
import java.util.ArrayList;

public class LogString {
    private JTextArea textField;
    private ArrayList<String> list = new ArrayList<String>();

    public LogString(JTextArea textField) {
        this.textField = textField;
    }

    public void addString(String str) {
        list.add(str);
    }

    public void chengeTextLog(int count) {
        StringBuilder log = new StringBuilder();
        for (int i = 0; i <= count; i++) {
            log.append(list.get(i)).append("\n");
        }
        textField.setText("");
        textField.setText(log.toString());
    }

    public ArrayList<String> getList() {
        return list;
    }


}
