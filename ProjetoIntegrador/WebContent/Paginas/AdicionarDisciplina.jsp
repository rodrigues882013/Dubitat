<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.object.Monitor"%>
<%@page import="model.object.Disciplina"%>
<%@page import="model.db.DisciplinaBD"%>
<%
Monitor M = (Monitor)session.getAttribute("monitor");

if(M != null){
	String nome = (String)M.getNome();
	String senha = (String)M.getSenha();
	String matricula = (String)M.getMatricula();

%>
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
    <div class="corpo" id="corpo">
 
      <!-- Login -->
      <br/>
    <div class="login" id="login"> 
      
          <div align="right">

              Ola <%=M.getNome() %>, seja bem vindo<br>
              <a href="AreaMonitor2.jsp" >INICIO</a> |
              <a href="../../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
      	<br>
    <table>
    	<tr>
    		<th>Disciplina</th>
    		<th>Incluir</th>
    	</tr>
    	<%
    		ArrayList<String> myDisc = new ArrayList<String>();
    		ArrayList<String> myDisci = new ArrayList<String>();
    		Set<String> disc = M.getDisciplina();
    		Iterator<String> iti = disc.iterator();
    		while(iti.hasNext()){
    			myDisci.add(DisciplinaBD.listaDisciplinaPorNome((String)iti.next()));
    		}
    		
    	
    		myDisc = DisciplinaBD.listaDisciplinaDiferente(disc);
    		
        	Iterator<String> it = myDisc.iterator();
        	
        	while(it.hasNext()){
        		String d = (String)it.next();
        		
		
		%>
    	<tr>
    		<td><%=d %></td>
    		<td><a href="AdicionarDisciplina2.jsp?Disciplina=<%=d%>">Incluir</a></td>
    	</tr>
    <%}  %>
    </table>
	
  </div>  
      
      
  

      
      
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