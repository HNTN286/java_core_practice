import java.util.regex.Pattern;

class Owner {
    private String cmnd;
    private String fullName;
    private String email;

    private static final Pattern EMAIL =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$");

    public Owner(String cmnd, String fullName, String email) {
        if (cmnd == null || !cmnd.matches("\\d{12}"))
            throw new IllegalArgumentException("Số CMND phải gồm đúng 12 chữ số");
        if (fullName == null || fullName.trim().isEmpty())
            throw new IllegalArgumentException("Họ tên không được rỗng");
        if (email == null || !EMAIL.matcher(email).matches())
            throw new IllegalArgumentException("Email không đúng định dạng");
        this.cmnd = cmnd;
        this.fullName = fullName.trim();
        this.email = email;
    }

    public String getCmnd() { return cmnd; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("Chủ xe[CMND: %s, Họ tên: %s, Email: %s]", cmnd, fullName, email);
    }
}
