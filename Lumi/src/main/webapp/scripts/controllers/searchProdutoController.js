

angular.module('lumi').controller('SearchProdutoController', function($scope, $http, ProdutoResource ) {

    $scope.search={};
    $scope.currentPage = 0;
    $scope.pageSize= 10;
    $scope.searchResults = [];
    $scope.filteredResults = [];
    $scope.pageRange = [];
    $scope.numberOfPages = function() {
        var result = Math.ceil($scope.filteredResults.length/$scope.pageSize);
        var max = (result == 0) ? 1 : result;
        $scope.pageRange = [];
        for(var ctr=0;ctr<max;ctr++) {
            $scope.pageRange.push(ctr);
        }
        return max;
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

    $scope.performSearch = function() {
        $scope.searchResults = ProdutoResource.queryAll(function(){
            $scope.numberOfPages();
        });
    };
    
    $scope.previous = function() {
       if($scope.currentPage > 0) {
           $scope.currentPage--;
       }
    };
    
    $scope.next = function() {
       if($scope.currentPage < ($scope.numberOfPages() - 1) ) {
           $scope.currentPage++;
       }
    };
    
    $scope.setPage = function(n) {
       $scope.currentPage = n;
    };

    $scope.performSearch();
});