package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.MonitorBD;
import model.object.Monitor;

/**
 * Servlet implementation class LiberarMonitorServlet
 */
public class LiberarMonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiberarMonitorServlet() {
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
		String matr = (String)request.getParameter("matr");
		String cod = (String)request.getParameter("disc");
		
		Monitor M = MonitorBD.listaMonitorPorMatricula(matr);
		
		M.setStatus();
		if(MonitorBD.alterarStatus("ATIVO", matr)){
			if(MonitorBD.ativarMonitoria(matr, cod)){
				session.setAttribute("Aviso", "Monitoria Liberada");
				response.sendRedirect("Paginas/RespostaTutor.jsp");
			}
			else{
				session.setAttribute("Aviso", "Aluno nao pode ser liberado, verifique com o administrador");
				response.sendRedirect("Paginas/RespostaTutor.jsp");
			}
			
		}
		else{
			session.setAttribute("Aviso", "Aluno nao pode ser liberado, verifique com o administrador");
			response.sendRedirect("Paginas/RespostaTutor.jsp");
		}
	}

}
