'use strict';

angular
	.module('app.services')
	.factory('ProductService', function($http, $log, config) {

		var data = {
			'getProducts': getProducts,
			'getProductById': getProductById,
			'addProductIntoCart': addProductIntoCart
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

		function addProductIntoCart(productId) {
			$http.post(config.url + 'purchases/products?product_id=' + productId)
					.success(function(data) {
						$log.info("Add product [id=" + productId + "] into cart");
					});
		}

		return data;

		function dataServiceError(errorResponse) {
	        $log.error('Failed for ProductService');
	        $log.error(errorResponse);
	        return errorResponse;
	    }
	});
