'use strict'

angular
	.module('app.services')
	.factory('AccountService', function($http, $log, $cookies, config, User) {
		var data = {
			'getAccount': getAccount,
			'register': register
		};

		function getAccount() {
			return $http({
				'url': config.url + 'account',
				'method': 'GET',
				'headers': {
					'Authorization': $cookies.get('access-token')
				}
			})
			.success(function(data) {
				$log.info("Get my account " + JSON.stringify(data));
				User.username = data.data.username;
			})
			.catch(dataServiceError);
		}

		function register(registerUser) {
			$http({
				'url': config.url + 'account/register',
				'method': 'POST',
				'headers': {
					'Authorization': $cookies.get('access-token'),
					'Content-Type': 'application/json'
				},
				'data': registerUser
			})
			.success(function(data) {
				$log.info("Register account " + JSON.stringify(registerUser));
			})
			.catch(dataServiceError);
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for AccountService');
	        $log.error(errorResponse);
	        alert("You must login");
	        return errorResponse;
	    }

	    return data;
	});