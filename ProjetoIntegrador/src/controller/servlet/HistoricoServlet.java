package controller.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.DisciplinaBD;
import model.db.PerguntaBD;
import model.object.Pergunta;

/**
 * Servlet implementation class HistoricoServlet
 */
public class HistoricoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoricoServlet() {
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
		
		String campo = (String)request.getParameter("campo");
		
		//Busca Perguntas por Disciplina
		if(campo.compareTo("Disciplina") == 0){
			String disc = (String)request.getParameter("Disciplina");
			String cod = DisciplinaBD.listaDisciplinaPorCodigo(disc);
			ArrayList<Pergunta> P = PerguntaBD.historicoPergunta(cod);
			session.setAttribute("perguntas", P);
			
		}
		//Busca Perguntas por palavra chave
		else{
			if(campo.compareTo("palavra") == 0){
				String key = (String)request.getParameter("Disciplina");
				ArrayList<Pergunta> P = PerguntaBD.historicoPerguntaPorPalavraChave(key);
				session.setAttribute("perguntas", P);
			}
			//Busca Perguntas por data da pergunta
			else{
				
			}
		}
		response.sendRedirect("Paginas/VisualizarHistorico.jsp");
		
	}

}
