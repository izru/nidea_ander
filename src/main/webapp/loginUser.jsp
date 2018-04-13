<jsp:include page="templates/head.jsp"></jsp:include>
<jsp:include page="templates/navbar.jsp"></jsp:include>
<jsp:include page="templates/alert.jsp"></jsp:include>

<div id="loginUser">

  <form class="form-signin" action="loginUser" method="post">     

      <div class="form-label-group">
        <input type="text" class="form-control"
               name="usuario" 
               placeholder="Nombre Usuario" 
               required autofocus>
               
        <label for="usuario">Nombre Usuario</label>
      </div>

      
     
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      
    </form>

</div>
<jsp:include page="templates/footer.jsp"></jsp:include>