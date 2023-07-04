package testData;

import org.json.JSONObject;

public class TestDataHerokuapp {

    public int successfulStatusCode = 200;


    public JSONObject bookingdatesOlusturJSON() {

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-06-01");
        bookingdates.put("checkout", "2021-06-11");

        return bookingdates;
    }

    public JSONObject bookingOlusturJSON() {

        JSONObject booking = new JSONObject();
        booking.put("firstname", "Ali");
        booking.put("lastname", "Bak");
        booking.put("totalprice", 500);
        booking.put("depositpaid", false);
        booking.put("additionalneeds", "wi-fi");
        booking.put("bookingdates", bookingdatesOlusturJSON()); // bookingdates yerine mecburen bookingdatesOlusturJSON() cagirdik. Scope problemi.

        return booking;
    }

    public JSONObject expectedBodyOlusturJSON(){

        JSONObject expData = new JSONObject();
        expData.put("bookingid", 24);
        expData.put("booking", bookingOlusturJSON());


        return expData;
    }
}
