<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/cart.css"/>"/>

<%--<div id="wrapper" class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="col text-center">
            <div class="breadcrumb">
                <a href="#" class="active">Cart overview</a>
                <a href="#" class="active">Delivery & Payment</a>
                <a href="#" class="active">Order resume</a>
            </div>
            </div>
        </div>
        <div class="col-md-1"></div>
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
</div>--%>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="col text-center">
                <div class="breadcrumb">
                    <a href="#" class="active">Cart overview</a>
                    <a href="#" class="active">Delivery & Payment</a>
                    <a href="#" class="active">Order resume</a>
                </div>
            </div>
        </div>
        <div class="col-md-1"></div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="invoice-title">
                <p style="font-style: italic; color: green;">${message}</p>
                <hr>
                <h2>Invoice</h2><h3 class="pull-right">Order #<c:out value="${userCartSession.order.idOrderHeader}" /></h3>
            </div>
            <hr>
            <div class="row">
                <div class="col-md-6">
                    <address>
                        <strong>Billed To:</strong><br>
                        <c:out value="${userCartSession.client.firstName} ${userCartSession.client.lastName}" /><br>
                        <c:out value="${userCartSession.client.addressByInvoiceAddressId.addressLine1}" /><br>
                        <c:out value="${userCartSession.client.addressByInvoiceAddressId.addressLine2}" /><br>
                        <c:out value="${userCartSession.client.addressByInvoiceAddressId.city}" />,
                        <c:out value="${userCartSession.client.addressByInvoiceAddressId.state} ${userCartSession.client.addressByInvoiceAddressId.postalCode}" /><br>
                        <c:out value="${userCartSession.client.addressByInvoiceAddressId.countryByCountryId.description}" />
                    </address>
                </div>
                <div class="col-md-6 text-right">
                    <address>
                        <strong>Shipped To:</strong><br>
                        <c:out value="${userCartSession.client.firstName} ${userCartSession.client.lastName}" /><br>
                        <c:out value="${userCartSession.client.addressByDeliveryAddressId.addressLine1}" /><br>
                        <c:out value="${userCartSession.client.addressByDeliveryAddressId.addressLine2}" /><br>
                        <c:out value="${userCartSession.client.addressByDeliveryAddressId.city}" />,
                        <c:out value="${userCartSession.client.addressByDeliveryAddressId.state} ${userCartSession.client.addressByInvoiceAddressId.postalCode}" /><br>
                        <c:out value="${userCartSession.client.addressByDeliveryAddressId.countryByCountryId.description}" />
                    </address>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <address>
                        <strong>Payment Method:</strong><br>
                        <c:out value="${userCartSession.client.paymentMethodByPaymentMethodId.description}" />&nbsp;ending **** 4666<br>
                        <c:out value="${userCartSession.client.email}" />
                    </address>
                </div>
                <div class="col-md-6 text-right">
                    <address>
                        <strong>Order Date:</strong><br>
                        <c:out value="${userCartSession.order.datePlaced}" />
                    </address>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><strong>Order summary</strong></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <td><strong>Item</strong></td>
                                <td class="text-center"><strong>Price</strong></td>
                                <td class="text-center"><strong>Quantity</strong></td>
                                <td class="text-right"><strong>Totals</strong></td>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="orderLine" items="${userCartSession.orderLines}">
                                <tr>
                                    <td><c:out value="${orderLine.productByProductId}" /></td>
                                    <td class="text-center"><fmt:formatNumber value="${orderLine.productByProductId.priceExVat}" type="currency"/></td>
                                    <td class="text-center">${orderLine.quantity}</td>
                                    <td class="text-right"><fmt:formatNumber value="${orderLine.quantity * orderLine.productByProductId.priceExVat}" type="currency"/></td>
                                </tr>
                            </c:forEach>

                                <td class="thick-line"></td>
                                <td class="thick-line"></td>
                                <td class="thick-line text-center"><strong>Subtotal</strong></td>
                                <td class="thick-line text-right"><fmt:formatNumber value="${userCartSession.totalCostExVat}" type="currency"/></td>

                            </tr>
                            <tr>
                                <td class="no-line"></td>
                                <td class="no-line"></td>
                                <td class="no-line text-center"><strong>Shipping</strong></td>
                                <td class="no-line text-right"><fmt:formatNumber value="${userCartSession.shippingTax}" type="currency"/></td>

                            </tr>
                            <tr>
                                <td class="no-line"></td>
                                <td class="no-line"></td>
                                <td class="no-line text-center"><strong>Total</strong></td>
                                <td class="no-line text-right"><fmt:formatNumber value="${userCartSession.totalCostExVat + userCartSession.shippingTax}" type="currency"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
