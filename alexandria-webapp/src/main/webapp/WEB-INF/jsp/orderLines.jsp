
<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">Order number</th>
        <th scope="col">Date placed</th>
        <th scope="col">Date shipped</th>
        <th scope="col">Date delivered</th>
        <th scope="col">Total order lines</th>
        <th scope="col">Shipping method</th>
        <th scope="col">Total cost (ex VAT)</th>
        <th scope="col">order comment</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${clientOrder.idOrderHeader}</td>
            <td>${clientOrder.datePlaced}</td>
            <td>${clientOrder.dateShipped}</td>
            <td>${clientOrder.dateDelivered}</td>
            <td>${clientOrder.nbOrderLines}</td>
            <td>${clientOrder.shippingMethodByShippingMethodId.description}</td>
            <td>${clientOrder.totalCostExVat}</td>
            <td>${clientOrder.comment}</td>
        </tr>
    </tbody>
</table>

<table class="table">
    <thead class="thead-light">
    <tr>
        <th scope="col">Picture</th>
        <th scope="col">Product id</th>
        <th scope="col">Quantity</th>
        <th scope="col">Unit price</th>
        <th scope="col">Total line amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="orderLine" items="${orderLines}" varStatus="status">
        <tr>
            <td><a href="<c:url value="/product"><c:param name="productId" value="${orderLine.productId}"/></c:url> ">
                <img src="data:image/jpg;base64,${orderLine.productByProductId.base64Image}" alt="${orderLine.productByProductId.name}" width="50px" height="50px"/>
            </a></td>
            <td>${orderLine.productByProductId.idProduct}</td>
            <td>${orderLine.quantity}</td>
            <td><fmt:formatNumber value="${orderLine.productByProductId.priceExVat}" type="currency"/></td>
            <td><fmt:formatNumber value="${orderLine.productByProductId.priceExVat * orderLine.quantity}" type="currency"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<a href="<c:url value="/orders"/>">back</a>