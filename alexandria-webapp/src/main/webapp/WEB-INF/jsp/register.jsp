
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/my-login.css"/>">

<section class="h-100 my-login-page">
	<div class="container h-100">
		<div class="row justify-content-md-center h-100">
			<div class="card-wrapper">
				<div class="brand">
					<img src="<c:url value="/static/images/logo.jpg"/>" alt="logo">
				</div>
				<div class="card fat">
					<div class="card-body">
						<h4 class="card-title">Register</h4>
						<form:form method="POST" class="my-login-validation" action="registerProcess" modelAttribute="client" novalidate="">

							<div class="form-group">
								<label for="gender">Gender</label>
								<select class="custom-select form-control" id="gender" name="gender" required autofocus>
									<option selected disabled>Select one</option>
									<c:forEach var="item" items="${titles}" varStatus="status">
										<option value="${status.index}">${item.description}</option>
									</c:forEach>
								</select>
								<%--<form:select path="titleByTitleId" class="custom-select form-control" id="gender" name="gender" required="R" autofocus="A">--%>
									<%--<form:option value="-" label="--Please Select" />--%>
									<%--<form:options items="${titles}" itemValue="idTitle" itemLabel="description" />--%>
								<%--</form:select>--%>
								<div class="invalid-feedback">
									What's your gender?
								</div>
							</div>

							<div class="form-group">
								<label for="firstName">First Name</label>
								<input id="firstName" type="text" class="form-control" name="firstName" required>
								<div class="invalid-feedback">
									What's your first name?
								</div>
							</div>

							<div class="form-group">
								<label for="lastName">Last Name</label>
								<input id="lastName" type="text" class="form-control" name="lastName" required>
								<div class="invalid-feedback">
									What's your last name?
								</div>
							</div>

							<div class="form-group">
								<label for="email">E-Mail Address</label>
								<input id="email" type="email" class="form-control" name="email" required>
								<div class="invalid-feedback">
									Your email is invalid
								</div>
							</div>

							<div class="form-group">
								<label for="password">Password</label>
								<input id="password" type="password" class="form-control" name="password" required data-eye>
								<div class="invalid-feedback">
									Password is required
								</div>
							</div>

							<div class="form-group m-0">
								<button type="submit" class="btn btn-primary btn-block">
									Register
								</button>
							</div>
							<div class="mt-4 text-center">
								Already have an account? <a href="<c:url value="/login"/>">Login</a>
							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
	</div>
</section>

<script src="<c:url value="/static/js/my-login.js"/>"></script>

