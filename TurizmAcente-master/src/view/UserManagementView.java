package view;

import business.RoomManager;
import business.UserManager;
import core.Helper;
import entity.Room;
import entity.User;

import javax.swing.*;

public class UserManagementView extends  Layout{
    private JPanel container;
    private JTextField fld_mng_name;
    private JTextField fld_mng_pass;
    private JComboBox<User.Role>cmb_mng_role;
    private JButton btn_mng_save;
   // private JComboBox<ComboItem> cmb_brand;

    private User user   ;
    private UserManager userManager;


    public UserManagementView(User user) {
        this.userManager=new UserManager();
        this.user=user;

        add(container);
        this.guiInitilaze(400,400);

        if(user != null){
            fld_mng_name.setText(user.getUsername());
            fld_mng_pass.setText(user.getPassword());
            this.cmb_mng_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        }
        this.cmb_mng_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        btn_mng_save.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[]{this.fld_mng_name,this.fld_mng_pass,})){
                Helper.showMsg("fill");
            }else{
                //ComboItem selectedBrand = (ComboItem) cmb_mng_role.getSelectedItem();
                boolean result= false ;
                if (this.user != null){
                    this.user.setUsername(fld_mng_name.getText());
                    this.user.setPassword(fld_mng_pass.getText());
                    this.user.setRole((User.Role) cmb_mng_role.getSelectedItem());
                    result = this.userManager.update(this.user);
                    System.out.println("update kısmına gırdi");
                }else{
                    //result=this.userManager.save(this.user);
                    User obj =new User(fld_mng_name.getText(),fld_mng_pass.getText(), (User.Role) cmb_mng_role.getSelectedItem());
                    result =this.userManager.save(obj);
                }
                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }

            }


        });
    }
}
