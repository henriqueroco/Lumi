

angular.module('lumi').controller('EditProdutoController', function($scope, $routeParams, $location, ProdutoResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.produto = new ProdutoResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Produtos");
        };
        ProdutoResource.get({ProdutoId:$routeParams.ProdutoId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.produto);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.produto.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Produtos");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Produtos");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.produto.$remove(successCallback, errorCallback);
    };
    
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
    
    $scope.get();
});