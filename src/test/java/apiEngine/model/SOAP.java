package apiEngine.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SOAP {

    public HttpURLConnection getHttpURLConnection(String uri, String soapAction, boolean soap12, String method) throws Exception {
            URL endPointURL = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) endPointURL.openConnection();
            connection.setDoOutput(true);
           // connection.setDoInput(true);
            connection.setRequestMethod(method);
            connection.addRequestProperty("SOAPAction", soapAction);
        if (soap12) {
                connection.setRequestProperty("Content-Type", "application/soap+xml");
        } else {
                connection.setRequestProperty("Content-Type", "text/xml");
        }
                connection.connect();
        return connection;
    }
//        public static void main(String[] args) {
//            try {
//                String url = "http://localhost:8080/ws/libraryService/LibraryPort/getAuthorsRequest";
//                URL obj = new URL(url);
//                HttpURLConnection con = getHttpURLConnection(String.valueOf(obj), "", false, "GET");
//                String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\\\"xmlns:lib=\"libraryService\"> <soapenv:Header/> <soapenv:Body> <lib:getAuthorsRequest> <lib:search> <lib:orderType>asc</lib:orderType> <lib:page>1</lib:page> <lib:pagination>true</lib:pagination> <lib:size>10</lib:size> </lib:search> </lib:getAuthorsRequest> </soapenv:Body> </soapenv:Envelope>";
//                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//                wr.writeBytes(xml);
//                wr.flush();
//                wr.close();
//                String responseStatus = con.getResponseMessage();
//                System.out.println(responseStatus);
//                BufferedReader in = new BufferedReader(new InputStreamReader(
//                        con.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//                System.out.println("response:" + response.toString());
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
}
