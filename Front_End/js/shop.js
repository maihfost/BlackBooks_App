$(document).ready(function () {

    //GET CART ID

    var cart_id = localStorage.getItem("cartId");
    var username = localStorage.getItem("username");
    var myUrl2 = "http://localhost:8080/api/cartdetail/all/shoppingcart/" + cart_id;
    $.ajax(
        {
            type: "GET",
            url: myUrl2,
            dataType: "json",
            async: false,
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                'Authorization': localStorage.getItem("Authorization"),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }
    ).then(function (order_details) {
        $.each(order_details, function (i, order_detail) {
            var myUrl3 = "http://localhost:8080/api/book/cartdetail/" + order_detail.id;
            $.ajax(
                {
                    type: "GET",
                    url: myUrl3,
                    dataType: "json",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                        'Authorization': localStorage.getItem("Authorization"),
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                }
            ).then(function (book) {
                $("#books").append('\
                                <div id="div_'+ order_detail.id + '" class="row dtk">\
                                    <div class="col-12 col-sm-12 col-md-2 text-center ">\
                                        <img class="img-responsive" src="data:image/png;base64,' + book.image + '" alt="prewiew" width="80" height="100">\
                                    </div>\
                                    \
                                    <div class="col-12 text-sm-center col-sm-12 col-md-6 stil " >\
                                        <h5 class="product-name" ><strong>' + book.title + '</strong></h5>\
                                    </div>\
                                    \
                                    <div class="col-12 col-sm-12 text-sm-center col-md-4  row">\
                                        <div class="col-3 col-sm-3 col-md-6 stil" >\
                                            <h6 class= ><strong>' + book.regularPrice + '&euro; <span class="text-muted">x</span></strong></h6>\
                                        </div>\
                                        \
                                        <div class="col-4 col-sm-4 col-md-4" style="padding-top: 25px;"> \
                                            <div class="quantity " >\
                                                <input id="quantity_'+ order_detail.id + '" value="' + order_detail.quantity + '" class="qty" readonly>\
                                                <p id="oldQuantity_'+ order_detail.id + '" class="p_quantity">' + order_detail.quantity + '</p>\
                                                <span id="minus_' + order_detail.id + '" class="minus">-</span>\
                                                <span id="plus_' + order_detail.id + '"  class="plus">+</span>\
                                            </div>\
                                        </div>\
                                        <div class="col-2 col-sm-2 col-md-2 " style="padding-top: 10px;" style="margin-bottom:5px;">\
                                            <button id ="del_'+ order_detail.id + '" type="button" class="btn btn-outline-danger btn-xs del">\
                                                <i class="fa fa-trash" aria-hidden="true"></i>\
                                                <button id ="upd_'+ order_detail.id + '" type="button" class="btn btn-outline-info btn-xs upd" >\
                                                <i class="fa fa-refresh" aria-hidden="true"></i>\
                                            </button>\
                                        </div>\
                                    </div>\
                                </div>\
                                <hr>\
                            ');

                var myUrl4 = "http://localhost:8080/api/cart/id/" + cart_id;
                $.ajax(
                    {
                        type: "GET",
                        url: myUrl4,
                        dataType: "json",
                        headers: {
                            "Access-Control-Allow-Origin": "*",
                            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                            'Authorization': localStorage.getItem("Authorization"),
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        }
                    }
                ).then(function (order) {
                    $("#check").css("display", "block");
                    $("#check2").css("display", "block");
                    $("#total_price").html(order.totalPrice + "&euro;");
                });

            });
        });
    });

    // SUBMIT ORDER
    $("#check").click(function () {

        var myUrl5 = "http://localhost:8080/api/cart/submitorder/" + username + "/" + cart_id;
        $.ajax(
            {
                type: "POST",
                url: myUrl5,
                dataType: "json",
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                    "Authorization": localStorage.getItem("Authorization"),
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                error: function(xhr){
                    if (xhr.status == 406){
                        alert("Your amount is not enough to place an order!");
                    }
                }
            });
        alert("Order Submited!");
        localStorage.setItem("cartId", 0);
        $("#check2").prop("disabled", false);
    });

    //PAY ORDER

    $("#check2").click(function () {

        var myUrl6 = "http://localhost:8080/api/payment/new/" + username + "/" + cart_id;
        $.ajax(
            {
                type: "POST",
                url: myUrl6,
                dataType: "json",
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                    "Authorization": localStorage.getItem("Authorization"),
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                },
                data: JSON.stringify(
                    cart_id
                )
            }).then(function (books) {
                alert("Success Payment! Click to the link above to download your books");
                $("#im_d").append('<p id="cl">Click to the above links to download your books!</p>')
                $.each(books,function(i,book){
                    $("#im_d").append('<a href = "data:image/png;base64,'+ book.image + '" id="download" download> |  ' + book.title+ '  |</a>');
                    //Click here to Download your Books!
                });
                $("#tp").css("display", "none");
                $("#total_price").css("display", "none");
                $("#check").css("display", "none");
                $("#check2").css("display", "none");
            });
        //cart_id=0;
    });

    //UPDATE(+/- QUANTITY)/DELETE ordered product----------------------
    $("body").on("click", ".minus", function () {

        var idd = this.id.toString().slice(6);
        var quantity = $('#quantity_' + idd).val();

        if (quantity > 1) {
            $('#quantity_' + idd).val(quantity - 1);
        }
    });

    $("body").on("click", ".plus", function () {

        var idd = this.id.toString().slice(5);
        var quantity = parseInt($('#quantity_' + idd).val());

        if (quantity >= 1) {
            $('#quantity_' + idd).val(quantity + 1);
        }

    });

    //UPDATE ORDERED PRODUCT
    $("body").on("click", ".upd", function () {

        var idd = this.id.toString().slice(4);
        var quantity = $('#quantity_' + idd).val();
        var oldquantity = $('#oldQuantity_' + idd).text();
        if (oldquantity != quantity) {
            var myUrl5 = "http://localhost:8080/api/cart/updatecart/" + idd + "/" + quantity;
            $.ajax(
                {
                    type: "PUT",
                    url: myUrl5,
                    dataType: "json",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                        "Authorization": localStorage.getItem("Authorization"),
                        "Accept": "application/json",
                        "Content-Type": "application/json"
                    },
                    data: JSON.stringify({

                        quantity: quantity

                    })
                });
            alert("Updated");
            window.location.reload();
        }

    });

    //DELETE ORDERED PRODUCT
    $("body").on("click", ".del", function () {
        var idd = this.id.toString().slice(4);
        var myUrl4 = "http://localhost:8080/api/cart/deletefromcart/" + idd;
        
        $.ajax(
            {
                type: "DELETE",
                url: myUrl4,
                dataType: "json",
                headers: {
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                    "Authorization": localStorage.getItem("Authorization"),
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                }
            });
           
        $("#div_" + idd).remove();
        setTimeout(function(){
            window.location.reload();
        },1000);


    });


});