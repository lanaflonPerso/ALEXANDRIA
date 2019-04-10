<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/cart.css"/>">
<div id="wrapper" class="container">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="breadcrumb">
                <a href="#" class="active">Cart overview</a>
                <a href="">Delivery</a>
                <a href="">Payment</a>
            </div>
        </div>
        <div class="col-md-3"></div>
    </div>

    <div class="row">
        <div class="col-md-8 order-md-2 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">My cart ${userCartSession.client.lastName} &nbsp; ${userCartSession.client.firstName}</span>
            </h4>

            <c:forEach var="i" items="${userCartSession.orderLines}">
                <div class="col-xs-8">
                    <form action="" method="get">
                        <table class="table-condensed table-bordered">
                            <tr>
                                <td><${i.productByProductId.base64Image}</td>


                                <td>${i.productByProductId.name}<br>
                                        ${i.productByProductId.priceExVat} €
                                </td>

                                <td>Stock : ${i.productByProductId.stock}</td>

                                <td><div class="form-group">
                                    <label>Qty</label>
                                    <select class="custom-select">
                                        <option selected>Open this select menu</option>
                                        <option value="1">One</option>
                                        <option value="2">Two</option>
                                        <option value="3">Three</option>
                                    </select>

                                        ${i.productByProductId.priceExVat} EUR</td>

                                <td><span class="glyphicon glyphicon-trash"></span></td>
                                <br><br>
                            </tr>
                        </table>
                    </form>
                </div>
            </c:forEach>


        </div>

        <div class="col-md-4 order-md-2 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Total</span>
                <span class="badge badge-secondary badge-pill">${orderLines.size()}</span>
            </h4>
            <ul class="list-group mb-3">
                <c:forEach var="i" items="${orderLines}">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><img src="data:image/jpg;base64,${i.productByProductId.base64Image}" width="150" height="150"/></h6>
                            <small class="text-muted">${i.productByProductId.name}</small>
                        </div>
                        <span class="text-muted">${i.productByProductId.priceExVat}</span>
                    </li>
                </c:forEach>

                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">Second product</h6>
                        <small class="text-muted">Brief description</small>
                    </div>
                    <span class="text-muted">$8</span>
                </li>

                <li class="list-group-item d-flex justify-content-between lh-condensed">
                    <div>
                        <h6 class="my-0">Third item</h6>
                        <small class="text-muted">Brief description</small>
                    </div>
                    <span class="text-muted">$5</span>
                </li>

                <li class="list-group-item d-flex justify-content-between">
                    <span>Total (USD)</span>
                    <strong>$20</strong>
                </li>
            </ul>
         </div>
    </div>
</div>

</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>window.jQuery || document.write('<script src="/docs/4.3/assets/js/vendor/jquery-slim.min.js"><\/script>')</script><script src="/docs/4.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-xrRywqdh3PHs8keKZN+8zzc5TX0GRTLCcmivcbNJWm2rs5C8PRhcEn3czEjhAO9o" crossorigin="anonymous"></script>
<script src="form-validation.js"></script>
</body>
</html>
