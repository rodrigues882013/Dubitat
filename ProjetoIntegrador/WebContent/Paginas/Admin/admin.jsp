<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.object.Administrador"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<%
	Administrador A = new Administrador();
	A = (Administrador)session.getAttribute("admin");

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
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"../../Scripts/SpryMenuBarDownHover.gif", imgRight:"../../ProjetoIntegrador/Scripts/SpryMenuBarRightHover.gif"});
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
   	  <li><a href="../../LogoffServlet">IN&Iacute;CIO</a></li>
      <li><a href="../../DisciplinaServlet6">HIST&Oacute;RICO</a></li>
      <li><a href="../../DisciplinaServlet">FAZER PERGUNTA</a></li>
      <li><a href="../Ticket.jsp">VISUALIZAR TICKET</a></li>

      
    </ul>
  </div>
    
   <!-- Conteudo do Site -->
<div class="corpo" id="corpo">

	 <!-- Login -->
      <br/>
      <div class="login" id="login"> 
          <div align="right">
              Ola Administrador, seja bem vindo<br>
              <a href="../../LogoffServlet" >SAIR</a>            
          </div>   <!-- Fecha div de alinhamento -->
      	<br>
     	
     	
  	 </div>
     
      <!-- Botoes de Acesso rápido -->
      <div class="botoes" id="botoes">
      <center>
      	<img src="../../Imagens/btAdm.png" width="808" height="165" border="0" align="absmiddle" usemap="#Map" />
        <map name="Map" id="Map">
      	<!--Demarcação dos botões -->
        <area shape="rect" coords="16,21,239,141" href="../../DisciplinaServlet5" /> 
        <area shape="rect" coords="184,17,508,142" href="../../ListarPerguntasServlet" />
        <area shape="rect" coords="354,18,676,142" href="../../ListarMonitoresServlet" />
        <area shape="rect" coords="354,18,800,142" href="../../ListarTutoresServlet" />
      </map>
      </center>
      </div>   
      <!-- Fecha div botoes -->

  </div>  
      
      
  

      
      
  </div>   
</center>
</body>
</html>

<%
}
else
{
	response.sendRedirect("../../index.jsp");
}
%>