<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.object.Tutor"%>
<%@page import="model.object.Pergunta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>

<%
	Tutor M = new Tutor();
	M = (Tutor)session.getAttribute("tutor");


	if(M != null){
		String nome = (String)M.getNome();
		String matricula = (String)M.getMatricula();
		String email = (String)M.getEmail();
		
		
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
   	  <li><a href="AreaMonitor2.jsp">IN&Iacute;CIO</a></li>
      <li><a href="Historico.jsp">HIST&Oacute;RICO</a></li>
      <li><a href="FazerPergunta.jsp">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
    
  <!-- Conteudo do Site -->
<div class="corpo" id="corpo">
   <!-- Login -->
      <br/>
    <div class="login" id="login"> 
      
          <div class="login" id="login"> 
      
          <div align="right">
			
              
              Ola <%=M.getNome() %>, seja bem vindo<br>
               <a href="AreaTutor.jsp" >INICIO</a> |
              <a href="../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
      	<br>
   <div class="corpo" id="corpo">
   
     
  <form id="form1" name="form1" method="post" action="../AtualizarCadastroServlet?matr=<%=matricula%>">
   	<div align="left"><label for="Nome">Nome: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
    	<input name="Nome" type="text" id="Nome" value="<%=nome %>" size="50" />
      <br /> 
      <br />
    </div>
 	  
 	<div align="left"><label for="E-mail">E-mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
 		<input name="E-mail" type="text" id="E-mail" size="50" value="<%=email%>" />
 	  <br /> 
 	  <br />
 	  </div>
 	<div align="left"><label for="Senha">Senha Atual:</label>
 		 <input name="SenhaAtual" type="password" id="E-mail" size="50" />
 	  <br /> 
 	  <br />
 	  </div>
 	 <div align="left"> <label for="Senha">Nova Senha:</label>
 		 <input name="SenhaNova" type="password" id="E-mail" size="50" />
 	  <br /> 
 	  <br />
 	  </div>
 	<br><br>
 	<div align="left">
 	<input name="Atualizar" type="submit" value="Atualizar" />
 	<input name="Atualizar" type="reset" value="Limpar" />
 	</div>
    </form>
    </div>
 

  </div>   
</center>
</body>
</html>
<%
}
else
{
	response.sendRedirect("AreaMonitor2.jsp");
}
%>