package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;
    private JTextField fld_user_name;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JCheckBox chk_show_pass;

    private final UserManager userManager;

    private User user;


    public LoginView() {

        this.userManager=new UserManager();


        add(container);
        this.guiInitilaze(550,300);
        this.setVisible(true);

        btn_login.addActionListener(e -> {

            JTextField[] checkFieldList ={this.fld_user_name,this.fld_password};
            if(Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");
            }else {
                User loginUser = this.userManager.findByLogin(this.fld_user_name.getText(),this.fld_password.getText());
                System.out.println("kulanıcı bilgisi: "+loginUser);
                if(loginUser== null){
                    Helper.showMsg("notFound");
                }else {
                   if( loginUser.getRole().toString().equals("admin")) {
                        AdminView adminView = new AdminView(loginUser);
                       //this.setVisible(false);
                       dispose();

                    }
                    if( loginUser.getRole().toString().equals("employee")) {
                        HotelView employee = new HotelView(loginUser);
                        //this.setVisible(false);
                        dispose();
                    }
                }
            }

        });

        chk_show_pass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Checkbox durumuna göre şifre alanının görünürlüğünü ayarla
                JCheckBox checkbox = (JCheckBox) e.getSource();
                if (checkbox.isSelected()) {
                    fld_password.setEchoChar((char) 0); // Şifre gösterilsin
                    chk_show_pass.setText("Gizle");
                } else {
                    fld_password.setEchoChar('*'); // Şifre gizlensin
                    chk_show_pass.setText("Göster");
                }
            }
        });
    }
}
