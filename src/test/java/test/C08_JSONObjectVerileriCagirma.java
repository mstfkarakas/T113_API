package test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class C08_JSONObjectVerileriCagirma {  // Bu class'da API ile calismadik, sadece JSON objesi olusturma ve cagirma yaptik

    /*
{
"firstName": "John",
"lastName": "doe",
"age": 26,
"address": {
    "streetAddress": "naist street",
    "city": "Nara",
    "postalCode": "630-0192"
},
"phoneNumbers": [
    {
        "type": "iPhone",
        "number": "0123-4567-8888"
    },
    {
        "type": "home",
        "number": "0123-4567-8910"
    }
]
}
 */
    @Test
    public void jsonObje01() {

        JSONObject cepTel = new JSONObject();
        JSONObject evTel = new JSONObject();

        cepTel.put("type", "iPhone");
        cepTel.put("number", "0123-4567-8888");

        evTel.put("type", "home");
        evTel.put("number", "0123-4567-8910");

        JSONArray phoneNumbers = new JSONArray(); // Json Array olusturup, index'e gore bilgileri depoluyoruz.
        phoneNumbers.put(0, cepTel);
        phoneNumbers.put(1, evTel);

        JSONObject address = new JSONObject();  // address Json objesi olsturup, adres bilgilerini buraya ekliyoruz.
        address.put("streetAddress", "naist street");
        address.put("city", "Nara");
        address.put("postalCode", "630-0192");

        JSONObject kisiBilgisi = new JSONObject();
        kisiBilgisi.put("firstName", "John");
        kisiBilgisi.put("lastName", "doe");
        kisiBilgisi.put("age", 26);
        kisiBilgisi.put("address", address);
        kisiBilgisi.put("phoneNumbers", phoneNumbers);

        System.out.println("kisiBilgisi = " + kisiBilgisi);
        //kisiBilgisi = {"firstName":"John","lastName":"doe",
        // "address":{"streetAddress":"naist street","city":"Nara","postalCode":"630-0192"},"age":26,
        // "phoneNumbers":[{"number":"0123-4567-8888","type":"iPhone"},{"number":"0123-4567-8910","type":"home"}]}


        System.out.println("Firstname = " + kisiBilgisi.get("firstName")); // tek bir bilgiyi cagiriyoruz.
        System.out.println("Lastname = " + kisiBilgisi.get("lastName"));
        System.out.println("Age = " + kisiBilgisi.get("age"));


        //JSON Object oldugunda ise boyle cagiriyoruz;
        System.out.println("Address = " + kisiBilgisi.getJSONObject("address")); // address bir Json objesi oldugundan .getJSONObject metodu lazim.
        // Address = {"streetAddress":"naist street","city":"Nara","postalCode":"630-0192"}
        System.out.println("City Name = " + kisiBilgisi.getJSONObject("address").get("city")); // City Name = Nara
        System.out.println("Postal Code = " + kisiBilgisi.getJSONObject("address").get("postalCode")); // Postal Code = 630-0192


        //JSON Array oldugunda ise boyle cagiriyoruz. Ilk index'teki telefon numarasini cagiralim;
        System.out.println("tel no: " + kisiBilgisi.getJSONArray("phoneNumbers").getJSONObject(0).get("number")); // tel no: 0123-4567-8888

        //JSON Array oldugunda ise boyle cagiriyoruz. Ilk index'teki telefon type'ini cagiralim;
        System.out.println("Phone type: " + kisiBilgisi.getJSONArray("phoneNumbers").getJSONObject(0).get("type")); // Phone type: iPhone


    }


}