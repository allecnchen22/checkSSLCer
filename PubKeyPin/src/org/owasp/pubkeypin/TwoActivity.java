package org.owasp.pubkeypin;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpsTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class TwoActivity extends Activity {

	Button b1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);
		
		final Thread t=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				WebServiceCallExample();
				
				Message msg=new Message();
				msg.what=1;
				
				m_handler.sendMessage(msg);
			}
		});
		
	
		t.start();
	}
	
	private Handler m_handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
		
		
	};
	
	

public void WebServiceCallExample()
    {
        String NAMESPACE = "https://webservice.aspsms.com";
        String METHOD_NAME = "VerifyTokenResponse";
        String SOAP_ACTION = "https://webservice.aspsms.com/aspsmsx2";
        String URL = "https://webservice.aspsms.com/aspsmsx2.asmx?WSDL";
        
        SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
        
  
      
        
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(Request);
        
        //envelope.addMapping(NAMESPACE, "Category",new Category().getClass());
        HttpsTransportSE androidHttpTransport = new HttpsTransportSE("webservice.aspsms.com", 443, "", 20000);;
        /*
         * Call the web service and retrieve result ... how luvly <3
         * 
         * */
        try
        {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject response = (SoapObject)envelope.getResponse();
          
            
            Log.d("AAA",response.toString());
        }
        catch(Exception e)
        {
        	  Log.d("AAA",e.toString());
            e.printStackTrace();
        }
    }

}
