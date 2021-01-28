$(document).ready(function () {

    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = window.location.search.substring(1),
            sURLVariables = sPageURL.split('&'),
            sParameterName,
            i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
            }
        }
    };

    var fbook = getUrlParameter('book');

    //add to cart function
    $(".sendCart").click(function () {
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
                'Authorization': localStorage.getItem("Authorization"),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
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


    var myUrl = "http://localhost:8080/api/book/id/" + fbook;
    //get book details
    $.ajax({ url: myUrl }).then(
        function (book, status) {
            $('.col-lg-3 img').attr('src', `data:image/png;base64,${book.image}`);
            $(".titlos").append('' + book.title + '');
            $(".card-text").append('' + book.description + '');
            $(".money").append('' + book.regularPrice.toFixed(2) + '&euro;');
            $(".rating").append('<div class="rating"> <input type="radio" name="rating" value="5" id="5"' + (book.totalRating == 5 ? "checked" : "") + '><label for="5">☆</label> <input\
                    type="radio" name="rating" value="4" id="4"'+ (book.totalRating == 4 ? "checked" : "") + '><label for="4">☆</label> <input type="radio" name="rating"\
                    value="3" id="3" '+ (book.totalRating == 3 ? "checked" : "") + '><label for="3">☆</label> <input type="radio" name="rating" value="2" id="2" ' + (book.totalRating == 2 ? "checked" : "") + '><label\
                    for="2">☆</label> <input type="radio" name="rating" value="1" id="1" '+ (book.totalRating == 1 ? "checked" : "") + '><label for="1">☆</label>\
                    </div>' );
            $(".lepto").append('\
                    <fieldset >\
                    <legend><h3><u>Book Details</u></h3></legend>\
                    <table cellpadding="4" cellspacing="0" border="0">\
                    <tbody>\
                    <tr><td>ISBN:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ book.isbn + '</td></tr>\
                    <tr><td>Pages:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + book.noofpages + '</td></tr>\
                    <tr><td>Sale:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ book.countSales + '</td></tr>\
                    <tr><td>Stock:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+ book.storeQuantity + '</td></tr>');
            $(".sendCart").attr("id", "cart" + book.id);
        });


    var authUrl = "http://localhost:8080/api/author/book/" + fbook;
    //get book's author
    $.ajax({ url: authUrl }).then(
        function (auth, status) {
            $(".lepto").append('<tr><td> Author:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' + auth.firstName + " " + auth.lastName + '</td></tr>');
        });

    var categUrl = "http://localhost:8080/api/categorie/book/" + fbook;
    //get book's categories
    $.ajax({ url: categUrl }).then(
        function (categories, status) {
            $(".lepto").append('<tr><td> ');
            $.each(categories, function (i, categorie) {
                $(".lepto").append(' Categorie:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\
                '+ categorie.name + '</td></tr></br>');
            });
            $(".lepto").append(' </tbody></table></fieldset>');
        });

    var myUrl = "http://localhost:8080/api/review/all/book/" + fbook;

    //get book's reviews 
    $.ajax({ url: myUrl }).then(
        function (reviews, status) {
            $(".reviews").append('<h5>Reviews</h5><div class="review-section-big">');
            var myUrl = "http://localhost:8080/api/user/review/";
            $.each(reviews, function (i, review) {

                //get review's user
                $.ajax({ url: myUrl + review.id }).then(
                    function (user, status) {

                        $(".reviews").append('<div class="review-section">\
                        <h6><span>['+ review.reviewDatetime.toString().slice(0, 10) + ']&nbsp;&nbsp;<b>' + user.userName + '</b></span>\
                        </br>rating: ' + review.rating + '</br>  \
                         </h6><p>' + review.comment + '</p></span>\
                        </div>');
                    });
            });

            $(".reviews").append('</div><form method="POST" id="revForm">\
                <textarea id="comment" name="comment" rows="5" cols="33">\
                </textarea>\
                <input id="submit" type="Button" value="Submit" name="submit" >\
                <input type="reset" value="Clear" name="reset" >\
                </form>' );
        });

    //send review
    $(".reviews").on("click", "#submit", function () {
        var user = localStorage.getItem("username");
        var revUrl = "http://localhost:8080/api/review/new/" + fbook + "/" + user;
        var comment = $("#comment").val();
        if (comment != null && user != null) {
            $.ajax({
                url: revUrl,
                type: "POST",
                dataType: "json",
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                    "Authorization": localStorage.getItem("Authorization"),
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify({
                    comment: comment
                }),
                success: function () {
                    alert("Thank you for your review!");
                    location.reload();
                },
                error: function () {
                    location.reload();
                }
            });
        } else {
            alert("You must sign in to review a book!");
        }
        // 
    });


})
