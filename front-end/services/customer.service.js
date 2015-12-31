'use strict'

angular
	.module('app.services')
	.factory('CustomerService', function($http, $cookies, $log, config) {
		var data = {
			'getCustomers': getCustomers,
			'getCustomer': getCustomer
		};

		function getCustomers() {
			return $http({
				'url': config.url + 'customers',
				'method': 'GET',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Get customers");
			})
			.catch(dataServiceError);
		}

		function getCustomer(customerId) {
			return $http({
				'url': config.url + 'customers/' + customerId,
				'method': 'GET',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Get customer id " + customerId);
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