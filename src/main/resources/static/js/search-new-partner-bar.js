const articlesList = document.getElementById('articlesList');
const searchBar = document.getElementById('searchInput');
const allArticles = [];


fetch("http://localhost:8080/articles/new-partner-api-search")
// fetch("https://buil-to-go.herokuapp.com/articles/new-partner-api-search")
    .then(response => response.json())
    .then(data => {
        for (let d of data) {
            allArticles.push(d);
        }
    })


searchBar.addEventListener('keyup', (e) => {
    const searchingCharacters = searchBar.value.toLowerCase();
    console.log(allArticles);
    let filteredArticles = allArticles.filter(articles => {
        return articles.articleName.toLowerCase().includes(searchingCharacters)
            || articles.model.toLowerCase().includes(searchingCharacters);
    });
    console.log(filteredArticles);
    displayArticles(filteredArticles);
});


const displayArticles = (article) => {

    articlesList.innerHTML = article
        .map((a) => {
            return `<div class="col-md-3" >
                <div class="card mb-4 box-shadow">
                <img src="${a.imgUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"
                     style="height: 250px; width: 100%; display: block;">
                <div class="card-body">
                    <div class="text-center">
                        <p class="card-text   border-bottom "> ${a.articleName}</p>
                        <p class="card-text   border-bottom "> ${a.model}</p>                      
                       <p class="card-text   border-bottom ">Price: ${a.price}€</p>
                       <p class="card-text   border-bottom ">Quantity: ${a.quantity}St.</p>

                    </div>
                    <div class="d-flex justify-content-between align-items-center">

                        <div class="btn-group">
                            <a href="/articles/new-partner-details/${a.id}" type="button"
                             class="btn btn-sm btn-outline-secondary">Beschreibung</a>
                        </div>
                         

                    </div>
                </div>
            </div>
            </div>`
        })
        .join('');

}


