'use strict'

angular
	.module('app.services')
	.factory('CartService', function($http, $log, config, $cookies) {
		var data = {
			'getProductsInCart': getProductsInCart,
			'removeProductFromCart': removeProductFromCart,
			'addProductIntoCart': addProductIntoCart
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
				return data;
			})
			.catch(dataServiceError);
		}

		function addProductIntoCart(productId) {
			$http({
				'url': config.url + 'cart?insert_product_id=' + productId,
				'method': 'POST',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Add product id " + productId + " to cart " + config.url + 'cart?insert_product_id=' + productId);
			})
			.catch(dataServiceError);
		}



		function removeProductFromCart(productId, products) {

			return $http({
				'url': config.url + 'cart/' + productId,
				'method': 'DELETE',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Delete product id " + productId + " from cart " + config.url + 'cart/' + productId);
			})
			.catch(dataServiceError);
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for CartService');
	        $log.error(errorResponse);
	        alert(errorResponse.data.message);
	        return errorResponse;
	    }

	    return data;

	});