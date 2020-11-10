<!doctype html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Cerca un Articolo</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">

		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div
			class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
			role="alert">
			${errorMessage}
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>

		<div class='card'>
			<div class='card-header'>
				<h5>Cerca un Articolo</h5>
			</div>
			<div class='card-body'>

				<form method="post" action="ExecuteSearchArticoloServlet"
					novalidate="novalidate">

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Codice</label> <input
								type="text" name="codice" id="codice" class="form-control"
								placeholder="Inserire il codice" required>
						</div>

						<div class="form-group col-md-6">
							<label>Descrizione</label> <input
								type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Inserire la descrizione"
								required>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Prezzo</label> <input
								type="number" class="form-control" name="prezzo" id="prezzo"
								placeholder="Inserire prezzo" required>
						</div>

						<div class="form-group col-md-6">
							
								<label>Categoria</label>
								<select class="browser-default custom-select" name="idCat">
								
									<option value="-1">Seleziona una categoria...</option>
										
										<c:forEach var="categoria" items="${requestScope.listaCategorie}">
											<option value="${categoria.id}">${categoria.nome}</option>
										</c:forEach>
								</select>
							
						</div>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>
				</form>
				<!-- end card-body -->
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>