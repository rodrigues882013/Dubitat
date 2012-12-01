<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

</style>

<script type="text/javascript">
var MenuBar1 = new Spry.Widget.MenuBar("MenuBar1", {imgDown:"../Scripts/SpryMenuBarDownHover.gif", imgRight:"../Scripts/SpryMenuBarRightHover.gif"});
</script>
<script src="../Scripts/SpryMenuBar.js" type="text/javascript"></script>
<link href="../Styles/Styles.css" rel="stylesheet" type="text/css" />

</head>

<body onLoad="Limpa()">
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
      <li><a href="Historico.jsp">HIST&Oacute;RICO</a></li>
      <li><a href="../DisciplinaServlet">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
</div>
</center>
   <center>
  <!-- Conteudo do Site -->
<!-- Conteudo do Site -->
<div class="corpo" id="corpo">
     <div align="center"><br /> 
       <br />
     <b> 
	Para visualizar a sua pergunta, digite o número do ticket recebido ao fazer a mesma. </b><br/ ><br/ ><br/ ><br/ >
  </div>
  <div align="center">
       <form id="form1" name="form1" method="post" action="../VisualizaRespostaServlet">
   	<label for="Ticket">
   	  <div align="center">Ticket: </div>
   	</label>
    <div align="center">
      <input name="Ticket" type="text" id="Ticket" size="50" />
      <br/ ><br/ >
      <input name="Visualizar" type="submit" value="Visualizar" />
      
    </div>
  </form>
  
</div>  
</div> 
</center>
</body>
</html>