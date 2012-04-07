/**
 * Copyright (C) 2012  Sogima
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>
 */

package alwaysonline;

/**
 * Class that implements a UI for insert access credentials.
 * @author Sogima
 */
public class GUIAccess extends java.awt.Frame
{
    // Variables declaration - do not modify
    private javax.swing.JButton AccessButton;
    private javax.swing.JButton CloseButton;
    private java.awt.Label Password;
    private javax.swing.JPasswordField PasswordField;
    private java.awt.Label Username;
    private javax.swing.JTextField UsernameField;
    private javax.swing.JPanel jPanel1;
    public boolean changed;
    private Main m;

    /**
     * Creates new form GUIAccess.
     * @param user Username to use.
     * @param pass Password to use.
     * @param m Object tha contains the logic.
     */
    public GUIAccess(String user, String pass, Main m)
    {
        initComponents();
        this.setLocation(400, 200);
        this.setTitle("Serra logger");
        this.setResizable(false);

        UsernameField.setText(user);
        PasswordField.setText(pass);
        changed = false;
        this.m = m;
    }

    /**
     * Getter for the password data.
     * @return String containing the password insered by user
     */
    public String getPassword()
    {
        String str = "";
        for(char c : PasswordField.getPassword())
            str += c;
        return str;
    }

    /**
     * Getter for the username data.
     * @return String containing the password insered by user
     */
    public String getUsername() {
        return UsernameField.getText();
    }

    /** This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        Username = new java.awt.Label();
        Password = new java.awt.Label();
        UsernameField = new javax.swing.JTextField();
        AccessButton = new javax.swing.JButton();
        CloseButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        Username.setText("Username:");

        Password.setText("Password:");

        UsernameField.setFocusCycleRoot(true);
        UsernameField.setNextFocusableComponent(PasswordField);

        AccessButton.setText("Access");
        AccessButton.setNextFocusableComponent(CloseButton);
        AccessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccessButtonActionPerformed(evt);
            }
        });

        CloseButton.setText("Close");
        CloseButton.setNextFocusableComponent(UsernameField);
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        PasswordField.setNextFocusableComponent(AccessButton);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(AccessButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CloseButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PasswordField, 0, 0, Short.MAX_VALUE)
                            .addComponent(UsernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addGap(38, 38, 38))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AccessButton)
                    .addComponent(CloseButton))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        Username.getAccessibleContext().setAccessibleName("Username");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    /** Method that specify how to close the window */
    private void exitForm(java.awt.event.WindowEvent evt) {
        changed = false;
        setVisible(false);
    }

    /**
     * Method used to read access credentials from TextBox and save them.
     * @param evt
     */
    private void AccessButtonActionPerformed(java.awt.event.ActionEvent evt) {
        changed = true;
        setVisible(false);
        m.updateCredentials(this);
    }

    /**
     * Method used to close the window, without modifing previous credentials.
     * @param evt
     */
    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        changed = false;
        setVisible(false);
    }
}
