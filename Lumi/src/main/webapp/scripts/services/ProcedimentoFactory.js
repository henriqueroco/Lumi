angular.module('lumi').factory('ProcedimentoResource', function($resource){
    var resource = $resource('rest/procedimentos/:ProcedimentoId',{ProcedimentoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});