app.directive("predicates",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/predicates.html",
		scope:{			
			predicateConfig: "=",
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			console.log($scope.predicateConfig);
			
		}
	};
});