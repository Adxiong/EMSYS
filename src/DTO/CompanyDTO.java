package DTO;

public class CompanyDTO {
    private int no;
    private String name;
    private String code ;

    public CompanyDTO(int no, String name, String code) {
        this.no = no;
        this.name = name;
        this.code = code;
    }

    public CompanyDTO() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
