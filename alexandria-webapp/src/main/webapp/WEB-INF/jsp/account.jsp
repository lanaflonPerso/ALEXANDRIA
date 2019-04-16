<form:form method="POST" class="needs-validation" action="accountUpdate" modelAttribute="client" novalidate="novalidate">

    <div class="col-md-4 mb-3">
    <form:label path="idClient">numéro de client${userCartSession.client.idClient}</form:label>
    <form:input path="idClient" class="form-control" value="${userCartSession.client.idClient}" required="required" disabled="disabled"/>
    <div class="invalid-feedback">
        Valid first name is required.
    </div>
<%@include file="checkoutForm.jsp" %>

<div class="row">
    <div class="col-md-4 mb-3">
        <form:label path="email">Email</form:label>
        <form:input path="email" class="form-control" value="${userCartSession.client.email}" required="required"/>
        <div class="invalid-feedback">
            Valid first name is required.
        </div>
    </div>
    <div class="col-md-4 mb-3">
        <form:label path="password">Password</form:label>
        <form:input path="password" class="form-control" value="${userCartSession.client.password}" required="required" data-eye="data-eye"/>
        <div class="invalid-feedback">
            Valid first name is required.
        </div>
    </div>
    <div class="col-md-4 mb-3">
        <form:label path="phone">Phone</form:label>
        <form:input path="phone" class="form-control" value="${userCartSession.client.phone}" required="required"/>
        <div class="invalid-feedback">
            Valid first name is required.
        </div>
    </div>
</div>

    <hr class="mb-4">
    <button class="btn btn-primary btn-lg btn-block" type="submit">Save changes</button>
<%--prevoir cancel ?--%>
</form:form>

<%@include file="checkoutScript.jsp" %>