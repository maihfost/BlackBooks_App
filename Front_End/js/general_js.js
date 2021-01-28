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
                $.ajax({ url: myUrl}).then(
                    function (books) {
                        $(".dropdown-menu.searchh").empty();
                        $.each(books, function (i, book) {
                            var hrf="book.html?book=" + book.id + " class='bookLink' id='" + book.id + "'";
                            $(".dropdown-menu.searchh").append(
                                " <a class='dropdown-item s_text' href=" +  hrf + " >" + book.title + "</a>");
                        });
                    });
    
                $(".dropdown-menu.searchh").empty();
                }   
                   
            } else {
                return;
            }
        });
        });

    //logout--------------------------------------------
    $("#logout_btn").click(function(){
        
        
        var logoutUrl= "http://localhost:8080/api/home/signout";
        var username = localStorage.getItem("username");

        
        $.ajax({
            type:"DELETE",
            url:logoutUrl,
            dataType: "json",
            headers: {
                    "Referrer-Policy": "strict-origin-when-cross-origin",
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                    'Authorization' : localStorage.getItem("Authorization") ,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' },
            data: JSON.stringify({
                userName : username
            }),
            success:function(){
                alert("See you soon!");
            },
            error: function(){
                alert("You'll stay logged in forever!");
            }
        });
        localStorage.removeItem("Authorization");
        localStorage.removeItem("username");
        window.location.href = "Login.html";
    });

    // login/logout myProfile, shoppingcart buttons show/hide and href
    var timerBig;
    var timerSmall
    if(localStorage.getItem("username") != null){
        $("#login").hide();
        $("#logout_btn").show();
        $("#myProfile").show();
        timerSmall = window.setTimeout(warning, 1500000);
        timerBig = window.setTimeout(logoutFun, 1800000);
    }else{
        $("#login").show();
        $("#logout_btn").hide();
        $("#myProfile").hide();
        $("#cartImg").attr("href", "Login.html")
    }

    // Admin button show/hide
    if(localStorage.getItem("username") == "admin"){
        $("#navbarDropdown").show();
    }else{
        $("#navbarDropdown").hide();
    }

    //auto logout after session timeout
    function logoutFun(){
        var logoutUrl= "http://localhost:8080/api/home/signout";
        var username = localStorage.getItem("username");

        
        $.ajax({
            type:"DELETE",
            url:logoutUrl,
            dataType: "json",
            headers: {
                    "Referrer-Policy": "strict-origin-when-cross-origin",
                    "Access-Control-Allow-Origin": "*",
                    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                    'Authorization' : localStorage.getItem("Authorization") ,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' },
            data: JSON.stringify({
                userName : username
            }),
            success:function(){
                alert("See you soon!");
            },
            error: function(){
                alert("You'll stay logged in forever!");
            }
        });
        localStorage.removeItem("Authorization");
        localStorage.removeItem("username");
        window.location.href = "Login.html";
    }

    //warning 5 minutes before timeout
    function warning(){
        alert("In 5 minutes you will get logged out automatically!!");
    }


    $("#contac").click(function(){
        alert("Thank you for your message.")
        window.location.reload;
    });
});