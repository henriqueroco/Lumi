

angular.module('lumi').controller('EditEstoqueController', function($scope, $routeParams, $location, EstoqueResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.estoque = new EstoqueResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Estoques");
        };
        EstoqueResource.get({EstoqueId:$routeParams.EstoqueId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.estoque);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.estoque.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Estoques");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Estoques");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.estoque.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});