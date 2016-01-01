'use strict'

angular
	.module('app.services')
	.factory('AccountService', function($http, $log, $cookies, config, User) {
		var data = {
			'getAccount': getAccount,
			'register': register,
			'logout': logout,
			'confirmRegistration': confirmRegistration,
			'askToResetPassword': askToResetPassword,
			'resetPassword': resetPassword
		};

		function getAccount() {

			$log.info("Get account");
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
				User.role = data.data.role;
			})
			.catch(dataServiceError);
		}

		function register(registerUser) {
			return $http({
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

		function confirmRegistration(token) {
			return $http({
				'url': config.url + 'registryConfirm?token=' + token,
				'method': 'GET'
			})
			.success(function(data) {
				$log.info("Confirm registration with token " + token);
			})
			.catch(dataServiceError);
		}

		function askToResetPassword(email) {
			return $http({
				'url': config.url + 'account/resetPassword?email=' + email,
				'method': 'POST'
			})
			.success(function(data) {
				$log.info("Ask to reset password of customer who has email " + email);
			})
			.catch(dataServiceError);
		}

		function resetPassword(token, newPassword) {
			return $http({
				'url': config.url + 'passwordResetConfirm?token=' + token + '&new_password=' + newPassword,
				'method': 'POST'
			})
			.success(function(data) {
				$log.info("Change password");
			})
			.catch(dataServiceError);
		}

		function logout() {
			$log.info(User.username + " logout");
			$cookies.remove('access-token');
		}

		function dataServiceError(errorResponse) {
	        $log.error('Failed for AccountService');
	        $log.error(errorResponse);
	        alert(errorResponse.data.developerMessage);
	        return errorResponse;
	    }

	    return data;
	});