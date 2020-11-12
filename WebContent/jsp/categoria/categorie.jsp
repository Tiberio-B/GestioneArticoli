<%@page import="it.gestionearticoli.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Elenco delle Categorie</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="../navbar.jsp" />
	
	<main role="main" class="container">
	
	<jsp:include page="../error-message.jsp" />
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<input type="hidden" name="listaCategorie" value="${requestScope.listaCategorie}">
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Elenco delle Categorie${searched==true? ' che soddisfano i parametri della ricerca': ''}</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary ${sessionScope.cannotInsert?'disabled':''}" href="PrepareInsertCategoriaServlet" aria-disabled="${sessionScope.cannotInsert}">Inserisci nuova Categoria</a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<c:forEach var = "item" items ="${requestScope.listaCategorie}">
		                    <tr >
		                        <td><c:out value = "${item.id}"/></td>
		                        <td><c:out value = "${item.nome}"/></td>
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="VisualizzaCategoriaServlet?idCat=${item.id}">Visualizza</a>
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2 ${sessionScope.cannotUpdate?'disabled':''}" href="PrepareUpdateCategoriaServlet?idCat=${item.id}" aria-disabled="${sessionScope.cannotUpdate}">Modifica</a>
									<a class="btn btn-outline-danger btn-sm ${sessionScope.cannotDelete?'disabled':''}" href="PrepareDeleteCategoriaServlet?idCat=${item.id}"  aria-disabled="${sessionScope.cannotDelete}">Rimuovi</a>
								</td>
		                    </tr>
		                    </c:forEach>
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
		
	
	<!-- end container -->	
	</main>
	<jsp:include page="../footer.jsp" />
	
</body>
</html>