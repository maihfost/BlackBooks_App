$(document).ready(function () {



    const Book={
        title:'o ksenos',
        isbn:'927349827',
        NumberofPages:'222',
        desc:'ofkwokwofwofwfj',
        RP:'32,50',
        SQ:'65',
        TR:'4',
        AuthorId:'6',
        authorname:'cami',
        cd:'3',
        cn:'mythistorima'

        }

    
    $( ".onomasia" ).append( '<label for="title" class="col-4 col-form-label">Title</label>\
    <div class="col-8">\
        <input id="onomasia" name="onomasia" placeholder="'+Book["title"]+'" class="form-control here" type="text">\
    </div>\
</div>');

$( ".isbn" ).append('<label for="text" class="col-4 col-form-label">ISBN</label>\
<div class="col-8">\
    <input id="text" name="text" placeholder="'+Book["isbn"]+'" class="form-control here"  type="text">\
</div>\
</div>');

$( ".nop" ).append(' <label for="password" class="col-4 col-form-label">Number of Pages</label>\
<div class="col-8">\
    <input id="password" name="password" placeholder="'+Book["NumberofPages"]+'"\
        class="form-control here" type="text">\
</div>\
</div>');

    
    
$( ".des" ).append('<label for="email" class="col-4 col-form-label">Description</label>\
<div class="col-8">\
    <input id="email" name="email" placeholder="'+Book["desc"]+'" class="form-control here"\
        type="text">\
</div>\
</div>');
    
    
$( ".rpr" ).append(' <label for="bdate" class="col-4 col-form-label">Regular Price</label>\
<div class="col-8">\
    <input id="cakedate" name="birth" placeholder="'+Book["RP"]+'"\
        class="form-control here" type="text">\
</div>\
</div>');
    
$( ".st" ).append('<label for="phone" class="col-4 col-form-label">Store Quantity</label>\
<div class="col-8">\
    <input id="phone" name="lastname" placeholder="'+Book["SQ"]+'" class="form-control here" type="text">\
</div>\
</div>');
    
$( ".tr" ).append('<label for="location" class="col-4 col-form-label">Total Rating</label>\
<div class="col-8">\
    <input id="Country" name="Country" placeholder="'+Book["TR"]+'" class="form-control here" type="text">\
</div>\
</div>');
    
$( ".Auid" ).append(' <label for="Orders" class="col-4 col-form-label">Author/Id</label>\
<div class="col-8">\
    <select form="text" id="Orders" name="orders" placeholder="Your order" class="form-control order-change"\
        type="text">\
        <option value="order-1">Author: ' +Book["authorname"]+' with id: '+Book["AuthorId"]+'</option>\
        <option value="order-1">Author: ' +Book["authorname"]+' with id: '+Book["AuthorId"]+'</option>\
        <option value="order-1">Author: ' +Book["authorname"]+' with id: '+Book["AuthorId"]+'</option>\
        <option value="order-1">Author: ' +Book["authorname"]+' with id: '+Book["AuthorId"]+'</option>\
        <option value="order-1">Author: ' +Book["authorname"]+' with id: '+Book["AuthorId"]+'</option>\
        <option value="order-1">Author: ' +Book["authorname"]+' with id: '+Book["AuthorId"]+'</option>\
    </select>\
</div>\
</div>');




const authorsdb = [
    {
      name: "giorgos",
      id: 61,
    
    },
    {
      name: "takis",
      id:62 
    },
    {
      name: "takis",
      id: 63
    },
    {
      name: "takis",
      id: 64
    },
    {
      name: "takis",
      id: 65
    }
  ]

  const categoriesdb = [
    {
      name: "mythistorima",
      id: 66
    },
    {
      name: "istoria",
      id: 67
    },
    {
      name: "mythistorima",
      id: 68
    },
    {
      name: "mythistorima",
      id: 69
    },
    {
      name: "mythistorima",
      id: 70
    }
  ]

  var authors = []
  var categories = []
 

 var i;
    for (i = 0; i < authorsdb.length; i++) {
  $( ".Ath" ).append( '<div class="collapse" id="collapseExample1">\
  <div class="card card-body auth">\
    <ul class="list-group list-group-flush">\
      <li class="list-group-item">\
        <!-- Default checked -->\
        <div class="custom-control custom-checkbox">\
          <input type="checkbox" class="custom-control-input" id="check">\
          <label class="custom-control-label" for="check5">'+authorsdb[i]["name"]+'</label>\
        </div>\
      </li>\
    </ul>\
  </div>\
</div>');
    }

    var i;
    for (i = 0; i < categoriesdb.length; i++) {
      $( ".categ" ).append( '<div class="collapse" id="collapseExample2">\
      <div class="card card-body cate">\
        <ul class="list-group list-group-flush">\
          <li class="list-group-item">\
            <!-- Default checked -->\
            <div class="custom-control custom-checkbox">\
              <input type="checkbox" class="custom-control-input" id="check">\
              <label class="custom-control-label" for="check5">'+categoriesdb[i]["name"]+'</label>\
            </div>\
          </li>\
        </ul>\
      </div>\
    </div>' );
        }


  $(".auth label").click(function () {
    setTimeout(() => {
      authors = [];
      $(".auth label").each(function (index) {
        var check = $(this).parent(".custom-control").find(".custom-control-input").is(":checked");
        const author = {
          id: $(this).attr("data-authorid"),
          checked: check
        }
        authors.push(author);
      });
    }, 1000);
    console.log(authors)
  });




  $(".cate label").click(function () {
    setTimeout(() => {
      categories = [];
      $(".cate label").each(function (index) {
        var check = $(this).parent(".custom-control").find(".custom-control-input").is(":checked");
        const categori = {
          id: $(this).attr("data-authorid"),
          checked: check
        }
        categories.push(categori);
      });
    }, 1000);
    console.log(categories)
  });

// gia mia katigoria

// $( ".caid" ).append('<label for="Orders" class="col-4 col-form-label">Category/Id</label>\
// <div class="col-8">\
//     <select form="text" id="Orders" name="orders" placeholder="Your order" class="form-control order-change"\
//         type="text">\
//         <option value="order-1">Category: '+Book["cn"]+' with id: '+Book["cd"]+'</option>\
//         <option value="order-1">Category: '+Book["cn"]+' with id: '+Book["cd"]+'</option>\
//         <option value="order-1">Category: '+Book["cn"]+' with id: '+Book["cd"]+'</option>\
//         <option value="order-1">Category: '+Book["cn"]+' with id: '+Book["cd"]+'</option>\
//         <option value="order-1">Category: '+Book["cn"]+' with id: '+Book["cd"]+'</option>\
//         <option value="order-1">Category: '+Book["cn"]+' with id: '+Book["cd"]+'</option>\
// </div>\
// </div>');
    


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


const Aut={
    fn:'giorg',
    ln:'koko',
    biog:'ofijwofjow',
    auth:'cami',
    catego:'mythistorima'
    }


    $( ".first" ).append(' <label for="name" class="col-4 col-form-label">First Name</label>\
    <div class="col-8">\
        <input id="name" name="name" placeholder="'+Aut["fn"]+'"\
            class="form-control here" type="text">\
    </div>\
</div>');

$( ".last" ).append('<label for="lastname" class="col-4 col-form-label">Last Name</label>\
<div class="col-8">\
    <input id="lastname" name="lastname" placeholder="'+Aut["ln"]+'"\
        class="form-control here" type="text">\
</div>');


$( ".bio" ).append('<label for="password" class="col-4 col-form-label">Biography</label>\
<div class="col-8">\
    <input id="password" name="password" placeholder="'+Aut["biog"]+'"\
        class="form-control here" type="text">\
</div>\
</div>');


$( ".la" ).append(' <label for="Orders" class="col-4 col-form-label">Authors</label>\
<div class="col-8">\
    <select form="text" id="Orders" name="orders" placeholder="Choose Author" class="form-control order-change"\
        type="text">\
        <option value="order-1">'+Aut["auth"]+'</option>\
        <option value="order-1">'+Aut["auth"]+'</option>\
        <option value="order-1">'+Aut["auth"]+'</option>\
        <option value="order-1">'+Aut["auth"]+'</option>\
        <option value="order-1">'+Aut["auth"]+'</option>\
    </select>\
</div>\
</div>');


const CAT={
    fn:'giorg',
    ln:'koko',
    biog:'ofijwofjow',
    auth:'cami',
    catego:'mythistorima',
    catid:'7'
    }


    $( ".cat" ).append('<label for="lastname" class="col-4 col-form-label">Category</label>\
    <div class="col-8">\
        <input id="lastname" name="lastname" placeholder="'+CAT["catego"]+'"\
            class="form-control here" type="text">\
    </div>\
</div>');

$( ".cai" ).append('<label for="Orders" class="col-4 col-form-label">Categories</label>\
<div class="col-8">\
    <select form="text" id="Orders" name="orders" placeholder="Choose Categories" class="form-control order-change"\
        type="text">\
        <option value="order-1">'+CAT["catego"]+'</option>\
        <option value="order-2">'+CAT["catego"]+'</option>\
        <option value="order-3">'+CAT["catego"]+'</option>\
        <option value="order-4">'+CAT["catego"]+'</option>\
        <option value="order-5">'+CAT["catego"]+'</option>\
        <option value="order-6">'+CAT["catego"]+'</option>\
    </select>\
</div>\
</div>');

});