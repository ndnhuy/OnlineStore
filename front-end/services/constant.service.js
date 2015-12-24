'use strict'

angular
	.module('app.services')
	.constant('config', {
		url: 'http://localhost:8183/'
	})
	.value('currentUser', {
		'username': null,
		'token': null
	});
