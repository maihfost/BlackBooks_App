$(document).ready(function () {
//dropdown list by authors id

var myUrl7 = "http://localhost:8080/api/author/all";
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
  function (authors) {
    $.each(authors, function (i, authors) {
      var ap_book_option = "<option value='" + authors.id + "'> ID : " + authors.id + "</option>";
      $("#Auth").append(ap_book_option);
    });
  }
);


$("#Auth").change(function () {
  sel_id = this.value;
  var myUrl6 = "http://localhost:8080/api/author/id/" + sel_id;
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
        $("#Aname").val(response.firstName);
        $("#Alastname").val(response.lastName);
        $("#Abio").val(response.biography);
        $("#But").append('<button name="submit" type="button" id="upd_' + response.id + '"\
                  class="Upd btn btn-primary">Update</button>\
              <button name="reset" type="reset" id="del_'+ response.id + '" class="Del btn btn-danger"></i>\
                  Delete</button>\
              ')
      }
    });

});
//-------------------------------------------------------------------
//update author onclick

$("body").on("click", ".Upd", function () {

  var idd = this.id.toString().slice(4);

  var firstname = $("#Aname").val();
  var lastname = $("#Alastname").val();
  var biography = $("#Abio").val();
  var myUrl5 = "http://localhost:8080/api/author/update/" + idd;
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
        firstName: firstname,
        lastName: lastname,
        biography: biography
      }),
      success: function () {
        // window.location.reload();
      }
    });
  alert("Updated");

});
//---------------------------------------------------
//delete author onclick

$("body").on("click", ".Del", function () {

  var idd = this.id.toString().slice(4);
  var myUrl90 = "http://localhost:8080/api/author/delete/" + idd;
  $.ajax(
    {
      type: "DELETE",
      url: myUrl90,
      dataType: "json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
        "Authorization": localStorage.getItem("Authorization"),
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
      success: function () {
        alert("Account deleted successfully!");
        window.location.reload();
      },
      error: function (xhr) {
        if (xhr.status == 500) {
          alert("Can't delete this author, because books exist to this author ");
        }
      }
    });
  // alert("Deleted");

});


//----------------------------------------------------------------------
// submit author onclick

$("body").on("click", "#sub", function () {
  var firstname = $("#Aname").val();
  var lastname = $("#Alastname").val();
  var biography = $("#Abio").val();
  var myUrl91 = "http://localhost:8080/api/author/new";
  $.ajax(
    {
      type: "POST",
      url: myUrl91,
      dataType: "json",
      headers: {
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
        "Authorization": localStorage.getItem("Authorization"),
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
      data: JSON.stringify({
        firstName: firstname,
        lastName: lastname,
        biography: biography
      }),
      success: function () {
        alert("Data passed");
      }
    });

});

});