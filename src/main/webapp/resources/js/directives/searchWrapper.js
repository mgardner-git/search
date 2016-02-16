app.directive("searchWrapper",function(){
	return{ 
		restrict: "E",
		templateUrl: "resources/js/directives/templates/search.html",
		scope: {
			predicateConfig: "=",
			searchResults: "=",
			pageNumber : "="
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			console.log("search controller.");
		}
	};
});