/**
 * A Directive to show a table containing the results of a search.
 */
app.directive("searchInput",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/searchInput.html",
		scope:{			
			predicate: "="						
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			
		}
	};
});