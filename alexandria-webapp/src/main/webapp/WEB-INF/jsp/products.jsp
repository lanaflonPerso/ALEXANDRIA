<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/products.css"/>">
<link href="https://fonts.googleapis.com/css?family=Jaldi:400,700" rel="stylesheet" type="text/css">

<%--pagination--%>
<div class="row">
    <div class="col-sm-2"><%--pour centrer la pagination--%></div>
    <div class="col-md-10">
        <div class="col text-center">
            <c:url value="/products" var="prev">
                <c:param name="page" value="${page-1}"/>
                <c:param name="categoryId" value="${category}"/>
            </c:url>
            <c:if test="${page > 1}">
                <a href="<c:out value="${prev}" />">Previous page &nbsp;<i class="fas fa-arrow-circle-left"></i></a>
            </c:if>
            <c:url value="/products" var="next">
                <c:param name="page" value="${page + 1}"/>
                <c:param name="categoryId" value="${category}"/>
            </c:url>
            <c:if test="${page + 1 <= maxPages}">
                <a href="<c:out value="${next}"/>"><i class="fas fa-arrow-circle-right"></i>&nbsp; Next page</a>
            </c:if>
        </div>
    </div>
</div>

<div class="row">
    <%--catégories--%>
    <div class="vnav col-sm-2">
        <ul class="cd-accordion-menu">
            <li class="has-children">
                <c:forEach var="catLvl1" items="${categoryList}">
                    <c:set var="parent" value="${catLvl1.categoryByParent.idCategory}"/>
                    <c:if test="${parent == null}">
                        <input type="checkbox" name="${catLvl1.description}" id="${catLvl1.description}" checked>
                        <label for="${catLvl1.description}"><a href="<c:url value="/products"/>">All
                            categories</a></label>
                    </c:if>
                </c:forEach>

                <ul>

                    <c:forEach var="catLvl2" items="${categoryList}">
                        <c:set var="parent" value="${catLvl2.categoryByParent.idCategory}"/>
                        <c:if test="${parent == 1}">
                            <li class="has-children">
                            <input type="checkbox" name="${catLvl2.description}" id="${catLvl2.description}">
                            <label for="${catLvl2.description}">
                        <span>
                            <c:forEach var="categoryParent" items="${categoryParent}">
                                <c:if test="${catLvl2 == categoryParent}">
                                    <i class="fas fa-bars"></i>
                                </c:if>
                            </c:forEach>
                            <a href="<c:url value="/products"><c:param name="categoryId" value="${catLvl2.idCategory}"/></c:url> ">${catLvl2.idCategory}-${catLvl2.description}</a>
                        </span>
                            </label>
                            <ul>
                                <c:forEach var="catLvl3" items="${categoryList}">
                                    <c:set var="parent" value="${catLvl2.idCategory}"/>
                                    <c:if test="${parent == catLvl3.categoryByParent.idCategory}">
                                        <li>
                                            <a href="<c:url value="/products"><c:param name="categoryId" value="${catLvl3.idCategory}"/></c:url> ">${catLvl3.idCategory}-${catLvl3.description}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </c:if>

                        </li>
                    </c:forEach>
                </ul>
            </li>
        </ul>
    </div>

    <%--liste de produits--%>
    <div class="container main-section">
        <div class="row">
            <c:forEach var="product" items="${productsList}">
                <div class="col-md-3 col-sm-6 col-xs-10 product">
                    <div class="row product-part">
                        <div class="col-md-12 col-sm-12 colxs-12 img-section">
                            <a href="<c:url value="/product"><c:param name="productId" value="${product.idProduct}"/></c:url> "><img
                                    src="data:image/jpg;base64,${product.base64Image}"/></a>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 product-title">
                            <h1>${product.idProduct} - ${product.name}</h1>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 product-description">
                            <p>
                                    ${product.bookByIdProduct.title}
                                    by ${product.bookByIdProduct.authorByAuthorId.firstName} ${product.bookByIdProduct.authorByAuthorId.lastName}
                            </p>
                        </div>
                        <div class="col-md-12 col-sm-12 col-xs-12 product-cart">
                            <div class="row">
                                <div class="col-md-6 col-sm-12 col-xs-6">
                                    <p><fmt:formatNumber value="${product.priceExVat}" type="currency"/></p>
                                </div>
                                <div class="col-md-6 col-sm-12 col-xs-6 text-right product-add-cart">
                                    <c:if test="${product.stock >= 1}">
                                        <c:out value="Stock: ${product.stock} "/>
                                        <a href="<c:url value="/addProduct"><c:param name="idProduct" value="${product.idProduct}"/></c:url> "
                                           class="btn btn-secondary">ADD TO CART</a>
                                    </c:if>
                                    <c:if test="${product.stock < 1}">
                                        <a class="btn btn-danger disabled" role="button" href="<c:url value="#"/>">OUT OF STOCK</a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
