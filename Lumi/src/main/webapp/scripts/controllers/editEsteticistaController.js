

angular.module('lumi').controller('EditEsteticistaController', function($scope, $routeParams, $location, flash, EsteticistaResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.esteticista = new EsteticistaResource(self.original);
        };
        var errorCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The esteticista could not be found.'});
            $location.path("/Esteticista");
        };
        EsteticistaResource.get({EsteticistaId:$routeParams.EsteticistaId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.esteticista);
    };

    $scope.save = function() {
        var successCallback = function(){
            flash.setMessage({'type':'success','text':'Esteticista alterado consucesso.'}, true);
            $scope.get();
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        $scope.esteticista.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Esteticista");
    };

    $scope.remove = function() {
        var successCallback = function() {
            flash.setMessage({'type': 'error', 'text': 'The esteticista was deleted.'});
            $location.path("/Esteticista");
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
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