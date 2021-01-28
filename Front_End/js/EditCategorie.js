$(document).ready(function () {
// dropdown list by category id
 var myUrl7 = "http://localhost:8080/api/categorie/all";
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
   function (category) {
     $.each(category, function (i, category) {
       var ap_book_option = "<option value='" + category.id + "'> ID : " + category.id + "</option>";
       $("#CAT").append(ap_book_option);
     });
   }
 );

 $("#CAT").change(function () {
   sel_id = this.value;
   if (this.value != "") {
     var myUrl97 = "http://localhost:8080/api/categorie/id/" + sel_id;
     $.ajax(
       {
         type: "GET",
         url: myUrl97,
         dataType: "json",
         headers: {
           "Access-Control-Allow-Origin": "*",
           "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
           'Authorization': localStorage.getItem("Authorization"),
           'Accept': 'application/json',
           'Content-Type': 'application/json'
         },
         success: function (response) {
           $("#Cat").val(response.name);
           $("#Butt").append('<button name="submit" type="button" id="upd_' + response.id + '"\
                   class="CUpd btn btn-primary">Update</button>\
               <button name="reset" type="reset" id="del_'+ response.id + '" class="CDel btn btn-danger"></i>\
                   Delete</button>\
               ')
         }
       });
   } else {
     $("#title").val("");
   }
 });

//--------------------------------------------------------------------------------------
//update to categories

$("body").on("click", ".CUpd", function () {

 var idd = this.id.toString().slice(4);

 var category = $("#Cat").val();
 var myUrl89 = "http://localhost:8080/api/categorie/update/" + idd;
 $.ajax(
   {
     type: "PUT",
     url: myUrl89,
     dataType: "json",
     headers: {
       "Access-Control-Allow-Origin": "*",
       "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
       "Authorization": localStorage.getItem("Authorization"),
       "Accept": "application/json",
       "Content-Type": "application/json"
     },
     data: JSON.stringify({
       name: category
     }),
     success: function () {
       // window.location.reload();
     }
   });
 alert("Updated");

});

//---------------------------------------------------
//delete categorie onclick

$("body").on("click", ".CDel", function () {

 var idd = this.id.toString().slice(4);
 var myUrl88 = "http://localhost:8080/api/categorie/delete/" + idd;
 $.ajax(
   {
     type: "DELETE",
     url: myUrl88,
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
// submit categories onclick

$("body").on("click", "#submi", function () {

 var category = $("#Cat").val();
 var myUrl86 = "http://localhost:8080/api/categorie/new";
 $.ajax(
   {
     type: "POST",
     url: myUrl86,
     dataType: "json",
     headers: {
       "Access-Control-Allow-Origin": "*",
       "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
       "Authorization": localStorage.getItem("Authorization"),
       "Accept": "application/json",
       "Content-Type": "application/json"
     },
     data: JSON.stringify({
       name: category
     }),
     success: function () {
       alert("Data passed");
     }
   });

});

});

