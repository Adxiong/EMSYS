package VIEWS;

import DAO.UserDAO;
import DTO.UserDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private final int WEIGHT = 300;
    private final int HEIGHT = 300;
    public  Login(){
        //        初始化设置
        Toolkit tool = Toolkit.getDefaultToolkit();
        this.setTitle("登录界面");
        this.setLocation((tool.getScreenSize().width-WEIGHT)/2 , (tool.getScreenSize().height-HEIGHT)/2);
        this.setSize(WEIGHT,HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        //      布局
        JLabel jl_title = new JLabel("快递管理系统",SwingConstants.CENTER);
        jl_title.setForeground(Color.blue);
        jl_title.setFont(new Font("楷体",Font.BOLD,30));
        JLabel jl_user = new JLabel("账号：");
        JLabel jl_pwd = new JLabel("密码");
        JTextField user = new JTextField();
        JPasswordField pwd = new JPasswordField();
        JButton submit = new JButton("登录");
        jl_title.setBounds(40,10,210,70);
        jl_user.setBounds(40,80,50,30);
        user.setBounds(100,80,150,30);
        jl_pwd.setBounds(40,130,50,30);
        pwd.setBounds(100,130,150,30);
        submit.setBounds(40,180,210,30);
        this.add(jl_title);
        this.add(jl_user);
        this.add(user);
        this.add(jl_pwd);
        this.add(pwd);
        this.add(submit);

        //      事件处理
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str_user = user.getText();
                String str_pwd = pwd.getText();
                //  判断是否填写用户名和密码
                if(str_user.isEmpty()&&str_pwd.isEmpty()){
                    JOptionPane.showMessageDialog(Login.this,"账号或密码为空","未填写信息",JOptionPane.ERROR_MESSAGE);
                }else{
                    //  通过mgr_dao的checkUser 来判断用户名和密码是否正确
                    UserDTO mgr =  new UserDAO().checkLogin(str_user ,str_pwd);
                    if(mgr!=null){
                        JOptionPane.showMessageDialog(Login.this,"登录成功","点击跳转",JOptionPane.INFORMATION_MESSAGE);
                        //  生成主窗体 并关闭当前窗体
                        Login.this.dispose();
                        new MainPanel(str_user).setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(Login.this, "账号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                        user.setText("");
                        pwd.setText("");
                    }

                }
            }
        });
    }
}
