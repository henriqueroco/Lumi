
angular
		.module('lumi')
		.controller(
				'EditProdutoController',
				function($scope, $routeParams, $location, flash,
						ProdutoResource) {
					var self = this;
					$scope.disabled = false;
					$scope.$location = $location;

					$scope.get = function() {
						var successCallback = function(data) {
							self.original = data;
							$scope.produto = new ProdutoResource(self.original);
						};
						var errorCallback = function() {
							flash.setMessage({
								'type' : 'error',
								'text' : 'The produto could not be found.'
							});
							$location.path("/Produtos");
						};
						ProdutoResource.get({
							ProdutoId : $routeParams.ProdutoId
						}, successCallback, errorCallback);
					};

					$scope.isClean = function() {
						return angular.equals(self.original, $scope.produto);
					};

					$scope.save = function() {
						var successCallback = function() {
							flash
									.setMessage(
											{
												'type' : 'success',
												'text' : 'Produto alterado consucesso.'
											}, true);
							$scope.get();
						};
						var errorCallback = function(response) {
							if (response && response.data
									&& response.data.message) {
								flash.setMessage({
									'type' : 'error',
									'text' : response.data.message
								}, true);
							} else {
								flash
										.setMessage(
												{
													'type' : 'error',
													'text' : 'Something broke. Retry, or cancel and start afresh.'
												}, true);
							}
						};
						$scope.produto.$update(successCallback, errorCallback);
					};

					$scope.cancel = function() {
						$location.path("/Produtos");
					};

					$scope.remove = function() {
						var successCallback = function() {
							flash.setMessage({
								'type' : 'error',
								'text' : 'The produto was deleted.'
							});
							$location.path("/Produtos");
						};
						var errorCallback = function(response) {
							if (response && response.data
									&& response.data.message) {
								flash.setMessage({
									'type' : 'error',
									'text' : response.data.message
								}, true);
							} else {
								flash
										.setMessage(
												{
													'type' : 'error',
													'text' : 'Something broke. Retry, or cancel and start afresh.'
												}, true);
							}
						};
						$scope.produto.$remove(successCallback, errorCallback);
					};

					$scope.tipoEmbalagemList = [ "AMPOLA", "BISNAGA", "CAIXA",
							"POTE", "UNIDADE" ];

					$scope.unidadeMedidaEmbalagemList = [ "KG", "G", "MG",
							"UND", "L", "ML" ];

					$scope.formaUtilizacaoList = [ "FIXO", "SUGERIDO" ];

					$scope.unidadeMedidaUtilizacaoList = [ "KG", "G", "MG",
							"UND", "L", "ML" ];
					$scope.situacaoList = [ "ATIVO", "INATIVO" ];

					$scope.get();
				});