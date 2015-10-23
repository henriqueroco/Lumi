

angular.module('lumi').controller('EditMovimentoEstoqueController', function($scope, $routeParams, $location, flash, MovimentoEstoqueResource , ProdutoResource, AtendimentoResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.movimentoEstoque = new MovimentoEstoqueResource(self.original);
            ProdutoResource.queryAll(function(items) {
                $scope.produtoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.movimentoEstoque.produto){
                        $.each($scope.movimentoEstoque.produto, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.produtoSelection.push(labelObject);
                                $scope.movimentoEstoque.produto.push(wrappedObject);
                            }
                        });
                        self.original.produto = $scope.movimentoEstoque.produto;
                    }
                    return labelObject;
                });
            });
            AtendimentoResource.queryAll(function(items) {
                $scope.atendimentoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.movimentoEstoque.atendimento){
                        $.each($scope.movimentoEstoque.atendimento, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.atendimentoSelection.push(labelObject);
                                $scope.movimentoEstoque.atendimento.push(wrappedObject);
                            }
                        });
                        self.original.atendimento = $scope.movimentoEstoque.atendimento;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The movimentoEstoque could not be found.'});
            $location.path("/MovimentoEstoques");
        };
        MovimentoEstoqueResource.get({MovimentoEstoqueId:$routeParams.MovimentoEstoqueId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.movimentoEstoque);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'The movimentoEstoque was updated successfully.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.movimentoEstoque.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/MovimentoEstoques");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The movimentoEstoque was deleted.'});
            $location.path("/MovimentoEstoques");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        }; 
        $scope.movimentoEstoque.$remove(successCallback, errorCallback);
    };
    
    $scope.tipoMovimentoList = [
        "ENTRADA",  
        "SAIDA"  
    ];
    $scope.unidadeMedidaMovimentacaoList = [
        "UND",  
        "KG",  
        "L"  
    ];
    $scope.produtoSelection = $scope.produtoSelection || [];
    $scope.$watch("produtoSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.movimentoEstoque) {
            $scope.movimentoEstoque.produto = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.movimentoEstoque.produto.push(collectionItem);
            });
        }
    });
    $scope.atendimentoSelection = $scope.atendimentoSelection || [];
    $scope.$watch("atendimentoSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.movimentoEstoque) {
            $scope.movimentoEstoque.atendimento = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.movimentoEstoque.atendimento.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});