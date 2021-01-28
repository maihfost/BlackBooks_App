$(document).ready(function () {

  //on page load author list
  var myUrl = "http://localhost:8080/api/author/all";
  $.ajax({ url: myUrl }).then(
    function (authors, status) {
      $.each(authors, function (i, author) {

        $("#collapseExample1").append('\
         <div class="card card-body ">\
           <ul class="list-group list-group-flush">\
             <li class="list-group-item">\
               <!-- Default checked -->\
               <div class="form-check ">\
                 <input type="radio" name="author" class="bookbut form-check-input" id="auth'+ author.id + '" >\
                 <label class="form-check-label" for="auth'+ author.id + '">' + author.firstName + " " + author.lastName + '</label>\
               </div>\
             </li>\
           </ul>\
         </div>' );
      });
    });


  //on page load categorie list
  var myUrl = "http://localhost:8080/api/categorie/all";
  $.ajax({ url: myUrl }).then(
    function (categories, status) {
      $.each(categories, function (j, categorie) {
        $("#collapseExample2").append('\
       <div class="card card-body">\
         <ul class="list-group list-group-flush">\
           <li class="list-group-item">\
             <!-- Default checked -->\
             <div class="form-check">\
               <input type="radio" name="categ" class="cate form-check-input" id="cate'+ categorie.id + '">\
               <label class="form-check-label" for="cate'+ categorie.id + '">' + categorie.name + '</label>\
             </div>\
           </li>\
         </ul>\
       </div>');
      });
    });

  //dropdown list by book id
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
        $("#book").append(ap_book_option);
      });
    }
  );


  $("#book").change(function () {
    $(".Updat").remove();
    $(".Dele").remove();
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
            $("#onomasia").val(response.title);
            $("#is").val(response.isbn);
            $("#nof").val(response.noofpages);
            $("#desc").val(response.description);
            $("#rg").val(response.regularPrice);
            $("#st").val(response.storeQuantity);
            $("#tr").val(response.totalRating);
            $("#Butto").append('<button name="submit" type="button" id="upd_' + response.id + '"\
                    class="Updat btn btn-primary">Update</button>\
                <button name="reset" type="reset" id="del_'+ response.id + '" class="Dele btn btn-danger"></i>\
                    Delete</button>\
                ')
          }
        });
    } else {
      $("#title").val("");
    }
  });

  //-------------------------------------------------------------------
  //update book onclick

  $("body").on("click", ".Updat", function () {

    var idd = this.id.toString().slice(4);

    var title1 = $("#onomasia").val();
    var isbn1 = $("#is").val();
    var numofpage = $("#nof").val();
    var desc1 = $("#desc").val();
    var repr = $("#rg").val();
    var stoQ = $("#st").val();
    var totR= $("#tr").val();
    var myUrl70 = "http://localhost:8080/api/book/update/" + idd;
    $.ajax(
      {
        type: "PUT",
        url: myUrl70,
        dataType: "json",
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
          "Authorization": localStorage.getItem("Authorization"),
          "Accept": "application/json",
          "Content-Type": "application/json"
        },
        data: JSON.stringify({
          title: title1,
          isbn: isbn1,
          noofpages:numofpage,
          description:desc1,
          regularPrice:repr,
          storeQuantity:stoQ,
          totalRating:totR
        }),
        success: function () {
          // window.location.reload();
        }
      });
    alert("Updated");

  });

   //---------------------------------------------------
  //delete Book onclick

  $("body").on("click", ".Dele", function () {

    var idd = this.id.toString().slice(4);
    var myUrl66= "http://localhost:8080/api/book/delete/" + idd;
    $.ajax(
      {
        type: "DELETE",
        url: myUrl66,
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
    location.reload();

  });

//----------------------------------------------------------------------------
//method for filling categories

  var cat;
  $("#collapseExample2").on("change", ".cate ", function () {
      
      var cate=this.id.toString().slice(4)
      var isChecked = $('#cate'+cate).is(":checked") ? true : false;
      if(isChecked){
        cat=cate;
      }
  })

  //----------------------------------------------------------------------
  // submit book onclick
  var auth;
  $("#collapseExample1").on("change", ".bookbut", function () {
    var aid = this.id.toString().slice(4);
    var isChecked = $('#auth' + aid).is(":checked") ? true : false;
    if(isChecked){
      auth=aid;
    }
  });

  
  var respbook;
  $("body").on("click", "#submIT", function () {
    var title1 = $("#onomasia").val();
    var isbn1 = $("#is").val();
    var numofpage = $("#nof").val();
    var desc1 = $("#desc").val();
    var repr = $("#rg").val();
    var stoQ = $("#st").val();
    var totR= $("#tr").val();
  
    alert(auth);
    
    var myUrl94 = "http://localhost:8080/api/book/new/"+auth;
    $.ajax(
      {
        type: "POST",
        url: myUrl94,
        dataType: "json",
        headers: {
          "Access-Control-Allow-Origin": "*",
          "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
          "Authorization": localStorage.getItem("Authorization"),
          "Accept": "application/json",
          "Content-Type": "application/json"
        },
        data: JSON.stringify({
          title: title1,
          isbn: isbn1,
          noofpages:numofpage,
          description:desc1,
          regularPrice:repr,
          storeQuantity:stoQ,
          totalRating:totR
        }),
        success: function (response) {
          respbook=response.id
          alert(cat);
          if(cat!=0){
            var myurl="http://localhost:8080/api/categoriebook/new/categorielist/"+cat;
            $.ajax({
              type: "POST",
              url: myUrl94,
              dataType: "json",
              headers: {
                "Access-Control-Allow-Origin": "*",
                "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
                "Authorization": localStorage.getItem("Authorization"),
                "Accept": "application/json",
                "Content-Type": "application/json"
              },
              data:JSON.stringify({
                id:respbook
              })

            })
              alert("ok");
            }
            alert("no ok");
          }
          

          // alert("Data passed"+respbook);
        }
    );
      });

});
//       }).then (function(){
//         var data = new FormData("form[name='file']");
//       //   jQuery.each(jQuery('#upload')[0].files, function(i, file) {
//       //     data.append('file-'+i, file);
//       // })

// alert(data);
//       $.ajax({
//           type:'POST',
//           url:"http://localhost:8080/api/book/new/image/"+ respbook,
//           enctype: 'multipart/form-data',
//           data:data,
//           cache:false,
//           contentType:false ,
//           processData: false,
//           headers: {
//             "Access-Control-Allow-Origin": "*",
//             "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, OPTIONS, UPDATE",
//             "Authorization": localStorage.getItem("Authorization")
//             // "Accept": "application/json"
//             // "Content-Type": "application/json"
//           },
//           success:function(data){
//               console.log("success");
//               console.log(data);
//           },
//           error: function(data){
//               console.log("error");
//               console.log(data);
//           }
//       });
  


  // jQuery.noConflict(); 
  // formdata = new FormData(); 
  // jQuery("#upload").on("change", function() {
  // var file = this.files[0];

  // if (formdata) { 
  // formdata.append("image", file);
  // jQuery.ajax({
  // url:"http://localhost:8080/api/book/new/image/" +respbook,
  // type: "POST",
  // data: formdata,
  // processData: false,
  // contentType: false,
  // success:function(){
  //   alert("you did it");
  // }
 
  // });
  // } 
  // }); 
 


    // gia to Image 

    // function readURL(input) {
//     if (input.files && input.files[0]) {
//         var reader = new FileReader();

//         reader.onload = function (e) {
//             $('#imageResult')
//                 .attr('src', e.target.result);
//         };
//         reader.readAsDataURL(input.files[0]);
//     }
// }

// $(function () {
//     $('#upload').on('change', function () {
//         readURL(input);
//     });
// });

// /*  ==========================================
//     SHOW UPLOADED IMAGE NAME
// * ========================================== */
// var input = document.getElementById( 'upload' );
// var infoArea = document.getElementById( 'upload-label' );

// input.addEventListener( 'change', showFileName );
// function showFileName( event ) {
//   var input = event.srcElement;
//   var fileName = input.files[0].name;
//   infoArea.textContent = 'File name: ' + fileName;
// }
