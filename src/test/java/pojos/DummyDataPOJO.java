package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data notasyonu Getter, Setter ve toString'leri olusturur
@NoArgsConstructor // @NoArgsConstructor notasyonu Parametresiz Constructor olusturur
@AllArgsConstructor // @AllArgsConstructor notasyonu tum argumanlari iceren parametreli Constructor'i olusturur

public class DummyDataPOJO {

    /*
    "data":{
            "id":3,
            "employee_name":"Ashton Cox",
            "employee_salary":86000,
            "employee_age":66,
            "profile_image":""
            },
     */

    private int id;
    private String employee_name;
    private int employee_salary;
    private int employee_age;
    private String profile_image;

}
