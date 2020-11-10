<!doctype html>
<html lang="it">
<head>
	<jsp:include page="/header.jsp" />
	<title>Pagina di Login</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="/navbar.jsp" />
	
	<main role="main" class="container">
	
	<jsp:include page="/error-message.jsp" />
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Effettua il Login</h5> 
		    </div>
		    <div class='card-body'>


					<form method="post" action="LoginServlet" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Username</label>
								<input type="text" name="username" id="username" class="form-control" placeholder="Inserire username" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Password</label>
								<input type="text" name="password" id="password" class="form-control" placeholder="Inserire password" required>
							</div>
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Accedi</button>
					

					</form>
			<!-- end card-body -->			   
		    </div>
		    
		    <div class='card-footer'>
		        <a href="index.jsp" class='btn btn-outline-secondary' style='width:100px'>
		            <i class='fa fa-chevron-left'></i> Indietro
		        </a>
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="/footer.jsp" />
	
</body>
</html>