<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>


<%
//ArrayList<Material> lista = (ArrayList<Material>)request.getAttribute("materiales");
//mejor usamos EL => Expresion Lenguage => ${}
//Podemos usar cualquier expresion ,si no hay expresion pinta la variable
%>

<div class="content-wrapper">
    <div class="container-fluid">
      <!-- Breadcrumbs-->
      <ol class="breadcrumb">
        <li class="breadcrumb-item">
          <a href="#">Dashboard</a>
        </li>
        <li class="breadcrumb-item active">Backoffice</li>
      </ol>
      <!-- Icon Cards-->
      <div class="row">
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-primary o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-comments"></i>
              </div>
              <div class="mr-5">Materiales</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="materiales-backoffice">
              <span class="float-left">Detalle</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-warning o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-list"></i>
              </div>
              <div class="mr-5">11 New Tasks!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-success o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-shopping-cart"></i>
              </div>
              <div class="mr-5">123 New Orders!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-3">
          <div class="card text-white bg-danger o-hidden h-100">
            <div class="card-body">
              <div class="card-body-icon">
                <i class="fa fa-fw fa-support"></i>
              </div>
              <div class="mr-5">13 New Tickets!</div>
            </div>
            <a class="card-footer text-white clearfix small z-1" href="#">
              <span class="float-left">View Details</span>
              <span class="float-right">
                <i class="fa fa-angle-right"></i>
              </span>
            </a>
          </div>
        </div>
      </div>



<!-- ${materiales} saca listado de materiales -->
<ol>
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
      	<li>  ${material.nombre} - <span class="${clase}"> ${material.precio} &euro;</span></li>     
      		
     </c:forEach>
</ol>


<jsp:include page="/templates/footer.jsp"></jsp:include>