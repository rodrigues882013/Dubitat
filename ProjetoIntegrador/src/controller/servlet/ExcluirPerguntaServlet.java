package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.PerguntaBD;
import model.object.Disciplina;
import model.object.Pergunta;

/**
 * Servlet implementation class ExcluirPerguntaServlet
 */
public class ExcluirPerguntaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirPerguntaServlet() {
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
		String ticket = (String)request.getParameter("ticket");
		
		
		Pergunta P = PerguntaBD.listaPergunta(ticket);
		
		if(P!=null){
			//Libera o ticket
			Pergunta.removePergunta(ticket);
		
			//Remover da disciplina a pergunta
			Disciplina.removePergunta(P);
			
			//Remove do Banco
			PerguntaBD.deletaPergunta(ticket);
			ArrayList<Pergunta> pergunta = PerguntaBD.retornaPerguntaPendenteDeLiberacao();
			session.setAttribute("perguntas", pergunta);
			response.sendRedirect("Paginas/Admin/Perguntas.jsp");
		}
		else{
			
		}
		
	}

}
