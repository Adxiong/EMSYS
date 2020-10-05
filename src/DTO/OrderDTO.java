package DTO;

import javax.print.DocFlavor;

public class OrderDTO {
    private String no;
    private String send;
    private String tel;
    private String desc;
    private int cno;

    public OrderDTO(String no, String send, String tel, String desc, int cno) {
        this.no = no;
        this.send = send;
        this.tel = tel;
        this.desc = desc;
        this.cno = cno;
    }

    public OrderDTO() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSend() {
        return send;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "no='" + no + '\'' +
                ", send='" + send + '\'' +
                ", tel='" + tel + '\'' +
                ", desc='" + desc + '\'' +
                ", cno=" + cno +
                '}';
    }
}

