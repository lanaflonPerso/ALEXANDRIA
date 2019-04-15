<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/cart.css"/>"/>

<div class="container bg-light">

    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-7">
            <div class="breadcrumb">
                <a href="#" class="active">Cart overview</a>
                <a href="#" class="active">Delivery & Payment</a>
                <a>Order resume</a>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>

    <div class="py-5 text-center">
        <%--<img class="d-block mx-auto mb-4" src="<c:url value="/static/images/logo.png"/>" alt="" width="72" height="72">--%>
        <h2>Checkout form</h2>
    </div>

    <form:form method="POST" class="needs-validation" action="checkoutProcess" modelAttribute="client" novalidate="novalidate">

        <h4 class="mb-3">Billing address</h4>

        <div class="form-row">

            <div class="col-md-4 mb-3">
                <label for="gender">Gender</label>
                <select class="custom-select form-control" id="gender" name="gender" required>
                    <option disabled>Select one</option>
                    <c:forEach var="title" items="${titles}" varStatus="status">
                        <option value="${status.index}"
                                <c:if test="${client.titleByTitleId.idTitle == title.idTitle}"> selected </c:if>
                        >${title.description}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    What's your gender?
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <form:label path="firstName">First Name</form:label>
                <form:input path="firstName" class="form-control" value="${userCartSession.client.firstName}" required="required"/>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>

            <div class="col-md-4 mb-3">
                <form:label path="lastName">Last Name</form:label>
                <form:input path="lastName" class="form-control" value="${userCartSession.client.lastName}" required="required"/>
                <div class="invalid-feedback">
                    Valid last name is required.
                </div>
            </div>

        </div>

        <div class="form-row">

            <div class="col-md-6 mb-3">
                <form:label path="addressByInvoiceAddressId.addressLine1">Address</form:label>
                <form:input path="addressByInvoiceAddressId.addressLine1" class="form-control" placeholder="1234 Main St" required="required" autofocus="autofocus"/>
                <div class="invalid-feedback">
                    Please enter your shipping address.
                </div>
            </div>

            <div class="col-md-6 mb-3">
                <form:label path="addressByInvoiceAddressId.addressLine2">Address 2 <span class="text-muted">(Optional)</span></form:label>
                <form:input path="addressByInvoiceAddressId.addressLine2" class="form-control" placeholder="Apartment or suite" required="required"/>
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
                <form:label path="addressByInvoiceAddressId.city">City</form:label>
                <form:input path="addressByInvoiceAddressId.city" class="form-control" required="required"/>
                <div class="invalid-feedback">
                    City is required.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <form:label path="addressByInvoiceAddressId.state">State</form:label>
                <form:input path="addressByInvoiceAddressId.state" class="form-control" required="required"/>
                <div class="invalid-feedback">
                    State is required.
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <form:label path="addressByInvoiceAddressId.postalCode">Postal code</form:label>
                <form:input path="addressByInvoiceAddressId.postalCode" class="form-control" required="required"/>
                <div class="invalid-feedback">
                    Postal code is required.
                </div>
            </div>

        </div>

        <div id="deliveryAddress" style="display:none;">

            <h4 class="mb-3">Delivery address</h4>

            <div class="form-row">

                <div class="col-md-6 mb-3">
                    <form:label path="addressByDeliveryAddressId.addressLine1">Address</form:label>
                    <form:input path="addressByDeliveryAddressId.addressLine1" class="form-control" placeholder="1234 Main St" required="required" autofocus="autofocus"/>
                    <div class="invalid-feedback">
                        Please enter your delivery address.
                    </div>
                </div>

                <div class="col-md-6 mb-3">
                    <form:label path="addressByDeliveryAddressId.addressLine2">Address 2 <span class="text-muted">(Optional)</span></form:label>
                    <form:input path="addressByDeliveryAddressId.addressLine2" class="form-control" placeholder="Apartment or suite" required="required"/>
                </div>

            </div>

            <div class="form-row">

                <div class="col-md-3 mb-3">
                    <label for="country">Country</label>
                    <select class="custom-select form-control" name="country" id="countryDelivery" required>
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
                    <form:label path="addressByDeliveryAddressId.city">City</form:label>
                    <form:input path="addressByDeliveryAddressId.city" class="form-control" required="required"/>
                    <div class="invalid-feedback">
                        City is required.
                    </div>
                </div>

                <div class="col-md-3 mb-3">
                    <form:label path="addressByDeliveryAddressId.state">State</form:label>
                    <form:input path="addressByDeliveryAddressId.state" class="form-control" required="required"/>
                    <div class="invalid-feedback">
                        State is required.
                    </div>
                </div>

                <div class="col-md-3 mb-3">
                    <form:label path="addressByDeliveryAddressId.postalCode">Postal code</form:label>
                    <form:input path="addressByDeliveryAddressId.postalCode" class="form-control" required="required"/>
                    <div class="invalid-feedback">
                        Postal code is required.
                    </div>
                </div>

            </div>

        </div>

        <hr class="mb-4">

        <div class="custom-control custom-checkbox">
            <input type="checkbox" class="custom-control-input" id="same-address" checked onclick="showData()">
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
                <input type="text" class="form-control" id="cc-name" value="<c:out value="${userCartSession.client.lastName} ${userCartSession.client.firstName}"/>" placeholder="" required>
                <small class="text-muted">Full name as displayed on card</small>
                <div class="invalid-feedback">
                    Name on card is required
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="cc-number">Credit card number</label>
                <input type="text" class="form-control" id="cc-number" value="4978040303994666" placeholder="" required>
                <div class="invalid-feedback">
                    Credit card number is required
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="cc-expiration">Expiration</label>
                <input type="text" class="form-control" id="cc-expiration" value="25/12/2020" placeholder="" required>
                <div class="invalid-feedback">
                    Expiration date required
                </div>
            </div>

            <div class="col-md-3 mb-3">
                <label for="cc-cvv">CVV</label>
                <input type="text" class="form-control" id="cc-cvv" value="666" placeholder="" required>
                <div class="invalid-feedback">
                    Security code required
                </div>
            </div>

        </div>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>

    </form:form>

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

    function showData() {
        if(document.getElementById("deliveryAddress").style.display === "block") {
            document.getElementById("deliveryAddress").style.display="none";

        } else {
            document.getElementById("deliveryAddress").style.display = "block";
        }
    }
</script>


