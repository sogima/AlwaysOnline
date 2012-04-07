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

import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * This class provide methods that allow to connect to the authentication proxy.
 * @author Nicola
 */
public class Access extends Thread
{
    /** Default constructor */
    public Access() {}

    /**
     * Method for the access.
     * @param user String that contains the username
     * @param pass String that contains the password
     * @return Access result: 0 failed, 1 success.
     */
    public int doAccess(String user, String pass)
    {
        WebDriver driver = new HtmlUnitDriver();
        WebElement username, password;

        // access page
        driver.get("https://auth3.unipi.it/auth/perfigo_cm_validate.jsp");
        // looking for the credential fields
        username = driver.findElement(By.name("username"));
        username.clear();
        username.sendKeys(user);
        password = driver.findElement(By.name("password"));
        password.clear();
        password.sendKeys(pass);
        // Executing the access, submiting values
        driver.findElement(By.name("username")).submit();
        // If the session was already opened
        if(driver.getPageSource().contains("Too many users using this account"))
        {
            // put again credentials
            username = driver.findElement(By.name("username"));
            username.clear();
            username.sendKeys(user);
            password = driver.findElement(By.name("password"));
            password.clear();
            password.sendKeys(pass);
            // remove the old session
            driver.findElement(By.name("remove_old")).click();
            // submit again access data
            driver.findElement(By.name("username")).submit();
        }
        // controll if the access has been done successful
        if(driver.getPageSource().contains("You have been successfully logged on the network"))
            return 1;
        return 0;
    }
}