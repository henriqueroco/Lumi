

angular.module('lumi').controller('EditEsteticistaController', function($scope, $routeParams, $location, EsteticistaResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.esteticista = new EsteticistaResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Esteticista");
        };
        EsteticistaResource.get({EsteticistaId:$routeParams.EsteticistaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.esteticista);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.esteticista.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Esteticista");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Esteticista");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.esteticista.$remove(successCallback, errorCallback);
    };
    
    $scope.tipoList = [
        "ESTETICISTA",  
        "ATENDIMENTO"  
    ];
    $scope.situacaoList = [
        "ATIVO",  
        "INATIVO"  
    ];
    
    $scope.get();
});