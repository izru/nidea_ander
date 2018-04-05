<%@page import="com.ipartek.formacion.nidea.controller.backoffice.MaterialesController"%>
<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>

<h1>Detalle</h1>

${material}

<form>
  <div class="form-group"> 
  	<label for="id">ID</label>   
    <input type="text" class="form-control" id="id" name="id" value="${material.id}" placeholder="ID">   
  </div>
  <div class="form-group">
  	<label for="nombre">Nombre</label>    
    <input type="text" class="form-control" id="nombre" name="nombre" value="${material.nombre}" placeholder="Nombre material">   
  </div>
  <div class="form-group">
    <label for="precio">Precio</label>
    <input type="number" class="form-control" id="precio" value="${material.precio}" name="precio" placeholder="precio">
  </div>


<jsp:include page="/templates/footer.jsp"></jsp:include>