package VIEWS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame implements ActionListener {
    private final static int HEIGHT = 700;
    private final static int WIDHT = 500;
    private final static String[][] menuArr={
        {"快递信息","发布快递信息","查看快递信息"},
        {"快递公司","添加快递公司","查看快递公司"},
        {"系统设置","修改用户密码"},
    };
    private static String name = "";
    private JDesktopPane desktopPane = new JDesktopPane();
    public MainPanel(String user){
        //        初始化设置
        this.name=user;
        Toolkit tool = Toolkit.getDefaultToolkit();
        this.setTitle("快递管理系统 by ax");
        this.setLocation((tool.getScreenSize().width-WIDHT)/2 , (tool.getScreenSize().height-HEIGHT)/2);
        this.setSize(WIDHT,HEIGHT);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setJMenuBar(createMenuBar());
        desktopPane.add(new SMInfo(WIDHT,HEIGHT));
        this.setContentPane(desktopPane);
    }


    //  生成导航菜单
    public JMenuBar createMenuBar(){
        JMenuBar menu = new JMenuBar();
        for(int i =0 ; i < menuArr.length; i++){
            JMenu m1 = new JMenu(menuArr[i][0]);
            for(int j =1; j < menuArr[i].length ; j++){
                JMenuItem m2 = new JMenuItem(menuArr[i][j]);
                m2.addActionListener(this::actionPerformed);
                m1.add(m2);
            }
            menu.add(m1);
        }
        return menu;
    }


    //  事件处理
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("发布快递信息")){
            this.desktopPane.removeAll();
            this.desktopPane.add(new addSMInfo(WIDHT,HEIGHT));
        }
        if(e.getActionCommand().equals("查看快递信息")){
            this.desktopPane.removeAll();
            this.desktopPane.add(new SMInfo(WIDHT,HEIGHT));
        }
        if(e.getActionCommand().equals("修改用户密码")){
            this.desktopPane.removeAll();
            this.desktopPane.add(new setpwd(MainPanel.this,name, WIDHT,HEIGHT));
        }
        if(e.getActionCommand().equals("添加快递公司")){
            this.desktopPane.removeAll();
            this.desktopPane.add(new addCompany(WIDHT,HEIGHT));
        }
        if(e.getActionCommand().equals("查看快递公司")){
            this.desktopPane.removeAll();
            this.desktopPane.add(new company(WIDHT,HEIGHT));
        }
    }
}
