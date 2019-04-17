
<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/cart.css"/>"/>

<div class="container bg-light">

    <div class="row">
        <div class="col-md-12">
            <div class="col text-center">
                <div class="breadcrumb">
                    <a href="#" class="active">Cart overview</a>
                    <a href="#" class="active">Delivery & Payment</a>
                    <a>Order resume</a>
                </div>
            </div>
        </div>
    </div>

    <div class="py-5 text-center">
    <%--<img class="d-block mx-auto mb-4" src="<c:url value="/static/images/logo.png"/>" alt="" width="72" height="72">--%>
        <h2>Checkout form</h2>
    </div>

    <hr>

    <form:form method="POST" class="needs-validation" action="checkoutProcess" modelAttribute="client" novalidate="novalidate">

        <%@include file="_include/checkoutForm.jspf" %>

        <hr>

        <div class="row">
            <div class="col-md-3 mb-3">
                <label for="shippingMethod">Shipping method</label>
                <select class="custom-select form-control" name="shippingMethod" id="shippingMethod" required>
                    <option disabled>Select one</option>
                    <c:forEach var="shippingMethod" items="${shippingMethods}" varStatus="status">
                        <option value="${status.index}"
                                <c:if test="${sessionScope.userCartSession.order.shippingMethodByShippingMethodId.idShippingMethod == shippingMethod.idShippingMethod}"> selected </c:if>
                        >${shippingMethod.description} - <fmt:formatNumber value="${shippingMethod.charges}" type="currency"/></option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    Please select a valid shipping method.
                </div>
            </div>
        </div>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit" onclick="FillBilling(this.checked);">Continue to checkout</button>
        <hr>

    </form:form>

</div>

<script src="<c:url value="/static/js/checkout.js"/>"></script>
