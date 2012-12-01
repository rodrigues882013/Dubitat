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
		
		String ticket = (String)request.getParameter("ticket");
		String msg = (String)request.getParameter("msg");
		String disc = (String)request.getParameter("disc");
		String prio = (String)request.getParameter("prio");
		String[] p = {"ALTA", "MEDIA", "BAIXA"};
		
    	ArrayList<String> L = (ArrayList<String>) session.getAttribute("disciplina");
    
		
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

#pesquisa {
	height: 550px;
	width: 250px;
	margin-top: 0px;
	position: absolute;
	color: #000;
	text-align: justify;
}
#resultadoConsulta {
	height: 550px;
	width: 750px;
	position: absolute;
	margin-left: 250px;
}

</style>

<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"../Scripts/SpryMenuBarDownHover.gif", imgRight:"../Scripts/SpryMenuBarRightHover.gif"});
function Envia(str)
{
	document.getElementById("form1").action = str;
	document.getElementById("form1").submit();
}
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
<div class="corpo" id="corpo">
	<br>
	<div class="login" id="login"> 
          <div align="right">
              Ola <%=T.getNome() %>, seja bem vindo<br>
              <a href="AvaliarPerguntas.jsp" >INICIO</a> |
              <a href="../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
   </div> 
   <form id="form1" name="form1" method="post" action="../AlterarPerguntaServlet?ticket=<%=ticket%>">
   		
 	<div align="left"><label for="Pergunta">Pergunta: </label><br />
    	<textarea name="Pergunta" cols="60" rows="10"><%=msg %></textarea>
      	<br /> <br />
    </div>
    <div align="left"><label for="Disciplina">Disciplina:&nbsp;</label>
      <select name="Disciplina">
      	<option><%=disc%></option>
        <%
        	Iterator it = L.iterator();
        	while(it.hasNext()){
        		String d = (String)it.next();
        		if(d.compareTo(disc) != 0){
        %>
        <option><%=d%></option>
        <%}} %>
      </select> <br /> <br />
    </div>
 	<div align="left"><label for="Prioridade">Prioridade: </label>
        <select name="Prioridade">
        	<option><%=prio%></option>
        	<%int i=0;
        		while(i < 3){
        			if((prio.compareTo(p[i]) != 0)){	
        		
        	%>
        	<option><%=p[i] %></option>
        	<%}i++;} %>
      	</select> <br /> <br />
    </div>
    <br><br>
    <div align="left">
    	<input type="button" value="Alterar" onClick="Envia('../AlterarPerguntaServlet?ticket=<%=ticket%>')">
    	<input type="button" value="Excluir" onClick="Envia('../ExcluirPerguntaServlet?ticket=<%=ticket%>')">
    	<input type="button" value="Excluir" onClick="Envia('../LiberarPerguntaServlet?ticket=<%=ticket%>')">
    </div>
    </form>
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