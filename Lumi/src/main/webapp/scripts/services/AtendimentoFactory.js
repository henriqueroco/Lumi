angular.module('lumi').factory('AtendimentoResource', function($resource){
    var resource = $resource('rest/atendimentos/:AtendimentoId',{AtendimentoId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});