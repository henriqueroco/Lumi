
angular.module('lumi').controller('NewEsteticistaController', function ($scope, $location, locationParser, flash, EsteticistaResource ) {
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
            flash.setMessage({'type':'success','text':'The esteticista was created successfully.'});
            $location.path('/Esteticista');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        EsteticistaResource.save($scope.esteticista, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Esteticista");
    };
});