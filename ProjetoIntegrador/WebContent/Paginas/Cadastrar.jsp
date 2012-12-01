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
				// Nome será obrigatório (required) e terá tamanho mínimo (minLength)
				required: true, minlength: 3
			},
			Email:{
				// Email será obrigatório (required) e precisará ser um e-mail válido (email)
				required: true, email: true
			},
			Matricula:{
				// Matricula será obrigatório (required) e terá tamanho mínimo (minLength)
				required: true, minlength: 8
			},
			Senha:{
				// Senha será obrigatório (required) e terá tamanho mínimo (minLength)
				required: true
			}
		},
		// Define as mensagens de erro para cada regra
		messages:{
			Nome:{
				required: "Digite o seu nome",
				minLength: "O seu nome deve conter, no mínimo, 3 caracteres"
			},
			Email:{
				required: "Digite o seu e-mail para contato",
				email: "Digite um e-mail válido"
			},
			Matricula:{
				required: "Digite a sua Matricula",
				minLength: "A sua mensagem deve conter, no mínimo, 2 caracteres",
				maxLenght: "Matricula inválida"
			},
			Senha:{
				required: "Digite a sua Senha",
				minLength: "A sua senha deve conter, no mínimo, 4 caracteres",
				maxLenght: "A senha não pode ter mais de 12 caracteres"
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
    
  <!-- Menu -->
<div class="menu" id="menu"> 
<ul id="MenuBar1" class="MenuBarHorizontal">
   	  <li><a href="../LogoffServlet">IN&Iacute;CIO</a></li>
      <li><a href="Historico.jsp">HIST&Oacute;RICO</a></li>
      <li><a href="FazerPergunta.jsp">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
    
  <!-- Conteudo do Site -->
<div class="corpo" id="corpo">
     <br /> <br />
   Olá, se você ja tem cadastro como monitor e deseja adicionar mais uma matéria click <a href=#>aqui</a>  
   <br><br>
  <form id="form1" name="form1" method="post" action="../CadastroServlet">
   	<div align="left"><label for="Nome">Nome: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
    	<input name="Nome" type="text" id="Nome" value="" size="50" />
      <br /> 
      <br />
    </div>
    <div align="left"><label for="Matricula">Matricula:&nbsp;&nbsp; </label>
 		<input name="Matricula" type="text" id="Matricula" size="50" />
 	  <br /> 
 	  <br />
 	</div>
 	<div align="left"><label for="E-mail">E-mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
 		<input name="Email" type="text" id="E-mail" size="50" />
 	  <br /> 
 	  <br />
 	</div>
 	<div align="left"><label for="Senha">Senha:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
 		 <input name="Senha" type="password" id="Senha" size="50" />
 	  <br /> 
 	  <br />
 	</div>
 	
 	<div align="left"><label for="Tipo">Tipo de Usuario:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
 		<br>
 		Monitor
 		<input name="tipo" type="radio" value="Monitor" checked />
 		Tutor
 		<input name="tipo" type="radio" value="Tutor" />
 		<br><br>
 	</div>
 	<div align="left"><label for="Disciplina">Disciplina:&nbsp;&nbsp;</label>
      <select name="Disciplina">
        <%
            ArrayList<String> L = (ArrayList<String>) session.getAttribute("disciplina");
        	Iterator it = L.iterator();
        	while(it.hasNext()){
        		String d = (String)it.next();
        %>
        <option><%=d%></option>
        <%} %>
      </select> <br /> <br />
      <input name="Cadastrar" type="submit" value="Cadastrar" />
    </div>
   
 	</form>
</div>   
</center>
</body>
</html>