package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.DisciplinaBD;
import model.object.Disciplina;

/**
 * Servlet implementation class NovaDisciplinaServlet
 */
public class NovaDisciplinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NovaDisciplinaServlet() {
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
		
		Integer subCod  =  DisciplinaBD.numeroDeRegistro() + 1;
		
		String novaDisc = (String)request.getParameter("Nome");
		char ini = novaDisc.charAt(0);
		subCod += 1;
		
		String codigo = "" +  ini + ini + ini + subCod;
		
		Disciplina D = new Disciplina (codigo, novaDisc);
		if(DisciplinaBD.addDisciplina(D)){
			ArrayList<Disciplina> disciplina = DisciplinaBD.listaDisciplina2();
			session.setAttribute("disciplina", disciplina);
			response.sendRedirect("Paginas/Admin/Disciplinas.jsp");
		}
		else{
			
		}
		
	}

}
