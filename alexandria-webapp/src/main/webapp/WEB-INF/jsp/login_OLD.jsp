
<div id="login">
    <h3 class="text-center pt-5">Welcome to ${application.name}</h3>
    <div class="container">
        <div id="login-row" class="row justify-content-center align-items-center">
            <div id="login-column" class="col-md-6">
                <div id="login-box" class="col-md-12">
                    <form id="login-form" class="form" action="loginProcess" method="post">
                        <h3 class="text-center text-info">Login</h3>

                        <p style="font-style: italic; color: red;">${message}</p>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupPrependEmail"><i class="fas fa-at"></i></span>
                                </div>
                                <input type="email" class="form-control" name="email" id="email" placeholder="" aria-describedby="inputGroupPrependEmail" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="inputGroupPrependPassword"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control pwd" name="password" id="password" aria-describedby="passwordHelpId"
                                       <%--pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{4,8}$" --%>
                                       placeholder=""
                                       title="Doit contenir entre 4 et 8 caractères contenant impérativement au moins un chiffre, une lettre majuscule, une lettre minuscule et un autre caractère."
                                       required>
                                <input type="button" class="btnShow" value="show"/>
                                <small id="passwordHelpId" class="form-text text-muted">Passwords must contain at least eight characters, including at least 1 letter and 1 number.
                                    Doit contenir entre 4 et 8 caractères contenant impérativement
                                    au moins un chiffre, une lettre majuscule, une lettre minuscule et un autre caractère.</small>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                            <input type="submit" name="submit" class="btn btn-info btn-md" value="submit">
                        </div>

                        <c:url value="/register" var="URL_REGISTER"/>
                        <div class="form-group">
                            <p class="text-center">Don't have account? <a href="${URL_REGISTER}" id="signup">Register here</a></p>
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

