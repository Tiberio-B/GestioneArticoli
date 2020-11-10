<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--  

<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
	Operazione fallita!
	<button type="button" class="close" data-dismiss="alert" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>

-->

<div
	class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}"
	role="alert">
	${errorMessage}
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
</div>
