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

import model.db.MonitorBD;
import model.db.PerguntaBD;
import model.object.Monitor;
import model.object.Pergunta;

/**
 * Servlet implementation class AtualizarGridServlet
 */
public class AtualizarGridServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizarGridServlet() {
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
		
		String login = (String)request.getParameter("Login"); //Matricula
		String senha = (String)request.getParameter("Senha");
		
		
		//Esse usuario é um tutor
		if((login.length() >= 10) && (login.length() <= 12)){
			Monitor M = MonitorBD.validarLogin(login, senha);
			
			if(M == null){
				session.setAttribute("aviso","Usuario ou senha invalido/nao cadastrado");
				response.sendRedirect("index.jsp");
			}
			else{
				ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();
				Set<String> disciplina = M.getDisciplina();
				Iterator<String> it = disciplina.iterator();
				
				while(it.hasNext()){
					String s = (String)it.next();
					if(PerguntaBD.retornaPerguntaPendenteDeResposta(s) != null){
						pergunta.addAll(PerguntaBD.retornaPerguntaPendenteDeResposta(s));
					}
				}
				
				if(!pergunta.isEmpty()){
					session.setAttribute("pergunta", pergunta);
					session.setAttribute("nome", M.getNome());
					session.setAttribute("monitor", M);
				}
				else{
					session.setAttribute("pergunta", null);
					session.setAttribute("nome", M.getNome());
					session.setAttribute("monitor", M);
				}
				
				response.sendRedirect("Paginas/Perguntas.jsp");
			}
		}
	}

}
