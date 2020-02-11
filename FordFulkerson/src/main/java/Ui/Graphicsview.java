package Ui;

import Controller.GraphDrawer;
import Controller.LogString;
import Model.Edge;
import Model.Graph;
import Model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class Graphicsview extends JFrame {
    private final JTextArea jTextArea=new JTextArea(); //log
    private final JTextField jTextFieldMaxFlow=new JTextField(); //max flow
    private final JButton prevButton = new JButton(new ImageIcon(getClass().getResource("/Image/back.png")));
    private final JButton nextButton = new JButton(new ImageIcon(getClass().getResource("/Image/next.png")));
    public final JButton startButton = new JButton(new ImageIcon(getClass().getResource("/Image/start.png")));
    private final JButton goToStart = new JButton(new ImageIcon(getClass().getResource("/Image/goToStart.png")));
    private final JButton goToEnd = new JButton(new ImageIcon(getClass().getResource("/Image/goToEnd.png")));
    private JTextField counter = new JTextField();
    private Container contaner;
    private int n;
    private final double pi = 3.14159265359;
    private int[][] result;
    private final Graph graph = new Graph();
    private final GraphDrawer drawer = new GraphDrawer(graph, this);
    private LogString logString;


    JButton getPrevButton() {
        return prevButton;
    }

    JButton getNextButton() {
        return nextButton;
    }

    JButton getGoToStart() {
        return goToStart;
    }

    JButton getGoToEnd() {
        return goToEnd;
    }

    GraphDrawer getDrawer() {
        return drawer;
    }

    JTextField getCounter() {
        return counter;
    }

    JTextField getjTextFieldMaxFlow() {
        return jTextFieldMaxFlow;
    }


    public LogString getLogString() {
        return logString;
    }

    public Graphicsview() {
        super("Ford–Fulkerson");
        setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contaner = this.getContentPane();
        contaner.setLayout(new BorderLayout());
        /*
          menu
         */
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createHelpMenu(this));
        menuBar.add(createMenu(this));
        setJMenuBar(menuBar);
        /*
          textArea
         */
        JPanel panelText = new JPanel(new BorderLayout());
        jTextArea.setSize(200, 100);
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        logString = new LogString(jTextArea);
        JScrollPane scroll = new JScrollPane(jTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelText.add(scroll);
        contaner.add(panelText, BorderLayout.LINE_START);
        /*
          Slider and button
         */

        JPanel panelSlider = new JPanel();

         /*
          Max flow
         */
        JLabel myLabel = new JLabel("Max flow");
        panelSlider.add(myLabel,BorderLayout.WEST);
        jTextFieldMaxFlow.setEnabled(false);
        jTextFieldMaxFlow.setPreferredSize(new Dimension(30, 30));
        panelSlider.add(jTextFieldMaxFlow);

        /*
          start
         */
        startButton.setEnabled(false);
        startButton.addActionListener(new StartCommand(graph, drawer, this));
        panelSlider.add(startButton);
        /*
            goToStart
         */
        goToStart.setEnabled(false);
        goToStart.addActionListener(new goToStartCommand(drawer, this));
        panelSlider.add(goToStart);
        /*
           prevButton
         */
        prevButton.setEnabled(false);
        prevButton.addActionListener(new PrevCommand(drawer, this));
        panelSlider.add(prevButton);
        /*
           counter
         */
        counter.setEditable(false);
        counter.setPreferredSize(new Dimension(45, 30));
        panelSlider.add(counter);
        /*
           nextButton
         */
        nextButton.setEnabled(false);
        nextButton.addActionListener(new NextCommand(drawer, this));
        panelSlider.add(nextButton);
        /*
          goToEnd
         */
        goToEnd.setEnabled(false);
        goToEnd.addActionListener(new goToEndCommand(drawer, this));
        panelSlider.add(goToEnd);

        contaner.add(panelSlider, BorderLayout.SOUTH);
        /*
          graph
         */
        contaner.add(drawer, BorderLayout.CENTER);
    }

    private JMenu createHelpMenu(JFrame frame) {

        JMenu helpMenu = new JMenu("Help");
        JMenuItem Task = new JMenuItem("Task");
        helpMenu.add(Task);
        Task.addActionListener(new ActionListener() {
            JDialog dialog = new HelpMenuTask(frame);

            public void actionPerformed(ActionEvent event) {
                if (dialog == null) // в первый раз
                    dialog = new HelpMenuTask(frame);
                dialog.setVisible(true); // отобразить диалог
            }
        });
        return helpMenu;
    }


    private JMenu createMenu(JFrame frame) {
        JMenu menu = new JMenu("Menu");
        JMenuItem file = new JMenuItem("File");
        JMenuItem random = new JMenuItem("Random");
        JMenuItem clear = new JMenuItem("Clear graph");
        menu.add(file);
        menu.addSeparator();
        menu.add(random);
        menu.addSeparator();
        menu.add(clear);
        file.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graph.getNodes().size() > 0) {
                    JOptionPane.showMessageDialog(drawer, "Graph already exist!!!!!");
                    return;
                }
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    try {
                        Scanner scanner = new Scanner(file);
                        n = scanner.nextInt();
                        result = new int[n][n];
                        int i = 0, j = 0;
                        while (i != n) {
                            while (j != n) {
                                result[i][j] = scanner.nextInt();
                                j++;
                            }
                            j = 0;
                            i++;
                        }

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    DrawGraph();
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            System.out.print(result[i][j] + " ");
                        }
                    }
                }
            }
        });
        random.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (graph.getNodes().size() > 0) {
                    JOptionPane.showMessageDialog(drawer, "Graph already exist!!!!!");
                    return;
                }
                result = CreationRandomAdjacencyMatrix();
                DrawGraph();
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graph.Clear();
                drawer.getGraphList().clear();
                drawer.getEdgeDrawer().getGraph().Clear();
                drawer.getNodeDrawer().getGraph().Clear();
                drawer.setIteration(0);
                drawer.setGraph(graph);
                startButton.setEnabled(true);
                goToEnd.setEnabled(false);
                goToStart.setEnabled(false);
                nextButton.setEnabled(false);
                prevButton.setEnabled(false);
                startButton.setEnabled(false);
                jTextArea.setText("");
                logString.getList().clear();

                drawer.repaint();
            }
        });
        return menu;
    }

    private void DrawGraph() {
        this.startButton.setEnabled(true);
        Node[] node = new Node[result.length];
        float alpha = 360 / result.length;
        float radius = 100;
        float x, y;
        node[0] = new Node("1" + 0, drawer.getSize().width / 2 - 12, drawer.getSize().height / 2 - 116);
        graph.addNode(node[0]);
        for (int i = 1; i < result.length; i++) {
            if (i % 2 == 0) {
                float radian = (float) (alpha * (i / 2) * pi / 180);
                float horda = 2 * radius * (float) Math.sin(radian / 2);
                y = horda * (float) Math.sin(pi * (180 - alpha * (i / 2)) / 360);
                x = (float) Math.sqrt((horda * horda) - (y * y));
                node[i - 1] = new Node("1" + (i - 1), drawer.getSize().width / 2 + Math.round(-12 - y), drawer.getSize().height / 2 + Math.round(-116 + x));
                graph.addNode(node[i - 1]);
                node[i] = new Node("1" + i, drawer.getSize().width / 2 + Math.round(-12 + y), drawer.getSize().height / 2 + Math.round(-116 + x));
                graph.addNode(node[i]);
            }
        }
        if (result.length % 2 == 0) {
            node[result.length - 1] = new Node("1" + (result.length - 1), drawer.getSize().width / 2 - 12, drawer.getSize().height / 2 + 84);
            graph.addNode(node[result.length - 1]);
        }
        drawer.repaint();
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result.length; j++)
                if (result[i][j] != 0)
                    graph.addEdge(new Edge(node[i], node[j], 0, result[i][j]));
        drawer.repaint();
    }

    private int[][] CreationRandomAdjacencyMatrix() {
        final Random random = new Random();
        int matrix_dimension = random.nextInt(4) + 4;
        n = matrix_dimension;
        int[][] adjacency_matrix = new int[matrix_dimension][matrix_dimension];
        int edge_weight_1;
        for (int i = 0; i < matrix_dimension; i++) {
            for (int j = i; j < matrix_dimension; j++) {
                edge_weight_1 = random.nextInt(10);
                if (i != j) {
                    if (j - i == 1) {
                        adjacency_matrix[i][j] = random.nextInt(9) + 1;
                    } else if (edge_weight_1 % 5 != 0)
                        adjacency_matrix[i][j] = edge_weight_1;
                } else
                    adjacency_matrix[i][j] = 0;
                if (adjacency_matrix[i][j] == 0 && i != j)
                    adjacency_matrix[j][i] = random.nextInt(10);
            }
        }
        adjacency_matrix[0][adjacency_matrix[0].length - 1] = 0;
        return adjacency_matrix;
    }

}
