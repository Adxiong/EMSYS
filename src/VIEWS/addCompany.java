package VIEWS;

import DAO.CompanyDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addCompany extends JInternalFrame {
    public addCompany(int WIDHT,int HEIGHT){
        this.setTitle("添加快递公司");
        this.setSize(WIDHT,HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

        //布局
        JLabel jl_name = new JLabel("快递公司");
        JLabel jl_no = new JLabel("公司编号");
        JTextField jt_name = new JTextField();
        JTextField jt_no = new JTextField();
        JButton submit = new JButton("提交");
        jl_name.setBounds(10,50,60,30);
        jt_name.setBounds(70,50,100,30);
        jl_no.setBounds(10,90,60,30);
        jt_no.setBounds(70,90,100,30);
        submit.setBounds(10,200,160,30);
        this.add(jl_name);
        this.add(jl_no);
        this.add(jt_name);
        this.add(jt_no);
        this.add(submit);

        //事件
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jt_name.getText();
                String code = jt_no.getText();
                Boolean res = new CompanyDAO().addCompany(name,code);
                if(res==null){
                    JOptionPane.showMessageDialog(addCompany.this,"添加失败","失败",JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(addCompany.this,"添加成功","成功",JOptionPane.INFORMATION_MESSAGE);
                    jt_name.setText("");
                    jt_no.setText("");
                }
            }
        });
    }
}
