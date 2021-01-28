$(document).ready(function () {

  var slider = document.getElementById("ex1");
  var output = document.getElementById("demo");
  output.innerHTML = slider.value;

  slider.oninput = function () {
    output.innerHTML = this.value;
  }

  //on page load books
  var myUrl = "http://localhost:8080/api/book/all";
  $.ajax({ url: myUrl }).then(
    function (books, status) {
      $.each(books, function (n, book) {
        $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                  <div class="card h-100" >\
                      <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                      <div class="card-body">\
                      <h4 class="card-title">\
                          <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                      </h4>\
                      <h5>'+ book.regularPrice + '€</h5>\
                      <p class="card-text2" >'+ book.description + '</p>\
                  </div>\
                  <div class="addToCart" >\
                    <a type="button" id="cart'+ book.id + '" class="sendCart" style="text-decoration: none;"><i\
                    class="fas fa-heart mr-1" ></i>&#128722; Add to cart</a>\
                  </div>\
                  <div class="card-footer">\
                  <div class="rating"> <input data-readonly:"true" type="radio" name="rating" value="5" id="5"' + (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                  data-readonly:"true" type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input  type="radio" name="rating"\
                  data-readonly:"true"  value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input data-readonly:"true" type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                    for="2">☆</label> <input data-readonly:"true" type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                  </div>\
                </div>\
               </div>\
              </div>\
            ' );
      });
    });

  //on page load author list
  var myUrl = "http://localhost:8080/api/author/all";
  $.ajax({ url: myUrl }).then(
    function (authors, status) {
      $.each(authors, function (i, author) {

        $("#collapseExample1").append('<div class="card card-body">\
        <ul class="list-group list-group-flush">\
            <li class="list-group-item">\
              <!-- Default checked -->\
              <div class="custom-control custom-checkbox">\
                <input type="checkbox" class="custom-control-input auth" id="auth'+ author.id + '" >\
                <label class="custom-control-label" for="auth'+ author.id + '">' + author.firstName + " " + author.lastName + '</label>\
              </div>\
            </li>\
            </ul>\
              </div>\
          ' );
      });
    });

  //on page load categorie list
  var myUrl = "http://localhost:8080/api/categorie/all";
  $.ajax({ url: myUrl }).then(
    function (categories, status) {
      $.each(categories, function (j, categorie) {
        $("#collapseExample2").append('\
      <div class="card card-body">\
        <ul class="list-group list-group-flush">\
          <li class="list-group-item">\
            <!-- Default checked -->\
            <div class="custom-control custom-checkbox">\
              <input type="checkbox" class="custom-control-input cate" id="cate'+ categorie.id + '">\
              <label class="custom-control-label" for="cate'+ categorie.id + '">' + categorie.name + '</label>\
            </div>\
          </li>\
        </ul>\
      </div>');
      });
    });

  //books filter author checked
  var counter = 0;
  var authUrl = "http://localhost:8080/api/book/all/author/";
  $("#collapseExample1").on("change", ".auth ", function () {
    var aid = this.id.toString().slice(4);
    var aid2 = this.id;
    var isChecked = $('#auth' + aid).is(":checked") ? true : false;
    if (counter == 0) {
      $(".book1").empty();
      counter = 1;
    }
    if (!($(".auth").is(":checked")) && !($(".cate").is(":checked"))) {
      location.reload();
    }
    if (isChecked) {
      $.ajax({ url: authUrl + aid }).then(
        function (books, status) {
          $.each(books, function (i, book) {
            $(".book1").append('<div class="col-lg-4 col-md-6 mb-4 authorApp' + aid + '">\
                        <div class="card h-100" >\
                            <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                            <div class="card-body">\
                            <h4 class="card-title">\
                                <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                            </h4>\
                            <h5>'+ book.regularPrice + '€</h5>\
                            <p class="card-text2" >'+ book.description + '</p>\
                        </div>\
                        <div class="addToCart">\
                            <a type="button" id="cart'+ book.id + '" class="sendCart" style="text-decoration: none;"><i\
                            class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
                        </div>\
                        <div class="card-footer">\
                        <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                            type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                            value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                            for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                        </div>\
                      </div>\
                     </div>\
                    </div>\
                  ' );
          });
        });
    } else {
      $(".authorApp" + aid).css("display", "none");
    }
  });

  //books filter categorie checked
  var counter2 = 0;
  var catUrl = "http://localhost:8080/api/book/all/categories/";
  $("#collapseExample2").on("change", ".cate ", function () {
    var cid = [this.id.toString().slice(4)];
    var cid2 = this.id;
    var isChecked = $('#cate' + cid).is(":checked") ? true : false;
    if (counter2 == 0) {
      $(".book1").empty();
      counter2 = 1;
    }
    if (!($(".auth").is(":checked")) && !($(".cate").is(":checked"))) {
      location.reload();
    }
    if (isChecked) {
      $.ajax({ url: catUrl + cid }).then(
        function (books, status) {
          $.each(books, function (i, book) {

            var myUrl2 = "http://localhost:8080/api/book/id/" + book.id;
            $(".book1").append('<div class="col-lg-4 col-md-6 mb-4 cateApp' + cid + '" >\
                        <div class="card h-100" >\
                            <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                            <div class="card-body">\
                            <h4 class="card-title">\
                                <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                            </h4>\
                            <h5>'+ book.regularPrice + '€</h5>\
                            <p class="card-text2" >'+ book.description + '</p>\
                        </div>\
                        <div class="addToCart">\
                            <a type="button" id="cart'+ book.id + '" class="sendCart" style="text-decoration: none;"><i\
                            class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
                        </div>\
                        <div class="card-footer">\
                        <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                            type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                            value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                            for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                        </div>\
                      </div>\
                     </div>\
                    </div>\
                  ' );
          });
        });
    } else {
      $(".cateApp" + cid).css("display", "none");
    }
  });

  //check option for sort by
  $("#sort").change(function () {
    switch ($(this).children(":selected").val()) {
      case "1": defaultBooks(); break;
      case "2": hotBooks(); break;
      case "3": priceAscBooks(); break;
      case "4": priceDescBooks(); break;
    }
  });

  //sort by default
  function defaultBooks() {
    $(".book1").empty();
    var myUrl = "http://localhost:8080/api/book/all";
    $.ajax({ url: myUrl }).then(
      function (books, status) {
        var temp = [];
        $.each(books, function (k, book) {
          temp[k] = book;
        });
        for (i = 0; i < temp.length - 1; i++) {
          for (j = 0; j < temp.length - i - 1; j++) {
            if (temp[j].countSales < temp[j + 1].countSales) {
              var t = temp[j];
              temp[j] = temp[j + 1];
              temp[j + 1] = t;
            }
          }
        }
        $.each(temp, function (n, book) {
          $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                  <div class="card h-100" >\
                      <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                      <div class="card-body">\
                      <h4 class="card-title">\
                          <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                      </h4>\
                      <h5>'+ book.regularPrice + '€</h5>\
                      <p class="card-text2" >'+ book.description + '</p>\
                  </div>\
                  <div class="addToCart">\
                      <a id="cart'+ book.id + '"  type="button" class="sendCart" style="text-decoration: none;"><i\
                      class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
                  </div>\
                  <div class="card-footer">\
                  <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                      type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                      value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                      for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                  </div>\
                </div>\
               </div>\
              </div>\
            ' );
        });
      });
  };

  //sort by hot products
  function hotBooks() {
    $(".book1").empty();
    var myUrl = "http://localhost:8080/api/book/all/hot";
    $.ajax({ url: myUrl }).then(
      function (books, status) {
        var temp = [];
        $.each(books, function (k, book) {
          temp[k] = book;
        });
        for (i = 0; i < temp.length - 1; i++) {
          for (j = 0; j < temp.length - i - 1; j++) {
            if (temp[j].countSales < temp[j + 1].countSales) {
              var t = temp[j];
              temp[j] = temp[j + 1];
              temp[j + 1] = t;
            }
          }
        }
        $.each(temp, function (n, book) {
          $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                  <div class="card h-100" >\
                      <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                      <div class="card-body">\
                      <h4 class="card-title">\
                          <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                      </h4>\
                      <h5>'+ book.regularPrice + '€</h5>\
                      <p class="card-text2" >'+ book.description + '</p>\
                  </div>\
                  <div class="addToCart">\
                      <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
                      class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
                  </div>\
                  <div class="card-footer">\
                  <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                      type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                      value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                      for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                  </div>\
                </div>\
               </div>\
              </div>\
            ' );
        });
      });
  };

  //sort by price descending
  function priceDescBooks() {
    $(".book1").empty();
    var myUrl = "http://localhost:8080/api/book/all";
    $.ajax({ url: myUrl }).then(
      function (books, status) {
        var temp = [];
        $.each(books, function (k, book) {
          temp[k] = book;
        });
        for (i = 0; i < temp.length - 1; i++) {
          for (j = 0; j < temp.length - i - 1; j++) {
            if (temp[j].regularPrice < temp[j + 1].regularPrice) {
              var t = temp[j];
              temp[j] = temp[j + 1];
              temp[j + 1] = t;
            }
          }
        }
        $.each(temp, function (n, book) {
          $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                  <div class="card h-100" >\
                      <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                      <div class="card-body">\
                      <h4 class="card-title">\
                          <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                      </h4>\
                      <h5>'+ book.regularPrice + '€</h5>\
                      <p class="card-text2" >'+ book.description + '</p>\
                  </div>\
                  <div class="addToCart">\
                      <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
                      class="fas fa-heart mr-1" ></i>&#128722; Add to cart</a>\
                  </div>\
                  <div class="card-footer">\
                  <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                      type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                      value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                      for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                  </div>\
                </div>\
               </div>\
              </div>\
            ' );
        });
      });
  };

  //sort by price asscending
  function priceAscBooks() {
    $(".book1").empty();
    var myUrl = "http://localhost:8080/api/book/all";
    $.ajax({ url: myUrl }).then(
      function (books, status) {
        var temp = [];
        $.each(books, function (k, book) {
          temp[k] = book;
        });
        for (i = 0; i < temp.length - 1; i++) {
          for (j = 0; j < temp.length - i - 1; j++) {
            if (temp[j].regularPrice > temp[j + 1].regularPrice) {
              var t = temp[j];
              temp[j] = temp[j + 1];
              temp[j + 1] = t;
            }
          }
        }
        $.each(temp, function (n, book) {
          $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                  <div class="card h-100" >\
                      <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                      <div class="card-body">\
                      <h4 class="card-title">\
                          <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                      </h4>\
                      <h5>'+ book.regularPrice + '€</h5>\
                      <p class="card-text2" >'+ book.description + '</p>\
                  </div>\
                  <div class="addToCart">\
                      <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
                      class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
                  </div>\
                  <div class="card-footer">\
                  <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                      type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                      value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                      for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                  </div>\
                </div>\
               </div>\
              </div>\
            ' );
        });
      });
  };

  //dislpay range value
  var value;
  $("#ex1").change(function () {
    $(".book1").empty();
    var myUrl = "http://localhost:8080/api/book/all/range/0/" + this.value;
    $.ajax({ url: myUrl }).then(
      function (books, status) {
        $.each(books, function (i, book) {

          $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                  <div class="card h-100" >\
                      <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="data:image/png;base64,' + book.image + '"  alt="" height="350px"></a>\
                      <div class="card-body">\
                      <h4 class="card-title">\
                          <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
                      </h4>\
                      <h5>'+ book.regularPrice + '€</h5>\
                      <p class="card-text2" >'+ book.description + '</p>\
                  </div>\
                  <div class="addToCart">\
                      <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
                      class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
                  </div>\
                  <div class="card-footer">\
                  <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                      type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                      value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                      for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                  </div>\
                </div>\
               </div>\
              </div>\
            ' );
        });
      });
  });

  //add to cart 
  $("body").on("click", ".sendCart", function () {
    var cartId = localStorage.getItem("cartId");
    var username = localStorage.getItem("username");
    var bookId = this.id.toString().slice(4);
    var myUrl = "http://localhost:8080/api/cart/addtocart/" + cartId + "/" + username + "/" + bookId + "/" + 1;
    $.ajax({
      type: "POST",
      url: myUrl,
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
        "Authorization": localStorage.getItem("Authorization"),
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
      success: function (response, status) {
        localStorage.setItem("cartId", response.id);
        $("head").append("<link id='shakeCart' rel='stylesheet' href='/css/shake.css'>");
      },
      error: function (xhr) {
        if (xhr.status == 406) {
          alert("Not enough books on store!");
        }
        if (localStorage.getItem("username") == null) {
          alert("Please sign in to add to cart!");
        }
      }
    });
    $("#shakeCart").remove();
  });

});