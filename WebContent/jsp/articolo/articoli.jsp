<%@page import="it.gestionearticoli.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="../header.jsp" />
	<title>Elenco degli Articoli</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="${pageContext.request.contextPath}/assets/css/global.css" rel="stylesheet">
    
    
    
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
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Elenco degli Articoli${filtered==true? ' in ': ''} ${categoria} ${searched==true? ' che soddisfano i parametri della ricerca': ''}</h5> 
		    </div>
		    <div class='card-body'>
		    	<a class="btn btn-primary ${sessionScope.cannotInsert?'disabled':''}" href="PrepareInsertArticoloServlet" aria-disabled="${sessionScope.cannotInsert}">Inserisci nuovo Articolo</a>
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Codice</th>
		                        <th>Descrizione</th>
		                        <th>Prezzo</th>
		                        <th>Categoria</th>
		                        <th>Azioni</th>
		                    </tr>
		                </thead>
		                <tbody>
		                	<c:forEach var = "item" items ="${requestScope.listaArticoliAttribute}">
		                    <tr >
		                        <td><c:out value = "${item.id}"/></td>
		                        <td><c:out value = "${item.codice}"/></td>
		                        <td><c:out value = "${item.descrizione}"/></td>
		                        <td><c:out value = "${item.prezzo}"/></td>
		                        <td><c:out value = "${item.categoria}"/></td>
		                        <td>
									<a class="btn  btn-sm btn-outline-secondary" href="VisualizzaArticoloServlet?idParam=${item.id}">Visualizza</a>
									<a class="btn  btn-sm btn-outline-primary ml-2 mr-2 ${sessionScope.cannotUpdate?'disabled':''}" href="PrepareUpdateArticoloServlet?idParam=${item.id}" aria-disabled="${sessionScope.cannotUpdate}">Modifica</a>
									<a class="btn btn-outline-danger btn-sm ${sessionScope.cannotDelete?'disabled':''}" href="PrepareDeleteArticoloServlet?idParam=${item.id}" aria-disabled="${sessionScope.cannotDelete}">Rimuovi</a>
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