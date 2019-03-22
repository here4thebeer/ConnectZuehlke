package ch.zuehlke.fullstack.ConnectZuehlke.apis.google;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeocodeService {
    private static final Logger log = LoggerFactory.getLogger(GeocodeService.class);

    public Optional<LatLng> getGeocodeOfAddress(String address) {
        String apiKey = "AIzaSyBrgp24CvFV3M0PZGByVDVEG0qn56k8Y-g";

        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(apiKey)
                    .build();
            GeocodingResult[] results = GeocodingApi.geocode(context,
                    address).await();
            if (results.length > 0) {
                return Optional.of(results[0].geometry.location);
            }
        } catch (Exception e) {
            log.error("could not get location of address '" + address + "'", e);
        }
        return Optional.empty();
    }
}
