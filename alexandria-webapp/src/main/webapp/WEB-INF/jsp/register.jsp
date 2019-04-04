
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

						<form:form method="POST" class="my-login-validation" action="registerProcess" modelAttribute="client" novalidate="novalidate">

							<%--TODO : use Spring form--%>
							<div class="form-group">
								<label for="gender">Gender</label>
								<select class="custom-select form-control" id="gender" name="gender" required autofocus>
									<option selected disabled>Select one</option>
									<c:forEach var="item" items="${titles}" varStatus="status">
										<option value="${status.index}">${item.description}</option>
									</c:forEach>
								</select>
								<div class="invalid-feedback">
									What's your gender?
								</div>
							</div>

							<div class="form-group">
								<form:label path="firstName">First Name</form:label>
								<form:input path="firstName" class="form-control" required="required"/>
								<div class="invalid-feedback">
									What's your first name?
								</div>
							</div>

							<div class="form-group">
								<form:label path="lastName">Last Name</form:label>
								<form:input path="lastName" class="form-control" required="required"/>
								<div class="invalid-feedback">
									What's your last name?
								</div>
							</div>

							<div class="form-group">
								<form:label path="email">E-Mail Address</form:label>
								<form:input path="email" type="email" class="form-control" required="required"/>
								<div class="invalid-feedback">
									Your email is invalid
								</div>
							</div>

							<div class="form-group">
								<form:label path="password">Password</form:label>
								<form:password path="password" class="form-control"
											   pattern="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"
											   required="required" data-eye="data-eye"/>
                                <small class="form-text text-muted">
                                    Passwords must contain at least eight characters, including at least 1 upper letter 1 lower letter and 1 number.
                                </small>
								<div class="invalid-feedback">
									Password is required
								</div>
							</div>

							<div class="form-group m-0">
								<form:button type="submit" class="btn btn-primary btn-block">Register</form:button>
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
