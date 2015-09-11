
angular.module('lumi').controller('NewMovimentoEstoqueController', function ($scope, $location, locationParser, MovimentoEstoqueResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.movimentoEstoque = $scope.movimentoEstoque || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/MovimentoEstoques/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        MovimentoEstoqueResource.save($scope.movimentoEstoque, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/MovimentoEstoques");
    };
});