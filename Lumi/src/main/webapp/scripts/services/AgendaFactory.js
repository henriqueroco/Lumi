angular.module('lumi').factory('AgendaResource', function($resource){
    var resource = $resource('rest/agendas/:AgendaId',{AgendaId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});