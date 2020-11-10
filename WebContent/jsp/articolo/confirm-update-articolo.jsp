<%@page import="it.gestionearticoli.model.Articolo"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<title>Modifica elemento</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">

		<form method="post" action="ExecuteUpdateArticoloServlet"
			novalidate="novalidate">

			<div class='card'>
				<div class='card-header'>
					Sei sicuro di voler modificare in questo modo l'elemento?
				</div>
				
				<!--  l'articolo originale -->
				<c:set var="itemOld" scope="request" value="${articoloOld}" />
				<!--  gli attuali valori dell'articolo originale -->
				<c:set var="idOld" value="${itemOld.id}" />
				<c:set var="codiceOld" value="${itemOld.codice}" />
				<c:set var="descrizioneOld" value="${itemOld.descrizione}" />
				<c:set var="prezzoOld" value="${itemOld.prezzo}" />
				<c:set var="categoriaOld" value="${itemOld.categoria}" />

				<!--  l'articolo da aggiornare -->
				<c:set var="itemNew" scope="request" value="${articoloNew}" />
				<!--  i valori input utente per l'articolo da aggiornare -->
				<c:set var="codiceNew" value="${itemNew.codice}" />
				<c:set var="descrizioneNew" value="${itemNew.descrizione}" />
				<c:set var="prezzoNew" value="${itemNew.prezzo}" />
				<c:set var="categoriaNew" value="${itemNew.categoria}" />
				
				<input type="hidden" name="idArt" id="idArt" value="${idOld}">
				<input type="hidden" name="codice" id="codice" value="${codiceNew}">
				<input type="hidden" name="descrizione" id="descrizione" value="${descrizioneNew}">
				<input type="hidden" name="prezzo" id="prezzo" value="${prezzoNew}">
				<input type="hidden" name="idCat" id="idCat" value="${categoriaNew.id}">

					<div class='card-body'>

						<dl class="row">
							<div class="col md-6">
								<dt class="col-sm-10">Vecchio Codice:</dt>
								<dd class="col-sm-9 text-center">${codiceOld}</dd>
							</div>

							<div class="col md-6">
								<dt class="col-sm-10">Nuovo Codice:</dt>
								<dd class="col-sm-9 text-center">${codiceNew}</dd>
							</div>
						</dl>

						<dl class="row">
							<div class="col">
								<dt class="col-sm-10">Vecchia Descrizione:</dt>
								<dd class="col-sm-9 text-center">${descrizioneOld}</dd>
							</div>

							<div class="col">
								<dt class="col-sm-10">Nuova Descrizione:</dt>
								<dd class="col-sm-9 text-center">${descrizioneNew}</dd>
							</div>
						</dl>

						<dl class="row">
							<div class="col">
								<dt class="col-sm-10">Vecchio Prezzo:</dt>
								<dd class="col-sm-9 text-center">${prezzoOld}</dd>
							</div>

							<div class="col">
								<dt class="col-sm-10">Nuovo Prezzo:</dt>
								<dd class="col-sm-9 text-center">${prezzoNew}</dd>
							</div>
						</dl>
						
						<dl class="row">
							<div class="col">
								<dt class="col-sm-10">Vecchia Categoria:</dt>
								<dd class="col-sm-9 text-center">${categoriaOld}</dd>
							</div>

							<div class="col">
								<dt class="col-sm-10">Nuova Categoria:</dt>
								<dd class="col-sm-9 text-center">${categoriaNew}</dd>
							</div>
						</dl>
						
					</div>

					<div class='card-footer'>
						<a href="PrepareUpdateArticoloServlet?idParam=${idOld}"
							class='btn btn-outline-secondary' style='width: 100px'> <i
							class='fa fa-chevron-left'></i> Indietro
						</a>

						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">
							Conferma
						</button>

						<a href="PrepareDeleteServlet?idParam=${idOld}"
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