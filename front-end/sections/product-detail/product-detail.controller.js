'use strict';

angular
    .module('app.core')
    .controller('ProductDetailController', function($scope, $log, product) {
        var vm = this;

        vm.product = product.data.data;
    });

