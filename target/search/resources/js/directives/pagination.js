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
				var spaceBetweenPages = $scope.searchResponse.numPages <= 10 ? 1 : Math.floor($scope.searchResponse.numPages/10);
				/*
				for (var p=1; p <= $scope.searchResponse.numPages; p+=spaceBetweenPages){						
					$scope.pages.push(p);
				}*/
				for (var p=1; p <= $scope.searchResponse.numPages; p++){
					var localPage = $scope.searchRequest.pageNumber;
					var isLocal = p==localPage || p==localPage-1 || p==localPage+1;
					var isSection = p % (spaceBetweenPages+1) == 0;
					if (isLocal || isSection){
						$scope.pages.push(p);
					}
				}
				
			}
			//$scope.calculateOptions();
			$scope.$watch("searchResponse", $scope.calculateOptions, true);
			
			$scope.isLocal = function(page){
				return Math.abs(page-$scope.searchResponse.pageNumber) <= 1;
			}
			$scope.first = function(){
				if ($scope.searchRequest.pageNumber > 1){
					$scope.setPageNumber(1);
				}				
			}
			$scope.last = function(){
				if ($scope.searchResponse != null && $scope.searchRequest.pageNumber < $scope.searchResponse.numPages){
					$scope.setPageNumber($scope.searchResponse.numPages);
				}				
			}
			$scope.next = function(){
				if ($scope.searchResponse != null && $scope.searchRequest.pageNumber < $scope.searchResponse.numPages){
					$scope.setPageNumber($scope.searchRequest.pageNumber+1);
				}				
			}
			$scope.previous = function(){
				if ($scope.searchRequest.pageNumber > 1){
					$scope.setPageNumber($scope.searchRequest.pageNumber - 1);
				}				
			}
			$scope.setPageNumber = function(pageNumber){
				$scope.searchRequest.pageNumber = pageNumber;
				$scope.$emit("search");
			}
			$scope.$watch("searchRequest.itemsPerPage", function(){
				$scope.$emit("search");
			});
		}
	};
});