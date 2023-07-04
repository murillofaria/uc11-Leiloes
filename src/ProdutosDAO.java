
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().connectDB();

        if (conn != null) {
            try {
                String sql = "INSERT INTO produtos (nome, valor, status) VALUES(?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);

                String nome = produto.getNome();
                int valor = produto.getValor();
                String status = produto.getStatus();

                preparedStatement.setString(1, nome);
                preparedStatement.setInt(2, valor);
                preparedStatement.setString(3, status);

                int query = preparedStatement.executeUpdate();

                if (query > 0) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto!");
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto: " + erro.getMessage());
            }
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
