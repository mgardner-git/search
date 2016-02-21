app.directive("numeric",function(){
	return{ 
		restrict: "E",
		template:'<input type="text" class="{{cssClass}}" ng-model="fieldName" tabIndex="{{tabIndex}}" ng-model-options="{updateOn: \'blur\'}" placeholder="{{placeholder}}"></input>',
		scope:{
			cssClass: "@",
			tabIndex: "@",
			blur: "&",
			fieldName: "=",
			placeholder: "@"
		},
		link: function($scope, element, attrs){
			
			var inputNode = jQuery(element).find("input");
			inputNode.spinner({
				min: 0,
				spin: function(event,ui){					
					$scope.$apply(function(){
						$scope.fieldName =ui.value;
					});
					
					inputNode.change();
				}
			});
			

		},
		controller: function($scope){
			/*
			$scope.$watch("subVal", function(){
				$scope.fieldName = $scope.subVal;				
			});
			$scope.$watch("fieldName", function(){
				$scope.subVal = $scope.fieldName;
			});
			*/
		}
	};
});