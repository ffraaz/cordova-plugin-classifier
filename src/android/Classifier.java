
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
	
	svm_predict activities, directions;
	String predictClass = "";
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

		activities = new svm_predict("activities.train.model");
		directions = new svm_predict("directions.train.model");
	}
	 
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

     	this.classify(args.getString(0), args.getString(1), callbackContext);

		return true;
	}

	private void classify(String featureString, String mode, CallbackContext callbackContext) {

		//making the prediction and sending the result back to Javascript
        String instance = featureString + "\n";


        //decide which model to use (activity classifier vs direction classifier)
        if(mode.equals("activity")){
		   predictClass = "" + activities.predict(instance);
		}else if(mode.equals("direction")){
		   predictClass = "" + directions.predict(instance);
		}else {
		   Log.v(TAG,mode + " #svm_predict \n");
		}
		
        callbackContext.success(predictClass);
	}
}