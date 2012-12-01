<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.object.Administrador"%>
<%@page import="model.object.Pergunta"%>
<%@page import="model.db.PerguntaBD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>

<%
	Administrador T = new Administrador();
	T = (Administrador) session.getAttribute("admin");

	if (T != null) {
	
%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dubitat</title>
    
<style type="text/css">
body {
	background-color: #CFCFCF;
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
}

#login {
	height: 100px;
	width: 228px;
	margin-left: 770px;
	position: static;
	left: 4px;
	top: 250px;
}

#topo {
	height: 160px;
	width: 1000px;
	color: #000;
}

#corpo {
	height: 600px;
	width: 1000px;
	padding-top: 0px;
	color: #999;
}
#menu {
	height: 45px;
	width: 1000px;
	position: relative;
}

</style>

<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"../../Scripts/SpryMenuBarDownHover.gif", imgRight:"../../Scripts/SpryMenuBarRightHover.gif"});
</script>
<script src="../../Scripts/SpryMenuBar.js" type="text/javascript"></script>
<link href="../../Styles/Styles.css" rel="stylesheet" type="text/css" />

</head>

<body>
<center>
  <!-- Topo -->
    <div class="topo" id="topo">
    	<br /> <img src="../../Imagens/dubitatlogo.png" width="300" height="114" align="left" /> <br /><br /><br />
        <!-- Texto Acessibilidade -->
    	<img src="../../Imagens/acesso.png" align="right"  />
   </div>
    
  <!-- Menu -->
  <div class="menu" id="menu"> 
  <ul id="MenuBar1" class="MenuBarHorizontal">
   	  <li><a href="../../index.jsp">IN&Iacute;CIO</a></li>
      <li><a href="Historico.jsp">HIST&Oacute;RICO</a></li>
      <li><a href="FazerPergunta.jsp">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
    
  <!-- Conteudo do Site -->
  <%
  			ArrayList<Pergunta> pergunta = (ArrayList<Pergunta>)session.getAttribute("perguntas");
  		
  			
  		if (pergunta != null) {
  %>
<div class="corpo" id="corpo">
	<br><br>
	<div class="login" id="login"> 
      
          <div align="right">
              Ola Administrador, seja bem vindo<br>
              <a href="admin.jsp" >INICIO</a> |
              <a href="../../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
     </div>
    
     <table style="margin-left:-100px;">
  		<tr>
  			
  			<th>Disciplina</th>
  			<th>Aluno</th>
  			<th>Matricula</th>
  			<th>Data </th>
  			<th>Prioridade</th>
  			<th colspan=4>Opções</th>
  	</tr>
  	<%
  		Iterator<Pergunta> it = pergunta.iterator();
  				while (it.hasNext()) {
  					Pergunta p = (Pergunta) it.next();
  					String ticket = p.getTicket();
  					String msg = p.getMensagem();
  					String disc = p.getDisciplina().getNome();
  					String data = p.getDataHora().toString();
  					String nomeA = p.getNomeAluno();
  					String matr = p.getMatriculaAluno();
  					String prio = p.getPrioridade();
  	%>
  	<tr>
  		
  		<td><%=disc%></td>
  		<td><%=nomeA%></td>
  		<td><%=matr%></td>
  		<td><%=data%></td>
  		<td align="center"><%=prio%></td>
  		<%
  			String editar = "EditarPergunta.jsp?ticket=" + ticket
  								+ "&msg=" + msg + "&disc=" + disc + "&prio="
  								+ prio;
  		%>
  		<td><a href="<%=editar%>">Analizar</a></td>
  		<td><a href="../../ExcluirPerguntaServlet?ticket=<%=ticket%>"><img src="../../Imagens/excluir.png"></a></td>
  		<td><a href="../../LiberarPerguntaServlet?ticket=<%=ticket%>">Liberar</a></td>
  		
  	</tr>
  	<%
  		}
  	%> <!-- Fim While -->
  </table>
  </div>
<%
	} /*Fim IF*/
		else {
%>
  <div class="corpo" id="corpo">
  	Nao ha Perguntas pendentes de aprovação
  	 
      <!-- Login -->
      <div class="login" id="login"> 
      
          <div align="right">
			
              
              Ola Tutor, seja bem vindo<br>
              <a href="admin.jsp" >INICIO</a>|
              <a href="../../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
      <br/>
    	
      <%
    	      	}
    	      %> <!-- Fim ELSE -->
  </div>
  <br>  
 
  </div> 
  <br><br><br>  
</center>

</body>
</html>

<%
	} else {
		response.sendRedirect("../../index.jsp");
	}
%>