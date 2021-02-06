$(document).ready(function () {

    // search-----------------------------------------------
    $("#search_bar").on("keyup", function (ev) {
        $("#search_bar").on("keyup", function (ev) {

            if ((ev.keyCode >= 65 && ev.keyCode <= 90) || ev.keyCode == 8) {

                var value = $(this).val().toLowerCase();

                if (value == null || value == "") {
                    $(".dropdown-menu.searchh").css("display", "none");
                    $(".dropdown-menu.searchh").empty();
                } else {
                    $(".dropdown-menu.searchh").css("display", "block");
                }

                if (ev.keyCode != 8) {
                    $(".dropdown-menu.searchh").empty();

                    // Access firstletter controller
                    var myUrl = "http://localhost:8080/api/book/all/firstletter/" + value;
                    $.ajax({ url: myUrl }).then(
                        function (books) {
                            $(".dropdown-menu.searchh").empty();
                            $.each(books, function (i, book) {
                                var hrf = "book.html?book=" + book.id + " class='bookLink' id='" + book.id + "'";
                                $(".dropdown-menu.searchh").append(
                                    " <a class='dropdown-item s_text' href=" + hrf + " >" + book.title + "</a>");
                            });
                        });

                    $(".dropdown-menu.searchh").empty();
                }

            } else {
                return;
            }
        });
    });

    // login
    $("#login_btn").click(function () {

        var username = $("#user_n").val().trim();
        var password = $("#user_p").val().trim();
        var login_url = "http://localhost:8080/api/auth/signin";

        if (username != "" && password != "") {
            $.ajax({
                type: "POST",
                url: login_url,
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },

                data: JSON.stringify({
                    username: username,
                    password: password
                }),
                success: function (response) {
                    localStorage.setItem("Authorization", response.tokenType + " " + response.accessToken);
                    localStorage.setItem("cartId", 0);
                    alert("Welcome, " + username + "!");
                    $.each(response.roles, function (i, role) {
                        if (role == "admin") {
                            localStorage.setItem("isAdmin", true);
                        }
                    });

                    window.location.href = "index.html";
                },
                error: function (xhr) {
                    if (xhr.status == 401) {
                        alert("Username or Password is Incorrect. Try again!")
                        //location.reload();
                    }
                    else {
                        alert("A " + xhr.status + " error occured. Contact us.")
                    }
                }

            });
        }



    });

    //logout--------------------------------------------
    $("#logout_btn").click(function () {

        var logoutUrl = "http://localhost:8080/api/home/signout";

        $.ajax({
            type: "DELETE",
            url: logoutUrl,
            headers: {
                //"Referrer-Policy": "strict-origin-when-cross-origin",
                //"Access-Control-Allow-Origin": "*",
                //"Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                'Authorization': localStorage.getItem("Authorization"),
                //'Accept': 'application/json',
                //'Content-Type': 'application/json'
            },
            success: function () {
                alert("See you soon!");
            },
            error: function () {
                alert("You'll stay logged in forever!");
            }
        });
        localStorage.removeItem("Authorization");
        localStorage.removeItem("cartId");
        localStorage.removeItem("isAdmin");
        window.location.href = "Login.html";
    });

    // login/logout/myProfile/shoppingcart Buttons show/hide and href
    if (localStorage.getItem("Authorization") != null) {
        $("#login").hide();
        $("#logout_btn").show();
        $("#myProfile").show();
    } else {
        $("#login").show();
        $("#logout_btn").hide();
        $("#myProfile").hide();
        $("#cartImg").attr("href", "Login.html");
    }

    //Admin button show/hide
    if (localStorage.getItem("isAdmin") != null) {
        $("#navbarDropdown").show();
    }
    else {
        $("#navbarDropdown").hide();
    }

    //Contact Submit Button
    $("#contac").click(function () {
        alert("Thank you for your message.")
        window.location.reload;
    });


});