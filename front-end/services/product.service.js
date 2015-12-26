'use strict';

angular
	.module('app.services')
	.factory('ProductService', function($http, $log, config) {

		var data = {
			'getProducts': getProducts,
			'getProductById': getProductById
		};

		function getProducts() {
			return $http.get(config.url + 'products')
			.success(function(data, status, headers, config) {
				return data;
			})
			.catch(dataServiceError);
		}


		function getProductById(id) {
			return $http.get(config.url + 'products/' + id)
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
