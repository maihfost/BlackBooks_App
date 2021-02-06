$(document).ready(function () {
  var oldUsername;
  var myPro = "http://localhost:8080/api/user/username";
  var userId;
  var pass;
  $.ajax({
    type: "GET",
    url: myPro,
    dataType: "json",
    headers: {
      "Access-Control-Allow-Origin": "*",
      "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
      'Authorization': localStorage.getItem("Authorization"),
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    error: function (xhr) {
      if (xhr.status == 401) {
        logoutFun();
      }
    }
  }).then(
    function (user, status) {
      userId = user.id;
      oldUsername = user.userName;
      $(".first").append(' <label for="name" class="col-4 col-form-label">First Name</label>\
            <div class="col-8">\
              <input id="name" name="name" value="'+ user.firstName + '" class="form-control here" type="text">\
            </div>' );
      $(".last").append(' <label  for="lastname" class="col-4 col-form-label">Last Name</label>\
            <div class="col-8">\
              <input id="lastname" name="lastname" value="'+ user["lastName"] + '" class="form-control here" type="text">\
            </div>' );
      $(".name").append(' <label for="text" class="col-4 col-form-label">Nick Name*</label>\
            <div class="col-8">\
              <input id="username" name="text" value="'+ user["userName"] + '" class="form-control here" required="required"\
                type="text">\
            </div>' );
      $(".emai").append('<label for="email" class="col-4 col-form-label">Email*</label>\
            <div class="col-8">\
              <input id="email" name="email" value="'+ user["email"] + '" class="form-control here" required="required"\
                type="email">\
            </div>' );
      $(".da").append(' <label for="bdate" class="col-4 col-form-label">Date of Birth</label>\
            <div class="col-8">\
              <input id="cakedate" name="birth" value="'+ user["dateofbirth"] + '" class="form-control here" type="formatdate ="yyyy/MM/dd">\
            </div>' );
      $(".tele").append('<label for="phone" class="col-4 col-form-label">Telephone</label>\
            <div class="col-8">\
              <input id="phone" name="lastname" value="'+ user["telephone"] + '" class="form-control here"\
                type="text">\
            </div>' );
      $(".countr").append('<label for="location" class="col-4 col-form-label">Country</label>\
            <div class="col-8">\
              <input id="Country" name="Country" value="'+ user["country"] + '" class="form-control here"\
                type="text">\
            </div>' );
      $(".wall").append(' <label for="digitalWallet" class="col-4 col-form-label">Digital Wallet</label>\
            <div class="col-8">\
              <input id="wallet" name="wallet" value="'+ user["digitWallet"] + '"\
                class="form-control here" type="text">\
            </div>' );

      var myUrl34 = "http://localhost:8080/api/cart/all/user/" + user.id;
      $.ajax(
        {
          type: "GET",
          url: myUrl34,
          dataType: "json",
          headers: {
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
            'Authorization': localStorage.getItem("Authorization"),
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          error: function (xhr) {
            if (xhr.status == 401) {
              logoutFun();
            }
          }
        }
      ).then(function (orders) {
        $.each(orders, function (i, order) {
          if (!order.isPaid && order.dateTime != null) {
            var ap_option = "<option id='op_" + order.id + "' value='" + order.id + "'> ID : " + order.id + "</option>";
            $("#sel_ord").append(ap_option);
          }
        });
      });
    }
  );

  $("#sel_ord").change(function () {
    if ($(this).val() != "") {
      $("#su").prop("disabled", true);
      $("#del").prop("disabled", true);
    }
  });

  //Pay Submited Order
  $("#check2").click(function () {

    var cart_id = $("#sel_ord").val();
    if (cart_id == "") {
      alert("Select an order ID to execute the payment");
    } else {
      var myUrl26 = "http://localhost:8080/api/payment/new/" + cart_id;
      $.ajax(
        {
          type: "POST",
          url: myUrl26,
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
          ),
          error: function (xhr) {
            if (xhr.status == 401) {
              logoutFun();
            }
          }
        }
      ).then(function (books) {
        alert("Success Payment! Click to the link above to download your books");
        $.each(books, function (i, book) {
          $("#im_dd").append('<a href = "data:image/png;base64,' + book.image + '" id="download" download> |  ' + book.title + '  |</a>');
        });
        $("#sel_ord").find('[value=' + cart_id + ']').remove();
      });
    }
  });

  $(".sub").on("click", "#su", function (event) {
    event.preventDefault();
    var firstname = $.trim($("#name").val());
    var lastname = $.trim($("#lastname").val());
    var nickname = $.trim($("#username").val());
    var ema = $.trim($("#email").val());
    var dof = $.trim($("#cakedate").val());
    var tel = $.trim($("#phone").val());
    var count = $.trim($("#Country").val());
    var wall = $.trim($("#wallet").val());

    const data2 = {
      id: userId,
      firstName: firstname,
      lastName: lastname,
      userName: nickname,
      email: ema,
      dateofbirth: dof,
      telephone: tel,
      country: count,
      digitWallet: wall
    }

    var myUp = "http://localhost:8080/api/user/update";
    $.ajax({
      type: "PUT",
      url: myUp,
      contentType: "application/json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
        'Authorization': localStorage.getItem("Authorization"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      data: JSON.stringify(data2),
      success: function () {
        alert("Update Submitted!");
        if (nickname != oldUsername) {
          recursion(nickname);
        }
      },
      error: function () {
        if (xhr.status == 401) {
          logoutFun();
        }
      }
    });
  });

  $(".sub").on("click", "#del", function (event) {
    event.preventDefault();

    var myUp = "http://localhost:8080/api/user/delete";
    $.ajax({
      type: "DELETE",
      url: myUp,
      dataType: "json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
        'Authorization': localStorage.getItem("Authorization"),
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      success: function () {
        alert("Account deleted successfully!");
      },
      error: function () {
        if (xhr.status == 401) {
          logoutFun();
        }
      }
    });

  });
  var counter = 0;

  function recursion(nickname) {
    if (counter < 3) {
      pass = prompt("Enter your password to change your NickName:");
      if (pass != null && pass != "") {
        var login_url = "http://localhost:8080/api/auth/signin";
        $.ajax({
          type: "POST",
          url: login_url,
          dataType: "json",
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          data: JSON.stringify({
            username: nickname,
            password: pass
          }),
          success: function (response) {
            localStorage.setItem("Authorization", response.tokenType + " " + response.accessToken);
            alert("Success");
          },
          error: function (xhr) {
            if (xhr.status == 401) {
              alert("Username or Password is Incorrect. " + (2 - counter) + " tries left.");
              counter++;
              recursion(nickname);
            }
          }
        });
      } else {
        alert("Username or Password is Incorrect.");
        localStorage.removeItem("Authorization");
        localStorage.removeItem("cartId");
        window.location.href = "Login.html";
      }
    } else {
      localStorage.removeItem("Authorization");
      localStorage.removeItem("cartId");
      localStorage.removeItem("isAdmin");
      window.location.href = "Login.html";
    }

  }

  //unauthorized auto logout
  function logoutFun() {
    var logoutUrl = "http://localhost:8080/api/home/signout";

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
