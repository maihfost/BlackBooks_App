$(document).ready(function(){ 

    var login_url= "http://localhost:8080/api/auth/signin";
    
    $("#login_btn").click(function(){
        
        var username = $("#user_n").val().trim();
        var password = $("#user_p").val().trim();

        if( username != "" && password != "" ){
            $.ajax({
                type:"POST",
                url: login_url,
                dataType: "json",
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                    },
                
                data: JSON.stringify({
                    username : username,
                    password : password
                }),
                success: function(response){
                    localStorage.setItem("Authorization",response.tokenType + " " +response.accessToken);
                    localStorage.setItem("username", username);
                    localStorage.setItem("cartId", 0);
                    alert("Welcome, " + username + "!");
                    window.location.href="index.html";

                },
                error: function(xhr){
                   if (xhr.status == 401) {
                       alert("Username or Password is Incorrect. Try again!")
                       location.reload();
                   }
                   else{
                       alert("A " + xhr.status + " error occured. Contact us.")
                   }
                }
                
            });
        }
        
            
                  
    });


    

});