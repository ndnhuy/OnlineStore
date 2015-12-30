'use strict'

angular
	.module('app.services')
	.factory('OrderService', function($http, $log, $cookies, config) {
		var data = {
			'getOrders': getOrders,
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

		function dataServiceError(errorResponse) {
	        $log.error('Failed for validating account');
	        $log.error(errorResponse.data);
	        return errorResponse;
	    }
		return data;
	});