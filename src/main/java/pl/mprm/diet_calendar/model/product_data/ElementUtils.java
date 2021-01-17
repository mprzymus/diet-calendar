package pl.mprm.diet_calendar.model.product_data;

public class ElementUtils {

    public static String generateElementString(String name, Double amount) {
        name = name == null  ? "" : name;
        var amountAsString = amount == null ? "" : amount.toString();
        if (name.isEmpty() && amountAsString.isEmpty()) {
            return "";
        } else {
            return name + ": " + amountAsString;
        }
    }
}
