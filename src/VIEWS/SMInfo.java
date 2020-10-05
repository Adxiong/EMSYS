package VIEWS;

import DAO.CompanyDAO;
import DAO.OrderDAO;
import DTO.CompanyDTO;
import DTO.OrderDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SMInfo extends JInternalFrame implements ActionListener {
    DefaultTableModel model;
    JTextField jt_search;
    JTextField jt_cp;
    JTextField jt_sd;
    JTextField jt_tel;
    JTextField jt_desc;
    JMenuItem del;
    JMenuItem update;
    JTextField jt_no;
    JTable table;
    public SMInfo(int WIDHT,int HEIGHT){
        //        初始化设置
        this.setTitle("快递订单信息");
        this.setSize(WIDHT,HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);

        // 布局
        //弹出菜单
        JPopupMenu jpop = new JPopupMenu();
        del = new JMenuItem("删除");
        del.addActionListener(this::actionPerformed);
        update = new JMenuItem("选择修改");
        update.addActionListener(this::actionPerformed);
        jpop.add(del);
        jpop.add(update);
        // 时间 天气
        Calendar calendar =  Calendar.getInstance();
        JLabel time = new JLabel("时间："+calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH));
        time.setBounds(10,10,150,30);
        this.add(time);
        JLabel weather = new JLabel("黄石今日天气："+new getTodayWeather().getTodayWeather());
        weather.setBounds(170,10,150,30);
        this.add(weather);
        // 查询
        jt_search = new JTextField();
        JButton jb_search = new JButton("查询");
        jb_search.addActionListener(this::actionPerformed);
        jt_search.setBounds(10,60,200,30);
        jb_search.setBounds(220,60,100,30);
        this.add(jt_search);
        this.add(jb_search);

        // 快递信息表格
        String[] columnNames={"快递单号","快递公司","寄件人","联系电话","快递信息"};

        model=new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table=new JTable(model);
        List<OrderDTO> re = new OrderDAO().searchAllOrder();
        for(OrderDTO c : re){
            String cname = new CompanyDAO().searchAllcompanyNameByNo(c.getCno());
            Object[] data = {c.getNo() ,cname, c.getSend() ,c.getTel() , c.getDesc() };
            model.addRow(data);
        }

        JScrollPane tableSp=new JScrollPane(table);
        tableSp.setBounds(10,120,WIDHT-40, 220);
        this.add(tableSp);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.isMetaDown()){
                    int focusedRowIndex = table.rowAtPoint(e.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    //将表格所选项设为当前右键点击的行
                    table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    //弹出菜单
                    jpop.show(table, e.getX(), e.getY());

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // 修改
        JPanel jp_modify = new JPanel();
        jp_modify.setLayout(null);
        jp_modify.setBounds(10,370, WIDHT-40, 120);
        jp_modify.setBorder(new TitledBorder("修改快递信息"));
        JLabel jl_no = new JLabel("快递单号");
        JLabel jl_cp = new JLabel("快递公司");
        JLabel jl_sd = new JLabel("寄件人");
        JLabel jl_tel = new JLabel("联系电话");
        JLabel jl_desc = new JLabel("快递信息");
        jl_no.setBounds(10,10,60,30);
        jl_cp.setBounds(80,10,60,30);
        jl_sd.setBounds(150,10,60,30);
        jl_tel.setBounds(220,10,60,30);
        jl_desc.setBounds(290,10,60,30);
        jp_modify.add(jl_no);
        jp_modify.add(jl_cp);
        jp_modify.add(jl_sd);
        jp_modify.add(jl_tel);
        jp_modify.add(jl_desc);
        jt_no = new JTextField();
        jt_no.setEnabled(false);
        jt_cp = new JTextField();
        jt_cp.setEnabled(false);
        jt_sd = new JTextField();
        jl_sd.setEnabled(false);
        jt_tel = new JTextField();
        jt_desc = new JTextField();
        jt_no.setBounds(10,50,60,30);
        jt_cp.setBounds(80,50,60,30);
        jt_sd.setBounds(150,50,60,30);
        jt_tel.setBounds(220,50,60,30);
        jt_desc.setBounds(290,50,60,30);
        jp_modify.add(jt_no);
        jp_modify.add(jt_cp);
        jp_modify.add(jt_sd);
        jp_modify.add(jt_tel);
        jp_modify.add(jt_desc);
        JButton jb_modify = new JButton("修改");
        jb_modify.addActionListener(this::actionPerformed);
        jb_modify.setBounds(360,50,60,30);
        jp_modify.add(jb_modify);
        this.add(jp_modify);

        // 每日一句
        JPanel jp_sen = new JPanel();
        jp_sen.setLayout(null);
        jp_sen.setBounds(10,500, WIDHT-40, 80);
        jp_sen.setBorder(new TitledBorder("每日一句："));
        JLabel jsen = new JLabel(new getSentence().getSentence());
        jsen.setBounds(10,10,WIDHT-40,60);
        jp_sen.add(jsen);
        this.add(jp_sen);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 事件处理
        if(e.getActionCommand().equals("查询")){
            String no = jt_search.getText();
            if(no==null||no.length()==0){
                for(int i = model.getRowCount()-1 ; i>=0 ;i--){
                    model.removeRow(i);
                }
                List<OrderDTO> re = new OrderDAO().searchAllOrder();
                for(OrderDTO c : re){
                    Object[] data = {c.getNo() ,c.getCno(), c.getSend() ,c.getTel() , c.getDesc() };
                    model.addRow(data);
                }
            }else{
                OrderDTO res = new OrderDAO().searchOrder(no);
                if(res==null){
                    JOptionPane.showMessageDialog(SMInfo.this,"不存在该快递公司信息","查询错误",JOptionPane.ERROR_MESSAGE);
                }else {
                    for(int i = model.getRowCount()-1 ; i>=0 ;i--){
                        model.removeRow(i);
                    }
                    String cname = new CompanyDAO().searchAllcompanyNameByNo(res.getCno());
                    Object[] data = {res.getNo() ,cname, res.getSend() ,res.getTel() , res.getDesc() };
                    model.addRow(data);
                    jt_search.setText("");
                }
            }

        }
        if(e.getActionCommand().equals("修改")){
            String no = jt_no.getText();
            String tel = jt_tel.getText();
            String desc = jt_desc.getText();
            Boolean res = new OrderDAO().updateOrder(no,tel,desc);
            if(res){
                JOptionPane.showMessageDialog(SMInfo.this,"成功修改数据");
                for(int i = model.getRowCount()-1 ; i>=0 ;i--){
                    model.removeRow(i);
                }
                List<OrderDTO> re = new OrderDAO().searchAllOrder();
                for(OrderDTO c : re){
                    String cname = new CompanyDAO().searchAllcompanyNameByNo(c.getCno());
                    Object[] data = {c.getNo() ,cname, c.getSend() ,c.getTel() , c.getDesc() };
                    model.addRow(data);
                }
                jt_sd.setText("");
                jt_cp.setText("");
                jt_desc.setText("");
                jt_tel.setText("");
                jt_no.setText("");
            }else{
                JOptionPane.showMessageDialog(SMInfo.this,"数据修改失败");
            }
        }
        if(e.getActionCommand().equals("选择修改")){
            int index = table.getSelectedRow();
            String no = (String) model.getValueAt(index,0);
            String cp = (String) model.getValueAt(index,1);
            String sd = (String) model.getValueAt(index,2);
            String tel = (String) model.getValueAt(index,3);
            String desc = (String) model.getValueAt(index,4);
            jt_no.setText(no);
            jt_cp.setText(cp);
            jt_sd.setText(sd);
            jt_tel.setText(tel);
            jt_desc.setText(desc);
        }

        if(e.getActionCommand().equals("删除")){
            int index = table.getSelectedRow();
            String no = (String) model.getValueAt(index,0);
            Boolean res = new OrderDAO().delOrder(no);
            if(res){
                model.removeRow(index);
                JOptionPane.showMessageDialog(SMInfo.this,"成功删除数据");
            }else {
                JOptionPane.showMessageDialog(SMInfo.this,"删除失败");
            }
        }
    }
}
