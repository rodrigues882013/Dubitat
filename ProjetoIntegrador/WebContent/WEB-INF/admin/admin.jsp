<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.object.Administrador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%
	Administrador A = new Administrador();
	A = (Administrador)session.getAttribute("administrador");

	if(A != null){
		
		
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
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"../ProjetoIntegrador/Scripts/SpryMenuBarDownHover.gif", imgRight:"../ProjetoIntegrador/Scripts/SpryMenuBarRightHover.gif"});
</script>
<script src="../ProjetoIntegrador/Scripts/SpryMenuBar.js" type="text/javascript"></script>
<link href="../ProjetoIntegrador/Styles/Styles.css" rel="stylesheet" type="text/css" />

</head>

<body>
<center>
  <!-- Topo -->
    <div class="topo" id="topo">
    	<br /> <img src="../ProjetoIntegrador/Imagens/dubitatlogo.png" width="300" height="114" align="left" /> <br /><br /><br />
        <!-- Texto Acessibilidade -->
    	<img src="../ProjetoIntegrador/Imagens/acesso.png" align="right"  />
   </div>
    
  <!-- Menu -->
  <div class="menu" id="menu"> 
  <ul id="MenuBar1" class="MenuBarHorizontal">
   	  <li><a href="#">IN&Iacute;CIO</a></li>
      <li><a href="../../MonitoresInativosServlet">MONITORES</a></li>
      <li><a href="FazerPergunta.jsp">TUTORES</a></li>
      <li><a href="Ticket.jsp">RELAT�RIOS</a></li>
      <li><a href="../../LogoffServlet">SAIR</a></li>
    </ul>
  </div>
    
  <!-- Conteudo do Site -->
<div class="corpo" id="corpo">
     
      <!-- Histórico -->
  <div class="resultadoConsulta" id="resultadoConsulta"> 

  </div>  
      
      
  

      
      
  </div>   
</center>
</body>
</html>

<%
}
else
{
	response.sendRedirect("../ProjetoIntegrador/index.jsp");
}
%>