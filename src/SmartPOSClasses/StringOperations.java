/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

/**
 *
 * @author Emir
 */
public class StringOperations {

    /**
     * Removes Double Quotes from String, takes a string as input returns the string without the double quotes
     * @param totrim
     * @return
     */
    public String TrimDoubleQuotes(String totrim){

        String needle = "\"";
        int index =0;

        index = totrim.indexOf(needle);

        String trimmed = totrim;
         String temp = "";
        
       // System.out.println("totrim: " + totrim);
        
        //if string contains " double quotes remove them
        if (index != -1){

                  char c = '"';
                    for (int j = 0; j < totrim.length(); j++) {
                        if (totrim.charAt(j) != c) temp += totrim.charAt(j);
                    }

         trimmed = temp;
         
        }

      //  System.out.println("trimmed : " + trimmed);

        return trimmed;

    }

    /**
     * Removes At Signs from String, takes a string as input returns the string without the at sign
     * @param totrim
     * @return
     */
    public String TrimAtSign(String totrim){

        String needle = "@";
        int index =0;

        index = totrim.indexOf(needle);

        String trimmed = totrim;
        String temp = "";
     //   System.out.println("index: " + index);
     //   System.out.println("totrim: " + totrim);

        //if string contains " double quotes remove them
        if (index != -1){


                  char c = '@';
                    for (int j = 0; j < totrim.length(); j++) {
                        if (totrim.charAt(j) != c) temp += totrim.charAt(j);
                    }

                    trimmed = temp;

        }

     //   System.out.println("trimmed : " + trimmed);



        return trimmed;

    }

    /**
     * Checks if all the characters of a string are digits, takes a string as input and returns either true or false
     * @param str
     * @return
     */
    public boolean containsOnlyNumbers(String str) {

        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
            return false;

        for (int i = 0; i < str.length(); i++) {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i)))
                return false;
        }

        return true;
    }

}
