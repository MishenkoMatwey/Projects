package my_projects.search_engine.view;

import my_projects.search_engine.controller.SearchController;

import javax.swing.*;
import java.awt.*;

import static javax.swing.GroupLayout.Alignment.*;

public class BottomJPanel extends JPanel {
    private final JTextField pattern = new JTextField();
    private final JTextField pathForDirectories = new JTextField();
    private final JTextField extension = new JTextField(".log");
    private final JCheckBox buttonCheckRegister = new JCheckBox("С учетом регистра");
    private final ButtonGroup buttonGroup = new ButtonGroup();


    public JTextField getPattern() {
        return pattern;
    }

    public JTextField getExtension() {
        return extension;
    }

    public JCheckBox getButtonCheckRegister() {
        return buttonCheckRegister;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public JTextField getPathForDirectories() {
        return pathForDirectories;
    }


    BottomJPanel(SearchController controller) {
        JButton buttonChooseDir = new JButton("Выбрать папку");
        buttonChooseDir.addActionListener(controller);
        JButton buttonFindFiles = new JButton("Найти Файлы");
        buttonFindFiles.addActionListener(controller);
        buttonChooseDir.setName("buttonChooseDir");
        buttonFindFiles.setName("buttonFindFiles");
        buttonCheckRegister.setSelected(true);
        pathForDirectories.setEnabled(false);

        extension.setHorizontalAlignment(JTextField.CENTER);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        GroupLayout layout = new GroupLayout(this);

        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        JLabel labelForPattern = new JLabel("Поиск строки :");
        JLabel labelForExtension = new JLabel("Расширение файла:");

        hGroup.addGroup(layout.createParallelGroup().
                addComponent(labelForPattern).addComponent(labelForExtension).addComponent(buttonCheckRegister));
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(pattern).addComponent(extension).addComponent(buttonFindFiles));
        hGroup.addGroup(layout.createParallelGroup().
                addComponent(buttonChooseDir).addComponent(pathForDirectories));


        layout.linkSize(SwingConstants.HORIZONTAL, buttonChooseDir, pathForDirectories);
        layout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup(CENTER).
                addComponent(labelForPattern).addComponent(pattern).addComponent(buttonChooseDir));
        vGroup.addGroup(layout.createParallelGroup(CENTER).
                addComponent(labelForExtension).addComponent(extension).addComponent(pathForDirectories));
        vGroup.addGroup(layout.createParallelGroup(CENTER).
                addComponent(buttonCheckRegister).addComponent(buttonFindFiles));
        layout.setVerticalGroup(vGroup);


    }
}
