<%@include file="/templates/head.jsp" %>
<%@include file="/templates/navbar.jsp"%>
<%@include file="/templates/alert.jsp"%>

<h1>Materiales</h1>
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