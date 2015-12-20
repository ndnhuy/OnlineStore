'use strict';

angular
	.module('app.routes', ['ngRoute'])
	.config(config);

function config($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl: 'sections/product/product.template.html',
			controller: 'ProductController as productCtrl',
			resolve: {
				products: function(ProductService) {
					return ProductService.getProducts();
				}
 			}
		})
		.when('/products/:id', {
			templateUrl: 'sections/product-detail/product-detail.template.html',
			controller: 'ProductDetailController as productDetailCtrl',
			resolve: {
				product : function(ProductService, $route) {
					return ProductService.getProductById($route.current.params.id);
				}
			}
		})
		.when('/cart', {
			templateUrl: 'sections/cart/cart.template.html',
			controller: 'CartController as cartCtrl',
			resolve: {
				products : function(CartService) {
					return CartService.getProductsInCart();
				}
			}
		})
		.otherwise({
            redirectTo: '/'
        });
}