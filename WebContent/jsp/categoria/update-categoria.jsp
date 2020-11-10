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
				<h5>Modifica Categoria</h5>
			</div>
			<div class='card-body'>

				<h6 class="card-title">
					I campi con <span class="text-danger">*</span> sono obbligatori
				</h6>

				<form method="post" action="ConfirmUpdateCategoriaServlet"
					novalidate="novalidate">

					<c:set var="old" scope="request" value="${categoriaOld}" />

					<input type="hidden" name="idOld" id="idOld" class="form-control"
						value="${old.id}">

					<div class="form-row">
						<div class="form-group col-md-6">
							<label>Nome <span class="text-danger">*</span></label> <input
								type="text" name="nome" id="nome" class="form-control"
								placeholder="Inserire il nome" value="${old.nome}" required>
						</div>
					</div>

					<button type="submit" name="submit" value="submit" id="submit"
						class="btn btn-primary">Conferma</button>

				</form>
				<!-- end card-body -->
			</div>

			<div class='card-footer'>
				<a href="ListCategorieServlet" class='btn btn-outline-secondary'
					style='width: 100px'> <i class='fa fa-chevron-left'></i>
					Indietro
				</a> <a href="PrepareDeleteCategoriaServlet?idCat=${old.id}"
					class='btn btn-outline-secondary btn-outline-danger'
					style='width: 100px'> <i class='fa fa-chevron-left'></i>
					Rimuovi
				</a>
			</div>

		</div>


		<!-- end container -->
	</main>
	<jsp:include page="/footer.jsp" />

</body>
</html>