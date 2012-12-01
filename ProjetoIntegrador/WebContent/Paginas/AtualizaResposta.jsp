<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<% 
		String cond = (String)session.getAttribute("cond");
		if(cond.compareTo("M") == 0){ 
	%>
	<meta http-equiv="refresh" content="3;URL=http://localhost:8080/ProjetoIntegrador/Paginas/AreaMonitor2.jsp">
	<%}else{ %>
	<meta http-equiv="refresh" content="3;URL=http://localhost:8080/ProjetoIntegrador/Paginas/AreaTutor.jsp">
	<%} %>
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
<script language="JavaScript" type="text/javascript">
var cont = 3;
function contador() {
document.getElementById('tempo').innerHTML=cont;
	if(cont == 0) {
		document.getElementById('link').style.display="block";
	}
	if (cont != 0){
		cont = cont-1;
		setTimeout("contador()", 1000);
	}
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
		
    	<%
			String aviso = (String) session.getAttribute("aviso");
			if(aviso == null)aviso = " ";
			
		%>
		<font color="red">
				<%=aviso%>
				Voce será redirecionado automaticamente em <span id="tempo">-</span>  segundos, aguarde
				<script>
   					contador();
				</script>
		</font>
      
      
  

      
      
  </div>   
</center>
</body>
</html>