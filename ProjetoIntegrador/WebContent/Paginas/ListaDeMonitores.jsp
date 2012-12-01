<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="model.object.Tutor"%>
<%@page import="model.object.Disciplina"%>
<%@page import="model.object.Monitor"%>
<%@page import="model.db.MonitorBD"%>
<%@page import="model.object.Pergunta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>

<%
	Tutor T = (Tutor)session.getAttribute("tutor");

	if(T != null){
		String nome = (String)T.getNome();
		String matricula = (String)T.getMatricula();
		String atualizar = "CadastroTutor.jsp?" + "Nome="+ nome + "&Matricula=" + matricula + "&Email=" + T.getEmail();
		
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
   	  <li><a href="../index.jsp">IN&Iacute;CIO</a></li>
      <li><a href="Historico.jsp">HIST&Oacute;RICO</a></li>
      <li><a href="FazerPergunta.jsp">FAZER PERGUNTA</a></li>
      <li><a href="Ticket.jsp">VISUALIZAR TICKET</a></li>
    </ul>
  </div>
    
  <!-- Conteudo do Site -->
  <% ArrayList<Monitor> M = new ArrayList<Monitor>();
  	Set<String> disciplina = T.getDisciplina();
  	ArrayList<String> aux = new ArrayList<String>();
	Iterator it2 = disciplina.iterator();
  	while(it2.hasNext()){
		String s = (String)it2.next();
		if(MonitorBD.listaMonitoresMonitora(s) != null){
			aux.add(s);
			M.addAll(MonitorBD.listaMonitoresMonitora(s));
			
		}
	}
  	%>
<div class="corpo" id="corpo">
	<br><br>
	<div class="login" id="login"> 
          <div align="right">
              Ola <%=T.getNome() %>, seja bem vindo<br>
               <a href="../AreaTutor.jsp" >INICIO</a> |
              <a href="../LogoffServlet" >SAIR</a>
             
          </div>   <!-- Fecha div de alinhamento -->
   </div> 
   <% if(!M.isEmpty()){%>
    <strong>Lista de Monitores cadastrados no sistema</strong><br><br>
    <table>
    	<tr>
    		<th>Matricula</th>
    		<th>Nome</th>
    		<th>Disciplina</th>
    		<th colspan=2>Opções</th>
    	</tr>
    	<%
  		
		Iterator<Monitor> it = M.iterator();
    	int i=0;
  		while(it.hasNext()){ 
  			Monitor p = (Monitor) it.next();
  			String matr = p.getMatricula();
  			String nomeM = p.getNome();
  			Disciplina disc = p.getDisciplina(aux.get(i));
  			String nomeDisc = disc.getNome();
  			i++;
  	%>
  	<tbody>
  	<tr>
  		<td><%=matr %></td>
  		<td><%=nomeM %></td>
  		<td><%=nomeDisc %></td>
  		<td><a href="../LiberarMonitorServlet?matr=<%=matr%>&disc=<%=disc.getCodigo()%>">Autorizar</a></td>
  		<td><a href="#">Notificar</a></td>
  	</tr>
  	<%} %>
    </tbody>	
    </table> 
    <br><br>
    <%} else{%>
    <strong>Não há monitores para serem liberados</strong>
    <%} %>
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