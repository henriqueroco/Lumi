

angular.module('lumi').controller('EditMovimentoEstoqueController', function($scope, $routeParams, $location, MovimentoEstoqueResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.movimentoEstoque = new MovimentoEstoqueResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/MovimentoEstoques");
        };
        MovimentoEstoqueResource.get({MovimentoEstoqueId:$routeParams.MovimentoEstoqueId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.movimentoEstoque);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.movimentoEstoque.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/MovimentoEstoques");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/MovimentoEstoques");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.movimentoEstoque.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});