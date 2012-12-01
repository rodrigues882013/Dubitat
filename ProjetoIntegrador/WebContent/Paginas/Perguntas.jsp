<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.object.Monitor"%>
<%@page import="model.object.Pergunta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.db.PerguntaBD"%>

<%
	Monitor M = (Monitor)session.getAttribute("monitor");

	if(M != null){
		String nome = (String)M.getNome();
		String senha = (String)M.getSenha();
		String matricula = (String)M.getMatricula();
		String atualizar = "Cadastro.jsp?" + "Nome="+ nome + "&Matricula=" + matricula + "&Email=" + M.getEmail();
		
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
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"../Scripts/SpryMenuBarDownHover.gif", imgRight:"../Scripts/SpryMenuBarRightHover.gif"});
</script>
<script src="../Scripts/SpryMenuBar.js" type="text/javascript"></script>
<link href="../Styles/Styles.css" rel="stylesheet" type="text/css" />

</head>

<body>
<center>
  <!-- Topo -->
    <div class="topo" id="topo">
    	<br /> <img src="../Imagens/dubitatlogo.png" width="300" height="114" align="left" /> <br /><br /><br />
        <!-- Texto Acessibilidade -->
    	<img src="../Imagens/acesso.png" align="right"  />
   </div>
    
  <!-- Menu -->
  <div class="menu" id="menu"> 
  <ul id="MenuBar1" class="MenuBarHorizontal">
   	  <li><a href="../LogoffServlet">IN&Iacute;CIO</a></li>
      <li><a href="Historico.jsp">HIST&Oacute;RICO</a></li>
      <li><a href="FazerPergunta.jsp">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>

      
    </ul>
  </div>
   <br>
  <!-- Conteudo do Site -->
  <% 
  	ArrayList<Pergunta> pergunta = new ArrayList<Pergunta>();
	Set<String> disciplina = M.getDisciplina();
	Iterator it2 = disciplina.iterator();
	
	while(it2.hasNext()){
		String s = (String)it2.next();
		if(PerguntaBD.retornaPerguntaPendenteDeResposta(s) != null){
			pergunta.addAll(PerguntaBD.retornaPerguntaPendenteDeResposta(s));
		}
	}
  	if(pergunta != null){
  %>
<div class="corpo" id="corpo">
      <!-- Login -->
      <br/>
      <div class="login" id="login"> 
          <div align="right">
              Ola <%=M.getNome() %>, seja bem vindo<br>
               <a href="AreaMonitor2.jsp" >INICIO</a> |
              <a href="../LogoffServlet" >SAIR</a>            
          </div>   <!-- Fecha div de alinhamento -->
      	<br>
     	<br>
     	<br>
  	 </div>
  	
  		<table>
  			<tr>
  				<th>Ticket</th>
  				<th>Pergunta</th>
  				<th>Disciplina</th>
  				<th>Data da Pergunta</th>
  				<th>Responder</th>
  			</tr>
  	<%
  		
		Iterator<Pergunta> it = pergunta.iterator();
  		while(it.hasNext()){ 
  			Pergunta p = (Pergunta) it.next();
  			String ticket = p.getTicket();
  			String msg = p.getMensagem();
  			String disc = p.getDisciplina().getNome();
  			String data = p.getDataHora().toString();
  	%>
  		<tr>
  			<td><%=ticket %></td>
  			<td><%=msg %></td>
  			<td><%=disc %></td>
  			<td><%=data%></td>
  			<td><a href="Responder.jsp?ticket=<%= ticket %>">Responder</a></td>
  		</tr>
  	<%} %>
  </table>
  <br><br>
    <form action="../AtualizarGridServlet?Login=<%= matricula %>&Senha=<%= senha %>" method="post">
    	<input type="submit" value="Atualizar">
    </form>
<%}
 else{
 %>
  
  	Nao ha Perguntas para responder
      <!-- Login -->
    <br/>
    <div class="login" id="login"> 
          <div align="right">
              Ola Monitor, seja bem vindo<br>
              <a href="../LogoffServlet" >SAIR</a>
          </div>   <!-- Fecha div de alinhamento -->
      <%} %>
  	</div>
  	</div>
</center>
</body>
</html>
<%
}
else
{
	response.sendRedirect("../index.jsp");
}
%>
