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
					return ProductService.getProducts(null);
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
		.when('/login', {
			templateUrl: 'sections/login/login.template.html',
			controller: 'LoginController as loginCtrl'
		})
		.when('/register', {
			templateUrl: 'sections/registry/register.template.html',
			controller: 'RegisterController as registerCtrl'
		})
		.when('/myaccount', {
			templateUrl: 'sections/account/account.template.html',
			controller: 'AccountController as accountCtrl',
			resolve: {
				user : function(AccountService) {
					return AccountService.getAccount;
				}
			}
		})
		.otherwise({
            redirectTo: '/'
        });
}