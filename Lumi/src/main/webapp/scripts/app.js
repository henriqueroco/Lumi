'use strict';

angular.module('lumi',['ngRoute','ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/',{templateUrl:'views/landing.html',controller:'LandingPageController'})
      .when('/Bairros',{templateUrl:'views/Bairro/search.html',controller:'SearchBairroController'})
      .when('/Bairros/new',{templateUrl:'views/Bairro/detail.html',controller:'NewBairroController'})
      .when('/Bairros/edit/:BairroId',{templateUrl:'views/Bairro/detail.html',controller:'EditBairroController'})
      .when('/Clientes',{templateUrl:'views/Cliente/search.html',controller:'SearchClienteController'})
      .when('/Clientes/new',{templateUrl:'views/Cliente/detail.html',controller:'NewClienteController'})
      .when('/Clientes/edit/:ClienteId',{templateUrl:'views/Cliente/detail.html',controller:'EditClienteController'})
      .when('/Esteticista',{templateUrl:'views/Esteticista/search.html',controller:'SearchEsteticistaController'})
      .when('/Esteticista/new',{templateUrl:'views/Esteticista/detail.html',controller:'NewEsteticistaController'})
      .when('/Esteticista/edit/:EsteticistaId',{templateUrl:'views/Esteticista/detail.html',controller:'EditEsteticistaController'})
      .when('/Localidades',{templateUrl:'views/Localidade/search.html',controller:'SearchLocalidadeController'})
      .when('/Localidades/new',{templateUrl:'views/Localidade/detail.html',controller:'NewLocalidadeController'})
      .when('/Localidades/edit/:LocalidadeId',{templateUrl:'views/Localidade/detail.html',controller:'EditLocalidadeController'})
      .when('/Logradouros',{templateUrl:'views/Logradouro/search.html',controller:'SearchLogradouroController'})
      .when('/Logradouros/new',{templateUrl:'views/Logradouro/detail.html',controller:'NewLogradouroController'})
      .when('/Logradouros/edit/:LogradouroId',{templateUrl:'views/Logradouro/detail.html',controller:'EditLogradouroController'})
      .when('/Procedimentos',{templateUrl:'views/Procedimento/search.html',controller:'SearchProcedimentoController'})
      .when('/Procedimentos/new',{templateUrl:'views/Procedimento/detail.html',controller:'NewProcedimentoController'})
      .when('/Procedimentos/edit/:ProcedimentoId',{templateUrl:'views/Procedimento/detail.html',controller:'EditProcedimentoController'})
      .when('/Produtos',{templateUrl:'views/Produto/search.html',controller:'SearchProdutoController'})
      .when('/Produtos/new',{templateUrl:'views/Produto/detail.html',controller:'NewProdutoController'})
      .when('/Produtos/edit/:ProdutoId',{templateUrl:'views/Produto/detail.html',controller:'EditProdutoController'})
      .when('/ProdutoProcedimentos',{templateUrl:'views/ProdutoProcedimento/search.html',controller:'SearchProdutoProcedimentoController'})
      .when('/ProdutoProcedimentos/new',{templateUrl:'views/ProdutoProcedimento/detail.html',controller:'NewProdutoProcedimentoController'})
      .when('/ProdutoProcedimentos/edit/:ProdutoProcedimentoId',{templateUrl:'views/ProdutoProcedimento/detail.html',controller:'EditProdutoProcedimentoController'})
      .otherwise({
        redirectTo: '/'
      });
  }])
  .controller('LandingPageController', function LandingPageController() {
  })
  .controller('NavController', function NavController($scope, $location) {
    $scope.matchesRoute = function(route) {
        var path = $location.path();
        return (path === ("/" + route) || path.indexOf("/" + route + "/") == 0);
    };
  });
