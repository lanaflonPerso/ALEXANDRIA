<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/cart.css"/>"/>

<div id="wrapper" class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-7">
            <div class="breadcrumb">
                <a href="#" class="active">Cart overview</a>
                <a href="#" class="active">Delivery & Payment</a>
                <a href="#" class="active">Order resume</a>
            </div>
        </div>
        <div class="col-md-2"></div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart: <c:out value="${userCartSession.client.titleByTitleId.description} ${userCartSession.client.firstName} ${userCartSession.client.lastName}"/></span>
            </h4>
            <p style="font-style: italic; color: green;">${message}</p>

            <ul class="list-group mb-3">

                <c:forEach var="i" items="${userCartSession.orderLines}">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <h6 class="my-0"><img src="data:image/jpg;base64,${i.productByProductId.base64Image}" width="150" height="150"/></h6>

                        ${i.productByProductId.name}<br>
                        <fmt:formatNumber value="${i.productByProductId.priceExVat}" type="currency"/>
                    <br>
                        Stock : ${i.productByProductId.stock}

                        <label>Quantity</label>
                        <c:out value="${i.quantity}"/>

                    <br>
                        <span class="text-muted"><fmt:formatNumber value="${i.productByProductId.priceExVat}" type="currency"/></span>

                    </li>
                </c:forEach>
            </ul>

        </div>

        <div class="col-md-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Products</span>
                <span class="badge badge-secondary badge-pill">${userCartSession.nbOrderLines}</span>
            </h4>
            <ul class="list-group mb-3">

                <c:forEach var="i" items="${userCartSession.orderLines}">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <small class="text-muted">${i.productByProductId.name}</small>
                        <span class="text-muted"><fmt:formatNumber value="${i.quantity * i.productByProductId.priceExVat}" type="currency"/></span>
                    </li>
                </c:forEach>

                <li class="list-group-item d-flex justify-content-between">
                    <span>Total</span>
                    <strong><fmt:formatNumber value="${userCartSession.totalCostExVat}" type="currency"/></strong>
                </li>

            </ul>

        </div>
    </div>
</div>


