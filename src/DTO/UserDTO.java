package DTO;

public class UserDTO {
    private int no;
    private String user;
    private String pwd;

    public UserDTO(int no, String user, String pwd) {
        this.no = no;
        this.user = user;
        this.pwd = pwd;
    }

    public UserDTO() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "no=" + no +
                ", user='" + user + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
