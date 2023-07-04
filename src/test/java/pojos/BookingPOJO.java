package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data notasyonu Getter, Setter ve toString'leri olusturur
@NoArgsConstructor // @NoArgsConstructor notasyonu Parametresiz Constructor olusturur
@AllArgsConstructor // @AllArgsConstructor notasyonu tum argumanlari iceren parametreli Constructor'i olusturur
public class BookingPOJO {

    /*
     {
    	                "firstname" : "Ali",
    	                "lastname" : “Bak",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }
     */

    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;  // boolean ve Booolean Getter ve Setter'larda "is" farki ile doner.
    private BookingDatesPOJO bookingdates;  // Kendi olusturdugumuz class'lar da data type olarak kullanilabilir.
    private String additionalneeds;



}
