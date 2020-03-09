package my_projects.web_tree.model;

import my_projects.web_tree.utill.Jdbc;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBModelLayer {
    private final DataSource dataSource = Jdbc.JDBC.getDataSource();

    public Tree loadTree(String treeName) throws SQLException, IOException, ClassNotFoundException {
        try (
                Connection c = dataSource.getConnection();
                PreparedStatement statment = c.prepareStatement("select tree from Models where  name=?")
        ) {
            statment.setString(1, treeName);
            ResultSet resultSet = statment.executeQuery();
            resultSet.next();

            try (ObjectInputStream objectInputStream = new ObjectInputStream(resultSet.getBlob(1).getBinaryStream())){
                return  (Tree) objectInputStream.readObject();
            }
        }
    }

    public   StringBuilder getTreeNames() throws SQLException {
        StringBuilder listTreeNames = new StringBuilder();
        try (
                Connection c = dataSource.getConnection();
                Statement statment = c.createStatement()
        ) {
            ResultSet resultSet = statment.executeQuery("select name from Models");
            while (resultSet.next()) {
                listTreeNames.append(resultSet.getString(1)).append(" ");
            }

        }
        return listTreeNames;
    }

    public void saveTree(String treeName, Tree tree) throws SQLException, IOException {
        try (
                Connection c = dataSource.getConnection();
                PreparedStatement statment = c.prepareStatement("INSERT into Models  VALUES (?,?)")
        ) {
            Blob blob = c.createBlob();
            try (OutputStream outputStream = blob.setBinaryStream(1)) {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(tree);
            }
            statment.setString(1, treeName);
            statment.setBlob(2, blob);
            statment.executeUpdate();
        }

    }

}
