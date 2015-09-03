

angular.module('lumi').controller('EditLocalidadeController', function($scope, $routeParams, $location, LocalidadeResource , BairroResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.localidade = new LocalidadeResource(self.original);
            BairroResource.queryAll(function(items) {
                $scope.bairrosSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.id
                    };
                    if($scope.localidade.bairros){
                        $.each($scope.localidade.bairros, function(idx, element) {
                            if(item.id == element.id) {
                                $scope.bairrosSelection.push(labelObject);
                                $scope.localidade.bairros.push(wrappedObject);
                            }
                        });
                        self.original.bairros = $scope.localidade.bairros;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Localidades");
        };
        LocalidadeResource.get({LocalidadeId:$routeParams.LocalidadeId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.localidade);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.localidade.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Localidades");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Localidades");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.localidade.$remove(successCallback, errorCallback);
    };
    
    $scope.ufList = [
        "AC",  
        "AL",  
        "AP",  
        "AM",  
        "BA",  
        "CE",  
        "DF",  
        "ES",  
        "GO",  
        "MA",  
        "MT",  
        "MS",  
        "MG",  
        "PA",  
        "PB",  
        "PR",  
        "PE",  
        "PI",  
        "RJ",  
        "RN",  
        "RS",  
        "RO",  
        "RR",  
        "SC",  
        "SP",  
        "SE",  
        "TO",  
        "EX"  
    ];
    $scope.paisList = [
        "AFEGANISTAO",  
        "AFRICADOSUL",  
        "AKROTIRI",  
        "ALBANIA",  
        "ALEMANHA",  
        "ANDORRA",  
        "ANGOLA",  
        "ANGUILA",  
        "ANTARCTIDA",  
        "ANTIGUAEBARBUDA",  
        "ANTILHASNEERLANDESAS",  
        "ARABIASAUDITA",  
        "ARCTICOCEAN",  
        "ARGELIA",  
        "ARGENTINA",  
        "ARMENIA",  
        "ARUBA",  
        "ASHMOREANDCARTIERISLANDS",  
        "ATLANTICOCEAN",  
        "AUSTRALIA",  
        "AUSTRIA",  
        "AZERBAIJAO",  
        "BAAMAS",  
        "BANGLADECHE",  
        "BARBADOS",  
        "BAREM",  
        "BELGICA",  
        "BELIZE",  
        "BENIM",  
        "BERMUDAS",  
        "BIELORRUSSIA",  
        "BIRMANIA",  
        "BOLIVIA",  
        "BOSNIAEHERZEGOVINA",  
        "BOTSUANA",  
        "BRASIL",  
        "BRUNEI",  
        "BULGARIA",  
        "BURQUINAFASO",  
        "BURUNDI",  
        "BUTAO",  
        "CABOVERDE",  
        "CAMAROES",  
        "CAMBOJA",  
        "CANADA",  
        "CATAR",  
        "CAZAQUISTAO",  
        "CHADE",  
        "CHILE",  
        "CHINA",  
        "CHIPRE",  
        "CLIPPERTONISLAND",  
        "COLOMBIA",  
        "COMORES",  
        "CONGOBRAZZAVILLE",  
        "CONGOKINSHASA",  
        "CORALSEAISLANDS",  
        "COREIADONORTE",  
        "COREIADOSUL",  
        "COSTADOMARFIM",  
        "COSTARICA",  
        "CROACIA",  
        "CUBA",  
        "DHEKELIA",  
        "DINAMARCA",  
        "DOMINICA",  
        "EGIPTO",  
        "EMIRATOSARABESUNIDOS",  
        "EQUADOR",  
        "ERITREIA",  
        "ESLOVAQUIA",  
        "ESLOVENIA",  
        "ESPANHA",  
        "ESTADOSUNIDOS",  
        "ESTONIA",  
        "ETIOPIA",  
        "FAROE",  
        "FIJI",  
        "FILIPINAS",  
        "FINLANDIA",  
        "FRANCA",  
        "GABAO",  
        "GAMBIA",  
        "GANA",  
        "GAZASTRIP",  
        "GEORGIA",  
        "GEORGIADOSULESANDWICHDOSUL",  
        "GIBRALTAR",  
        "GRANADA",  
        "GRECIA",  
        "GRONELANDIA",  
        "GUAME",  
        "GUATEMALA",  
        "GUERNSEY",  
        "GUIANA",  
        "GUINE",  
        "GUINEEQUATORIAL",  
        "GUINEBISSAU",  
        "HAITI",  
        "HONDURAS",  
        "HONGKONG",  
        "HUNGRIA",  
        "IEMEN",  
        "ILHABOUVET",  
        "ILHADONATAL",  
        "ILHANORFOLK",  
        "ILHASCAIMAO",  
        "ILHASCOOK",  
        "ILHASDOSCOCOS",  
        "ILHASFALKLAND",  
        "ILHASHEARDEMCDONALD",  
        "ILHASMARSHALL",  
        "ILHASSALOMAO",  
        "ILHASTURCASECAICOS",  
        "ILHASVIRGENSAMERICANAS",  
        "ILHASVIRGENSBRITANICAS",  
        "INDIA",  
        "INDIANOCEAN",  
        "INDONESIA",  
        "IRA",  
        "IRAQUE",  
        "IRLANDA",  
        "ISLANDIA",  
        "ISRAEL",  
        "ITALIA",  
        "JAMAICA",  
        "JANMAYEN",  
        "JAPAO",  
        "JERSEY",  
        "JIBUTI",  
        "JORDANIA",  
        "KUWAIT",  
        "LAOS",  
        "LESOTO",  
        "LETONIA",  
        "LIBANO",  
        "LIBERIA",  
        "LIBIA",  
        "LISTENSTAINE",  
        "LITUANIA",  
        "LUXEMBURGO",  
        "MACAU",  
        "MACEDONIA",  
        "MADAGASCAR",  
        "MALASIA",  
        "MALAVI",  
        "MALDIVAS",  
        "MALI",  
        "MALTA",  
        "MANISLEOF",  
        "MARIANASDONORTE",  
        "MARROCOS",  
        "MAURICIA",  
        "MAURITANIA",  
        "MAYOTTE",  
        "MEXICO",  
        "MICRONESIA",  
        "MOCAMBIQUE",  
        "MOLDAVIA",  
        "MONACO",  
        "MONGOLIA",  
        "MONSERRATE",  
        "MONTENEGRO",  
        "MUNDO",  
        "NAMIBIA",  
        "NAURU",  
        "NAVASSAISLAND",  
        "NEPAL",  
        "NICARAGUA",  
        "NIGER",  
        "NIGERIA",  
        "NIUE",  
        "NORUEGA",  
        "NOVACALEDONIA",  
        "NOVAZELANDIA",  
        "OMA",  
        "PACIFICOCEAN",  
        "PAISESBAIXOS",  
        "PALAU",  
        "PANAMA",  
        "PAPUANOVAGUINE",  
        "PAQUISTAO",  
        "PARACELISLANDS",  
        "PARAGUAI",  
        "PERU",  
        "PITCAIRN",  
        "POLINESIAFRANCESA",  
        "POLONIA",  
        "PORTORICO",  
        "PORTUGAL",  
        "QUENIA",  
        "QUIRGUIZISTAO",  
        "QUIRIBATI",  
        "REINOUNIDO",  
        "REPUBLICACENTROAFRICANA",  
        "REPUBLICACHECA",  
        "REPUBLICADOMINICANA",  
        "ROMENIA",  
        "RUANDA",  
        "RUSSIA",  
        "SALVADOR",  
        "SAMOA",  
        "SAMOAAMERICANA",  
        "SANTAHELENA",  
        "SANTALUCIA",  
        "SAOCRISTOVAOENEVES",  
        "SAOMARINHO",  
        "SAOPEDROEMIQUELON",  
        "SAOTOMEEPRINCIPE",  
        "SAOVICENTEEGRANADINAS",  
        "SARAOCIDENTAL",  
        "SEICHELES",  
        "SENEGAL",  
        "SERRALEOA",  
        "SERVIA",  
        "SINGAPURA",  
        "SIRIA",  
        "SOMALIA",  
        "SOUTHERNOCEAN",  
        "SPRATLYISLANDS",  
        "SRILANCA",  
        "SUAZILANDIA",  
        "SUDAO",  
        "SUECIA",  
        "SUICA",  
        "SURINAME",  
        "SVALBARDEJANMAYEN",  
        "TAILANDIA",  
        "TAIWAN",  
        "TAJIQUISTAO",  
        "TANZANIA",  
        "TERRITORIOBRITANICODOOCEANOINDICO",  
        "TERRITORIOSAUSTRAISFRANCESES",  
        "TIMORLESTE",  
        "TOGO",  
        "TOKELAU",  
        "TONGA",  
        "TRINDADEETOBAGO",  
        "TUNISIA",  
        "TURQUEMENISTAO",  
        "TURQUIA",  
        "TUVALU",  
        "UCRANIA",  
        "UGANDA",  
        "UNIAOEUROPEIA",  
        "URUGUAI",  
        "USBEQUISTAO",  
        "VANUATU",  
        "VATICANO",  
        "VENEZUELA",  
        "VIETNAME",  
        "WAKEISLAND",  
        "WALLISEFUTUNA",  
        "WESTBANK",  
        "ZAMBIA",  
        "ZIMBABUE"  
    ];
    $scope.bairrosSelection = $scope.bairrosSelection || [];
    $scope.$watch("bairrosSelection", function(selection) {
        if (typeof selection != 'undefined' && $scope.localidade) {
            $scope.localidade.bairros = [];
            $.each(selection, function(idx,selectedItem) {
                var collectionItem = {};
                collectionItem.id = selectedItem.value;
                $scope.localidade.bairros.push(collectionItem);
            });
        }
    });
    
    $scope.get();
});