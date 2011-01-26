/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Emir
 */
public class DateUtils {
  
  public static String now(String dateFormat) {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    return sdf.format(cal.getTime());

  }

}

