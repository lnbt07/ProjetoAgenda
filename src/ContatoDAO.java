import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//responsável por agrupar todas as operações de bd relacionadas a classe Contato
public class ContatoDAO {

	private Connection connection; //armazena a conexao estabelecida com o banco de dados
	private PreparedStatement p; //armazena e executa as operações no banco de dados
	private String sql; //variável auxiliar para armazenar a instrução sql
	private ResultSet rs; //armazena resultado de uma instrução "select"
	
	public boolean inserir(Contato contato) {
		boolean retorno;
		sql = "insert into POO_Contato (nome, email, endereco, dataNascimento) values(?, ?, ?, ?)";
		connection = new Conexao().conectar();
		try {
			p = connection.prepareStatement(sql);
			p.setString(1, contato.getNome()); // 1ª interrogação será substituida pelo valor da variável
			p.setString(2, contato.getEmail()); // 1ª interrogação será substituida pelo valor da variável
			p.setString(3, contato.getEndereco()); // 1ª interrogação será substituida pelo valor da variável
			p.setString(4, contato.getDataNascimento()); // 1ª interrogação será substituida pelo valor da variável
			p.execute();
			retorno = true;
		} catch (SQLException e) {
			System.err.println("Erro ao inserir dados no banco\n" + e);
			retorno = false;
		}
		return retorno;
	}
	
	public Contato pesquisar(String nome, String email) {
		Contato contato = null;
		connection = new Conexao().conectar();
		sql = "select * from POO_Contato where nome=? and email=?";
		try {
			p = connection.prepareStatement(sql);
			p.setString(1, nome);
			p.setString(2, email);
			rs = p.executeQuery();
			if(rs.next()) {
				contato = new Contato(nome, email, rs.getString("endereco"), rs.getString("dataNascimento"));
			}
		} catch (SQLException e) {
			System.err.println("Erro ao pesquisar no banco de dados\n"+e);
		}
		
		return contato;
	}

}
