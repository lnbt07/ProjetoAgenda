import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//respons�vel por agrupar todas as opera��es de bd relacionadas a classe Contato
public class ContatoDAO {

	private Connection connection; //armazena a conexao estabelecida com o banco de dados
	private PreparedStatement p; //armazena e executa as opera��es no banco de dados
	private String sql; //vari�vel auxiliar para armazenar a instru��o sql
	private ResultSet rs; //armazena resultado de uma instru��o "select"
	
	public boolean inserir(Contato contato) {
		boolean retorno;
		sql = "insert into POO_Contato (nome, email, endereco, dataNascimento) values(?, ?, ?, ?)";
		connection = new Conexao().conectar();
		try {
			p = connection.prepareStatement(sql);
			p.setString(1, contato.getNome()); // 1� interroga��o ser� substituida pelo valor da vari�vel
			p.setString(2, contato.getEmail()); // 1� interroga��o ser� substituida pelo valor da vari�vel
			p.setString(3, contato.getEndereco()); // 1� interroga��o ser� substituida pelo valor da vari�vel
			p.setString(4, contato.getDataNascimento()); // 1� interroga��o ser� substituida pelo valor da vari�vel
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
