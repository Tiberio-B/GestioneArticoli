<%@page import="it.gestionearticoli.model.Articolo"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Elimina Articolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Sei sicuro di voler eliminare il seguente articolo?
		    </div>
		    
		    <c:set var="item" scope="request" value="${articoloAttr}"/>
		    
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Codice:</dt>
				  <dd class="col-sm-9">${item.codice}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Descrizione:</dt>
				  <dd class="col-sm-9">${item.descrizione}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Prezzo:</dt>
				  <dd class="col-sm-9">${item.prezzo}</dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Categoria:</dt>
				  <dd class="col-sm-9">${item.categoria}</dd>
		    	</dl>
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="ListArticoliServlet" class='btn btn-outline-secondary' style='width:100px'>
		            <i class='fa fa-chevron-left'></i> Indietro
		        </a>
		        
		        <a href="PrepareUpdateArticoloServlet?idParam=${item.id}" class='btn btn-outline-primary ml-2 mr-2' style='width:100px'>
		            <i class='fa fa-chevron-left'></i> Modifica
		        </a>
		        
		        <a href="DeleteArticoloServlet?idParam=${item.id}" class='btn btn-outline-secondary btn-outline-danger' style='width:100px'>
		            <i class='fa fa-chevron-left'></i> Conferma
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="/footer.jsp" />
	
</body>
</html>