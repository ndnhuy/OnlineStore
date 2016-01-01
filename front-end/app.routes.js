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
					return AccountService.getAccount();
				}
			}
		})
		.when('/checkout', {
			templateUrl: 'sections/checkout/checkout.template.html',
			controller: 'CheckoutController as checkoutCtrl',
			resolve: {
				products : function(CartService) {
					return CartService.getProductsInCart();
				}
			}
		})
		.when('/orders', {
			templateUrl: 'sections/order/order.template.html',
			controller: 'OrderController as orderCtrl',
			resolve: {
				orders : function(OrderService) {
					return OrderService.getOrders();
				},
				user: function(AccountService) {
					return AccountService.getAccount();
				}
			}
		})
		.when('/customers', {
			templateUrl: 'sections/customer/customer.template.html',
			controller: 'CustomerController as customerCtrl',
			resolve: {
				customers : function(CustomerService) {
					return CustomerService.getCustomers();
				}
			}
		})
		.when('/customers/:customerId/orders', {
			templateUrl: 'sections/order-manage/order-manage.template.html',
			controller: 'OrderManageController as orderManageCtrl',
			resolve: {
				orders : function(OrderService, $route) {
					return OrderService.getOrdersOfCustomer($route.current.params.customerId);
				},
				customer: function(CustomerService, $route) {
					return CustomerService.getCustomer($route.current.params.customerId);
				}
			}
		})
		.when('/registerConfirm', {
			templateUrl: 'sections/registration-confirm/registration-confirm.template.html',
			controller: 'RegisterController as registerCtrl'
		})
		.when('/resetPassword', {
			templateUrl: 'sections/password-reset/password-reset.template.html',
			controller: 'AccountController as accountCtrl',
			resolve: {
				user : function(AccountService) {
					return null;
				}
			}
		})
		.when('/changePassword', {
			templateUrl: 'sections/password-reset/enter-new-password.template.html',
			controller: 'AccountController as accountCtrl',
			resolve: {
				user : function(AccountService) {
					return null;
				}
			}
		})
		.otherwise({
            redirectTo: '/'
        });
}