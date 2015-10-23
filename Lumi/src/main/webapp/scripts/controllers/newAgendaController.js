
angular.module('lumi').controller('NewAgendaController', function ($scope, $location, locationParser, flash, AgendaResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.agenda = $scope.agenda || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The agenda was created successfully.'});
            $location.path('/Agendas');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        AgendaResource.save($scope.agenda, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Agendas");
    };
});