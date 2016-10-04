window.predict = function(featureString, mode, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "Classifier", featureString, [featureString, mode]);
};