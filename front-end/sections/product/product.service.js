'use strict';

angular
	.module('app.services')
	.factory('ProductService', function($http, $log) {

		var data = {
			'getProducts': getProducts
		};

		function getProducts() {
			return $http.get('http://localhost:8183/products')
			.success(function(data, status, headers, config) {
				return data;
			});
		}


		function getProductById(id) {
			return $http.get('http://localhost:8183/products/' + id)
				.success(function(data) {
					return data;
				});
		}

		return data;
	});
