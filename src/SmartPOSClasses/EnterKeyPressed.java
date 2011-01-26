/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class EnterKeyPressed {

    public void EnterKeyPressed(int key){
       //  System.out.println("UserField is focused");

         if (key == KeyEvent.VK_ENTER) {
        System.out.println("ENTER pressed");
            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_TAB);
            } catch (AWTException ex) {
                System.out.println( "Error from Enter Key Pressed Class : " + ex.getMessage());
            }

        }
    }

}
