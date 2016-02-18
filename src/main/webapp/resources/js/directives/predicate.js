app.directive("predicate",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/predicate.html",
		scope:{			
			predicate: "=",
			busy: "=",
			callback: "="
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			$scope.operator = $scope.predicate.operator;
			$scope.value = $scope.predicate.value;
			/*
			$scope.update = function(){
				$scope.predicate.value = $scope.value;
				$scope.predicate.operator = $scope.operator;
				if ($scope.value !== undefined && $scope.value.length > 0
					&& $scope.operator !== undefined && $scope.operator.length > 0){
					callback();
				}				
			};
			
			$scope.$watch("operator", $scope.update);
			$scope.$watch("value", $scope.update);

			*/
		}
	};
});