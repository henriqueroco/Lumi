
angular.module('lumi').controller('NewEsteticistaController', function ($scope, $location, locationParser, EsteticistaResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.esteticista = $scope.esteticista || {};
    
    $scope.tipoList = [
        "ESTETICISTA",
        "ATENDIMENTO"
    ];
    
    $scope.situacaoList = [
        "ATIVO",
        "INATIVO"
    ];
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Esteticista/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        EsteticistaResource.save($scope.esteticista, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Esteticista");
    };
});