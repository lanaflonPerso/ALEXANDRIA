

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

        <%@include file="checkoutForm.jsp" %>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Continue to checkout</button>

    </form:form>

</div>

<%@include file="checkoutScript.jsp" %>


