
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.List;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultSet;

    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new ConectaDAO().connectDB();

        if (conn != null) {
            try {
                String sql = "INSERT INTO produtos (nome, valor, status) VALUES(?, ?, ?)";
                prep = conn.prepareStatement(sql);

                String nome = produto.getNome();
                int valor = produto.getValor();
                String status = produto.getStatus();

                prep.setString(1, nome);
                prep.setInt(2, valor);
                prep.setString(3, status);

                int query = prep.executeUpdate();

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

    public List<ProdutosDTO> listarProdutos() {
        conn = new ConectaDAO().connectDB();
        List<ProdutosDTO> listagem = new ArrayList<>();
        String sql = "SELECT * FROM produtos";
        try {
            prep = conn.prepareStatement(sql);
            resultSet = prep.executeQuery();

            while (resultSet.next()) {
                ProdutosDTO produtoDto = new ProdutosDTO();
                produtoDto.setId(resultSet.getInt("id"));
                produtoDto.setNome(resultSet.getString("nome"));
                produtoDto.setValor(resultSet.getInt("valor"));
                produtoDto.setStatus(resultSet.getString("status"));

                listagem.add(produtoDto);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + erro.getMessage());
        }
        return listagem;
    }

    public void venderProduto(int id) {
        conn = new ConectaDAO().connectDB();
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        try {
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            int idResultado = prep.executeUpdate();
            if (idResultado >= 1) {
                JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto já vendido!");
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao vender o produto: " + erro.getMessage());
        }
    }

}
