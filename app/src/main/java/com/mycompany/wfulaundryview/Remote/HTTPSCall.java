package com.mycompany.wfulaundryview.Remote;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.InputStream;


public class HTTPSCall {
	
	private static final String HTTP_SCHEME_NAME = "http";
	private String urlValue;
	
	
	public HTTPSCall(String urlValue) {
		this.urlValue = urlValue;
	}


    public InputStream doRemoteCall() throws Exception {

        HttpClient httpsClient = makeHTTPSClient();
        HttpGet httpsGet = new HttpGet(urlValue);
        HttpResponse response;
        String result = null;
        response = httpsClient.execute(httpsGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            return instream;
            // Closing the input stream triggers connection release
        } else
        {
            return null;
        }

    }


	public String doRemoteCall2() throws Exception {

		HttpClient httpsClient = makeHTTPSClient();
		HttpGet httpsGet = new HttpGet(urlValue);
		HttpResponse response;
		String result = null;
		    response = httpsClient.execute(httpsGet);
		    HttpEntity entity = response.getEntity();
		    if (entity != null) {
		        InputStream instream = entity.getContent();
		        result = InputStreamConverter.convertToString(instream);
		        // Closing the input stream triggers connection release
		        instream.close();
		    }
		return result;
	}

	private HttpClient makeHTTPSClient() {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme(HTTP_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));

		HttpParams params = new BasicHttpParams();
		SingleClientConnManager mgr = new SingleClientConnManager(params, schemeRegistry);

		return new DefaultHttpClient(mgr, params);
	}
}
