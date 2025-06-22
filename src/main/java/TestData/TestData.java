package TestData;

import java.util.HashMap;
import java.util.Map;

public class TestData {

    public static Map<String, String> getUseData_Valid() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "test.user@example.com");
        data.put("firstName", "Test");
        data.put("lastName", "User");
        data.put("address", "123 someAddress st");
        data.put("city", "Testville");
        data.put("province", "");
        data.put("zipCode", "90001");
        data.put("country", "Egypt");
        data.put("phoneNumber", "1234567890");
        return data;
    }

    public static Map<String, String> getUseData_InvalidPhoneNumber() {
        Map<String, String> data = new HashMap<>();
        data.put("email", "test.user@example.com");
        data.put("firstName", "Test");
        data.put("lastName", "User");
        data.put("address", "123 someAddress st");
        data.put("city", "Testville");
        data.put("province", "");
        data.put("zipCode", "90001");
        data.put("country", "Egypt");
        data.put("phoneNumber", "1234567890");
        return data;
    }

    public static String successMessage = "Thank you for your purchase!";

    public static int firstItemIndex = 0;
    public static int secondItemIndex = 1;

    public static String addToCartMessageKeywordSuccess = "You added";
    public static String addToCartMessageKeywordFailure = "not available";
}
