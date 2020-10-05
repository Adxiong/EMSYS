package VIEWS;

import DAO.CompanyDAO;
import DAO.OrderDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class addSMInfo extends JInternalFrame {
    public addSMInfo(int WIDHT,int HEIGHT){
        //        初始化设置
        this.setTitle("入库");
        this.setSize(WIDHT,HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        List<String> arr = new CompanyDAO().searchAllcompanyName();
        //布局
        JLabel jl_cp = new JLabel("快递公司");
        JComboBox<String> jt_cp = new JComboBox(arr.toArray());
        JLabel jl_send = new JLabel("寄件人");
        JLabel jl_tel = new JLabel("联系电话");
        JLabel jl_desc = new JLabel("描述信息");
        JTextField jt_send = new JTextField();
        JTextField jt_tel = new JTextField();
        JTextArea jt_desc = new JTextArea();
        JButton submit = new JButton("提交");
        jl_cp.setBounds(10,50,60,30);
        jt_cp.setBounds(70,50,100,30);
        jl_send.setBounds(10,90,60,30);
        jt_send.setBounds(70,90,100,30);
        jl_tel.setBounds(10,130,60,30);
        jt_tel.setBounds(70,130,100,30);
        jl_desc.setBounds(10,170,60,30);
        jt_desc.setBounds(70,170,100,60);
        submit.setBounds(10,250,160,30);
        this.add(jl_cp);
        this.add(jt_cp);
        this.add(jl_send);
        this.add(jt_send);
        this.add(jl_tel);
        this.add(jt_tel);
        this.add(jl_desc);
        this.add(jt_desc);
        this.add(submit);

        //事件
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cp = jt_cp.getSelectedItem().toString();
                String send = jt_send.getText();
                String tel = jt_tel.getText();
                String desc = jt_desc.getText();
                Boolean res = new OrderDAO().addOrder(cp,send,tel,desc);
                if(res==null){
                    JOptionPane.showMessageDialog(addSMInfo.this,"添加失败","失败",JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(addSMInfo.this,"添加成功","成功",JOptionPane.INFORMATION_MESSAGE);
                    jt_send.setText("");
                    jt_tel.setText("");
                    jt_desc.setText("");
                }
            }
        });
    }
}
