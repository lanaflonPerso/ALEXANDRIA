

<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/my-login.css"/>">

<%--<body class="my-login-page">--%>
	<section class="h-100 my-login-page">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<h3 class="text-center pt-5">Welcome to ${application.name}</h3>
					<div class="brand">
						<img src="<c:url value="/static/images/logo.jpg"/>" alt="logo">
					</div>
					<div class="card fat">
						<div class="card-body">
							<h4 class="card-title">Login</h4>
							<p style="font-style: italic; color: red;">${message}</p>
							<form:form id="loginForm" class="form" modelAttribute="login" action="loginProcess" method="post">
							<%--<form method="POST" class="my-login-validation" novalidate="">--%>
								<div class="form-group">
									<label for="email">E-Mail Address</label>
									<form:input path="email" type="email" class="form-control" name="email" id="email" value="" required="R" autofocus="A"/>
									<%--<input id="email" type="email" class="form-control" name="email" value="" required autofocus>--%>
									<div class="invalid-feedback">
										Email is invalid
									</div>
								</div>

								<div class="form-group">
									<label for="password">Password
										<a href="forgot.html" class="float-right">
											Forgot Password?
										</a>
									</label>
									<form:input path="password" type="password" class="form-control" name="password" id="password" value="" required="R" data-eye="D"/>
									<%--<input id="password" type="password" class="form-control" name="password" required data-eye>--%>
								    <div class="invalid-feedback">
								    	Password is required
							    	</div>
								</div>

								<div class="form-group">
									<div class="custom-checkbox custom-control">
										<form:checkbox path="remember" class="custom-control-input" name="remember" id="remember"/>
										<%--<input type="checkbox" name="remember" id="remember" class="custom-control-input">--%>
										<label for="remember" class="custom-control-label">Remember Me</label>
									</div>
								</div>

								<div class="form-group m-0">
									<button type="submit" class="btn btn-primary btn-block">
										Login
									</button>
								</div>
								<div class="mt-4 text-center">
									Don't have an account? <a href="register.html">Create One</a>
								</div>
							<%--</form>--%>
							</form:form>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>

	<script src="<c:url value="/static/js/my-login.js"/>"></script>

<%--</body>--%>
