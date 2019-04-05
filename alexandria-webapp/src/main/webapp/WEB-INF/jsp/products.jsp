<div id="carouselExampleSlidesOnly" class="carousel slide" data-ride="carousel">
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
<div class="row">
    <%--catégories--%>
    <div id="jquery-accordion-menu" class="jquery-accordion-menu col-sm-4">
        <div class="jquery-accordion-menu-header">HEADER</div>
        <ul>
            <c:forEach var="c" items="${categoryList}">
                <c:set var="parent" value="${c.categoryByParent.idCategory}"/>
                <c:if test="${parent == 1}">
                    <li><a href="productsByCat${c.idCategory}">${c.idCategory}--${c.description}</a></li>
                    <c:forEach var="k" items="${categoryList}">
                        <c:set var="parent" value="${c.idCategory}"/>
                        <c:if test="${parent == k.categoryByParent.idCategory}">
                            <li><a href="productsByCat${k.idCategory}">--->${k.idCategory}--${k.description}</a></li>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </ul>
        <div class="jquery-accordion-menu-footer">FOOTER</div>
    </div>

    <%-- liste de produits--%>
    <div class="container col-sm-8">
        <div class="row">

            <c:forEach var="i" items="${productsList}">
                <div class="col-sm-12 col-md-4 col-xl-2">
                    <form action="" method="get">
                        <table class="table-condensed table-bordered">
                            <tr>
                                <td><img src="data:image/jpg;base64,${i.base64Image}" width="150" height="150"/></td>
                            </tr>
                            <tr>
                                <td>${i.idProduct} - ${i.name}</td>
                            </tr>
                            <tr>
                                <td>Stock : ${i.stock}</td>
                            </tr>
                            <tr>
                                <td>Prix : ${i.priceExVat} EUR</td>
                            </tr>
                            <tr>
                                <td colspan="4"><input class="btn btn-info" type="submit" name="addToCart"
                                                       value="Ajouter au panier"></td>
                                <br><br>
                            </tr>
                        </table>
                    </form>
                </div>
            </c:forEach>

        </div>
    </div>
</div>