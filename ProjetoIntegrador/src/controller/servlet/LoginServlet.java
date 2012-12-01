package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.AdministradorBD;
import model.db.MonitorBD;
import model.db.PerguntaBD;
import model.db.TutorBD;
import model.object.*;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String login = (String)request.getParameter("Login"); //Matricula
		String senha = (String)request.getParameter("Senha");
		
		
		//Esse usuario é um tutor
		if((login.length() >= 10) && (login.length() <= 12)){
			Monitor M = MonitorBD.validarLogin(login, senha);
			
			if(M == null){
				session.setAttribute("aviso2","Usuario ou senha invalido/nao cadastrado");
				response.sendRedirect("index.jsp");
			}
			else{
				ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();
				Set<String> disciplina = M.getDisciplina();
				Iterator it = disciplina.iterator();
				
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
				
				response.sendRedirect("Paginas/AreaMonitor2.jsp");
			}
		}
		else{
			//Usuario Root
			if(login.length() == 5){
				Administrador S = AdministradorBD.validarLogin(login, senha);
				
				if(S == null){
					session.setAttribute("aviso2","Usuario ou senha invalido/nao cadastrado");
					response.sendRedirect("index.jsp");
				}
				else{
					session.setAttribute("admin", S);
					response.sendRedirect("Paginas/Admin/admin.jsp");
				}
			}
			//Usuario Tutor
			else{
				Tutor T = TutorBD.validarLogin(login, senha);
				if(T == null){
					session.setAttribute("aviso2","Usuario ou senha invalido/nao cadastrado");
					response.sendRedirect("index.jsp");
				}
				else{
					
						ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();
						Set<String> disciplina = T.getDisciplina();
						Iterator it = disciplina.iterator();
						
						
					
							session.setAttribute("tutor", T);
						
						
						response.sendRedirect("Paginas/AreaTutor.jsp");
					
				}
			}
		}
		
	}

}
