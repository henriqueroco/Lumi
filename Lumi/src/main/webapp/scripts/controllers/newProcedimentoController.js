
angular.module('lumi').controller('NewProcedimentoController', function ($scope, $location, locationParser, flash, ProcedimentoResource , ProdutoResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.procedimento = $scope.procedimento || {};
    
    $scope.produtosList = ProdutoResource.queryAll(function(items){
        $scope.produtosSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("produtosSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.procedimento.produtos = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.procedimento.produtos.push(collectionItem);
            });
        }
    });


    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            flash.setMessage({'type':'success','text':'The procedimento was created successfully.'});
            $location.path('/Procedimentos');
        };
        var errorCallback = function(response) {
            if(response && response.data && response.data.message) {
                flash.setMessage({'type': 'error', 'text': response.data.message}, true);
            } else {
                flash.setMessage({'type': 'error', 'text': 'Something broke. Retry, or cancel and start afresh.'}, true);
            }
        };
        ProcedimentoResource.save($scope.procedimento, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Procedimentos");
    };
});