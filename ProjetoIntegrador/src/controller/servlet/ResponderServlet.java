package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.PerguntaBD;
import model.object.Pergunta;
import model.object.Resposta;

/**
 * Servlet implementation class ResponderServlet
 */
public class ResponderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponderServlet() {
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
		String resposta = (String)request.getParameter("Resposta");
		String monitor = (String)request.getParameter("matricula");
		
		Resposta R = new Resposta(resposta);
		
		if(PerguntaBD.responderPergunta(R, ticket, monitor)){
			response.sendRedirect("Paginas/Perguntas.jsp");
		}
		else{
			session.setAttribute("aviso", "Pergunta não respondida, tente novamente");
			response.sendRedirect("Paginas/Resposta.jsp");
		}
	}

}
