
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/my-login.css"/>">

<section class="h-100 my-login-page">

	<div class="container h-100">

		<div class="row justify-content-md-center h-100">

			<div class="card-wrapper">

				<h3 class="text-center pt-5">Welcome to ${application.name}</h3>

				<div class="brand">
					<img src="<c:url value="/static/images/logo.png"/>" alt="logo">
				</div>

				<div class="card fat">

					<div class="card-body">

						<h4 class="card-title">Login</h4>

						<p style="font-style: italic; color: red;">${message}</p>

						<form:form id="loginForm" class="my-login-validation" modelAttribute="login" action="loginProcess" method="post" novalidate="novalidate">

							<div class="form-group">
								<form:label path="email">E-Mail Address</form:label>
								<form:input path="email" type="email" class="form-control" required="required" autofocus="autofocus"/>
								<div class="invalid-feedback">
									Email is invalid
								</div>
							</div>

							<div class="form-group">
								<form:label path="password">Password</form:label>
								<form:password path="password" class="form-control" required="required" data-eye="data-eye"/>
								<div class="invalid-feedback">
									Password is required
								</div>
							</div>

							<div class="form-group">
								<div class="custom-checkbox custom-control">
									<form:checkbox path="remember" class="custom-control-input"/>
									<form:label path="remember" class="custom-control-label">Remember Me</form:label>
								</div>
							</div>

							<div class="form-group m-0">
								<form:button type="submit" class="btn btn-primary btn-block">Login</form:button>
							</div>

							<div class="mt-4 text-center">
								Don't have an account? <a href="<c:url value="/register"/>">Create One</a>
							</div>

						</form:form>

					</div>

				</div>

			</div>

		</div>

	</div>

</section>

<script src="<c:url value="/static/js/my-login.js"/>"></script>
