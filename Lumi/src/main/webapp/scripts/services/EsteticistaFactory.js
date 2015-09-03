angular.module('lumi').factory('EsteticistaResource', function($resource){
    var resource = $resource('rest/esteticista/:EsteticistaId',{EsteticistaId:'@id'},{'queryAll':{method:'GET',isArray:true},'query':{method:'GET',isArray:false},'update':{method:'PUT'}});
    return resource;
});