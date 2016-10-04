import libsvm.*;
import java.io.*;
import java.util.*;
import android.os.Environment;

class svm_predict {

	private svm_model model;

	public svm_predict(String modelName) 
	{
		try 
		{
			File sdcard = Environment.getExternalStorageDirectory();
			FileReader modelReader = new FileReader(sdcard + "/" + modelName);
			BufferedReader bufferedModel = new BufferedReader(modelReader);

			this.model = svm.svm_load_model(bufferedModel);

			if (this.model == null)
			{
				System.err.print("can't open model file " + modelName + "#svm_predict \n");
				System.exit(1);
			}
			
		} 
		catch(FileNotFoundException e) 
		{
			System.err.print("FileNotFoundException hat occured #svm_predict\n");
		}
		catch(IOException e) 
		{
			System.err.print("IOException hat occured #svm_predict\n");
		}
		catch(ArrayIndexOutOfBoundsException e) 
		{
			System.err.print("ArrayIndexOutOfBoundsException hat occured #svm_predict\n");
		}
	}

	public double predict(String instance)
	{

		StringTokenizer st = new StringTokenizer(instance," \t\n\r\f:");

		int m = st.countTokens()/2;
		svm_node[] x = new svm_node[m];

		for(int j=0;j<m;j++)
		{
			x[j] = new svm_node();
			x[j].index = atoi(st.nextToken());
			x[j].value = atof(st.nextToken());
		}

		return svm.svm_predict(this.model,x);

	}

	private double atof(String s)
	{
		return Double.valueOf(s).doubleValue();
	}

	private int atoi(String s)
	{
		return Integer.parseInt(s);
	}
}
