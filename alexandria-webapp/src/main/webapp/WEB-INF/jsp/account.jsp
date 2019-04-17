<div class="container bg-light">
    <form:form method="POST" class="needs-validation" action="accountUpdate" modelAttribute="client"
               novalidate="novalidate">


        <%@include file="_include/checkoutForm.jspf" %>

        <hr>
        <div class="row">
            <div class="col-md-4 mb-3">
                <form:label path="email">Email</form:label>
                <form:input path="email" class="form-control" value="${userCartSession.client.email}"
                            required="required"/>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <form:label path="password">Password</form:label>
                <form:input type="password" path="password" class="form-control"
                            value="${userCartSession.client.password}" required="required" data-eye="data-eye"/>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>
            <div class="col-md-4 mb-3">
                <form:label path="phone">Phone</form:label>
                <form:input path="phone" class="form-control" value="${userCartSession.client.phone}"
                            required="required"/>
                <div class="invalid-feedback">
                    Valid first name is required.
                </div>
            </div>
        </div>

        <hr class="mb-4">
        <button class="btn btn-primary btn-lg btn-block" type="submit">Save changes</button>
        <hr>
    </form:form>
</div>
<%@include file="_include/checkoutScript.jspf" %>
