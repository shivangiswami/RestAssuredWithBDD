package resources;

import pojoClasses.AddPlace;
import pojoClasses.location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace buildTestData(String name,String language, String address)
    {
        AddPlace p=new AddPlace();
        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setName(name);
        p.setWebsite("http://google.com");
        List<String> type=new ArrayList<>();
        type.add("shoe park");
        type.add("shop");
        p.setTypes(type);
        location l1=new location();
        l1.setLat(-38.383494);
        l1.setLng(33.427362);
        p.setLocation(l1);
        return p;
    }

    public String deletePlacedata(String place_id)
    {  return
        "{\n" +
                "    \"place_id\":\""+place_id+"\"   \t \t\n" +
                "}";
    }

}
