package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.MonitorBD;
import model.db.TutorBD;
import model.object.Monitor;
import model.object.Tutor;

/**
 * Servlet implementation class AtualizarCadastroServlet
 */
public class AtualizarCadastroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizarCadastroServlet() {
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
		String senhaAtual = (String)request.getParameter("SenhaAtual");
		String senhaNova = (String)request.getParameter("SenhaNova");
		String matriculaAtual = (String)request.getParameter("matr");
		String email = (String)request.getParameter("E-mail");
		
		//Altera os dados do tutor
		if(matriculaAtual.length() == 8){
			if(senhaNova.compareTo(" ") != 0){
				Tutor M = new Tutor();
				M.setNome(nome);
				M.setMatricula(matriculaAtual);
				M.setSenha(senhaNova);
				M.setEmail(email);

				Tutor MM = TutorBD.validarLogin(matriculaAtual, senhaAtual);
				if(MM != null){
					if(TutorBD.updateTutor(M, matriculaAtual)){
						MM = TutorBD.validarLogin(M.getMatricula(), M.getSenha());
						session.setAttribute("tutor", MM);
						session.setAttribute("cond", "T");
						session.setAttribute("aviso", "Dados alterados com sucesso");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
					else{
						session.setAttribute("cond", "T");
						session.setAttribute("aviso", "Dados não alterados, tente novamente");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
				}
			}
			//Altera os dados do monitor
			else{
				Tutor M = new Tutor();
				M.setNome(nome);
				M.setMatricula(matriculaAtual);
				M.setEmail(email);

				Tutor MM = TutorBD.validarLogin(matriculaAtual, senhaAtual);
				if(MM != null){
					if(TutorBD.updateTutor(M, matriculaAtual)){
						MM = TutorBD.validarLogin(M.getMatricula(), M.getSenha());
						session.setAttribute("cond", 'T');
						session.setAttribute("monitor", MM);
						session.setAttribute("aviso", "Dados alterados com sucesso");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
					else{
						session.setAttribute("cond", 'T');
						session.setAttribute("aviso", "Dados não alterados, tente novamente");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
				}
			}
		
		}
		else{
			if(senhaNova.compareTo(" ") != 0){
				Monitor M = new Monitor();
				M.setNome(nome);
				M.setMatricula(matriculaAtual);
				M.setSenha(senhaNova);
				M.setEmail(email);

				Monitor MM = MonitorBD.validarLogin(matriculaAtual, senhaAtual);
				if(MM != null){
					if(MonitorBD.updateMonitor(M, matriculaAtual)){
						MM = MonitorBD.validarLogin(M.getMatricula(), M.getSenha());
						session.setAttribute("cond", 'M');
						session.setAttribute("monitor", MM);
						session.setAttribute("aviso", "Dados alterados com sucesso");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
					else{
						session.setAttribute("cond", 'M');
						session.setAttribute("aviso", "Dados não alterados, tente novamente");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
				}
			}
			
			else{
				Monitor M = new Monitor();
				M.setNome(nome);
				M.setMatricula(matriculaAtual);
				M.setEmail(email);

				Monitor MM = MonitorBD.validarLogin(matriculaAtual, senhaAtual);
				if(MM != null){
					if(MonitorBD.updateMonitor(M, matriculaAtual)){
						MM = MonitorBD.validarLogin(M.getMatricula(), M.getSenha());
						session.setAttribute("cond", 'M');
						session.setAttribute("monitor", MM);
						session.setAttribute("aviso", "Dados alterados com sucesso");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
					else{
						session.setAttribute("cond", 'M');
						session.setAttribute("aviso", "Dados não alterados, tente novamente");
						response.sendRedirect("Paginas/AtualizaResposta.jsp");
					}
				}
			}
		}
	}

}
