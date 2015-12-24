'use strict'

angular
	.module('app.services')
	.factory('AuthService', function($http, $log, config) {
		var data = {
			'validateAccount': validateAccount,
		};

		function validateAccount(username, password) {
			return $http.post(config.url + 'validate_account?' + 'username=' + username + '&password=' + password)
					.catch(dataServiceError);;
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for validating account');
	        $log.error(errorResponse.data);
	        return errorResponse;
	    }
		return data;
	});