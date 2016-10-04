window.predict = function(featureString, successCallback, errorCallback) {
    cordova.exec(successCallback, errorCallback, "Classifier", featureString, [featureString]);
};