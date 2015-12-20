'use strict';

angular
	.module('app.services')
	.factory('ProductService', function($http, $log) {

		var data = {
			'getProducts': getProducts,
			'getProductById': getProductById
		};

		function getProducts() {
			return $http.get('http://localhost:8183/products')
			.success(function(data, status, headers, config) {
				return data;
			})
			.catch(dataServiceError);
		}


		function getProductById(id) {
			return $http.get('http://localhost:8183/products/' + id)
				.success(function(data) {
					return data;
				});
		}

		return data;

		function dataServiceError(errorResponse) {
	        $log.error('Failed for ProductService');
	        $log.error(errorResponse);
	        return errorResponse;
	    }
	});
