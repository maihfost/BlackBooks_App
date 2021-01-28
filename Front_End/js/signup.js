$(document).ready(function(){ 

    var login_url= "http://localhost:8080/api/auth/signup";
    
    $("#register_btn").click(function(){
        
        var username = $("#user_n").val().trim();
        var password = $("#user_p").val().trim();
        var email= $("#user_email").val().trim();
        var firstname = $("#user_firstname").val().trim();
        var lastname = $("#user_lastname").val().trim();
        var birthDate = $("#user_dob").val().trim();
        var country = $("#user_coun").val().trim();
        var telephone = $("#user_tel").val().trim();
        var digitwallet = $("#user_wallet").val().trim();

        console.log(birthDate);
        
            $.ajax({
                type:"POST",
                url: login_url,
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                    },
                
                data: JSON.stringify({
                    email : email,
                    username : username,
                    password : password,
                    firstname : firstname,
                    lastname : lastname,
                    dateofbirth : birthDate,
                    country : country,
                    telephone : telephone,
                    digitwallet : digitwallet
                }),
                success: function(response){
                    
                    alert("Thank you for your Registration !");
                    window.location.href = "Login.html";
                    //window.location.href="http://127.0.0.1:5500/index.html";

                }
                // ,error: function(xhr){
                //    if (xhr.status == 401) {
                //        alert("Username or Password is Incorrect. Try again!")
                //        location.reload();
                //    }
                //    else{
                //        alert("A " + xhr.status + " error occured. Contact us.")
                //    }
                // }
                
            });        
            
                  
    });


    

});