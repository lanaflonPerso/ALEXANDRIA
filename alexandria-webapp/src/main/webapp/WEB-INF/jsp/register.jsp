
<div id="register">

    <div class="container">

    <form class="needs-validation" action="registerProcess" method="post" novalidate>

        <h3 class="text-center text-info">Register</h3>

        <div class="form-row">

            <div class="col-md-4 mb-3">
                <label for="validationCustomGender">Gender</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependGender"><i class="fas fa-transgender-alt"></i></span>
                    </div>
                    <select class="custom-select form-control" name="gender" id="validationCustomGender" aria-describedby="inputGroupPrependGender" required>
                        <option selected disabled>Select one</option>
                        <c:forEach var="item" items="${titles}" varStatus="status">
                            <option value="${status.index}">${item.description}</option>
                        </c:forEach>
                    </select>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustom01">First name</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependFirstName"><i class="fas fa-id-card"></i></span>
                    </div>
                    <input type="text" class="form-control" name="firstName" id="validationCustom01" aria-describedby="inputGroupPrependFirstName" placeholder="First name" value="" required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustom02">Last name</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependLastName"><i class="fas fa-id-card"></i></span>
                    </div>
                    <input type="text" class="form-control" name="lastName" id="validationCustom02" aria-describedby="inputGroupPrependLastName" placeholder="Last name" value="" required>
                    <div class="valid-feedback">
                        Looks good!
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustomEmail">Email</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependEmail"><i class="fas fa-at"></i></span>
                    </div>
                    <input type="email" class="form-control" name="email" id="validationCustomEmail" placeholder="Email" aria-describedby="inputGroupPrependEmail" required>
                    <div class="invalid-feedback">
                        Please choose an email.
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustomConfirmEmail">Confirm Email</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependConfirmEmail"><i class="fas fa-at"></i></span>
                    </div>
                    <input type="email" class="form-control" name="confirmEmail" id="validationCustomConfirmEmail" placeholder="Confirm Email" aria-describedby="inputGroupPrependConfirmEmail" required>
                    <div class="invalid-feedback">
                        Please choose an email.
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustomPhone">Phone</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependPhone"><i class="fas fa-phone  "></i></span>
                    </div>
                    <input type="tel" class="form-control" name="phone" id="validationCustomPhone" placeholder="Phone" aria-describedby="inputGroupPrependPhone" value=" ">
                    <div class="invalid-feedback">
                        Please choose a phone number.
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustomPassword">Password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependPassword"><i class="fas fa-key"></i></span>
                    </div>
                    <input type="password" class="form-control" name="password" id="validationCustomPassword" placeholder="Password" aria-describedby="inputGroupPrependPassword" required>
                    <div class="invalid-feedback">
                        Please choose a valid password.
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustomConfirmPassword">Password</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependConfirmPassword"><i class="fas fa-key"></i></span>
                    </div>
                    <input type="password" class="form-control" name="confirmPassword" id="validationCustomConfirmPassword" placeholder="Confirm Password" aria-describedby="inputGroupPrependConfirmPassword" required>
                    <div class="invalid-feedback">
                        Please choose a valid password.
                    </div>
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="validationCustomPaymentMethod">Payment method</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependPaymentMethod"><i class="fas fa-money-check-alt"></i></span>
                    </div>
                    <select class="custom-select form-control" name="paymentMethod" id="validationCustomPaymentMethod" aria-describedby="inputGroupPrependPaymentMethod" required>
                        <option selected disabled>Select one</option>
                        <c:forEach var="item" items="${paymentMethods}" varStatus="status">
                            <option value="${status.index}">${item.description}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                        Please choose a valid payment method.
                    </div>
                </div>
            </div>

        </div>

        <div class="form-row">

            <div class="col-md-6 mb-3">
                <label for="validationCustomLine1">Line 1</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependLine1"><i class="fas fa-road"></i></span>
                    </div>
                    <input type="text" class="form-control" name="line1" id="validationCustomLine1" aria-describedby="inputGroupPrependLine1" placeholder="Line 1" required>
                    <div class="invalid-feedback">
                        Please provide a valid line 1.
                    </div>
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="validationCustomLine2">Line 2</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="inputGroupPrependLine2"><i class="fas fa-road"></i></span>
                    </div>
                    <input type="text" class="form-control" name="line2" id="validationCustomLine2" aria-describedby="inputGroupPrependLine2" placeholder="Line 2" value=" ">
                    <div class="invalid-feedback">
                        Please provide a valid line 2.
                    </div>
                </div>
            </div>

        </div>

        <div class="form-row">

            <div class="col-md-6 mb-3">
                <label for="validationCustomCity">City</label>
                <input type="text" class="form-control" name="city" id="validationCustomCity" placeholder="City" required>
                <div class="invalid-feedback">
                    Please provide a valid city.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="validationCustomState">State</label>
                <input type="text" class="form-control" name="state" id="validationCustomState" placeholder="State" value=" ">
                <div class="invalid-feedback">
                    Please provide a valid state.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="validationCustomPostalCode">Postal code</label>
                <input type="text" class="form-control" name="postalCode" id="validationCustomPostalCode" placeholder="Postal code" required>
                <div class="invalid-feedback">
                    Please provide a valid postal code.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="validationCustomCountry">Country</label>
                <select class="custom-select form-control" name="country" id="validationCustomCountry" required>
                    <option selected disabled>Select one</option>
                    <c:forEach var="item" items="${countries}" varStatus="status">
                        <option value="${status.index}">${item.description}</option>
                    </c:forEach>
                </select>
                <div class="valid-feedback">
                    Looks good!
                </div>
            </div>

        </div>

        <button class="btn btn-primary" type="submit">Submit form</button>

    </form>

    </div>
</div>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
