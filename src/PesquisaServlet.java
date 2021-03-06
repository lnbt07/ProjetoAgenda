

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PesquisaServlet
 */
@WebServlet("/PesquisaServlet")
public class PesquisaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String nome = request.getParameter("nome");
		String email = request.getParameter("email");

		Contato contato = new ContatoDAO().pesquisar(nome, email);

		String aux;
		if (contato == null) {
			aux = "Dados do contato n?o foram encontrados.";
		} else {
			aux = nome + "\n" + email + "\n" + contato.getDataNascimento() + "\n" + contato.getEndereco();
		}

		PrintWriter out = response.getWriter();
		out.println("<html><body><h1>");
		out.println(aux);
		out.println("</h1></body></html>");
	}

}
