<div class="container">
    <h3 class="pb-3 mb-4 font-italic border-bottom">
        ${product.idProduct} - ${product.name}
    </h3>
    <div class="row">
        <div class="col-md-6">
            <div class="card flex-md-row mb-5 shadow-sm h-md-250">
                <div class="card-body d-flex flex-column align-items-start">
                    <strong class="d-inline-block mb-2 text-primary">${product.bookByIdProduct.title}</strong>

                    <h6 class="mb-0">
                        <p class="text-dark" href="#">by ${product.bookByIdProduct.authorByAuthorId.firstName} &nbsp; ${product.bookByIdProduct.authorByAuthorId.lastName}</p>
                    </h6>
                    <div class="mb-1 text-muted small"><p class="text-dark" href="#">${product.bookByIdProduct.authorByAuthorId.bio}</p></div>
                    <p><fmt:formatNumber value="${product.priceExVat}" type="currency"/> &nbsp; <a class="btn btn-secondary" role="button" href="<c:url value="/addProduct?code=${product.idProduct}"/>">ADD TO CART</a></p>
                    <p class="card-text mb-auto">Additional information :
                        <ul>
                            <li>Publisher : ${product.bookByIdProduct.publisherByPublisherId.name}</li>
                            <li>isbn : ${product.bookByIdProduct.isbn}</li>
                            <li>${product.bookByIdProduct.nbPages} pages</li>
                            <li>Genre(s) :
                                <c:forEach var="genre" items="${product.bookByIdProduct.bookGenresByIdBook}">
                                    ${genre.genreByGenreId.description} &nbsp;
                                </c:forEach>
                            </li>
                            <li>Collection : ${product.bookByIdProduct.collectionByCollectionId.description}</li>
                        </ul>


                    </p>


                    <a class="btn btn-danger" role="button" href="<c:url value="${referer}"/>">back</a>
                </div>
                <img class="card-img-right flex-auto d-none d-lg-block" style="width: 150px; height: 150px; margin-left: 10px;" src="data:image/jpg;base64,${product.base64Image}" alt="${product.name}"/>
            </div>
        </div>
