$(document).ready(function () {

  var slider = document.getElementById("ex1");
  var output = document.getElementById("demo");
  output.innerHTML = slider.value;
  var unsortedBooks = [];
  slider.oninput = function () {
    output.innerHTML = this.value;
  }
  var authorsList = [];
  var categorieList = [];

  //on page load books
  var myUrl = "http://localhost:8080/api/book/all";
  $.ajax({ url: myUrl }).then(
    function (books, status) {
      $.each(books, function (n, book) {
        $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                  <div class="card h-100" >\
                      <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
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
        unsortedBooks.push(book);
      });
      var sortChoice = $("#sort").val();
      sorting(sortChoice);
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
                <input type="checkbox" class="custom-control-input cate" id="auth'+ author.id + '" >\
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

  //categorie and author checked
  $(".synthetic").on("change", ".cate ", function () {
    var cid = this.id.toString().slice(4);
    if (this.id.substring(0, 1) == "a") {
      var isCheckedC = false;
      var isCheckedA = $('#auth' + cid).is(":checked") ? true : false;
    }
    if (this.id.substring(0, 1) == "c") {
      var isCheckedC = $('#cate' + cid).is(":checked") ? true : false;
      var isCheckedA = false;
    }
    if (!($(".auth").is(":checked")) && !($(".cate").is(":checked"))) {
      location.reload();
    }
    if (isCheckedA || isCheckedC) {
      if (this.id.substring(0, 1) == "a") authorsList.push(cid);
      if (this.id.substring(0, 1) == "c") categorieList.push(cid);

    } else {
      if (this.id.substring(0, 1) == "a") {
        for (var i = 0; i < authorsList.length; i++) {

          if (authorsList[i] == cid) {
            authorsList.splice(i, 1);
            i--;
          }
        }
      }
      if (this.id.substring(0, 1) == "c") {
        for (var j = 0; j < categorieList.length; j++) {

          if (categorieList[j] == cid) {
            categorieList.splice(j, 1);
            j--;
          }
        }
      }
    }
    var price = $("#ex1").val();
    console.log(price);
    synthFilters(authorsList, categorieList, price);
  });

  //synthetic filters function
  function synthFilters(authorsList, categorieList, price) {
    unsortedBooks = [];
    console.log(price);
    $(".book1").empty();
    urlFilt = "http://localhost:8080/api/book/all/filters";
    $.ajax({
      type: "PUT",
      url: urlFilt,
      dataType: "json",
      headers: {
        "Referrer-Policy": "strict-origin-when-cross-origin",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      data: JSON.stringify({
        authors: authorsList,
        categories: categorieList
      })
    }).then(
      function (books, status) {
        $.each(books, function (i, book) {
          if (book.regularPrice <= price) {
            $(".book1").append('<div class="col-lg-4 col-md-6 mb-4 synth">\
                      <div class="card h-100" >\
                          <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
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
            unsortedBooks.push(book);
          }

        });
        var sortChoice = $("#sort").val();
        sorting(sortChoice);
      });

  }

  // ==================================================================
  //                            SORTING


  //check option for sort by
  $("#sort").change(function () {
    var sortChoice = $(this).children(":selected").val();
    sorting(sortChoice);
  });

  function sorting(sortChoice) {
    switch (sortChoice) {
      case "1": defaultBooks2(unsortedBooks); break;
      case "2": hotBooks2(unsortedBooks); break;
      case "3": priceAscBooks2(unsortedBooks); break;
      case "4": priceDescBooks2(unsortedBooks); break;
    }
  };

  //sort by price descending2
  function priceDescBooks2(unsortedBooks) {
    $(".book1").empty();
    var temp = [];
    $.each(unsortedBooks, function (k, book) {
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
                        <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
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
  }

  //sort by price ascending2
  function priceAscBooks2(unsortedBooks) {
    $(".book1").empty();
    var temp = [];
    $.each(unsortedBooks, function (k, book) {
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
                        <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
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
  }

  //sort by hot products
  function hotBooks2(unsortedBooks) {
    $(".book1").empty();
    var temp = [];
    $.each(unsortedBooks, function (k, book) {
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
                    <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
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
  }

  //sort by default
  function defaultBooks2(unsortedBooks) {
    $(".book1").empty();
    $.each(unsortedBooks, function (n, book) {
      $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
                <div class="card h-100" >\
                    <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
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
  }

  // ==================================================================

  //add to cart 
  $("body").on("click", ".sendCart", function () {
    var cartId = localStorage.getItem("cartId");
    var bookId = this.id.toString().slice(4);
    var myUrl = "http://localhost:8080/api/cart/addtocart/" + cartId + "/" + bookId + "/" + 1;
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
        if (xhr.status == 401 || xhr.status == 400) {
          logoutFun();
        }
      }
    });
    $("#shakeCart").remove();
  });

  //unauthorized auto logout
  function logoutFun() {
    var logoutUrl = "http://localhost:8080/api/home/signout";
    // var username = localStorage.getItem("username");

    $.ajax({
      type: "DELETE",
      url: logoutUrl,
      dataType: "json",
      headers: {
        "Referrer-Policy": "strict-origin-when-cross-origin",
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
        'Authorization': localStorage.getItem("Authorization"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      success: function () {
      },
      error: function () {
        alert("Please sign in to proceed!");
      }
    });
    localStorage.removeItem("Authorization");
    localStorage.removeItem("isAdmin");
    localStorage.removeItem("cartId");
    window.location.href = "Login.html";
  }

  // ==================================================================
  //                            Range Value
  // ==================================================================


  // //dislpay range value
  // var value;
  // $("#ex1").change(function () {
  //   $(".book1").empty();
  //   var myUrl = "http://localhost:8080/api/book/all/range/0/" + this.value;
  //   $.ajax({ url: myUrl }).then(
  //     function (books, status) {
  //       $.each(books, function (i, book) {

  //         $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
  //                   <div class="card h-100" >\
  //                       <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
  //                       <div class="card-body">\
  //                       <h4 class="card-title">\
  //                           <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
  //                       </h4>\
  //                       <h5>'+ book.regularPrice + '€</h5>\
  //                       <p class="card-text2" >'+ book.description + '</p>\
  //                   </div>\
  //                   <div class="addToCart">\
  //                       <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
  //                       class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
  //                   </div>\
  //                   <div class="card-footer">\
  //                   <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
  //                       type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
  //                       value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
  //                       for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
  //                   </div>\
  //                 </div>\
  //                </div>\
  //               </div>\
  //             ' );
  //       });
  //     });
  // });
  // $("#ex1").change(function () {
  //   priceRange(unsortedBooks, this.value);
  // });

  // function priceRange(unsortedBooks, price) {
  //   $(".book1").empty();
  //   $.each(unsortedBooks, function (i, book) {
  //     if (book.regularPrice <= price) {
  //       $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
  //                   <div class="card h-100" >\
  //                       <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
  //                       <div class="card-body">\
  //                       <h4 class="card-title">\
  //                           <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
  //                       </h4>\
  //                       <h5>'+ book.regularPrice + '€</h5>\
  //                       <p class="card-text2" >'+ book.description + '</p>\
  //                   </div>\
  //                   <div class="addToCart">\
  //                       <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
  //                       class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
  //                   </div>\
  //                   <div class="card-footer">\
  //                   <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
  //                       type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
  //                       value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
  //                       for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
  //                   </div>\
  //                 </div>\
  //                </div>\
  //               </div>\
  //             ' );
  //       unsortedBooks.push(book);
  //     }
  //   });
  //   var sortChoice = $("#sort").val();
  //   sorting(sortChoice);
  // }


  // //sort by default
  // function defaultBooks() {
  //   $(".book1").empty();
  //   var myUrl = "http://localhost:8080/api/book/all";
  //   $.ajax({ url: myUrl }).then(
  //     function (books, status) {
  //       $.each(books, function (n, book) {
  //         $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
  //                 <div class="card h-100" >\
  //                     <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
  //                     <div class="card-body">\
  //                     <h4 class="card-title">\
  //                         <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
  //                     </h4>\
  //                     <h5>'+ book.regularPrice + '€</h5>\
  //                     <p class="card-text2" >'+ book.description + '</p>\
  //                 </div>\
  //                 <div class="addToCart">\
  //                     <a id="cart'+ book.id + '"  type="button" class="sendCart" style="text-decoration: none;"><i\
  //                     class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
  //                 </div>\
  //                 <div class="card-footer">\
  //                 <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
  //                     type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
  //                     value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
  //                     for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
  //                 </div>\
  //               </div>\
  //              </div>\
  //             </div>\
  //           ' );
  //       });
  //     });
  // };

  // //sort by hot products
  // function hotBooks() {
  //   $(".book1").empty();
  //   var myUrl = "http://localhost:8080/api/book/all/hot";
  //   $.ajax({ url: myUrl }).then(
  //     function (books, status) {
  //       var temp = [];
  //       $.each(books, function (k, book) {
  //         temp[k] = book;
  //       });
  //       for (i = 0; i < temp.length - 1; i++) {
  //         for (j = 0; j < temp.length - i - 1; j++) {
  //           if (temp[j].countSales < temp[j + 1].countSales) {
  //             var t = temp[j];
  //             temp[j] = temp[j + 1];
  //             temp[j + 1] = t;
  //           }
  //         }
  //       }
  //       $.each(temp, function (n, book) {
  //         $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
  //                 <div class="card h-100" >\
  //                     <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
  //                     <div class="card-body">\
  //                     <h4 class="card-title">\
  //                         <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
  //                     </h4>\
  //                     <h5>'+ book.regularPrice + '€</h5>\
  //                     <p class="card-text2" >'+ book.description + '</p>\
  //                 </div>\
  //                 <div class="addToCart">\
  //                     <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
  //                     class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
  //                 </div>\
  //                 <div class="card-footer">\
  //                 <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
  //                     type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
  //                     value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
  //                     for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
  //                 </div>\
  //               </div>\
  //              </div>\
  //             </div>\
  //           ' );
  //       });
  //     });
  // };

  // //sort by price descending
  // function priceDescBooks() {
  //   $(".book1").empty();
  //   var myUrl = "http://localhost:8080/api/book/all";
  //   $.ajax({ url: myUrl }).then(
  //     function (books, status) {
  //       var temp = [];
  //       $.each(books, function (k, book) {
  //         temp[k] = book;
  //       });
  //       for (i = 0; i < temp.length - 1; i++) {
  //         for (j = 0; j < temp.length - i - 1; j++) {
  //           if (temp[j].regularPrice < temp[j + 1].regularPrice) {
  //             var t = temp[j];
  //             temp[j] = temp[j + 1];
  //             temp[j + 1] = t;
  //           }
  //         }
  //       }
  //       $.each(temp, function (n, book) {
  //         $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
  //                 <div class="card h-100" >\
  //                     <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
  //                     <div class="card-body">\
  //                     <h4 class="card-title">\
  //                         <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
  //                     </h4>\
  //                     <h5>'+ book.regularPrice + '€</h5>\
  //                     <p class="card-text2" >'+ book.description + '</p>\
  //                 </div>\
  //                 <div class="addToCart">\
  //                     <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
  //                     class="fas fa-heart mr-1" ></i>&#128722; Add to cart</a>\
  //                 </div>\
  //                 <div class="card-footer">\
  //                 <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
  //                     type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
  //                     value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
  //                     for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
  //                 </div>\
  //               </div>\
  //              </div>\
  //             </div>\
  //           ' );
  //       });
  //     });
  // };

  // //sort by price asscending
  // function priceAscBooks() {
  //   $(".book1").empty();
  //   var myUrl = "http://localhost:8080/api/book/all";
  //   $.ajax({ url: myUrl }).then(
  //     function (books, status) {
  //       var temp = [];
  //       $.each(books, function (k, book) {
  //         temp[k] = book;
  //       });
  //       for (i = 0; i < temp.length - 1; i++) {
  //         for (j = 0; j < temp.length - i - 1; j++) {
  //           if (temp[j].regularPrice > temp[j + 1].regularPrice) {
  //             var t = temp[j];
  //             temp[j] = temp[j + 1];
  //             temp[j + 1] = t;
  //           }
  //         }
  //       }
  //       $.each(temp, function (n, book) {
  //         $(".book1").append('<div class="col-lg-4 col-md-6 mb-4" >\
  //                 <div class="card h-100" >\
  //                     <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '"><img class="card-img-top"  src="' + book.image2 + '"  alt="" height="350px"></a>\
  //                     <div class="card-body">\
  //                     <h4 class="card-title">\
  //                         <a href="book.html?book=' + book.id + '" class="bookLink" id="' + book.id + '">' + book.title + '</a>\
  //                     </h4>\
  //                     <h5>'+ book.regularPrice + '€</h5>\
  //                     <p class="card-text2" >'+ book.description + '</p>\
  //                 </div>\
  //                 <div class="addToCart">\
  //                     <a id="cart'+ book.id + '" type="button" class="sendCart" style="text-decoration: none;"><i\
  //                     class="fas fa-heart mr-1"></i>&#128722; Add to cart</a>\
  //                 </div>\
  //                 <div class="card-footer">\
  //                 <div class="rating"> <input type="radio" name="rating" value="5" id="5"'+ (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
  //                     type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
  //                     value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
  //                     for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
  //                 </div>\
  //               </div>\
  //              </div>\
  //             </div>\
  //           ' );
  //       });
  //     });
  // };


});