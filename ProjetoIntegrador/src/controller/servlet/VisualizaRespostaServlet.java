package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.PerguntaBD;
import model.object.Pergunta;

/**
 * Servlet implementation class VisualizaResposta
 */
public class VisualizaRespostaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizaRespostaServlet() {
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
		
		//Se assegura de não ter encerrado a ultima consulta
		session.invalidate();
		
		//Sessão nova
		session = request.getSession();
		
		String ticket = (String)request.getParameter("Ticket");
		
		Pergunta p = PerguntaBD.retornaPergunta(ticket);
		if(p != null){
			session.setAttribute("pergunta", p);
			response.sendRedirect("Paginas/respostaTicket.jsp");
		}
		else{
			session.setAttribute("aviso","Pergunta ainda não respondida");
			response.sendRedirect("Paginas/respostaTicket.jsp");
		}
	}

}
