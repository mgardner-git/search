var parseLocation = function() {
	var pairs = window.location.search.substring(1).split("&");
	var obj = {};
	var pair;
	var i;

	for ( i in pairs ) {
		if ( pairs[i] === "" ) continue;
			pair = pairs[i].split("=");
			obj[ decodeURIComponent( pair[0] ) ] = decodeURIComponent( pair[1] );
		}
	return obj;
};
	
	
var app = angular.module('allegiantApp', []);
app.controller('allegiantController', function($scope, $http) {
});

app.factory('myHttpResponseInterceptor',['$q','$location',function($q,$location){
	return {
		responseError: function(response){
			//see ErrorHandlerFilter.java
	        if (response.status === 403) {
	            window.location.href="/recipes/login.jsp";
	        }else{
	        	alert("We were unable to process your request: " + response);
	        }
	        return response;
	   
		}
	}
}]);

//Http Intercpetor to check auth failures for xhr requests
app.config(['$httpProvider',function($httpProvider) {
	$httpProvider.interceptors.push('myHttpResponseInterceptor');
}]);

jQuery(function() {
	 
  });

