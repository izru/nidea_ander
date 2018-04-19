<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>
<%@page import="com.ipartek.formacion.nidea.controller.backoffice.MaterialesController"%>

<h1>Materiales</h1>
<div class="row">

	<div class="col-md-6">
		<a class="btn btn-outline-primari" href="backoffice/materiales?op=<%=MaterialesController.OP_MOSTRAR_FORMULARIO%>">Crear Nuevo</a>
	</div> 
	
	<div class="col-md-6">
		<form action="backoffice/materiales" method="get">
			<input type="hidden" name="op" value="<%=MaterialesController.OP_BUSQUEDA%>">
			<input type="text" name="search" required placeholder="Nombre Material">
			<input type="submit" value="Buscar" class="btn btn-outline-primari">	
		</form>
	</div>	
	<div class="col-md-6">
	</div>

</div>
<br>

<!-- Example DataTables Card-->   
        
<div class="card mb-3">
	<div class="card-header">
    	<i class="fa fa-table"></i> Data Table Materiales
    </div>
    <div class="card-body">
		<div class="table-responsive">
            <div id="dataTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
            <div class="row">    
            	</div>
            	<div class="row">
            		<div class="col-sm-12">
            			<table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
              				<thead>
                				<tr role="row">
                					<th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Nombre: activate to sort column descending" style="width: 55px;">Nombre</th>
                					<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Precio: activate to sort column ascending" style="width: 64px;">Precio</th>
                					<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Usuario: activate to sort column ascending" style="width: 64px;">Usuario</th>
                					<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 48px;"></th>                	
                				</tr>
              				</thead>
              				<tfoot>
                				<tr>
                					<th rowspan="1" colspan="1">Nombre</th>
                					<th rowspan="1" colspan="1">Precio</th>
                					<th rowspan="1" colspan="1">Usuario</th>
                					<th rowspan="1" colspan="1"></th> 
                				</tr>               
              				</tfoot>
              				<tbody>
                 				<c:forEach items="${materiales}" var ="material">	
	 								<c:set var="clase" value=""/> 	
      								<c:choose>          	     
         								<c:when test = "${material.precio >=6.0 and material.precio<25.0 }">
          									<c:set var="clase" value="blue"/>             
         								</c:when>         
         								<c:when test = "${material.precio >=25.0}">
            								<c:set var="clase" value="text-danger"/>    
         								</c:when>         
         								<c:otherwise>              	  
         								</c:otherwise>
      								</c:choose>
      								<tr role="row">      	
      									<td class="sorting_1">  
      										<a href="backoffice/materiales?id=${material.id}&op=<%=MaterialesController.OP_MOSTRAR_FORMULARIO%>">
      									    	${material.nombre} 
      									    	</a>
      									</td>
      									<td> <span class="${clase}"> ${material.precio} &euro;</span></td> 
      									<td>  ${material.usuario.nombre} </td> 
      									<td>
      										<a href="backoffice/materiales?id=${material.id}&op=<%=MaterialesController.OP_MOSTRAR_FORMULARIO%>"><i class="fa fa-pencil" aria-hidden="true"></i></a> 
      										<a href="backoffice/materiales?id=${material.id}&op=<%=MaterialesController.OP_ELIMINAR%>"><i class="fa fa-trash-o" aria-hidden="true"></i></a>  
      									</td>   
      								</tr>
     							</c:forEach>
                			</tbody>
            			</table>
            		</div>
            	</div>            	
			</div>
		</div>
	</div>
    <div class="card-footer small text-muted">Updated yesterday at 11:59 PM
    </div>
</div>
<jsp:include page="/templates/footer.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>       

<script type="text/javascript">
$(document).ready(function() {
    $('#dataTable').DataTable();
} );
</script>


        
