if(!$('#jqueryInputMaskJs').length) {
    var s = document.createElement("script");
    s.id = "jqueryInputMaskJs";
    s.type = "text/javascript";
    s.src = "../ebodac/resources/js/jquery.inputmask.js";
    $("head").append(s);
}

if(!$('#inputMaskJs').length) {
    var s = document.createElement("script");
    s.id = "inputMaskJs";
    s.type = "text/javascript";
    s.src = "../ebodac/resources/js/inputmask.js";
    $("head").append(s);
}

if ($scope.selectedEntity.name === "Participant" || $scope.selectedEntity.name === "Visit") {
    $scope.showBackToEntityListButton = false;
} else {
    $scope.showImportButton = false;
    $scope.backToEntityList = function() {
        window.location.replace('#/ebodac/reports');
    };
}
$scope.showAddInstanceButton = false;
$scope.showDeleteInstanceButton = false;
var importCsvModal = '../ebodac/resources/partials/modals/import-csv.html';
var editSubjectModal = '../ebodac/resources/partials/modals/edit-subject.html';

$scope.customModals.push(importCsvModal);
$scope.customModals.push(editSubjectModal);

$scope.getMessageFromData = function(responseData) {
    var messageCode, messageParams;

    if (responseData && (typeof(responseData) === 'string')) {
        if (responseData.startsWith('key:')) {
            if (responseData.indexOf('params:') !== -1) {
               messageCode = responseData.split('\n')[0].split(':')[1];
               messageParams = responseData.split('\n')[1].split(':')[1].split(',');
            } else {
               messageCode = responseData.split(':')[1];
            }
        } else {
            messageCode = responseData;
        }
    }

    return $scope.msg(messageCode, messageParams);
};

$scope.importEntityInstances = function() {
    $('#importSubjectModal').modal('show');
};

$scope.importSubject = function () {
    blockUI();

    $('#importSubjectForm').ajaxSubmit({
        success: function () {
            $("#instancesTable").trigger('reloadGrid');
            $('#importSubjectForm').resetForm();
            $('#importSubjectModal').modal('hide');
            unblockUI();
        },
        error: function (response) {
            handleResponse('mds.error', 'mds.error.importCsv', response);
        }
    });
};

$scope.closeImportSubjectModal = function () {
    $('#importSubjectForm').resetForm();
    $('#importSubjectModal').modal('hide');
};

$scope.exportInstance = function() {
    var selectedFieldsName = [], url, sortColumn, sortDirection;

    url = "../ebodac/entities/" + $scope.selectedEntity.id + "/exportInstances";
    url = url + "?outputFormat=" + $scope.exportFormat;
    url = url + "&exportRecords=" + $scope.actualExportRecords;

   if ($scope.actualExportColumns === 'selected') {
       angular.forEach($scope.selectedFields, function(selectedField) {
           selectedFieldsName.push(selectedField.basic.name);
       });

       url = url + "&selectedFields=" + selectedFieldsName;
   }

   if ($scope.checkboxModel.exportWithOrder === true) {
       sortColumn = $('#instancesTable').getGridParam('sortname');
       sortDirection = $('#instancesTable').getGridParam('sortorder');

       url = url + "&sortColumn=" + sortColumn;
       url = url + "&sortDirection=" + sortDirection;
   }

   if ($scope.checkboxModel.exportWithLookup === true) {
       url = url + "&lookup=" + (($scope.selectedLookup) ? $scope.selectedLookup.lookupName : "");
       url = url + "&fields=" + JSON.stringify($scope.lookupBy);
   }

    $http.get(url)
    .success(function () {
        $('#exportInstanceForm').resetForm();
        $('#exportInstanceModal').modal('hide');
        window.location.replace(url);
    })
    .error(function (response) {
        handleResponse('mds.error', 'mds.error.exportData', response);
    });
};

$scope.saveCurrentRecord = function() {
    $scope.currentRecord.$save(function() {
        $scope.unselectInstance();
        unblockUI();
    }, angularHandler('mds.error', 'mds.error.cannotAddInstance'));
}

$scope.addEntityInstanceDefault = function () {
    blockUI();

    var visit = {};

    var values = $scope.currentRecord.fields;
    angular.forEach (values, function(value, key) {
        value.value = value.value === 'null' ? null : value.value;

        if (value.name === "changed") {
            value.value = true;
        }

        if (!$scope.isAutoGenerated(value)) {
            visit[value.name] = value.value;
        }
    });

    if ($scope.selectedEntity.name === "Visit" && $scope.selectedInstance !== undefined) {
        motechConfirm("ebodac.reenrollVisit.confirmMsg", "ebodac.reenrollVisit.confirmTitle",
            function (response) {
                if (!response) {
                    unblockUI();
                    return;
                }
                $http.post('../ebodac/reenrollSubject', visit)
                .success(function(response) {
                    motechAlert("ebodac.reenrollVisit.successmMsg", "ebodac.reenrollVisit.successTitle");
                    $scope.saveCurrentRecord();
                })
                .error(function(response) {
                    motechAlert("ebodac.reenrollVisit.errorMsg", "ebodac.reenrollVisit.errorTitle", $scope.getMessageFromData(response));
                    unblockUI();
                });
            });

    } else {
        $scope.saveCurrentRecord();
    }
};

$scope.addEntityInstance = function() {
    if ($scope.selectedEntity.name === "Participant") {
        var input = $("#phoneNumberForm");
        var fieldValue = input.val().replace(/ /g, '');
        input.val(fieldValue);
        input.trigger('input');

        $http.get('../ebodac/ebodac-config')
        .success(function(response){
            if(response.showWarnings) {
                $('#editSubjectModal').modal('show');
            } else {
                $scope.addEntityInstanceDefault();
            }
        })
        .error(function(response) {
            $('#editSubjectModal').modal('show');
        });
    } else {
        $scope.addEntityInstanceDefault();
    }
};

var isPhoneNumberForm = false;

$scope.loadEditValueFormDefault = $scope.loadEditValueForm;

$scope.loadEditValueForm = function (field) {
    if(field.name === 'phoneNumber') {
        isPhoneNumberForm = true;
        return '../ebodac/resources/partials/widgets/field-phone-number.html';
    }

    if(isPhoneNumberForm) {
        $("#phoneNumberForm").inputmask({ mask: "999 999 999[ 999]", greedy: false, autoUnmask: true });
        isPhoneNumberForm = false;
    }

    return $scope.loadEditValueFormDefault(field);
};