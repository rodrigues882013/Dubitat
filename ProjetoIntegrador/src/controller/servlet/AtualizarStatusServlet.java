package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.MonitorBD;
import model.object.Monitor;

/**
 * Servlet implementation class AtualizarStatusServlet
 */
public class AtualizarStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AtualizarStatusServlet() {
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
		
		String matricula = (String)request.getParameter("matr");
		Monitor M = MonitorBD.listaMonitorPorMatricula(matricula);
		String status = (String)request.getParameter("status");
		
		if(status.compareTo("INATIVO") == 0){
			M.setStatus();
		}
		else{
			M.setStatus("INATIVO");
		}
		
		if(MonitorBD.alterarStatus(M.getStatus(), matricula)){
			ArrayList<Monitor> MM = (ArrayList<Monitor>)MonitorBD.listaMonitor();
			session.setAttribute("monitores", MM);
			response.sendRedirect("Paginas/Admin/Monitores.jsp");
		
		}
	}

}
