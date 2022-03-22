const cupsList = document.getElementById('cupsList');
const allCups = [];


fetch("http://localhost:8080/articles/api-cups")
// fetch("https://buil-to-go.herokuapp.com/articles/api-cups")
    .then(response => response.json())
    .then((json) => json.forEach((articles, idx) => {

        allCups.push(articles);
        console.log(allCups);


        cupsList.innerHTML = allCups
            .map((a) => {
                return `<div class="col-md-3"  >
                <div class="card mb-4 box-shadow"  0>
                <img src="${a.imgUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height:250px; width: 100%; display: block;">
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text   border-bottom ">${a.articleName}</p>
                        <p class="card-text   border-bottom ">  ${a.model}</p>
                        <p class="card-text   border-bottom ">Price: ${a.price}€</p>
                       <p class="card-text   border-bottom ">Quantity: ${a.quantity}St.</p>
                    </div>
                    <div class="d-flex justify-content-between align-items-center" >

                        <div class="btn-group">
                            <a href="/articles/details/${a.id}"  type="button" class="btn btn-sm btn-outline-secondary">
                            Beschreibung</a>
                        </div>


                    </div>
                </div>
            </div>
            </div>`
            })
            .join('');


    }));






