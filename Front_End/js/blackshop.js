// Value-Price
$(document).ready(function () {
  
  //add to cart
    $(".sendCart").click(function(){
      //send to cart
      var cartId = localStorage.getItem("cartId");
      var username = localStorage.getItem("username");
      var myUrl = "http://localhost:8080/api/cart/addtocart/" + cartId + "/" + username + "/" + this.id + "/" + 1 ;
        $.ajax({
          type: "POST",
          url: myUrl,
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
            'Authorization' : localStorage.getItem("Authorization") ,
            'Accept': 'application/json',
            'Content-Type': 'application/json' },
          success: function(response, status){
            localStorage.setItem("cartId", response.id);
            $("head").append("<link id='shakeCart' rel='stylesheet' href='/css/shake.css'>");
          }, 
          error: function (xhr) {
            if(xhr.status == 406){
              alert("Not enough books on store!");
            }
            if(localStorage.getItem("username") == null) {
                alert("Please sign in to add to cart!");
            }
        }
        });
      $("#shakeCart").remove();
    });

   
});
