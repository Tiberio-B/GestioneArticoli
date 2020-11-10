<!doctype html>
<html lang="it">
<head>
<jsp:include page="/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Inserisci nuovo Articolo</title>

<!-- style per le pagine diverse dalla index -->
<link href="./assets/css/global.css" rel="stylesheet">

</head>
<body>
	<jsp:include page="/navbar.jsp" />

	<main role="main" class="container">
	
	<jsp:include page="/error-message.jsp" />
	
	<!-- 
		<div class="alert alert-danger alert-dismissible fade show d-none"
			role="alert">
			Operazione fallita!
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
	-->

		<div class='card'>
			<div class='card-header'>
				<h5>Inserisci nuovo elemento</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="ExecuteInsertArticoloServlet"
					novalidate="novalidate">

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Codice <span class="text-danger">*</span></label> <input
								type="text" name="codice" id="codice" class="form-control"
								placeholder="Inserire il codice" required>
						</div>

						<div class="form-group col-md-6">
							<label>Descrizione <span class="text-danger">*</span></label> <input
								type="text" name="descrizione" id="descrizione"
								class="form-control" placeholder="Inserire la descrizione"
								required>
						</div>
					</div>

					<div class="form-row">
						<div class="form-group col-md-3">
							<label>Prezzo <span class="text-danger">*</span></label> <input
								type="number" class="form-control" name="prezzo" id="prezzo"
								placeholder="Inserire prezzo" required>
						</div>

						<div class="form-group col-md-6">
							
								<label>Categoria <span class="text-danger">*</span></label>
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

			<div class='card-footer'>
				<a href="ListArticoliServlet" class='btn btn-outline-secondary'
					style='width: 100px'> <i class='fa fa-chevron-left'></i>
					Indietro
				</a>
			</div>
		</div>


		<!-- end container -->
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>