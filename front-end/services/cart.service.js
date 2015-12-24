'use strict'

angular
	.module('app.services')
	.factory('CartService', function($http, $log, config, $cookies) {
		var data = {
			'getProductsInCart': getProductsInCart,
			'removeProductFromCart': removeProductFromCart
		};

		function getProductsInCart() {
			return $http({
				'url': config.url + 'cart',
				'method': 'GET',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Get cart at " + config.url + 'cart');
			})
			.catch(dataServiceError);
		}

		return data;

		function removeProductFromCart(productId, products) {
			return $http.delete(config.url + 'purchases/products/' + productId)
					.success(function(data) {
						$log.info("Remove product id = " + productId + " from cart");
					})
					.catch(dataServiceError);
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for CartService');
	        $log.error(errorResponse);
	        return errorResponse;
	    }

	});