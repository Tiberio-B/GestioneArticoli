<%@page import="it.gestionearticoli.model.Articolo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>Modifica Categoria</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">

		<form method="post" action="ExecuteUpdateCategoriaServlet"
			novalidate="novalidate">

			<div class='card'>
				<div class='card-header'>
					Sei sicuro di voler modificare in questo modo la categoria?
				</div>
				
				<!--  l'articolo originale -->
				<c:set var="itemOld" scope="request" value="${categoriaOld}" />
				<!--  gli attuali valori dell'articolo originale -->
				<c:set var="idVar" value="${itemOld.id}" />
				<c:set var="nomeOld" value="${itemOld.nome}" />

				<!--  l'articolo da aggiornare -->
				<c:set var="itemNew" scope="request" value="${categoriaNew}" />
				<!--  i valori input utente per l'articolo da aggiornare -->
				<c:set var="nomeNew" value="${itemNew.nome}" />
				
				<input type="hidden" name="idCat" id="idCat" value="${idVar}">
				<input type="hidden" name="nomeNew" id="nomeNew" value="${nomeNew}">

					<div class='card-body'>

						<dl class="row">
							<div class="col md-6">
								<dt class="col-sm-3 text-center">Vecchio Nome:</dt>
								<dd class="col-sm-9">${nomeOld}</dd>
							</div>

							<div class="col md-6">
								<dt class="col-sm-3 text-center">Nuovo Nome:</dt>
								<dd class="col-sm-9">${nomeNew}</dd>
							</div>
						</dl>
						
					</div>

					<div class='card-footer'>
						<a href="PrepareUpdateCategoriaServlet?idCat=${idVar}"
							class='btn btn-outline-secondary' style='width: 100px'> <i
							class='fa fa-chevron-left'></i> Indietro
						</a>

						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">
							Conferma
						</button>

						<a href="PrepareDeleteCategoriaServlet?idCat=${idVar}"
							class='btn btn-outline-secondary btn-outline-danger'
							style='width: 100px'> <i class='fa fa-chevron-left'></i>
							Rimuovi
						</a>
					</div>
					
				</div>
		</form>
		<!-- end main container -->
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>