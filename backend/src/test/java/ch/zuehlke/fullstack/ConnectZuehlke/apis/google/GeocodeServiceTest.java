package ch.zuehlke.fullstack.ConnectZuehlke.apis.google;

import com.google.maps.model.LatLng;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class GeocodeServiceTest {
    private GeocodeService testee = new GeocodeService();

    @Test
    public void test() {
        String address = "Bank Julius Bär & Co. AG, Zürich";
        Optional<LatLng> results = testee.getGeocodeOfAddress(address);
        assertTrue(results.isPresent());
    }

}