app.directive("predicate",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/predicate.html",
		scope:{			
			predicate: "=",
			busy: "=",
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			$scope.operator = $scope.predicate.operator;
			$scope.value = $scope.predicate.value;
			
			$scope.$watch("[predicate.operator, predicate.value]", function(){
				var bothEntered = $scope.predicate.operator != undefined && ($scope.predicate.value != undefined && ($scope.predicate.type != "STRING" || $scope.predicate.value.length > 0));
				if (bothEntered){
					$scope.$emit("search");
				}
			})
		}
	};
});