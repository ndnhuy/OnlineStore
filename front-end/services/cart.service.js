'use strict'

angular
	.module('app.services')
	.factory('CartService', function($window, $http, $log, config, $cookies) {
		var data = {
			'getProductsInCart': getProductsInCart,
			'removeProductFromCart': removeProductFromCart,
			'addProductIntoCart': addProductIntoCart,
			'checkout': checkout,
			'updateQuantityOfProduct': updateQuantityOfProduct
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
			return $http({
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
				'url': config.url + 'cart/products/' + productId,
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

		function updateQuantityOfProduct(quantity, productId) {
			return $http({
				'url': config.url + 'cart/products/' + productId + '?quantity=' + quantity,
				'method': 'PATCH',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Update quantity of product id " + productId + " config.url + 'cart/products/1?quantity=' + quantity");
			})
			.catch(dataServiceError);
		}

		function checkout(order) {
			return $http({
				'url': config.url + 'cart/checkout',
				'method': 'POST',
				'headers': {
					'Authorization': $cookies.get('access-token')
				},
				'data': order
			})
			.success(function(data) {
				$log.info("Checkout " + JSON.stringify(order));
			})
			.catch(dataServiceError);;
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for CartService');
	        $log.error(errorResponse);
	        if (errorResponse.data.status == 500) {
	        	$window.location.href = '#/login';
	        }
	        return errorResponse;
	    }

	    return data;

	});