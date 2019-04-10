<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/products.css"/>">
<link href="https://fonts.googleapis.com/css?family=Jaldi:400,700" rel="stylesheet" type="text/css">

<div class="row">
    <%--catégories--%>
    <div class="vnav col-sm-2">
        <ul class="cd-accordion-menu">
            <li class="has-children">
            <c:forEach var="j" items="${categoryList}">
                <c:set var="parent" value="${j.categoryByParent.idCategory}"/>
                <c:if test="${parent == null}">
                    <input type="checkbox" name="${j.description}" id="${i.description}" checked>
                    <label for="${j.description}"><a href="<c:url value="/products"/>">All categories</a></label>
                </c:if>
            </c:forEach>

            <ul>

            <c:forEach var="c" items="${categoryList}">
                <c:set var="parent" value="${c.categoryByParent.idCategory}"/>
                <c:if test="${parent == 1}">
                    <li class="has-children">
                    <input type="checkbox" name="${c.description}" id="${c.description}">
                    <label for="${c.description}">
                        <span>
                            <c:forEach var="b" items="${categoryParent}">
                                <c:if test="${c == b}">
                                    <i class="fas fa-plus"></i>&nbsp;
                                </c:if>
                            </c:forEach>
                            <a href="<c:url value="/products${c.idCategory}"/>">${c.idCategory}-${c.description}</a>
                        </span>
                    </label>
                    <ul>
                        <c:forEach var="k" items="${categoryList}">
                            <c:set var="parent" value="${c.idCategory}"/>
                            <c:if test="${parent == k.categoryByParent.idCategory}">
                                <li>
                                    <a href="<c:url value="/products${k.idCategory}"/>">${k.idCategory}-${k.description}</a>
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
    </div><!-- cd-accordion-menu -->


    <%-- liste de produits--%>
    <div class="container col-sm-9">
        <div class="row">
            <ul class="list-group mb-3">
            <c:forEach var="i" items="${productsList}">

                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <p>
                        <header>${i.idProduct} - ${i.name}</header>
                            <br/>
                        <img src="data:image/jpg;base64,${i.base64Image}" width="150" height="150"/>
                        </p>
                    <br/>
                        <article>Description : ${i.bookByIdProduct.authorByAuthorId.bio}</article>  <%--c'est la bio de l'auteur mais ça fait le job en desc d'article--%>
                    <br/>
                        <p><h6>Book title : ${i.bookByIdProduct.title}</h6>
                        <em>isbn : ${i.bookByIdProduct.isbn}</em>
                        </p>
                    <br/>
                        <p> Author : ${i.bookByIdProduct.authorByAuthorId.firstName} &nbsp; ${i.bookByIdProduct.authorByAuthorId.lastName}
                            Publisher : ${i.bookByIdProduct.publisherByPublisherId.name}
                            Price : <fmt:formatNumber value="${i.priceExVat}" type="currency"/>
                        </p>
                    <br/>
                    </li>
                    <li>
                        <a href="<c:url value="/addProduct?code=${i.idProduct}"/>"><button class="btn btn-secondary">Add to cart</button></a>
                    </li>

            </c:forEach>
            </ul>
        </div>
    </div>
        <br/>
    <%--pagination--%>
    <div class="row">
    <div class="col-offset-5 col-sm-2">
        <c:url value="/products${category}" var="prev">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />">Previous Page</a>
        </c:if>
        <c:url value="/products${category}" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href="<c:out value="${next}"/>">Next Page</a>
        </c:if>
    </div>
</div>
</div>