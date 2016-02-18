app.directive("pagination",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/pagination.html",
		scope:{			
			searchRequest: "=",
			searchResponse : "=",
			busy: "=",
			callback: "="
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			$scope.pages = null;
			$scope.quantities = null;
			$scope.calculateOptions = function(){
				$scope.quantities = [];
				for (var q = 5; q <= 100; q+= 5){
					$scope.quantities.push(q);
				}
				
				$scope.pages = [];
				for (var p = 1; p<=$scope.searchResponse.numPages; p++){
					$scope.pages.push(p);
				}				
			}
			//$scope.calculateOptions();
			$scope.$watch("searchResponse", $scope.calculateOptions, true);
			
			$scope.first = function(){
				if ($scope.searchRequest.pageNumber > 1){
					$scope.searchRequest.pageNumber = 1;
				}
			}
			$scope.last = function(){
				if ($scope.searchResponse != null && $scope.searchRequest.pageNumber < $scope.searchResponse.numPages){
					$scope.searchRequest.pageNumber = $scope.searchResponse.numPages;
				}
			}
			$scope.next = function(){
				if ($scope.searchResponse != null && $scope.searchRequest.pageNumber < $scope.searchResponse.numPages){
					$scope.searchRequest.pageNumber++;
				}
			}
			$scope.previous = function(){
				if ($scope.searchRequest.pageNumber > 1){
					$scope.searchRequest.pageNumber --;
				}
			}
		}
	};
});