const articlesList = document.getElementById('articlesList');
const searchBar = document.getElementById('searchInput');
const allArticles = [];


fetch("http://localhost:8080/articles/api-all")
// fetch("https://buil-to-go.herokuapp.com/articles/api-all")
    .then(response => response.json())
    .then(data => {
        for (let d of data) {
            allArticles.push(d);
        }
    });

console.log(allArticles);

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
                        <p class="card-text text-light border-bottom ">${a.articleName}</p>
                        <p class="card-text text-light border-bottom ">${a.model}</p>
                      <p class="card-text text-light border-bottom ">Price: ${a.price}€</p>
                      <p class="card-text text-light border-bottom ">Quantity: ${a.quantity}St.</p>

                     </div>
                    <div class="d-flex justify-content-between align-items-center">

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

}


