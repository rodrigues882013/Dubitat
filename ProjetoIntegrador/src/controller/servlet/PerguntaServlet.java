package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.DisciplinaBD;
import model.db.PerguntaBD;
import model.object.Disciplina;
import model.object.Pergunta;

/**
 * Servlet implementation class PerguntaServlet
 */
public class PerguntaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PerguntaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String nome = (String)request.getParameter("Nome");
		String matricula = (String)request.getParameter("Matricula");
		String email = (String)request.getParameter("Email");
		String prioridade = (String)request.getParameter("Prioridade");
		String disciplina = (String)request.getParameter("Disciplina");
		String mensagem = (String)request.getParameter("Pergunta");
		String codigo = DisciplinaBD.listaDisciplinaPorCodigo(disciplina);
		Disciplina D = new Disciplina (codigo, disciplina);
		Pergunta P = new Pergunta(nome, matricula, email, prioridade, mensagem, D);
		D.setPergunta(P);
		
		if(PerguntaBD.addPergunta(P)){
			session.setAttribute("aviso","Seu ticket é: " + P.getTicket() + " Aguarde pelo menos 72 horas, Obrigado");
			response.sendRedirect("Paginas/Resposta.jsp");
		}
		else{
			session.setAttribute("aviso", "Pergunta não cadastrada, por favor revise os campos e tente novamente");
			response.sendRedirect("Paginas/Resposta.jsp");
		}
	}

}
