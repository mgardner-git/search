/**
 * A Directive to show a table containing the results of a search.
 */
app.directive("results",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/results.html",
		scope:{			
			searchRequest : "=",
			searchResponse: "=",			
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){

			$scope.formatResults = function (predicate,result){
				if (predicate.type == "DATE"){
					var time = parseInt(result);
					var date = new Date(time);
					var formattedDate = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
					formattedDate = formattedDate  + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
					return formattedDate;
				}else{
					return result;
				}
			};
			$scope.setSort = function(predicate, event){
				var sorts = new Array();
				var sort = {};
				sort.columnName = predicate.name;
				sort.sortType = event.ctrlKey ? "DESC":"ASC";
				sorts.push(sort);
				$scope.searchRequest.sorts = sorts;
				//TODO: Need some kind of visual marker on the header
			};
			
			$scope.isAscendingSort = function(predicate){
				for (var index=0; index < $scope.searchRequest.sorts.length; index++){
					var checkSort = $scope.searchRequest.sorts[index];
					if (checkSort.sortType == "ASC" && checkSort.columnName == predicate.name){
						return true;
					}
				}
				return false;
			};
			$scope.isDescendingSort = function(predicate){
				for (var index=0; index < $scope.searchRequest.sorts.length; index++){
					var checkSort = $scope.searchRequest.sorts[index];
					if (checkSort.sortType == "DESC" && checkSort.columnName == predicate.name){
						return true;
					}
				}
				return false;				
			}
			
		}
	};
});