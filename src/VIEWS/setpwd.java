package VIEWS;

import DAO.UserDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class setpwd extends JInternalFrame {
    public setpwd(JFrame m , String user , int WIDHT , int HEIGHT){
        this.setTitle("系统设置");
        this.setSize(WIDHT,HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

        //布局
        JLabel jl_user = new JLabel("用户名");
        JTextField jt_user = new JTextField(user);
        jt_user.setEnabled(false);
        JLabel jl_pwd = new JLabel("新密码");
        JPasswordField jt_pwd = new JPasswordField();
        JButton btn = new JButton("修改密码");
        jl_user.setBounds(50,50,60,30);
        jt_user.setBounds(120,50,100,30);
        jl_pwd.setBounds(50,100,60,30);
        jt_pwd.setBounds(120,100,100,30);
        btn.setBounds(50,140,100,30);
        this.add(jl_user);
        this.add(jt_user);
        this.add(jl_pwd);
        this.add(jt_pwd);
        this.add(btn);

        // 事件处理
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPwd = jt_pwd.getText();
                if(newPwd==null){
                    JOptionPane.showMessageDialog(setpwd.this,"请输入新密码");
                }
                Boolean res = new UserDAO().setNewPwd(user , newPwd);
                if(res){
                    JOptionPane.showMessageDialog(setpwd.this,"成功修改,将关闭程序,请重新登陆");
                    m.dispose();
                    new Login().setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(setpwd.this,"用户名不存在");
                }
            }
        });
    }
}
