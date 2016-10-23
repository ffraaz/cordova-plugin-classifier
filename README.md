# cordova-plugin-classifier
>Make predictions using [LIBSVM](https://www.csie.ntu.edu.tw/~cjlin/libsvm/) on Android

## Support Vector Machines

>In machine learning, support vector machines are supervised learning models with associated learning algorithms that analyze data used for classification and regression analysis. Given a set of training examples, each marked for belonging to one of two categories, an SVM training algorithm builds a model that assigns new examples into one category or the other, making it a non-probabilistic binary linear classifier. [[Wikipedia](http://en.wikipedia.org/wiki/Support_vector_machine)]
>[![Wikipedia image](http://upload.wikimedia.org/wikipedia/commons/1/1b/Kernel_Machine.png)](http://en.wikipedia.org/wiki/File:Kernel_Machine.png)

## LIBSVM
> LIBSVM is an integrated software for support vector classification, (C-SVC, nu-SVC), regression (epsilon-SVR, nu-SVR) and distribution estimation (one-class SVM). It supports multi-class classification. [[official page](https://www.csie.ntu.edu.tw/~cjlin/libsvm/)]

# Installation

``` 
cordova plugin add cordova-plugin-classifier
```

# Usage

1. Train the model on your computer. You can find more information about the process [here](https://www.csie.ntu.edu.tw/~cjlin/libsvm/).

2. Rename the model file to `svm.model` and copy it to the SD Card of the phone 

3. Make sure that your app has permissions to access the SD Card

Then you can use the plugin in your Javascript code like this

```javascript
document.addEventListener("deviceready", function () {
	var featuresForPrediction = "1:-1.43 2:-1.15 3:1.08 4:-1.13 5:-1.99";

	predict(featuresForPrediction, success, error);

	function success(result) {
		console.log(result);
	}	

	function error(err) {
		console.log("some error has occured");
	}
}, false);
```

## Format features in the format required by LIBSVM

```javascript
var features = [-1.43, -1.15, 1.08, -1.13, -1.99];

console.log(formatLIBSVM(features)); // 1:-1.43 2:-1.15 3:1.08 4:-1.13 5:-1.99

function formatLIBSVM(features) {
	var featuresLIBSVM = "";

	for (var i = 0; i < features.length; i++) {
		featuresLIBSVM = featuresLIBSVM + (i+1).toString() + ":" + features[i] + " ";
	}

	return featuresLIBSVM;
}
```

# License
[MIT](https://github.com/ffraaz/cordova-plugin-classifier/blob/master/LICENSE)