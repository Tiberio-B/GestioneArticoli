<!doctype html>
<html lang="it">
  <head>
    
    <jsp:include page="./header.jsp" />
    
    <!-- Custom styles for this template -->
    <link href="./assets/css/global.css" rel="stylesheet">
    <style type="text/css">
    	body {
		  padding-top: 3.5rem;
		}	
    </style>
    
    <title>Gestione degli Articoli!</title>
  </head>
  <body>
  
	<jsp:include page="./navbar.jsp"></jsp:include>
  
  
	<main role="main">

	  <!-- Main jumbotron for a primary marketing message or call to action -->
	  <div class="jumbotron" >
	    <div class="container">
	      <h1 class="display-3">Accesso effettuato come ${sessionScope.utente.nome}</h1>
	      <br/><br/>
	      <p><a class="btn btn-primary btn-lg" href="ListCategorieServlet" role="button">Tutte le Categorie &raquo;</a></p>
	    <p><a class="btn btn-primary btn-lg" href="ListArticoliServlet" role="button">Tutti gli Articoli &raquo;</a></p>
	    <p><a class="btn btn-primary btn-lg" href="PrepareSearchArticoloServlet" role="button">Cerca un Articolo &raquo;</a></p>
	    <p><a class="btn btn-primary btn-lg" href="PrepareSearchCategoriaServlet" role="button">Cerca una Categoria &raquo;</a></p>
	    </div>    
	  </div>
	
	</main>
	
	<jsp:include page="./footer.jsp" />
  </body>
</html>