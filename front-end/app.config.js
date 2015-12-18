'use strict';

angular
	.module('app.config', [])
	.config(configs)
	.run(runs);

function configs($httpProvider) {
	var interceptor = function($location, $log, $q) {
		var vm = this;
			

		function error(response) {
			alert("ERROR");
		}

		function success(response) {
			$log.debug("interceptor success");
			return response;
		}

		return function(promise) {
			return promise.then(success, error);
		}
	};

	$httpProvider.interceptors.push(interceptor);
}

function runs($rootScope) {
    $rootScope.$on('$routeChangeStart', function() {
    });
    $rootScope.$on('$routeChangeSuccess', function() {
    });
}