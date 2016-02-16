/**
 * A Directive to show a table containing the results of a search.
 */
app.directive("search",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/results.html",
		scope:{			
			searchResults: "=",
			predicateConfig:"="
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			
		}
	};
});