<%@page import="com.ipartek.formacion.nidea.controller.backoffice.RolesController"%>
<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp" %>
<%@include file="/templates/alert.jsp" %>


<h1>Detalle</h1>

${roll}

<div class="container">
	<div class="form-group row">
		<a class="btn btn-outline-dark btn-lg" href="backoffice/roles">Volver</a>
	</div>
	<form action="backoffice/roles" method="post">
 
  	<div class="form-group row">
	    <label for="id" class="col-sm-2 col-form-label">ID</label>
	    <div class="col-sm-2">
	      <input type="text" class="form-control" name="id" readonly value="${rol.id}" placeholder="ID">
	    </div>
	 </div>
	 <div class="form-group row">
	    <label for="nombre" class="col-sm-2 col-form-label">Rol</label>
	    <div class="col-sm-5">
	      <input type="text" value="${rol.nombre}" class="form-control" name="nombre" placeholder="Introduce el nombre del rol" required >
	    </div>
	  </div>
	  
	   <div class="form-group row">
	   </div>
	<c:if test="${rol.id == -1}">
		   <div class="form-group row">
			   <div class="col-sm-12">
			   	  <input type="hidden" name="op" value="<%=RolesController.OP_GUARDAR%>"> 	
			      <button type="submit" class="btn btn-primary btn-lg btn-block">Crear</button>
			  </div>
		  </div>
		</c:if>
		  
		<c:if test="${rol.id > -1}">  
		 
			  <div class="form-group row">
			  
			    <div class="col-sm-6">
			      <input type="hidden" name="op" value="<%=RolesController.OP_GUARDAR%>"> 	
			      <button type="submit" class="btn btn-success btn-lg btn-block">Modificar</button>
			    </div>
			    <div class="col-sm-6">	
			    <!-- Button trigger modal -->
					<button type="button" class="btn btn-primary btn-lg btn-block" data-toggle="modal" data-target="#exampleModal">
  					Eliminar
					</button>		      
			     <!--  <a href="backoffice/roles?id=${rol.id}&op=<%=RolesController.OP_ELIMINAR%>" 
			       class="btn btn-danger btn-lg btn-block">Eliminar</a>-->
			    </div>
			  </div>
		</c:if>	  
	</form>
</div>



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Confirmar borrado</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Desea confirmar el borrado
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>        
        <a href="backoffice/roles?id=${rol.id}&op=<%=RolesController.OP_ELIMINAR%>" 
			       class="btn btn-danger ">Confirmar</a>
      </div>
    </div>
  </div>
</div>



<jsp:include page="/templates/footer.jsp"></jsp:include>