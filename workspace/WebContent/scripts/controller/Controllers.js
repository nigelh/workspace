var app = angular.module('Workspace', ['ajoslin.promise-tracker']);

   app.host = 'www.nigelhole.com';
   

   app.controller('HomeController', function ($scope, $http, $log, promiseTracker, $timeout) {

    // Inititate the promise tracker to track form submissions.
    $scope.progress = promiseTracker();
    
    function User() {
  	  this.id;
  	  this.userName;
  	  this.firstName;
  	  this.secondName;
  	  this.email;
  	  this.password;
  	  this.status;
  	  this.phoneNumber;
  	  this.mobileNumber;
  	  
  	  
    }
    var user = User();

    // Guest Logout handler.
    
   $scope.getUser = function () {
	   
      
        
   }
   
   $scope.init = function() {
	
		$('#GuestLogonForm').show();
		$('#GuestLogoutForm').hide();
		
		var config = {
    	        params : {
    	          'callback' : 'JSON_CALLBACK',
    	        },
    	      };

	   $http.jsonp('http://'+app.host+':8080/workspace/json/user',config)
       .success(function(data, status, headers,config) {
           user = data;
           if( user != null ) {
           $('#username').text(user.firstName+' '+user.secondName);
           $('#GuestLoginForm').hide();
		   $('#GuestLogoutForm').show();
           }
       })
       .error(function(data, status, headers,config) {
         $log.error(data);
       });
   }
    
    $scope.logout = function(form) {
    	
    	var logout = {
    	        params : {
    	          'callback' : 'JSON_CALLBACK',
    	          'email' : user.email,
    	          'password' : user.password
    	        },
    	      };
    	
    	var $promise = $http.jsonp('http://'+app.host+':8080/workspace/json/logout', logout)
        .success(function(data, status, headers,logout) {
         
        	$scope.messages = 'Successful logout';  
            $scope.email = null;
            $scope.password = null;
            user = null;
            $('#GuestLoginForm').show();
            $('#GuestLogoutForm').hide();
            $('#username').text('');
            $scope.submitted = false;
        })
        .error(function(data, status, headers,login) {
          $scope.progress = data;
          $scope.messages = 'There was a network error. Try again later.';
          $log.error(data);
        })
        .finally(function() {
          // Hide status messages after three seconds.
          $timeout(function() {
            $scope.messages = null;
          }, 3000);
        });
    }
    
    // Guest Logon handler.
    $scope.logon = function(form) {
      // Trigger validation flag.
      $scope.submitted = true;

      // If form is invalid, return and let AngularJS show validation errors.
      if (form.$invalid) {
        return;
      }
      
      var formData = {email:$scope.email,password:$scope.password}; //Array 
      
      $.ajax({
          url : 'http://'+app.host+':8080/workspace/json/login',
          type: "GET",
          data : formData,
          contentType: "application/json",
          success: function(data, status, jqXHR)
          {
        	  if (data.status == 'ADMIN' ) {
              	$scope.messages = 'Successful logon';  
                  $scope.email = null;
                  $scope.password = null;
                  $scope.submitted = false;
                  user = data;
                  $('#GuestLoginForm').hide();
                  $('#GuestLogoutForm').show();
                  $('#username').text(user.firstName+' '+user.secondName);
                } else {
              	  $scope.messages = 'Email address and password are invalid';
              	  user = null;
                } 
          },
          error: function (jqXHR, textStatus, errorThrown)
          {
              $scope.messages = 'There was a network error. Try again later.';
             
          }
          
      });
   // Hide status messages after three seconds.
      $timeout(function() {
        $scope.messages = null;
      }, 3000);
      
      
    };
  });
   
  app.controller("RegisterController",function ($scope, $http, $log, promiseTracker, $timeout){
	   // Inititate the promise tracker to track form submissions.
	    $scope.progress = promiseTracker();
	    $scope.subject = "Registration";
	    
	    $scope.email_reg = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		$scope.password_reg = "/(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/";
		
		$scope.registration_init = function() {
			$scope.subject = "Registration";
		}
	    
	    $scope.emailMatch = function() {
	    		if( $scope.email === $scope.emailConfirm ) {
		    		return true;
		    	} else {
		    		return false; 
		    	}
	    }
	    
	    $scope.passwordMatch = function() {
	    	if( $scope.password === $scope.confirm ) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    }
	    
	    $scope.checkPassword =function()
	    {
	        // at least one number, one lowercase and one uppercase letter
	        // at least six characters
	        var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
	        return re.test($scope.password);
	      }
	    
	    $scope.reset = function() {
	    	
	    	$scope.firstName = null;
        	$scope.secondName = null;
            $scope.email = null;
            $scope.emailConfirm = null;
            $scope.password = null;
            $scope.confirm = null;
            $scope.phone = null;
            $scope.mobile = null;
            $scope.message = null;
            $scope.subject = "Registration"
            $scope.submitted = false;
	    	
	    } 
	    
	    function clearMessages() {
	    	$scope.messages = null;
	    	$scope.$apply();
	    }
	    
	    
	 // Registration handler.
	    $scope.register = function(form) {
	      // Trigger validation flag.
	      $scope.submitted = true;

	      // If form is invalid, return and let AngularJS show validation
			// errors.
	      if (form.$invalid) {
	        return;
	      }

	      // Default values for the request.
	      var data = {
	          'firstName' : $scope.firstName,
	          'secondName' : $scope.secondName,
	          'email' : $scope.email,
	          'userName' : $scope.userName,
	          'password' : $scope.password,
	          'mobile' : $scope.mobile,
	          'phone' : $scope.phone,
	          'subject' : $scope.subject,
	          'message' : $scope.message     
	      };
	      
	     
	      
	      // Perform JSONP request.
	      
	        $.ajax({
	        url: 'http://'+app.host+':8080/workspace/json/register',
	        data: JSON.stringify(data),  // serialized data to send on server
	        contentType: "application/json; charset=utf-8",
	        dataType:'json', // set recieving type - JSON in case of a
								// question
	        type:'POST', // set sending HTTP Request type
	        async:false, 
	        success: function(data) { // callback method for further
										// manipulations
	        	$scope.firstName = null;
	        	$scope.secondName = null;
	            $scope.email = null;
	            $scope.password = null;
	            $scope.confirm = null;
	            $scope.phone = null;
	            $scope.mobile = null;
	            $scope.message = null;
	            $scope.subject = "Registration"
	            $scope.submitted = false;
	            $scope.messages = 'Registration completed successfully for user '+data.firstName+ '' +data.secondName;
	            setTimeout(clearMessages,2000);
	        },
	        error: function(data,status,error) { // if error occurred
	        	 if( error == 'Found') {
		        	 	$scope.messages = 'The email address '+data.email+' is already used.'
		         } else {
			            $scope.messages = 'There was a network error. Try again later.';
		         }
		         setTimeout(clearMessages,2000);
	        }
	      });

	   
	    };
  });
    