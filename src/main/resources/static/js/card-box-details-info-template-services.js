function cardBoxDetailsInfo(allCups) {

    // cupsList.innerHTML = allCups
    allCups
        .map((a) => {
            return `<div class="col-md-3" >
                <div class="card mb-4 box-shadow">
                <img src="${a.imgUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 225px; width: 100%; display: block;">
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text border-bottom ">Name: ${a.articleName}</p>
                        <p class="card-text border-bottom ">Model: ${a.model}</p>
                        <p class="card-text border-bottom ">Price: ${a.price}</p>
                       <p class="card-text border-bottom ">Quantity: ${a.quantity} </p>
                    </div>
                    <div class="d-flex justify-content-between align-items-center">

                        <div class="btn-group">
                            <a href="/articles/details/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">
                            Details</a>
                        </div>
                        <div class="btn-group">
                            <a href="/articles/delete/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">
                            Delete</a>
                        </div>

                    </div>
                </div>
            </div>
            </div>`
        })
        .join('');


}