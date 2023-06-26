package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data notasyonu Getter, Setter ve toString'leri olusturur
@NoArgsConstructor // @NoArgsConstructor notasyonu Parametresiz Constructor olusturur
@AllArgsConstructor // @AllArgsConstructor notasyonu tum argumanlari iceren parametreli Constructor'i olusturur

public class BookingdatesPOJO {

    /*
                        "bookingdates" : {
                                 "checkin" : "2021-06-01",
                                 "checkout" : "2021-06-10"
                                          },
     */

    private String checkin;
    private String checkout;


}
