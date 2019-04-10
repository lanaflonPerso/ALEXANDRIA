<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/products.css"/>">
<link href="https://fonts.googleapis.com/css?family=Jaldi:400,700" rel="stylesheet" type="text/css">

<%--<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
    <div class="carousel-inner">
        <div class="carousel-item active">
            <table>
                <tr>
                    <td><img class="d-block w-20"
                             src="http://1.bp.blogspot.com/-oD8c6FAA7Gg/ULmcVbdPjfI/AAAAAAAAAGk/kEvziwGST7M/s320/Nyan+Cat+Wallet.jpg"
                             alt="First slide"></td>
                    <td>
                        Titre <br>
                        Prénom auteur nom auteur <br>
                        Bio auteur
                    </td>
                </tr>
            </table>

        </div>
        <c:forEach var="a" items="${productsList}">
            <div class="carousel-item">
                <table>
                    <tr>
                        <td>
                            <img class="d-block w-20" src="data:image/jpg;base64,${a.base64Image}"/>
                        </td>
                        <td>
                                ${a.bookByIdProduct.title} <br>
                                ${a.bookByIdProduct.authorByAuthorId.firstName}
                            &nbsp; ${a.bookByIdProduct.authorByAuthorId.lastName} <br>
                                ${a.bookByIdProduct.authorByAuthorId.bio}
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="row">--%>


<div class="row">
    <%--catégories--%>
    <%--<div id="jquery-accordion-menu" class="jquery-accordion-menu col-sm-4">
        <div class="jquery-accordion-menu-header">HEADER</div>
        <ul>
            <c:forEach var="c" items="${categoryList}">
                <c:set var="parent" value="${c.categoryByParent.idCategory}"/>
                <c:if test="${parent == 1}">
                    <li><a href="<c:url value="/productsByCat${c.idCategory}"/>">${c.idCategory}--${c.description}</a></li>
                    <c:forEach var="k" items="${categoryList}">
                        <c:set var="parent" value="${c.idCategory}"/>
                        <c:if test="${parent == k.categoryByParent.idCategory}">
                            <li><a href="<c:url value="/productsByCat${k.idCategory}"/>">--->${k.idCategory}--${k.description}</a></li>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </ul>
        <div class="jquery-accordion-menu-footer">FOOTER</div>
    </div>--%>

    <%--new category --%>
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
    <div class="container col-sm-8">
        <div class="row">

            <c:forEach var="i" items="${productsList}">
                <table class="table-condensed table-bordered">
                    <tr>
                        <td><img src="data:image/jpg;base64,${i.base64Image}" width="150" height="150"/></td>
                        <td> Description : ${i.bookByIdProduct.authorByAuthorId.bio} </td>  <%--c'est la bio de l'auteur mais ça fait le job en desc d'article--%>
                    </tr>
                    <tr>
                        <td>${i.idProduct} - ${i.name}</td>
                        <td>Book title : ${i.bookByIdProduct.title}</td>
                    </tr>
                    <tr>
                        <td>isbn : ${i.bookByIdProduct.isbn}</td>
                        <td>Prix : <fmt:formatNumber value="${i.priceExVat}" type="currency"/></td>
                    </tr>
                    <tr>
                        <td>Author : ${i.bookByIdProduct.authorByAuthorId.firstName} &nbsp; ${i.bookByIdProduct.authorByAuthorId.lastName}</td>
                        <td>Publisher : ${i.bookByIdProduct.publisherByPublisherId}</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <a href="<c:url value="/addProduct?code=${i.idProduct}"/>">Add to cart</a>
                        </td>
                        <br><br>
                    </tr>
                </table>
            </c:forEach>

        </div>
    </div>

    <%--pagination--%>
    <div class="col-sm-2">

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