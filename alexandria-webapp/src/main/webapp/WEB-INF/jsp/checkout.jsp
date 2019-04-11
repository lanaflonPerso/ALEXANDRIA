
<div class="container bg-light">

    <div class="py-5 text-center">
        <img class="d-block mx-auto mb-4" src="<c:url value="/static/images/logo.png"/>" alt="" width="72" height="72">
        <h2>Checkout form</h2>
    </div>

    <h4 class="mb-3">Billing address</h4>

    <form class="needs-validation" method="POST" action="checkoutProcess" novalidate>

        <div class="form-row">

            <div class="col-md-4 mb-3">
                <label for="firstName">First name</label>
                <input type="text" class="form-control" id="firstName" placeholder="" value="${userCartSession.client.firstName}" required>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <label for="lastName">Last name</label>
                <input type="text" class="form-control" id="lastName" placeholder="" value="${userCartSession.client.lastName}" required>
                <div class="invalid-feedback">
                    Valid last name is required.
                </div>
            </div>

        </div>

        <div class="form-row">

            <div class="col-md-6 mb-3">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" placeholder="1234 Main St" required>
                <div class="invalid-feedback">
                    Please enter your shipping address.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
                <input type="text" class="form-control" id="address2" placeholder="Apartment or suite">
            </div>

        </div>

        <div class="form-row">

            <div class="col-md-3 mb-3">
                <label for="country">Country</label>
                <select class="custom-select form-control" name="country" id="country" required>
                    <option disabled>Select one</option>
                    <c:forEach var="country" items="${countries}" varStatus="status">
                        <option value="${status.index}">${country.description}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please select a valid country.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="City">City</label>
                <input type="text" class="form-control" id="city" placeholder="" required>
                <div class="invalid-feedback">
                    City is required.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="state">State</label>
                <input type="text" class="form-control" id="state" placeholder="" required>
                <div class="invalid-feedback">
                    State is required.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="postalCode">Postal code</label>
                <input type="text" class="form-control" id="postalCode" placeholder="" required>
                <div class="invalid-feedback">
                    Postal code is required.
                </div>
            </div>

        </div>

        <hr class="mb-4">

        <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="same-address">
            <label class="custom-control-label" for="same-address">Shipping address is the same as my billing address</label>
        </div>

        <hr class="mb-4">

        <h4 class="mb-3">Payment</h4>

        <div class="form-row">

            <div class="col-md-3 mb-3">

                <label for="paymentMethod">Payment method</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text"><i class="fas fa-money-check-alt"></i></span>
                    </div>
                    <select class="custom-select form-control" name="paymentMethod" id="paymentMethod" required>
                        <option disabled>Select one</option>
                        <c:forEach var="paymentMethod" items="${paymentMethods}" varStatus="status">
                            <option value="${status.index}">${paymentMethod.description}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                        Please choose a valid payment method.
                    </div>
                </div>

            </div>

            <div class="col-md-3 mb-3">
                <label for="cc-name">Name on card</label>
                <input type="text" class="form-control" id="cc-name" placeholder="" required>
                <small class="text-muted">Full name as displayed on card</small>
                <div class="invalid-feedback">
                    Name on card is required
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="cc-number">Credit card number</label>
                <input type="text" class="form-control" id="cc-number" placeholder="" required>
                <div class="invalid-feedback">
                    Credit card number is required
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="cc-expiration">Expiration</label>
                <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
                <div class="invalid-feedback">
                    Expiration date required
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="cc-cvv">CVV</label>
                <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
                <div class="invalid-feedback">
                    Security code required
                </div>
            </div>

        </div>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>
    </form>
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


