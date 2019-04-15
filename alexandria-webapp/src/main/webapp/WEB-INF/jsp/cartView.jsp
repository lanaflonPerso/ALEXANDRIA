<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/cart.css"/>"/>

<div id="wrapper" class="container">
    <div class="row">
        <div class="col-md-4"></div>
        <div class="col-md-5">
            <div class="breadcrumb">
                <a href="#" class="active">Cart overview</a>
                <a>Delivery & Payment</a>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>
    </div>

    <div class="row">
        <div class="col-md-8">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart: <c:out value="${userCartSession.client.titleByTitleId.description} ${userCartSession.client.firstName} ${userCartSession.client.lastName}"/></span>
            </h4>

            <ul class="list-group mb-3">

                <c:forEach var="cart" items="${userCartSession.orderLines}">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
<%--                        <h6 class="my-0"><img src="data:image/jpg;base64,${i.productByProductId.base64Image}" width="150" height="150"/></h6>--%>
                        <h6 class="my-0"><a href="<c:url value="/product${cart.productByProductId.idProduct}"/>"><img src="data:image/jpg;base64,${cart.productByProductId.base64Image}" width="50" height="50"/></a></h6>

                        ${cart.productByProductId.name}<br>
                        <fmt:formatNumber value="${cart.productByProductId.priceExVat}" type="currency"/>
                    <br>
                        Stock : ${cart.productByProductId.stock}

                        <label for="quantity">Quantity</label>
                        <input id="quantity" type="number" value="${cart.quantity}" name="quantity" min="1" max="${cart.productByProductId.stock + cart.quantity}"
                               onchange="updateOrderLine(${cart.productByProductId.idProduct}, this.value)"/>
                    <br>
                        <span class="text-muted"><fmt:formatNumber value="${cart.productByProductId.priceExVat}" type="currency"/></span>

                        <a href="<c:url value="/remProduct?idProduct=${cart.productByProductId.idProduct}"/>"><i class="fa fa-trash" aria-hidden="true"></i></a>
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
<div class="row">
            <form action="checkout" method="get">
                <button type="submit" class="btn btn-secondary">Submit</button>
            </form>
    &nbsp;
            <form action="emptycart" method="get">
                <button type="submit" class="btn btn-danger">Empty cart</button>
            </form>
</div>
        </div>
    </div>
</div>

<script>
    function updateOrderLine(idProduct, quantity) {
        document.location.href='<c:url value="/cartUpdateLineItem"/>?idProduct=' + idProduct + '&quantity=' + quantity;
    }
</script>

