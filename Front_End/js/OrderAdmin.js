$(document).ready(function () {
    //Minus Plus Buttons--EditOrderonADD

    $('.minus1').click(function () {

        var quantity = $("#quantity").val();
        if (quantity > 1) {
            $("#quantity").val(quantity - 1);
        }

    });


    $('.plus1').click(function () {

        var quantity = parseInt($("#quantity").val());
        if (quantity >= 1) {
            $("#quantity").val(quantity + 1);
        }

    });

    //on page load------------------------------------------------------
    var myUrl = "http://localhost:8080/api/cart/all";
    $.ajax(
        {
            type: "GET",
            url: myUrl,
            dataType: "json",
            headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                'Authorization': localStorage.getItem("Authorization"),
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }
    ).then(
        function (orders) {
            $.each(orders, function (i, order) {
                var ap_option = "<option value='" + order.id + "'> ID : " + order.id + "</option>";
                $("#sel_order_id").append(ap_option);
            });
        });


    // On Select Order /show Order & ordered products
    $("#sel_order_id").change(function () {
        $("#sel_book_id").val("");
        $("#title").val("");
        $('#sel_book_id').find('*').not('#empty').remove();
        if (this.value != "") {
            id = this.value;
            $(".booksInfo").remove();
            var myUrl1 = "http://localhost:8080/api/cart/id/" + id;
            $.ajax(
                {
                    type: "GET",
                    url: myUrl1,
                    dataType: "json",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                        'Authorization': localStorage.getItem("Authorization"),
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    success: function (response) {
                        if (response.dateTime != null) {
                            datetime = response.dateTime.replace("T", " ").substring(0, 19);
                            $("#order_dateTime").val(datetime);
                        } else { datetime = "There is no Date!"; }
                        $("#order_tot_price").val(response.totalPrice);
                        $("#isPaid").prop("checked", response.isPaid);

                        if (!response.isPaid) {
                            $("#first_div").css("display", "block");
                            var myUrl7 = "http://localhost:8080/api/book/all";
                            $.ajax({
                                type: "GET",
                                url: myUrl7,
                                dataType: "json",
                                headers: {
                                    "Access-Control-Allow-Origin": "*",
                                    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                                    'Authorization': localStorage.getItem("Authorization"),
                                    'Accept': 'application/json',
                                    'Content-Type': 'application/json'
                                }
                            }
                            ).then(
                                function (books) {
                                    $.each(books, function (i, book) {
                                        var ap_book_option = "<option value='" + book.id + "'> ID : " + book.id + "</option>";
                                        $("#sel_book_id").append(ap_book_option);
                                    });
                                }
                            );
                        } else {
                            $("#first_div").css("display", "none");
                        }
                    }
                }
            ).then(function (order) {
                var myUrl2 = "http://localhost:8080/api/cartdetail/all/shoppingcart/" + order.id;
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
                            $("#books_per_order").append('\
                                <div id="div_'+ order_detail.id + '" class= "col-12 booksInfo" >\
                                <input id="id" name="name" class="form-control here orderbook" type="text" value="' + book.id + '">\
                                    <input id="title" name="name" class="form-control here orderbook" type="text" value="' + book.title + '">\
                                        <input id="quantity_'+ order_detail.id + '" name="name" class="form-control here orderbook" type="text" value="' + order_detail.quantity + '">\
                                            <div class="plus_minus_div" style="float: left;">\
                                                <span id="minus_' + order_detail.id + '" class="minus">-</span>\
                                                <span id="plus_' + order_detail.id + '"  class="plus">+</span>\
                                            </div>\
                                            <p id="oldQuantity_'+ order_detail.id + '" class="p_quantity">' + order_detail.quantity + '</p>\
                                            <a id="del_' + order_detail.id + '" class="btn btn-danger del"></i>Delete</a>\
                                            <a id="upd_' + order_detail.id + '" class="btn btn-primary upd"></i>Update</a>\
                                </div>\
                            ');
                        });
                    });
                });
            });
        } else {
            window.location.reload();
        }
    });

    //UPDATE(+/- QUANTITY)/DELETE ordered product----------------------
    $("body").on("click", ".minus", function () {

        var idd = this.id.toString().slice(6);
        var quantity = $('#quantity_' + idd ).val();

        if (quantity > 1) {
            $('#quantity_' + idd).val(quantity - 1);
        }
    });

    $("body").on("click", ".plus", function () {

        var idd = this.id.toString().slice(5);
        var quantity = parseInt($('#quantity_' + idd ).val());
        
        if (quantity >= 1) {
            $('#quantity_' + idd ).val(quantity + 1);
        }

    });
    
    //UPDATE ORDERED PRODUCT
    $("body").on("click", ".upd", function () {
        
        var idd = this.id.toString().slice(4);
        var quantity = $('#quantity_' + idd ).val();
        var oldquantity = $('#oldQuantity_' + idd ).text();
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

                    }),
                    success: function () {
                        alert("Updated");
                    },
                });
            alert("Updated");
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
        alert("Book deleted successfully from Order " + idd);
    });

    //on Select Book from Books Dropdownlist------------------------
    $("#sel_book_id").change(function () {
        sel_id = this.value;
        if (this.value != "") {
            var myUrl6 = "http://localhost:8080/api/book/id/" + sel_id;
            $.ajax(
                {
                    type: "GET",
                    url: myUrl6,
                    dataType: "json",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                        'Authorization': localStorage.getItem("Authorization"),
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    success: function (response) {
                        $("#title").val(response.title);
                    }
                });
        } else {
            $("#title").val("");
        }
    });

    //ADD product to the order
    $(".add").on("click", function () {
        var bookId_to_add = $("#sel_book_id").val();
        var quantity = $("#quantity").val();
        var cartId = $("#sel_order_id").val();
        var title = $("#title").val();
        var myUrl5 = "http://localhost:8080/api/cart/admin/addtocart/" + cartId + "/" + bookId_to_add + "/" + quantity;
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
                success: function () {
                    alert(quantity + " Books < " + title + " > were added to Order " + cartId);
                }
            });

    });

    //DELETE ORDER
    $(".del2").click(function () {
        var del_id = $("#sel_order_id").val();

        if (del_id != "") {
            var myUrl9 = "http://localhost:8080/api/cart/delete/" + del_id;
            $.ajax(
                {
                    type: "DELETE",
                    url: myUrl9,
                    dataType: "json",
                    headers: {
                        "Access-Control-Allow-Origin": "*",
                        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                        "Authorization": localStorage.getItem("Authorization"),
                        "Accept": "application/json",
                        "Content-Type": "application/json"
                    }
                });
            alert("Order (ID : " + del_id + " successfully deleted!) ");


        }
    });


});

