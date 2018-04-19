<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>
<%@page import="com.ipartek.formacion.nidea.controller.backoffice.RolesController"%>

<h1>Roles</h1>
<div class="row">

	<div class="col-md-6">
		<a class="btn btn-outline-primari" href="backoffice/roles?op=<%=RolesController.OP_MOSTRAR_FORMULARIO%>">Crear Nuevo</a>
	</div> 

	<div class="col-md-6">
		<form action="backoffice/roles" method="get">
			<input type="hidden" name="op" value="<%=RolesController.OP_BUSQUEDA%>">
			<input type="text" name="search" required placeholder="Nombre del Rol">
			<input type="submit" value="Buscar" class="btn btn-outline-primari">	
		</form>
	</div>	

</div>


<table class="tabla table table-striped table-bordered" style="width:100%">
   <thead>
       <tr>
           <th>Rol</th>           
           <th>Id</th>               
       </tr>
   </thead>
   <tbody>
            
	<c:forEach items="${roles}" var="rol">
		<tr>			
			<td>
				<a href="backoffice/roles?id=${rol.id}&op=<%=RolesController.OP_MOSTRAR_FORMULARIO%>">${rol.nombre}</a>
			</td>		
			<td>${rol.id} </td>				
		</tr>	
	</c:forEach>
	
	</tbody>
</table>

<jsp:include page="/templates/footer.jsp"></jsp:include>