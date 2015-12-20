'use strict'

angular
	.module('app.services')
	.factory('CartService', function($http, $log) {
		var data = {
			'getProductsInCart': getProductsInCart
		};

		function getProductsInCart() {
			return $http.get('http://localhost:8183/purchases/1')
			.success(function(data, status, headers, config) {
				
			})
			.catch(dataServiceError);
		}

		return data;

		function dataServiceError(errorResponse) {
	        $log.error('Failed for CartService');
	        $log.error(errorResponse);
	        return errorResponse;
	    }
	});