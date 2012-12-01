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
	color: #000;
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	position: relative;
}
#menu {
	height: 45px;
	width: 1000px;
	position: relative;
}


label.error { float: none; color: red; margin: 0 .5em 0 0; vertical-align: bottom;}
p { clear: both; }
.submit { margin-top: 1em; }
em { font-weight: bold; padding-right: 1em; vertical-align: top; }

</style>

<script language="Javascript" src="../Scripts/jQuery.js" type="text/javascript"></script>
<script language="Javascript" src="../Scripts/jquery.validate.js" type="text/javascript"></script>

<script type="text/javascript">
$(document).ready( function() {
	$("#form1").validate({
		// Define as regras
		rules:{
			Nome:{
				// Nome ser� obrigat�rio (required) e ter� tamanho m�nimo (minLength)
				required: true, minlength: 3
			},
			Email:{
				// Email ser� obrigat�rio (required) e precisar� ser um e-mail v�lido (email)
				required: true, email: true
			},
			Matricula:{
				// Mensagem ser� obrigat�rio (required) e ter� tamanho m�nimo (minLength)
				required: true, minlength: 8
			},
			Pergunta:{
				// campoMensagem ser� obrigat�rio (required) e ter� tamanho m�nimo (minLength)
				required: true
			}
		},
		// Define as mensagens de erro para cada regra
		messages:{
			Nome:{
				required: "Digite o seu nome",
				minLength: "O seu nome deve conter, no m�nimo, 3 caracteres"
			},
			Email:{
				required: "Digite o seu e-mail para contato",
				email: "Digite um e-mail v�lido"
			},
			Matricula:{
				required: "Digite a sua Matricula",
				minLength: "A sua mensagem deve conter, no m�nimo, 2 caracteres"
			},
			Pergunta:{
				required: "Digite a sua Pergunta",
				minLength: "A sua mensagem deve conter, no m�nimo, 2 caracteres"
			}
		}
	});
});
</script>
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
    	<br /><img src="../Imagens/dubitatlogo.png" width="300" height="114" align="left" />
        <br /><br /><br />
        <!-- Texto Acessibilidade -->
    	<img src="../Imagens/acesso.png" align="right"  />
   </div>
</center>
 <center>   
  <!-- Menu -->
<div class="menu" id="menu"> 
<ul id="MenuBar1" class="MenuBarHorizontal">
   	  <li><a href="../LogoffServlet">IN&Iacute;CIO</a></li>
      <li><a href="../DisciplinaServlet6">HIST&Oacute;RICO</a></li>
      <li><a href="#">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
  
<!-- Conteudo do Site -->

<div class="corpo" id="corpo">
<br>
<b>Preencha o formul�rio abaixo, para realizar a sua pergunta ao monitor correspondente a mat�ria escolhida!</b>
<br>*Campos Obrigat�rios <br><br><br>
<form id="form1" name="form1" method="post" action="../PerguntaServlet">
   	<div align="left"><label for="Nome">*Nome:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
      	<input name="Nome" type="text" id="Nome" value="" size="50" /><br /><br />
    </div>
    <div align="left"><label for="Matrícula">*Matricula:&nbsp;</label>
 		<input name="Matricula" type="text" value="" id="Matricula" size="50" /><br /><br />
 	</div>
 	<div align="left"><label for="E-mail">*E-mail:&nbsp;&nbsp;&nbsp;&nbsp;</label>
 		<input name="Email" type="text" id="E-mail" value="" size="50" /><br /> <br />
 	</div>
 	<div align="left"><label for="Prioridade">Prioridade: </label>
        <select name="Prioridade">
        <option>Alta</option>
        <option>M�dia</option>
        <option>Baixa</option>
      </select> <br /> <br />
    </div>
    <%
    	ArrayList<String> L = (ArrayList<String>) session.getAttribute("disciplina");
    %>
    <div align="left"><label for="Disciplina">Disciplina:&nbsp;</label>
      <select name="Disciplina">
        <%
        	Iterator it = L.iterator();
        	while(it.hasNext()){
        		String d = (String)it.next();
        %>
        <option><%=d%></option>
        <%} %>
      </select> <br /> <br />
    </div>
    <div align="left"><label for="Pergunta">*Pergunta: </label><br />
    	<textarea name="Pergunta" cols="60" rows="10"></textarea>
      <br /> <br />
      <input name="Enviar" type="submit" value="Enviar" />
      
    </div>
  </form>
</div>  
 

  </center> 
</body>
</html>