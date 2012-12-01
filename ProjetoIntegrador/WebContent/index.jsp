<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
#texto {
	height: 370px;
	width: 750px;
	margin-top: 0px;
	position: absolute;
	top: 220px;
	color: #000;
	text-align: justify;
}
#login {
	height: 366px;
	width: 228px;
	margin-left: 770px;
	position: static;
	left: 4px;
	top: 250px;
}
#botoes {
	height: 200px;
	width: 1000px;
	position: static;
	left: 10px;
	top: 620px;
}
</style>

<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"Scripts/SpryMenuBarDownHover.gif", imgRight:"Scripts/SpryMenuBarRightHover.gif"});
setInterval ("window.status = ''",10); 
</script>
<script src="Scripts/SpryMenuBar.js" type="text/javascript"></script>
<link href="Styles/Styles.css" rel="stylesheet" type="text/css" />

</head>

<body>
<center>
  <!-- Topo -->
    <div class="topo" id="topo">
    	<br /> <a href="index.jsp"><img src="Imagens/dubitatlogo.png" width="300" height="114" align="left" /></a> <br /><br /><br />
        <!-- Texto Acessibilidade -->
    	<img src="Imagens/acesso.png" align="right"  />
   </div>
<center>
  <!-- Menu -->
  <div class="menu" id="menu"> 
  <ul id="MenuBar1" class="MenuBarHorizontal">
   	  <li><a href="LogoffServlet" >IN&Iacute;CIO</a></li>
      <li><a href="DisciplinaServlet6">HIST&Oacute;RICO</a></li>
      <li><a href="DisciplinaServlet">FAZER PERGUNTA</a></li>
      <li><a href="Paginas/Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
  </center>
 
  <!-- Conteudo do Site -->
  <center>
<div class="corpo" id="corpo">
     
      <!-- Login -->
      <br/>
    <div class="login" id="login"> 
      <form id="form1" name="form1" method="post" action="LoginServlet">
          <div align="right">
			
              <label for="Login"><img src="Imagens/cadeado.png" width="29" height="29" align="absmiddle" />&nbsp;&nbsp;&nbsp;Login</label><br/>
              <input type="text" name="Login" id="Login" /><br/>
              <label for="Senha">Senha</label><br/>
              <input type="password" name="Senha" id="Senha" /><br/><br/>
               <a href="DisciplinaServlet2">Cadastre-se</a>&nbsp;
              <input name="Enviar" type="submit" value="Enviar" /><br/>
             
          </div>   <!-- Fecha div de alinhamento -->
          <div alig="center" style="color: red;"><% String aviso=(String)session.getAttribute("aviso2"); if(aviso == null)aviso=" "; %><%=aviso %></div>
      </form>
      
      </div>  <!-- Fecha div login -->
      
      <!-- Texto Sobre o Sistema -->
<div class="texto" id="texto"> 
	
        <br />Bem vindo ao Sistema Dubitat,<br /><br />
            Este sistema tem por finalidade, fazer uma ponte comunicativa 'online', entre os monitores e alunos do Instituto Superior de Tecnologia em Ciência da Compuatação do Rio de Janeiro (IST - Rio). E tem como objetivo tirar duvida sucintas dos alunos. Além dos monitores de cada disciplina, teremos o apoio dos professores (tutores) para sanar a duvida, caso a mesma não tenha sido esclarecida pelo monito da disciplina. <br />
        Para enviar uma pergunta é só clicar na opção do menu acima, ou no botão de acesso rápido, logo abaixo. Se desejar visualizar a sua pergunta, clique no botão visualizar ticket no menu acima ou no acesso rápido abaixo e digite o valor do ticket recebido ao ter feito a pergunta. <br />
    Esperamos que faça bom uso do sistema e que suas duvidas seja esclarecidas. <br /><br />
        Att,<br />
    Desenvolvedores.
  </div>
      <!-- Fecha div texto -->
     
<!-- Botoes de Acesso rÃ¡pido -->
      <div class="botoes" id="botoes">
      <center>
      	<img src="Imagens/botoes.png" width="500" height="165" border="0" align="absmiddle" usemap="#Map" />
        <map name="Map" id="Map">
      	<!--DemarcaÃ§Ã£o dos botÃµes -->
        <area shape="rect" coords="16,21,139,141" href="Paginas/FazerPergunta.jsp" /> 
        <area shape="rect" coords="184,17,308,142" href="Paginas/Historico.jsp" />
        <area shape="rect" coords="354,18,476,142" href="Paginas/Ticket.jsp" />
      </map>
      </center>
      </div>   
      <!-- Fecha div botoes -->
      
      
  </div>   
</center>
</body>
</html>
