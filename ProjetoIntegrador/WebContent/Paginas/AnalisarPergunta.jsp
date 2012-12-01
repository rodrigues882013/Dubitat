<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.object.Tutor"%>
<%@page import="model.object.Pergunta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<%
	Tutor T = (Tutor)session.getAttribute("tutor");

	if(T != null){
		String nome = (String)T.getNome();
		String matricula = (String)T.getMatricula();
		String msg = (String)request.getParameter("msg");

		
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
   	  <li><a href="../index.jsp">IN&Iacute;CIO</a></li>
      <li><a href="Historico.jsp">HIST&Oacute;RICO</a></li>
      <li><a href="FazerPergunta.jsp">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
    
  <!-- Conteudo do Site -->
  <% ArrayList<Pergunta> pergunta = (ArrayList<Pergunta>) session.getAttribute("pergunta");
  	if(pergunta != null){
  %>
<div class="corpo" id="corpo">
	<br><br>
	<div class="login" id="login"> 
          <div align="right">
              Ola Tutor, seja bem vindo<br>
              <a href="AvaliarPerguntas.jsp" >INICIO</a> |
              <a href="../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
     </div>
     <div align="left"><label for="Pergunta">Pergunta: </label><br />
    	<textarea name="Pergunta" cols="60" rows="10"><%=msg %></textarea>
      	<br /> <br />
    </div>
    
    
  </div>
<%} /*Fim IF*/
else{
 %>
  <div class="corpo" id="corpo">
  	<br><br>
      <!-- Login -->
      <div class="login" id="login"> 
      
          <div align="right">
			
              
              Ola Tutor, seja bem vindo<br>
              <a href="AnalisarPerguntas.jsp" >VOLTAR</a>
              <a href="../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
      <br/>
    	<label for="Pergunta"><div align="left">*Pergunta: </label><br />
    	<textarea name="Pergunta" cols="60" rows="10"></textarea>
      <br /> <br />
      <%} %> <!-- Fim ELSE -->
  </div>
  <br>  
  </div> 
  <br><br><br>  
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