<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/cart.css"/>"/>

<div id="wrapper" class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="col text-center">
                <div class="breadcrumb">
                    <a href="#" class="active">Cart overview</a>
                    <a>Delivery & Payment</a>
                    <a>Order resume</a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart: <c:out value="${userCartSession.client.titleByTitleId.description} ${userCartSession.client.firstName} ${userCartSession.client.lastName}"/></span>
            </h4>

            <ul class="list-group mb-3">

                <c:forEach var="orderLine" items="${userCartSession.orderLines}">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <h6 class="my-0"><img src="data:image/jpg;base64,${orderLine.productByProductId.base64Image}" width="150" height="150"/></h6>

                        ${orderLine.productByProductId.name}<br>
                        <fmt:formatNumber value="${orderLine.productByProductId.priceExVat}" type="currency"/>
                    <br>
                        Stock : ${orderLine.productByProductId.stock}

                        <label for="input-spinner">Quantity</label>
                        <input id="input-spinner" type="number" value="${orderLine.quantity}" name="quantity" min="1" max="${orderLine.productByProductId.stock + orderLine.quantity}"
                               onchange="updateOrderLine(${orderLine.productByProductId.idProduct}, this.value)"/>
                    <br>

                        <a href="<c:url value="/remProduct?idProduct=${orderLine.productByProductId.idProduct}"/>"><i class="fa fa-trash" aria-hidden="true"></i></a>
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

                <c:forEach var="orderLine" items="${userCartSession.orderLines}">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <small class="text-muted">${orderLine.productByProductId.name} &nbsp; x ${orderLine.quantity}</small>
                        <span class="text-muted"><fmt:formatNumber value="${orderLine.quantity * orderLine.productByProductId.priceExVat}" type="currency"/></span>
                    </li>
                </c:forEach>

                <li class="list-group-item d-flex justify-content-between">
                    <span>Total</span>
                    <strong><fmt:formatNumber value="${userCartSession.totalCostExVat}" type="currency"/></strong>
                </li>

            </ul>

            <form action="checkout" method="get">
                <button type="submit" class="btn btn-secondary">Submit</button>
            </form>
    &nbsp;
            <form action="emptyCart" method="get">
                <button type="submit" class="btn btn-danger">Empty cart</button>
            </form>
</div>
        </div>
    </div>

<script>
    function updateOrderLine(idProduct, quantity) {
        document.location.href='<c:url value="/cartUpdateLineItem"/>?idProduct=' + idProduct + '&quantity=' + quantity;
    }
</script>

<script src="<c:url value="/static/js/bootstrap-input-spinner.js"/>"></script>
<script>
    $("input[type='number']").inputSpinner()
</script>