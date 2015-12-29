'use strict';

angular
	.module('app.services')
	.factory('ProductService', function($http, $log, config) {

		var data = {
			'getProducts': getProducts,
			'getProductById': getProductById
		};

		function makeRequest(url, params) {
			if (params == null) {
				params = {};
			}

	        var requestUrl = url + '?';
	        angular.forEach(params, function(value, key){
	            requestUrl = requestUrl + '&' + key + '=' + value;
	        });
	        return requestUrl;
    }

		function getProducts(productQueryParams) {
			if (productQueryParams == null) {
				productQueryParams = [];
			}

			var requestUrl = config.url + 'products?';
			angular.forEach(productQueryParams, function(value, key) {
        		angular.forEach(value, function (value, key) {
        			requestUrl = requestUrl + '&' + key + '=' + value;
        		});
        		
        	});

        	$log.info('getProducts#requestUrl = ' + requestUrl);

			return $http.get(requestUrl)
				.success(function(data, status, headers, config) {
				return data;
				})
				.catch(dataServiceError);
		}


		function getProductById(id) {
			return $http.get(config.url + 'products/' + id)
				.success(function(data) {
					return data;
				});
		}

		return data;

		function dataServiceError(errorResponse) {
	        $log.error('Failed for ProductService');
	        $log.error(errorResponse);
	        return errorResponse;
	    }
	});
