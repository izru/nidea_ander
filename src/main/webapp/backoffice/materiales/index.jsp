<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>
<%@page import="com.ipartek.formacion.nidea.controller.backoffice.MaterialesController"%>

Buscador:
<!-- <form action="materiales-backoffice" method="get"> -->
<form action="materiales" method="get">
	<input type="hidden" name="op" value="<%=MaterialesController.OP_BUSQUEDA%>"></input>	
	<input type="text" name="search" required placeholder="Nombre material"></input>
	<input type="submit" value="buscar"></input>
</form> 

<!-- Example DataTables Card-->   
        
<div class="card mb-3">
	<div class="card-header">
    	<i class="fa fa-table"></i> Data Table Materiales
    </div>
    <div class="card-body">
		<div class="table-responsive">
            <div id="dataTable_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4">
            <div class="row">
            	<div class="col-sm-12 col-md-6">
            		<div class="dataTables_length" id="dataTable_length">
            			<label>Ver
            				<select name="dataTable_length" aria-controls="dataTable" class="form-control form-control-sm">
            					<option value="10">10</option>
            					<option value="25">25</option>
            					<option value="50">50</option>
            					<option value="100">100</option>
            				</select> entradas</label>
            			</div>
            		</div>
            		<div class="col-sm-12 col-md-6">
            			<div id="dataTable_filter" class="dataTables_filter">
            				Buscador:
            				<form action="backoffice/materiales" method="get">
								<input type="text" name="search" required placeholder="Nombre material"></input>
								<input type="submit" value="buscar"></input>
							</form>            				
            			</div>
            		</div>
            	</div>
            	<div class="row">
            		<div class="col-sm-12">
            			<table class="table table-bordered dataTable" id="dataTable" width="100%" cellspacing="0" role="grid" aria-describedby="dataTable_info" style="width: 100%;">
              				<thead>
                				<tr role="row">
                					<th class="sorting_asc" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Nombre: activate to sort column descending" style="width: 55px;">Nombre</th>
                					<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Precio: activate to sort column ascending" style="width: 64px;">Precio</th>
                					<th class="sorting" tabindex="0" aria-controls="dataTable" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 48px;"></th>                	
                				</tr>
              				</thead>
              				<tfoot>
                				<tr>
                					<th rowspan="1" colspan="1">Nombre</th>
                					<th rowspan="1" colspan="1">Precio</th>
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
      										<a href="backoffice/materiales?id=${material.id}&op=<%=MaterialesController.OP_MOSTRAR_FORMULARIO%>&nombre=${material.nombre}&precio=${material.precio}">
      									    	${material.nombre} 
      									    	</a>
      									</td>
      									<td> <span class="${clase}"> ${material.precio} &euro;</span></td>  
      									<td>
      										<a href="#"><i class="fa fa-pencil" aria-hidden="true"></i></a> 
      										<a href="#"><i class="fa fa-trash-o" aria-hidden="true"></i></a>  
      									</td>   
      								</tr>
     							</c:forEach>
                			</tbody>
            			</table>
            		</div>
            	</div>
            	<div class="row">
            		<div class="col-sm-12 col-md-5">
            			<div class="dataTables_info" id="dataTable_info" role="status" aria-live="polite">Showing 1 a 10 de entradas</div>
            		</div>
            		<div class="col-sm-12 col-md-7">
            			<div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
            				<ul class="pagination">
            					<li class="paginate_button page-item previous disabled" id="dataTable_previous">
            						<a href="#" aria-controls="dataTable" data-dt-idx="0" tabindex="0" class="page-link">Previous</a>
            					</li>
            					<li class="paginate_button page-item active">
            						<a href="#" aria-controls="dataTable" data-dt-idx="1" tabindex="0" class="page-link">1</a>
            					</li>
            					<li class="paginate_button page-item ">
            						<a href="#" aria-controls="dataTable" data-dt-idx="2" tabindex="0" class="page-link">2</a>
            					</li>
            					<li class="paginate_button page-item ">
            						<a href="#" aria-controls="dataTable" data-dt-idx="3" tabindex="0" class="page-link">3</a>
            					</li>
            					<li class="paginate_button page-item ">
            						<a href="#" aria-controls="dataTable" data-dt-idx="4" tabindex="0" class="page-link">4</a>
            					</li>
            					<li class="paginate_button page-item ">
            						<a href="#" aria-controls="dataTable" data-dt-idx="5" tabindex="0" class="page-link">5</a>
            					</li>
            					<li class="paginate_button page-item ">
            						<a href="#" aria-controls="dataTable" data-dt-idx="6" tabindex="0" class="page-link">6</a>
            					</li>
            					<li class="paginate_button page-item next" id="dataTable_next">
            						<a href="#" aria-controls="dataTable" data-dt-idx="7" tabindex="0" class="page-link">Next</a>
            					</li>
            				</ul>
            			</div>
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
    $('#example').DataTable();
} );
</script>


        
