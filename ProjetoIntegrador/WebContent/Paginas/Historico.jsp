<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
</script>
<script src="../Scripts/SpryMenuBar.js" type="text/javascript"></script>
<link href="../Styles/Styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/themes/base/jquery-ui.css" type="text/css" media="all" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js" type="text/javascript"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.6/jquery-ui.min.js" type="text/javascript"></script>

<script type="text/javascript">

$(function() {
	$( "#data" ).datepicker();
});

</script>

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
      <li><a href="../DisciplinaServlet">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
    
  <!-- Conteudo do Site -->
<div class="corpo" id="corpo">
     
      <!-- Histórico -->
  <div class="resultadoConsulta" id="resultadoConsulta"> 

      </div>  
      
  <!-- Parâmetros da Pesquisa -->
  <div class="pesquisa" id="pesquisa"> 
  <br/> <br/>
  Pesquisar por: <br><br>
  <form id="form1" name="form1" method="post" action="../HistoricoServlet">
	<input type="radio" name="campo" value="Data" checked >Periodo<br>
	<input type="text" name="data" id="data"> <br><br>
	
	 <input type="radio" name="campo" value="Palavra">Palavra Chave<br>
     <input type="text" name="palavra"> <br><br>

    <%
    	ArrayList<String> L = (ArrayList<String>) session.getAttribute("disciplina");
    %>
    
    <input type="radio" name="campo" value="Disciplina">Disciplina<br>
      <select name="Disciplina">
        <%
        	Iterator it = L.iterator();
        	while(it.hasNext()){
        		String d = (String)it.next();
        %>
        <option><%=d%></option>
        <%} %>
      </select> <br><br><br><br>  
    <input name="Pesquisar" type="submit" value="Pesquisar" />
    </form>
  </div>  

      
      
  </div>   
</center>
</body>
</html>