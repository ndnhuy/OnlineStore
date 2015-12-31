'use strict'

angular
	.module('app.services')
	.factory('OrderService', function($http, $log, $cookies, config) {
		var data = {
			'getOrders': getOrders,
			'deleteOrder': deleteOrder,
			'getOrdersOfCustomer': getOrdersOfCustomer
		};

		function getOrders() {
			return $http({
				'url': config.url + 'buy/orders',
				'method': 'GET',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Get my orders " + JSON.stringify(data));
			})
			.catch(dataServiceError);
		}

		function getOrdersOfCustomer(customerId) {
			return $http({
				'url': config.url + 'customers/' + customerId + '/orders',
				'method': 'GET',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Get orders of customer id " + customerId + ": " + JSON.stringify(data));
			})
			.catch(dataServiceError);
		}

		// function deleteOrderOfCustomer(customerId, orderId) {
		// 	return $http({
		// 		'url': config.url + 'buy/orders/' + id,
		// 		'method': 'DELETE',
		// 		'headers': {
		// 			'Authorization': $cookies.get('access-token')
		// 		}
		// 	})
		// 	.success(function(data) {
		// 		$log.info("Delete order id " + id);
		// 	})
		// 	.catch(dataServiceError);
		// }

		function deleteOrder(id) {
			return $http({
				'url': config.url + 'buy/orders/' + id,
				'method': 'DELETE',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Delete order id " + id);
			})
			.catch(dataServiceError);
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for validating account');
	        $log.error(errorResponse.data);
	        return errorResponse;
	    }
		return data;
	});