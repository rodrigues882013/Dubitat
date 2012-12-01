package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.DisciplinaBD;
import model.db.MonitorBD;

/**
 * Servlet implementation class AdicionarDisciplinaServlet
 */
public class AdicionarDisciplinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdicionarDisciplinaServlet() {
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
		
		String matricula = (String) session.getAttribute("matricula");
		
		matricula = MonitorBD.validarMatricula(matricula);
		
		if(matricula != null){
			String disc = (String)session.getAttribute("disciplina");
			String cod = DisciplinaBD.listaDisciplinaPorCodigo(disc);
			
			if(cod != null){
				if(MonitorBD.addDisciplina(matricula, cod)){
					session.setAttribute("aviso","Espere 72 horas para seu cadastro estar ativado");
					response.sendRedirect("Paginas/resposta.jsp");
				}
			}
			else{
				session.setAttribute("aviso","Tente de novo por favor");
				response.sendRedirect("Paginas/resposta.jsp");
			}
		}
		else{
			session.setAttribute("aviso","Tente de novo por favor");
			response.sendRedirect("Paginas/resposta.jsp");
		}
	}

}
