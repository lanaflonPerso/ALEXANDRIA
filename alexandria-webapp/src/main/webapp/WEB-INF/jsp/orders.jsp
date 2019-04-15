
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
            <th scope="col">order detail</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach var="order" items="${orders}" varStatus="status">
        <tr>
            <td>${order.idOrderHeader}</td>
            <td>${order.datePlaced}</td>
            <td>${order.dateShipped}</td>
            <td>${order.dateDelivered}</td>
            <td>${order.nbOrderLines}</td>
            <td>${order.shippingMethodByShippingMethodId.description}</td>
            <td><fmt:formatNumber value="${order.totalCostExVat}" type="currency"/></td>
            <td>${order.comment}</td>
            <td><a href="<c:url value="/orderLines"><c:param name="orderIndex" value="${status.index}"/></c:url> ">display order ${order.idOrderHeader} detail</a></td>

        </tr>
    </c:forEach>
        </tbody>
    </table>

