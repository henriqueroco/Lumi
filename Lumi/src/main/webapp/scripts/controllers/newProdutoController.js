
angular.module('lumi').controller('NewProdutoController', function ($scope, $location, locationParser, ProdutoResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produto = $scope.produto || {};
    
    $scope.tipoEmbalagemList = [
        "POTE",
        "UNIDADE",
        "BISNAGA"
    ];
    
    $scope.unidadeMedidaEmbalagemList = [
        "UND",
        "KG",
        "L"
    ];
    
    $scope.formaUtilizacaoList = [
        "FIXO",
        "SUGERIDO"
    ];
    
    $scope.unidadeMedidaUtilizacaoList = [
        "UND",
        "KG",
        "L"
    ];
    
    $scope.situacaoList = [
        "ATIVO",
        "INATIVO"
    ];
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Produtos/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        ProdutoResource.save($scope.produto, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Produtos");
    };
});