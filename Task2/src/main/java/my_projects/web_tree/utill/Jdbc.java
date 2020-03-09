package my_projects.web_tree.utill;

import java.io.InputStream;
import java.io.IOException;
import my_projects.web_tree.model.DBModelLayer;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public enum Jdbc
{
    JDBC;

    private final Properties dataBaseProperties;
    private final DataSource dataSource;

    public DataSource getDataSource() {
        return this.dataSource;
    }

    private Jdbc() {
        this.dataBaseProperties = new Properties();
        this.loadBaseProperties();
        this.dataSource = this.createDataSource();
        createTable();
    }
    private void createTable(){
        try (
                Connection c = dataSource.getConnection();
                Statement statment = c.createStatement()
        ) {

            statment.executeUpdate("create table if not exists Models\n" +
                    "(\n" +
                    " name varchar(50) not null ,\n" +
                    " tree blob not null ,\n" +
                    " constraint name\n" +
                    " unique (name)\n" +
                    ");\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private BasicDataSource createDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(this.dataBaseProperties.getProperty("db.driver"));
        dataSource.setUrl(this.dataBaseProperties.getProperty("db.url"));
        dataSource.setUsername(this.dataBaseProperties.getProperty("db.username"));
        dataSource.setPassword(this.dataBaseProperties.getProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(this.dataBaseProperties.getProperty("db.pool.initSize")));
        dataSource.setMaxTotal(Integer.parseInt(this.dataBaseProperties.getProperty("db.pool.maxSize")));
        return dataSource;
    }

    private void loadBaseProperties() {
        try (final InputStream in = DBModelLayer.class.getClassLoader().getResourceAsStream("dataBase.properties")) {
            this.dataBaseProperties.load(in);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
