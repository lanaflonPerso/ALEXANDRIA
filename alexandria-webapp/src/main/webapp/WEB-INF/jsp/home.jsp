<h1>Welcome to ${application.name}</h1>

<div id="login">
    <h3 class="text-center text-white pt-5">Login form</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="" method="post">
                        <h3 class="text-center text-info">Login</h3>

                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="" required>
                        </div>


                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control pwd" name="password" id="password" aria-describedby="passwordHelpId"
                                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{4,8}$" placeholder=""
                                   title="Doit contenir entre 4 et 8 caractères contenant impérativement au moins un chiffre, une lettre majuscule, une lettre minuscule et un autre caractère."
                                   required>
                            <input type="button" class="btnShow" value="show"/>
                            <small id="passwordHelpId" class="form-text text-muted">Doit contenir entre 4 et 8 caractères contenant impérativement
                                au moins un chiffre, une lettre majuscule, une lettre minuscule et un autre caractère.</small>
                        </div>


                        <div class="form-group">
                            <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                        </div>

                        <div id="register-link" class="text-right">
                            <a href="#" class="text-info">Register here</a>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    $( ".btnShow" ).mousedown(function() {
        $(".pwd").attr("type","text");
    });
    $( ".btnShow" ).on("mouseleave",function() {
        $(".pwd").attr("type","password");
    });
</script>

