package VIEWS;

import DAO.CompanyDAO;
import DTO.CompanyDTO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class company extends JInternalFrame implements ActionListener {
    JTextField jt_search;
    JTextField jt_name;
    JTextField jt_no;
    JTable table;
    DefaultTableModel model;
    JMenuItem del;
    JMenuItem update;
    public company(int WIDHT ,int HEIGHT){
        this.setTitle("快递公司信息");
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

        // 查询
        jt_search = new JTextField();
        JButton jb_search = new JButton("查询");
        jb_search.addActionListener(this::actionPerformed);
        jt_search.setBounds(10,60,200,30);
        jb_search.setBounds(220,60,100,30);
        this.add(jt_search);
        this.add(jb_search);

        // 快递信息表格
        String[] columnNames={"快递公司","公司编号"};
        model=new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table=new JTable(model);
        List<CompanyDTO> res = new CompanyDAO().searchAllCompany();
        for(CompanyDTO c : res){
            Object[] data = {c.getName(),c.getCode()};
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
        jp_modify.setBorder(new TitledBorder("修改快递公司信息"));
        JLabel jl_name = new JLabel("快递公司");
        JLabel jl_no = new JLabel("公司编号");

        jl_name.setBounds(10,30,60,30);
        jl_no.setBounds(80,30,60,30);
        jp_modify.add(jl_name);
        jp_modify.add(jl_no);
        jt_name = new JTextField();
        jt_name.setEnabled(false);
        jt_no = new JTextField();

        jt_name.setBounds(10,70,60,30);
        jt_no.setBounds(80,70,60,30);

        jp_modify.add(jt_name);
        jp_modify.add(jt_no);

        JButton jb_modify = new JButton("修改");
        jb_modify.addActionListener(this::actionPerformed);
        jb_modify.setBounds(360,70,60,30);
        jp_modify.add(jb_modify);
        this.add(jp_modify);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 事件处理
        if(e.getActionCommand().equals("查询")){
            String name = jt_search.getText();
            if(name==null||name.length()==0){
                for(int i = model.getRowCount()-1 ; i>=0 ;i--){
                    model.removeRow(i);
                }
                List<CompanyDTO> re = new CompanyDAO().searchAllCompany();
                for(CompanyDTO c : re){
                    System.out.println(c.toString());
                    Object[] data = {c.getName(),c.getCode()};
                    model.addRow(data);
                }
            }else{
                CompanyDTO res = new CompanyDAO().searchCompany(name);
                if(res==null){
                    JOptionPane.showMessageDialog(company.this,"不存在该快递公司信息","查询错误",JOptionPane.ERROR_MESSAGE);
                }else {
                    for(int i = model.getRowCount()-1 ; i>=0 ;i--){
                        model.removeRow(i);
                    }
                    Object[] data = {res.getName(),res.getCode()};
                    model.addRow(data);
                    jt_search.setText("");
                }
            }

        }
        if(e.getActionCommand().equals("修改")){
            String name = jt_name.getText();
            String code =  jt_no.getText();
            Boolean res = new CompanyDAO().updateCompany(name,code);
            if(res){
                JOptionPane.showMessageDialog(company.this,"成功修改数据");
                for(int i = model.getRowCount()-1 ; i>=0 ;i--){
                    model.removeRow(i);
                }
                List<CompanyDTO> re = new CompanyDAO().searchAllCompany();
                for(CompanyDTO c : re){
                    Object[] data = {c.getName(),c.getCode()};
                    model.addRow(data);
                }
                jt_name.setText("");
                jt_no.setText("");
            }else{
                JOptionPane.showMessageDialog(company.this,"数据修改失败");
            }
        }
        if(e.getActionCommand().equals("选择修改")){
            int index = table.getSelectedRow();
            String name = (String) model.getValueAt(index,0);
            String code = (String) model.getValueAt(index,1);
            jt_name.setText(name);
            jt_no.setText(code);
        }

        if(e.getActionCommand().equals("删除")){
            int index = table.getSelectedRow();
            String name = (String) model.getValueAt(index,0);
            String code = (String) model.getValueAt(index,1);
            Boolean res = new CompanyDAO().delCompany(name ,code);
            if(res){
                model.removeRow(index);
                JOptionPane.showMessageDialog(company.this,"成功删除数据");
            }else {
                JOptionPane.showMessageDialog(company.this,"删除失败");
            }
        }
    }
}
