app.directive("searchWrapper",function(){
	return{ 
		restrict: "E",
		templateUrl: "resources/js/directives/templates/search.html",
		scope: {
			searchRequest: "=",
			searchResponse: "=",			 
			searchUrl : "@searchUrl"
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){
			$scope.busy = false;
			
			/**
			 * Filter out predicates which don't have anything selected.
			 */
			$scope.preProcessRequest = function(){
				var result = {};
				result.pageNumber = $scope.searchRequest.pageNumber;
				result.itemsPerPage = $scope.searchRequest.itemsPerPage;
				result.predicates = [];
				for (var index=0; index < $scope.searchRequest.predicates.length; index++){
					var predicate = $scope.searchRequest.predicates[index];
					var isEntered = false;
					if (predicate.type == "DATE"){
						isEntered = predicate.operator && predicate.value;
					}else{
						isEntered = predicate.operator && predicate.value && predicate.value.length > 0;
					}
					if (isEntered){
						result.predicates.push(predicate);
					}
				}
				return result;
			}
			$scope.loadSearchResults = function(){
				console.log("loading");
				$scope.busy=false;			
				var submitText = angular.toJson($scope.preProcessRequest($scope.searchRequest));
				$scope.searchResponse.results=[];
				var callback = function(data){
					//$scope.searchResponse  = data;
					$scope.$apply(function(){
					$scope.searchResponse.numberOfPages = data.numberOfPages;
					$scope.searchResponse.pageNumber = data.pageNumber;					
					angular.copy(data.results, $scope.searchResponse.results);
					$scope.busy=false;
					});
				}					
				jQuery.ajax ({
				    url: $scope.searchUrl,
				    type: "POST",
				    data: submitText,
				    dataType: "json",
				    contentType: "application/json; charset=utf-8",
				    success: callback							    
				});		
			}
			
			
			$scope.$watch("searchRequest", $scope.loadSearchResults,true);
		}
	};
});