<!-- ya no usamos esto

<jsp:include page="templates/head.jsp"></jsp:include>
<jsp:include page="templates/navbar.jsp"></jsp:include>
<jsp:include page="/templates/alert.jsp"></jsp:include> -->

<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>

<%
	// Scriplet < %  ...   % >
	// varias sentencias 
	String nombre = "pepe";
	String hora = "10:78";
	
	//lanza adrede un Null PointerException y nos mostrara la pagina error.jsp
	//hemos configurado este comportamiento en web.xml con error<error-page>
	String nulo =null;
	nulo.length();

%>

<h2>Hello <%=nombre%></h2>
<p><%=hora%></p>

<a href="generar-mesa"> ¿Quieres Comprar una Mesa ?</a>


<jsp:include page="templates/footer.jsp"></jsp:include>