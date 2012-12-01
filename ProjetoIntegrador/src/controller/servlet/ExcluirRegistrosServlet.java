package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ExcluirRegistrosServlet
 */
public class ExcluirRegistrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcluirRegistrosServlet() {
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
		HttpSession session  = request.getSession();
		
		String matricula = (String)request.getParameter("matr");
		
		if(matricula.length() == 8){
			if(TutorBD.deleteTutor(matricula)){
				ArrayList<Tutor> M = (ArrayList<Tutor>)TutorBD.listaTutor();
				session.setAttribute("tutores", M);
				response.sendRedirect("Paginas/Admin/Tutores.jsp");
			}
		}
		else{
			
			if(MonitorBD.deleteMonitor(matricula)){
				ArrayList<Monitor> M = (ArrayList<Monitor>)MonitorBD.listaMonitor();
				session.setAttribute("monitores", M);
				response.sendRedirect("Paginas/Admin/Monitores.jsp");
			}
		}
		
	}

}
