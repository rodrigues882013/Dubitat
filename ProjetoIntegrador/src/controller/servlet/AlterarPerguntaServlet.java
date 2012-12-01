package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.DisciplinaBD;
import model.db.PerguntaBD;
import model.object.Disciplina;
import model.object.Pergunta;

/**
 * Servlet implementation class AlterarPerguntaServlet
 */
public class AlterarPerguntaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterarPerguntaServlet() {
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
		Disciplina D = DisciplinaBD.listaDisciplina(DisciplinaBD.listaDisciplinaPorCodigo((String)request.getParameter("Disciplina")));
		P.setMensagem((String)request.getParameter("Pergunta"));
		P.setDisciplina(D);
		P.setPrioridade((String)request.getParameter("Prioridade"));
		
		if(PerguntaBD.updatePergunta(P)){
			response.sendRedirect("Paginas/AreaTutor.jsp");
		}
		else{
			
		}
		
	}

}
