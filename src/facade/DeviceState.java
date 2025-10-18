package facade;

public class DeviceState {
    public String name;
    public String status;
    public String details;

    public DeviceState(String name, String status, String details) {
        this.name = name;
        this.status = status;
        this.details = details;
    }
    public String toJSON() {
        return String.format(
                "{\"name\":\"%s\",\"status\":\"%s\",\"details\":\"%s\"}",
                escape(name), escape(status), escape(details));
    }
    private static String escape(String s) {
        return s.replace("\"", "\\\"");
    }
}
