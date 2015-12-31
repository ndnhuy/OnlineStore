'use strict';

angular
    .module('app.core')
    .controller('ProductController', function($scope, $log, products, ProductService, CartService, AuthService, $cookies) {
        var vm = this;

        vm.products = products.data.data;

   //      $scope.params = [{"brand": "abc"}];
   //      $scope.params.push(
   //      		{"brand": "nexust"}
   //      	);
   //      $log.info("ProductController: " + JSON.stringify($scope.params));

   //      for (var key in $scope.params) {
 		// 	if ($scope.params.hasOwnProperty(key)) {
 		// 		$log.info(key + " -> " + JSON.stringify($scope.params[key]));

 		// 		if ($scope.params[key].brand === "nexust") {
 		// 			$scope.params.splice($scope.params.indexOf($scope.params[key]), 1);
			// 	}


			// }
   //      }
   //       $log.info("ProductController: " + JSON.stringify($scope.params));


        vm.addProductIntoCart = function(productId) {
        	CartService.addProductIntoCart(productId).then(function(data) {
        		if (data.data.status === 201) {
        			alert("Added to cart");	
        		}
        	});
        };

        // var obj = {};
        // obj['name'] = 'abc';
        // obj['age'] = 18;
        // $log.info("obj is " + JSON.stringify(obj));

   //      var productQueryParams = {};

   		
        vm.reloadProducts = function() {
        	$scope.productQueryParams = [];
        	$log.info(JSON.stringify(vm.brands));
        	angular.forEach(vm.brands, function(value, key) {
	        	
	        	if (value != false) {
	        		$scope.productQueryParams.push({
	        			'brand' : value
	        		});
	        	}
	        });
	        angular.forEach(vm.operatingSystems, function(value, key) {
	        	
	        	if (value != false) {
	        		$scope.productQueryParams.push({
	        			'operatingSystem' : value
	        		});
	        	}
	        });
	        angular.forEach(vm.colors, function(value, key) {
	        	
	        	if (value != false) {
	        		$scope.productQueryParams.push({
	        			'color' : value
	        		});
	        	}
	        });

	        angular.forEach(vm.prices, function(value, key) {
	        	if (key === 'price1') {
	        		if (value != false) {
	        			$scope.productQueryParams.push({
	        				'between_price': '0,100'
	        			});
	        		}
	        	}
	        	else if (key === 'price2') {
	        		if (value != false) {
	        			$scope.productQueryParams.push({
	        				'between_price': '100,200'
	        			});
	        		}
	        	}
	        	else if (key === 'price3') {
	        		if (value != false) {
	        			
	        			$scope.productQueryParams.push({
	        				'between_price': '200,300'
	        			});
	        		}	
	        	}
	        	else if (key === 'price4') {
	        		if (value != false) {
	        			
	        			$scope.productQueryParams.push({
	        				'between_price': '300,400'
	        			});
	        		}
	        	}
	        	else if (key === 'price5') {
	        		if (value != false) {
	        			$scope.productQueryParams.push({
	        				'between_price': '400,500'
	        			});
	        		}
	        	}
	        	else if (key === 'price6') {
	        		if (value != false) {
	        			
	        			$scope.productQueryParams.push({
	        				'between_price': '500,600'
	        			});
	        		}
	        	}
	        });

        	ProductService.getProducts($scope.productQueryParams)
        				.then(function(data) {
        					vm.products = data.data.data;
        				});




			// $log.info("productQueryParams is " + JSON.stringify($scope.productQueryParams));
        }
    });

