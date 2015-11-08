
angular.module('lumi').controller('NewProdutoController', function ($scope, $location, locationParser, flash, ProdutoResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.produto = $scope.produto || {};
    
    $scope.tipoEmbalagemList = [
        "AMPOLA",
        "BISNAGA",
        "CAIXA",
        "POTE",
        "UNIDADE"
    ];
    
    $scope.unidadeMedidaEmbalagemList = [
        "KG",
        "G",
        "MG",
        "UND",
        "L",
        "ML"
    ];
    
    $scope.formaUtilizacaoList = [
        "FIXO",
        "SUGERIDO"
    ];
    
    $scope.unidadeMedidaUtilizacaoList = [
        "KG",
        "G",
        "MG",
        "UND",
        "L",
        "ML"
    ];
    
    $scope.situacaoList = [
        "ATIVO",
        "INATIVO"
    ];
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The produto was created successfully.'});
            $location.path('/Produtos');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        ProdutoResource.save($scope.produto, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Produtos");
    };
});