
angular.module('lumi').controller('NewProdutoProcedimentoController', function ($scope, $location, locationParser, ProdutoProcedimentoResource , ProdutoResource, ProcedimentoResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produtoProcedimento = $scope.produtoProcedimento || {};
    
    $scope.produtoList = ProdutoResource.queryAll(function(items){
        $scope.produtoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("produtoSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.produtoProcedimento.produto = {};
            $scope.produtoProcedimento.produto.id = selection.value;
        }
    });
    
    $scope.procedimentoList = ProcedimentoResource.queryAll(function(items){
        $scope.procedimentoSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("procedimentoSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.produtoProcedimento.procedimento = {};
            $scope.produtoProcedimento.procedimento.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/ProdutoProcedimentos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProdutoProcedimentoResource.save($scope.produtoProcedimento, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/ProdutoProcedimentos");
    };
});