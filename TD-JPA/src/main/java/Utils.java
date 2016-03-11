import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

public class Utils {
    final static GeoApiContext MY_CONTEXT_GEOAPI = new GeoApiContext().setApiKey("AIzaSyAhf3JleYpal9S-xouJYH8lf7Dvz5Y2Nko");

    public static LatLng getLatLng(String adress) {
        try {
            GeocodingResult[] results = GeocodingApi.geocode(MY_CONTEXT_GEOAPI, adress).await();
            return results[0].geometry.location;
        } catch (Exception e) {
            return null;
        }
    }
}
