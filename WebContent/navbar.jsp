<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- navbar -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Menu</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
        <a class="dropdown-item" href="LoginServlet">Accedi</a>
          <a class="dropdown-item" href="ListArticoliServlet">Elenco Articoli</a>
          <a class="dropdown-item" href="ListCategorieServlet">Elenco Categorie</a>
          <a class="dropdown-item" href="LogoutServlet">Logout</a>
        </div>
      </li>
    </ul>
    
    <span class="nav-link" style="color:white;">${sessionScope.utente}</span>
    
    <a class="nav-link btn btn-outline-success my-2 my-sm-0" href="PrepareSearchArticoloServlet">Cerca Articolo</a>
    <a class="nav-link btn btn-outline-success my-2 my-sm-0" href="PrepareSearchCategoriaServlet">Cerca Categoria</a>

  </div>
</nav>
