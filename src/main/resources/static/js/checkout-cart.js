const checkout = document.getElementById('checkoutList');
const checkoutArticles = [];


fetch("http://localhost:8080/articles/checkoutArticles-api")
//      fetch("https://buil-to-go.herokuapp.com/articles/checkoutArticles-api")
    .then(response => response.json())
    .then((json) => json.forEach((articles) => {

        checkoutArticles.push(articles);
        console.log(checkoutArticles);


        checkout.innerHTML = checkoutArticles
            .map((a) => {

                return `<div class="col-md-3" >


                  <div class="card mb-4 box-shadow">

                                                   <div class="  text-light p-1  ">
            
                                                     <p  >SUMМЕ: ${a.currentSumOrder}€</p>
                                                   </div>          
                                                                 
                                                            
                                                                 
                                                                      
               
                                              


                  
                   <img src="${a.imgUrl}" class="card-img-top" alt="Thumbnail [100%x225]"
                     data-holder-rendered="true"  style="height: 250px; width: 100%; display: block;">
                    
                         <div class="card-body">
                           <div class="text-center">
                              <p class="card-text    border-bottom "> ${a.articleName}</p>
                               <p class="card-text    border-bottom ">Model:  ${a.model}</p>
                               <p class="card-text    border-bottom ">Price: ${a.price}€</p>
                               <p class="card-text    border-bottom ">VE: ${a.quantity}St.</p>
                                <p class="card-text    border-bottom ">Menge: ${a.itemCounter}</p>
                           </div>
                    
                    
                           <div class="d-flex justify-content-between align-items-center">


                              <div class="btn-group">
                                  <a href="/process/article/${a.idArticle}" type="button"
                                  class="btn btn-sm btn-outline-secondary">Bearbeiten</a>
                              </div> 
                              
                              <div class="btn-group">
                                  <a href="/checkout/clean/${a.idArticle}" type="button"
                                  class="btn btn-sm btn-outline-clean-loschen">LÖSCHEN</a>
                              </div> 
                        

                            </div>
                    
                          </div>
                  </div>
                </div>`
            })
            .join('');


    }));






