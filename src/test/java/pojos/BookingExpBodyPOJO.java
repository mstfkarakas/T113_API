package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data notasyonu Getter, Setter ve toString'leri olusturur
@NoArgsConstructor // @NoArgsConstructor notasyonu Parametresiz Constructor olusturur
@AllArgsConstructor // @AllArgsConstructor notasyonu tum argumanlari iceren parametreli Constructor'i olusturur

public class BookingExpBodyPOJO {

    /*
    {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ali",
                        "lastname":"Bak",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }

     */

    private int bookingid;
    private BookingPOJO booking;




}
