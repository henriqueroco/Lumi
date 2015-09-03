

angular.module('lumi').controller('EditProdutoProcedimentoController', function($scope, $routeParams, $location, ProdutoProcedimentoResource , ProdutoResource, ProcedimentoResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.produtoProcedimento = new ProdutoProcedimentoResource(self.original);
            ProdutoResource.queryAll(function(items) {
                $scope.produtoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.produtoProcedimento.produto && item.id == $scope.produtoProcedimento.produto.id) {
                        $scope.produtoSelection = labelObject;
                        $scope.produtoProcedimento.produto = wrappedObject;
                        self.original.produto = $scope.produtoProcedimento.produto;
                    }
                    return labelObject;
                });
            });
            ProcedimentoResource.queryAll(function(items) {
                $scope.procedimentoSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.produtoProcedimento.procedimento && item.id == $scope.produtoProcedimento.procedimento.id) {
                        $scope.procedimentoSelection = labelObject;
                        $scope.produtoProcedimento.procedimento = wrappedObject;
                        self.original.procedimento = $scope.produtoProcedimento.procedimento;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/ProdutoProcedimentos");
        };
        ProdutoProcedimentoResource.get({ProdutoProcedimentoId:$routeParams.ProdutoProcedimentoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.produtoProcedimento);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.produtoProcedimento.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/ProdutoProcedimentos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/ProdutoProcedimentos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.produtoProcedimento.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("produtoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.produtoProcedimento.produto = {};
            $scope.produtoProcedimento.produto.id = selection.value;
        }
    });
    $scope.$watch("procedimentoSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.produtoProcedimento.procedimento = {};
            $scope.produtoProcedimento.procedimento.id = selection.value;
        }
    });
    
    $scope.get();
});