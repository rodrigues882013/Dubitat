package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.DisciplinaBD;
import model.db.MonitorBD;
import model.db.TutorBD;
import model.object.Disciplina;
import model.object.Monitor;
import model.object.Tutor;

/**
 * Servlet implementation class CadastroServlet
 */
public class CadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroServlet() {
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
		
		String nome = (String) request.getParameter("Nome");
		String matricula = (String) request.getParameter("Matricula");
		String senha = (String) request.getParameter("Senha");
		String tipo = (String) request.getParameter("tipo");
		String disciplina = (String) request.getParameter("Disciplina");
		String email = (String) request.getParameter("Email");
		
		String codigo = DisciplinaBD.listaDisciplinaPorCodigo(disciplina);
		Disciplina D = new Disciplina(codigo, disciplina);
		
		System.out.println(codigo);
		
		if(tipo.compareTo("Monitor") == 0){
			Monitor M = new Monitor(nome, senha,  matricula, email, D);
			try{
				if(MonitorBD.addMonitor(M, codigo)){
					session.setAttribute("aviso", "Cadastrado com sucesso, no entanto espere um prazo de 72 horas para poder fazer login\nObrigado");
					response.sendRedirect("Paginas/Resposta.jsp");
				}
				else{
					session.setAttribute("aviso", "Cadastro não efetuado, por favor tente novamente");
					response.sendRedirect("Paginas/Resposta.jsp");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			Tutor T = new Tutor(nome, senha,  matricula, email, D);
			try{
				if(TutorBD.addTutor(T, codigo)){
					session.setAttribute("aviso", "Cadastrado com sucesso, no entanto espere um prazo de 72 horas para poder fazer login\nObrigado");
					response.sendRedirect("Paginas/Resposta.jsp");
				}
				else{
					session.setAttribute("aviso", "Cadastro não efetuado, por favor tente novamente");
					response.sendRedirect("Paginas/Resposta.jsp");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

}
