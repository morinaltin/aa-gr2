import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class Main {

    static class Order {
        String orderId;
        String email;
        String address;
        LocalDateTime timestamp;

        Order(String orderId, String email, String address, String timestamp) {
            this.orderId = orderId;
            this.email = email;
            this.address = address;
            this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
    }

    static class OrderInfo {
        String orderId;
        String email;
        LocalDateTime timestamp;

        OrderInfo(String orderId, String email, LocalDateTime timestamp) {
            this.orderId = orderId;
            this.email = email;
            this.timestamp = timestamp;
        }
    }

    private static final Pattern NON_ALNUM = Pattern.compile("[^a-z0-9\\s]");
    private static final Pattern MULTI_SPACES = Pattern.compile("\\s+");
    private static final long TIME_WINDOW_HOURS = 24;

    public static String normalizeAddress(String address) {
        if (address == null) return "";
        String s = address.toLowerCase();
        s = NON_ALNUM.matcher(s).replaceAll(" ");
        s = MULTI_SPACES.matcher(s).replaceAll(" ").trim();
        s = s.replaceAll("\\b(n y c)\\b", "new york city");
        s = s.replaceAll("\\bnyc\\b", "new york city");
        s = s.replaceAll("\\bavenue\\b", "ave");
        s = s.replaceAll("\\bstreet\\b", "st");
        s = s.replaceAll("\\broad\\b", "rd");
        s = s.replaceAll("\\bdrive\\b", "dr");
        s = s.replaceAll("\\bapartment\\b", "apt");
        s = s.replaceAll("\\bunit\\b", "apt");
        s = s.replaceAll("\\bsuite\\b", "apt");
        s = s.replaceAll("\\bapt\\b\\s*-?\\s*(\\d+)([a-z]*)", "apt$1$2");
        s = s.replaceAll("\\bapt\\s+(\\d+)([a-z]*)\\b", "apt$1$2");
        s = s.replaceAll("\\b(\\d+)\\s+apt\\b", "$1 apt");
        return s;
    }

    public static List<String> detectFraud(List<Order> orders) {
        Map<String, List<OrderInfo>> recentOrders = new HashMap<>();
        List<String> alerts = new ArrayList<>();

        for (Order o : orders) {
            String key = normalizeAddress(o.address);
            List<OrderInfo> orderList = recentOrders.getOrDefault(key, new ArrayList<>());
            orderList.removeIf(prev -> Duration.between(prev.timestamp, o.timestamp).toHours() > TIME_WINDOW_HOURS);
            for (OrderInfo prev : orderList) {
                if (!o.email.equalsIgnoreCase(prev.email) &&
                        Math.abs(Duration.between(prev.timestamp, o.timestamp).toHours()) <= TIME_WINDOW_HOURS) {
                    alerts.add("FRAUD ALERT: " + o.orderId +
                            " - Matches previous order " + prev.orderId +
                            " (different email, same address)");
                    break;
                }
            }
            orderList.add(new OrderInfo(o.orderId, o.email, o.timestamp));
            recentOrders.put(key, orderList);
        }

        return alerts;
    }

    public static void processOrdersInRealTime(List<Order> stream) {
        List<String> alerts = detectFraud(stream);
        if (alerts.isEmpty()) {
            System.out.println("No fraud detected.");
        } else {
            System.out.println("FRAUD ALERTS:");
            for (String a : alerts) System.out.println(a);
        }
    }

    public static void main(String[] args) {
        List<Order> orders = Arrays.asList(
                new Order("ORD001", "john@email.com", "123 Main Street, Apt 4B, NYC", "2025-10-23 10:00"),
                new Order("ORD002", "jane@email.com", "456 Oak Ave, Boston", "2025-10-23 10:15"),
                new Order("ORD003", "bob@email.com", "123 Main St, Apartment 4B, NYC", "2025-10-23 10:30"),
                new Order("ORD004", "alice@email.com", "789 Pine Road, Seattle", "2025-10-23 11:00"),
                new Order("ORD005", "charlie@email.com", "123 Main Street Apt 4-B New York City", "2025-10-23 12:00")
        );
        processOrdersInRealTime(orders);
    }
}
