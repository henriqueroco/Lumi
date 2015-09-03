angular.module('lumi').factory('ProdutoProcedimentoResource', function($resource){
    var resource = $resource('rest/produtoprocedimentos/:ProdutoProcedimentoId',{ProdutoProcedimentoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});