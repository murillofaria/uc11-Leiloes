
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectaDAO {

    Connection conn = null;

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/uc11-leilao";
            String nome = "root";
            String senha = "mysql";
            conn = DriverManager.getConnection(url, nome, senha);

        } catch (ClassNotFoundException | SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se: " + erro.getMessage());
        }
        return conn;
    }
}
