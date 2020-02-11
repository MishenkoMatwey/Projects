package Model;

import Controller.GraphDrawer;
import Ui.Graphicsview;

import java.awt.*;
import java.util.LinkedList;

public class FordFulkerson {
    private final int WHITE_NODE = 0;
    private final int GREY = 1;
    private final int BLACK = 2;

    private Graph graph;
    private GraphDrawer drawer;
    private Graphicsview frame;

    private int[][] flow; // Матрица потока
    private int[] color;      // Цвета для вершин
    private int[] pred;       // Массив пути
    private int head, tail;  // Начало, Конец
    private int[] q;
    public int[][] result;

    public GraphDrawer getDrawer() {
        return drawer;
    }

    public FordFulkerson(Graph graph, GraphDrawer drawer, Graphicsview frame) {
        this.graph = graph;
        this.drawer = drawer;
        this.frame = frame;
    }

    FordFulkerson(int[][] array, Graph graph, GraphDrawer graphDrawer) {
        this.result = array;
        this.graph = graph;
        this.drawer = graphDrawer;
    }

    private int min(int x, int y) {
        return Math.min(x, y);
    }

    private void enque(int x) {
        q[tail] = x;     // записать х в хвост
        tail++;          // хвостом становиться следующий элемент
        color[x] = GREY; // Цвет серый (из алгоритма поиска)
    }

    private int deque() {
        int x = q[head];  // Записать в х значение головы
        head++;           // Соответственно номер начала очереди увеличивается
        color[x] = BLACK; // Вершина х - отмечается как прочитанная
        return x;         // Возвращается номер х прочитанной вершины
    }

    private int bfs(int start, int end) {
        for (int u = 0; u < result.length; u++) // Сначала отмечаем все вершины не пройденными
            color[u] = WHITE_NODE;

        head = 0;   // Начало очереди 0
        tail = 0;   // Хвост 0
        enque(start); // Вступили на первую вершину
        int u;
        pred[start] = -1;   // Специальная метка для начала пути
        while (head != tail)  // Пока хвост не совпадёт с головой
        {
            u = deque();
            for (int v = 0; v < result.length; v++) // Смотрим смежные вершины
            {
                // Если не пройдена и не заполнена
                if (color[v] == WHITE_NODE && (result[u][v] - flow[u][v]) > 0) {
                    enque(v);  // Вступаем на вершину v
                    pred[v] = u; // Путь обновляем
                }
            }
        }
        if (color[end] == BLACK) // Если конечная вершина, дошли - возвращаем 0
            return 0;
        else return 1;
    }

    /**
     * возвращает список измененных ребер
     */
    private LinkedList<Edge> changedEdges(Graph prev) {

        LinkedList<Edge> list = new LinkedList<>(); // список измененных на данной итерации ребер
        for (int i = 0; i < prev.getEdges().size(); i++) {
            if (!prev.getEdges().get(i).getWeight().equals(this.graph.getEdges().get(i).getWeight())) { // если веса не совпали
                list.addLast(prev.getEdges().get(i));
            }
        }
        return list;
    }

    void setLog(LinkedList<Edge> list) {
        if (list.size() != 0) {
            String log = "";
            for (Edge edge : list) {
                log += edge.getStartNode().getName() + "-(" + edge.getWeight() + "|" + edge.getBandwidth() + ")->";
            }
            log += list.getLast().getEndNode().getName();
            frame.getLogString().addString(log);
        }
    }

    private void colorEdges(LinkedList<Edge> list, Color color) {
        for (Edge edge : list) {
            edge.changeColor(color);
        }
    }

    public int maxFlow(int[][] result, int source, int stock) {
        flow = new int[result.length][result.length]; // Матрица потока
        color = new int[result.length];      // Цвета для вершин
        pred = new int[result.length];
        q = new int[result.length];
        int max_flow = 0;            // Изначально нулевой
        for (int i = 0; i < result.length; i++)  // Зануляем матрицу потока
        {
            for (int j = 0; j < result.length; j++)
                flow[i][j] = 0;
        }
        while (bfs(source, stock) == 0)             // Пока существует путь
        {
            int delta = 10000;
            for (int u = result.length - 1; pred[u] >= 0; u = pred[u]) // Найти минимальный поток в сети
            {
                delta = min(delta, (result[pred[u]][u] - flow[pred[u]][u]));
            }
            for (int u = result.length - 1; pred[u] >= 0; u = pred[u]) // По алгоритму Форда-Фалкерсона
            {
                flow[pred[u]][u] += delta;
                flow[u][pred[u]] -= delta;
            }
            max_flow += delta;                       // Повышаем максимальный поток

            Graph prev = new Graph(this.graph); // запишем в граф предыдущее состояние
            matrixToGraph(); // обновим состояние графа
            colorEdges(changedEdges(prev), Color.ORANGE); // красим изменившиеся ребра в красный
            setLog(changedEdges(prev));
            drawer.getGraphList().add(prev); // сохраним в список состояний
            drawer.setIteration(drawer.getIteration() + 1);
            drawer.setGraph(drawer.getGraphList().getLast());
            drawer.repaint();
        }

        drawer.setIteration(drawer.getIteration() - 1); // так как это количество элементов списка, индекс последнего на 1 меньше
        return max_flow;
    }

    public void graphToMatrix() {
        result = new int[graph.getNodes().size()][graph.getNodes().size()];
        int index_edges = 0, index_nodes = 0, index_i, index_j, vertex_counter = 0;
        while (index_edges != graph.getEdges().size()) {
            index_i = 0;
            index_j = 0;
            while (index_nodes != graph.getNodes().size()) {
                if (graph.getNodes().get(index_nodes) == graph.getEdges().get(index_edges).getStartNode() && index_i == 0) {
                    index_i = index_nodes;
                    vertex_counter++;
                }
                if (graph.getNodes().get(index_nodes) == graph.getEdges().get(index_edges).getEndNode() && index_j == 0) {
                    index_j = index_nodes;
                    vertex_counter++;
                }
                if (vertex_counter == 2) {
                    result[index_i][index_j] = graph.getEdges().get(index_edges).getBandwidth();
                    break;
                }
                index_nodes++;
            }
            vertex_counter = 0;
            index_nodes = 0;
            index_edges++;
        }
    }

    public void matrixToGraph() {
        int index_edges = 0, index_nodes = 0, index_i, index_j, vertex_counter = 0;
        while (index_edges != graph.getEdges().size()) {
            index_i = 0;
            index_j = 0;
            while (index_nodes != graph.getNodes().size()) {
                if (graph.getNodes().get(index_nodes) == graph.getEdges().get(index_edges).getStartNode() && index_i == 0) {
                    index_i = index_nodes;
                    vertex_counter++;
                }
                if (graph.getNodes().get(index_nodes) == graph.getEdges().get(index_edges).getEndNode() && index_j == 0) {
                    index_j = index_nodes;
                    vertex_counter++;
                }
                if (vertex_counter == 2) {
                    graph.getEdges().get(index_edges).setWeight(flow[index_i][index_j]);
                    break;
                }
                index_nodes++;
            }
            vertex_counter = 0;
            index_nodes = 0;
            index_edges++;
        }
    }
}
