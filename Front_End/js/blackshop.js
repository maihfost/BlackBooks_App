// Value-Price
$(document).ready(function () {

  //add to cart
  $(".sendCart").click(function () {
    //send to cart
    var cartId = localStorage.getItem("cartId");
    var username = localStorage.getItem("username");
    var myUrl = "http://localhost:8080/api/cart/addtocart/" + cartId + "/" + this.id + "/" + 1;
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
});
