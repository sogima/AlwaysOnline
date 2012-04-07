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

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.Timer;

/**
 * Main class used to execute the program and provide a user interface.
 * @author Sogima
 */
public class Main
{
    private SystemTray tray = null;
    private GUIAccess g = null;
    private Access a = null;
    private TrayIcon trayIcon = null;
    private Timer timer = null;
    private String user, pass;

    public static void main(String[] args)
    {
        Main m = new Main();
    }

    /** Default constructor that allow to access and refreshg the session. */
    public Main()
    {
        Image image = Toolkit.getDefaultToolkit().getImage("data/icon.jpg");
        // define the menu
        PopupMenu popup = new PopupMenu();
        MenuItem exitItem = new MenuItem("Exit");
        MenuItem showItem = new MenuItem("Login data");
        exitItem.addActionListener(new exitListener());
        showItem.addActionListener(new openFormListener());
        popup.add(showItem);
        popup.add(exitItem);

        if (SystemTray.isSupported())
        {
            tray = SystemTray.getSystemTray();
            try
            {
                // read creadential saved previously  ( if exists )
                readCredential();

                // create an instance to access
                a = new Access();

                // create the UI for insert credential data
                g = new GUIAccess(user, pass, this);
                
                // create a tray icon and all objects needed
                trayIcon = new TrayIcon(image, "Serra logger", popup);
                trayIcon.setImageAutoSize(true);
                trayIcon.addActionListener(new openFormListener());
                tray.add(trayIcon);

                // create and start the access timer
                // in this case connection refresh is done every hour
                timer = new Timer(3600000, new doLoginListener());
                timer.start();
                doLogin();
            }
            catch (Exception e)
            {
            }
        }
    }

    /**
     * Method used to read a file containing cyphered access credential
     * if the file exists decypher credentials and initialize the variables
     * otherwise initialize the variables with standard values.
     */
    private void readCredential()
    {
        // control if the file exists and read data
        File file = new File("data/credential");
        if(file.exists())
        {
            try
            {
                FileInputStream fis = new FileInputStream(file);
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                user = br.readLine();
                pass = br.readLine();

                br.close();
                in.close();
                fis.close();
            }
            catch(Exception e)
            {}
        }
        // if the file doesn't exist assign default values
        else
        {
            user = "username";
            pass = "password";
        }
    }

    /**
     * Method that write credentials in file cyphering the values before
     * considering last values insered from the user in the GUI.
     */
    private void writeCredential()
    {
        try
        {
            FileOutputStream pos = new FileOutputStream("data/credential");
            PrintStream ps = new PrintStream(pos);

            ps.println(user);
            ps.println(pass);

            ps.close();
            pos.close();
        }
        catch(Exception e){}
    }

    /**
     * Method used to update access credentials odo che consente di aggiornare le credenziali di accesso.
     * @param g Interfaccia in cui si inseriscono le credenziali.
     */
    public void updateCredentials(GUIAccess g)
    {
        // save on global variables
        user = g.getUsername();
        pass = g.getPassword();
        doLogin();
        // save credential values on file
        writeCredential();
    }
	
    /**
     * Method used to made the login using the class Access and shows
     * a message in the trayIcon based on the result.
     */
    private void doLogin()
    {
        try
        {
            if (a.doAccess(user, pass) == 1)
                trayIcon.displayMessage("Logged in!", "Login success!", TrayIcon.MessageType.INFO);
            else
                trayIcon.displayMessage("Error!", "Login failed!", TrayIcon.MessageType.ERROR);
        }
        catch (Exception exc)
        {
            trayIcon.displayMessage("Error!", "Network failed!", TrayIcon.MessageType.ERROR);
        }
    }

    /**
     * Listener used to open the user GUI where insert credential data
     */
    class openFormListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            g.setVisible(true);
        }
    }
	
    /**
     * Listener associated to a timer to execute access periodically
     */
    class doLoginListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            doLogin();
        }
    }

    /**
     * Listener associated to the menu, used to allow to close the window.
     */
    class exitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
}