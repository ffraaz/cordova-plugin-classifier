
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import libsvm.*;
import java.io.*;
import java.util.*;
import android.os.Environment;


public class Classifier extends CordovaPlugin {

	public static final String TAG = "Classifier";
	svm_predict predictor;
	String predictedClass = "";
	/**
	* Constructor.
	*/
	public Classifier() {}
	 
	/**
	* Sets the context of the Command. This can then be used to do things like
	* get file paths associated with the Activity.
	*
	* @param cordova The context of the main Activity.
	* @param webView The CordovaWebView Cordova is running in.
	*/
	 

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		predictor = new svm_predict("svm.model");
	}
	 
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

     	this.classify(args.getString(0), callbackContext);

		return true;
	}

	private void classify(String featureString, CallbackContext callbackContext) {

		//making the prediction and sending the result back to Javascript
        String instance = featureString + "\n";

		predictedClass = "" + predictor.predict(instance);

        callbackContext.success(predictedClass);
	}
}