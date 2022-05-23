import apiEngine.model.Author;
import apiEngine.model.SOAP;
import apiEngine.model.request.AddNewAuthorRequest;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;


public class Test {
    private static final String BASE_URL = "http://localhost:8080/";
    String responseStatus;
    SOAP soap = new SOAP();
    public String getListOfAuthors() throws Exception {
      HttpURLConnection connection= soap.getHttpURLConnection(BASE_URL, "libraryService/LibraryPort/getAuthorsRequest", false, "GET");
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\\\"xmlns:lib=\"libraryService\"> <soapenv:Header/> <soapenv:Body> <lib:getAuthorsRequest> <lib:search> <lib:orderType>asc</lib:orderType> <lib:page>1</lib:page> <lib:pagination>true</lib:pagination> <lib:size>10</lib:size> </lib:search> </lib:getAuthorsRequest> </soapenv:Body> </soapenv:Envelope>";
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(xml);
                wr.flush();
                wr.close();
                responseStatus = connection.getResponseMessage();
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String input= String.valueOf(in);
                in.close();
                return input;
    }

    public void listIsAvaible() throws Exception {
        Assert.assertEquals("200", responseStatus);
    }

    public void addAuthorInList() throws Exception {
        AddNewAuthorRequest addNewAuthorRequest = new AddNewAuthorRequest(1029, "Ayn", "Rand", "American", "1905-01-20", "Russian", "Saint-Peterburg", "American writer of Jewish descent");
        HttpURLConnection connection= soap.getHttpURLConnection(BASE_URL, "libraryService/LibraryPort/postAuthorRequest", false, "POST");
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\\\"xmlns:lib=\"libraryService\"> <soapenv:Header/> <soapenv:Body> <lib:getAuthorRequest> <lib:search> <lib:orderType>asc</lib:orderType> <lib:author>"+addNewAuthorRequest+"</lib:author> <lib:pagination>true</lib:pagination> <lib:size>10</lib:size> </lib:search> </lib:getAuthorsRequest> </soapenv:Body> </soapenv:Envelope>";
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(xml);
        wr.flush();
        wr.close();
        responseStatus = connection.getResponseMessage();


    }
    public void authorIsAdded() throws Exception {
        Assert.assertEquals("201", responseStatus);
        HttpURLConnection connection= soap.getHttpURLConnection(BASE_URL, "libraryService/LibraryPort/getAuthorRequest", false, "GET");
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\\\"xmlns:lib=\"libraryService\"> <soapenv:Header/> <soapenv:Body> <lib:getAuthorRequest> <lib:search> <lib:orderType>asc</lib:orderType> <lib:author>1029</lib:author> <lib:pagination>true</lib:pagination> <lib:size>10</lib:size> </lib:search> </lib:getAuthorsRequest> </soapenv:Body> </soapenv:Envelope>";
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(xml);
        Assert.assertEquals("200", responseStatus);
    }

    public void removeAuthorFromList() throws Exception {
        HttpURLConnection connection= soap.getHttpURLConnection(BASE_URL, "libraryService/LibraryPort/deleteAuthorRequest", false, "GET");
        String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\\\"xmlns:lib=\"libraryService\"> <soapenv:Header/> <soapenv:Body> <lib:deleteAuthorRequest> <lib:search> <lib:orderType>asc</lib:orderType> <lib:authorId>1029</lib:authorId> <lib:pagination>true</lib:pagination> <lib:size>10</lib:size> </lib:search> </lib:getAuthorsRequest> </soapenv:Body> </soapenv:Envelope>";
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(xml);
        Assert.assertEquals("200", responseStatus);
    }

    @org.junit.Test
    public void MainTest() throws Exception {
        listIsAvaible();
        addAuthorInList();
        authorIsAdded();
        addAuthorInList();
        removeAuthorFromList();
    }
}
