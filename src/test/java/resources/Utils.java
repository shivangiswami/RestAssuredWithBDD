package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;


public class Utils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
        if(req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalProperties("BaseUrl")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();

        }
        return req;
    }

    public static String getGlobalProperties(String key) throws IOException {
        Properties properties=new Properties();
        FileInputStream fis=new FileInputStream("C:\\RestAssuerWithBDD\\src\\test\\java\\resources\\global.properties");
        properties.load(fis);
        System.out.println(properties.getProperty(key));
        return properties.getProperty(key);

    }

    public static String jsonClass(String response,String key)
    {
        JsonPath js=new JsonPath(response);
        return js.get(key).toString();
    }

}
