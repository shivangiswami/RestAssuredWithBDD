package resources;

public enum  APIresources {

    addPlaceApi("/maps/api/place/add/json"),
    deletePlaceApi("/maps/api/place/delete/json"),
    getPlaceApi("/maps/api/place/get/json");
    private String resource;
    APIresources(String s) {
        this.resource=s;
    }
    public String getResource()
    {
        return resource;
    }
}
