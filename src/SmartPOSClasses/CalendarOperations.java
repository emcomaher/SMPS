/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SmartPOSClasses;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Emir
 */
public class CalendarOperations {

    public int getNumOfDaysForMonthInYear (int Month, int Year){

        Calendar cal = new GregorianCalendar(Year, Month, 1);
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("days : "+days);
        return days;
        
    }

    public int calculateNumofDaysbetweenDates(JPanel jPanel2, String InitialDate, String FinalDate){

        String [] tempInitialDate = null;
        tempInitialDate = InitialDate.split(" ");

        String [] tempFinalDate = null;
        tempFinalDate = FinalDate.split(" ");

        int startYear = Integer.parseInt(tempInitialDate[2]);
        int startMonth = this.convertStringMonthtoIntNum(tempInitialDate[1]);
        int startDay = Integer.parseInt(tempInitialDate[0]);
        int endYear = Integer.parseInt(tempFinalDate[2]);
        int endMonth = this.convertStringMonthtoIntNum(tempFinalDate[1]);
        int endDay= Integer.parseInt(tempFinalDate[0]);

        Calendar beginDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        beginDate.set(startYear, startMonth, startDay);
        endDate.set(endYear, endMonth, endDay);

          if(!beginDate.before(endDate)){
              System.out.println("Krajnji period na kalendaru treba da bude poslije početnog perioda !");
              JOptionPane.showMessageDialog(jPanel2, "Krajnji period na kalendaru treba da bude poslije početnog perioda !");
          }

        int daysBetween = 0;

          if(beginDate.before(endDate)){

             

             while (beginDate.before(endDate)) {
                   beginDate.add(Calendar.DAY_OF_MONTH, 1);
                   daysBetween++;
             }
             
          }

        System.out.println("DAYS BETWEEN: " + daysBetween);
        return daysBetween;
      
    }

    public String getInitialDatefromJXCalendar(JXDatePicker jXDatePicker1){
        String initialDate = jXDatePicker1.getDate().toString();
        String [] tempInitialDate = null;
        tempInitialDate = initialDate.split(" ");

        String fullInitialYear = tempInitialDate[5];

        String myInitialDate = tempInitialDate[2].concat(" ").
                        concat(tempInitialDate[1]).concat(" ").
                        concat(tempInitialDate[5]);

 //CONVERT MONTH STR TO NUMBER THAT CAN BE USED WITH THE CALENDAR DATE
        int tempInitialMonth = 0;
        tempInitialMonth = this.convertStringMonthtoIntNum(tempInitialDate[1]);
        System.out.println("MY INITIAL DATE: " + myInitialDate);
        return myInitialDate;
    }

    public String getFinalDatefromJXCalendar(JXDatePicker jXDatePicker2){
        String finalDate = jXDatePicker2.getDate().toString();
        String [] tempFinalDate = null;
        tempFinalDate = finalDate.split(" ");

        String fullFinalYear = tempFinalDate[5];

        String myFinalDate = tempFinalDate[2].concat(" ").
                        concat(tempFinalDate[1]).concat(" ").
                        concat(tempFinalDate[5]);

//CONVERT MONTH STR TO NUMBER THAT CAN BE USED WITH THE CALENDAR DATE
        int tempFinalMonth = 0;
        tempFinalMonth = this.convertStringMonthtoIntNum(tempFinalDate[1]);
        System.out.println("MY FINAL DATE: " + myFinalDate);
        return myFinalDate;
    }

    public String convertStringMonthtoStringNum(String Month){
        String Num = "";

        if(Month.compareTo("Jan") == 0){ Num = "1";}
        if(Month.compareTo("Feb") == 0){ Num = "2";}
        if(Month.compareTo("Mar") == 0){ Num = "3";}
        if(Month.compareTo("Apr") == 0){ Num = "4";}
        if(Month.compareTo("May") == 0){ Num = "5";}
        if(Month.compareTo("Jun") == 0){ Num = "6";}
        if(Month.compareTo("Jul") == 0){ Num = "7";}
        if(Month.compareTo("Aug") == 0){ Num = "8";}
        if(Month.compareTo("Sep") == 0){ Num = "9";}
        if(Month.compareTo("Oct") == 0){ Num = "10";}
        if(Month.compareTo("Nov") == 0){ Num = "11";}
        if(Month.compareTo("Dec") == 0){ Num = "12";}

        return Num;
    }

    

    public int convertStringMonthtoIntNum(String Month){
        int Num = -1 ;
        
        if(Month.compareTo("Jan") == 0){ Num = 0;}
        if(Month.compareTo("Feb") == 0){ Num = 1;}
        if(Month.compareTo("Mar") == 0){ Num = 2;}
        if(Month.compareTo("Apr") == 0){ Num = 3;}
        if(Month.compareTo("May") == 0){ Num = 4;}
        if(Month.compareTo("Jun") == 0){ Num = 5;}
        if(Month.compareTo("Jul") == 0){ Num = 6;}
        if(Month.compareTo("Aug") == 0){ Num = 7;}
        if(Month.compareTo("Sep") == 0){ Num = 8;}
        if(Month.compareTo("Oct") == 0){ Num = 9;}
        if(Month.compareTo("Nov") == 0){ Num = 10;}
        if(Month.compareTo("Dec") == 0){ Num = 11;}

        return Num;
    }

    public String convertIntNumtoStringMonth(int Num){
        String Month = "";

        if(Num == 0){Month = "Jan";}
        if(Num == 1){Month = "Feb";}
        if(Num == 2){Month = "Mar";}
        if(Num == 3){Month = "Apr";}
        if(Num == 4){Month = "Maj";}
        if(Num == 5){Month = "Juni";}
        if(Num == 6){Month = "Juli";}
        if(Num == 7){Month = "Aug";}
        if(Num == 8){Month = "Sep";}
        if(Num == 9){Month = "Okt";}
        if(Num == 10){Month = "Nov";}
        if(Num == 11){Month = "Dec";}

        return Month;
    }

    /*
    public String convertIntNumtoStringMonthDB(int Num){
        String Month = "";

        if(Num == 0){Month = "Jan";}
        if(Num == 1){Month = "Feb";}
        if(Num == 2){Month = "Mar";}
        if(Num == 3){Month = "Apr";}
        if(Num == 4){Month = "May";}
        if(Num == 5){Month = "Jun";}
        if(Num == 6){Month = "Jul";}
        if(Num == 7){Month = "Aug";}
        if(Num == 8){Month = "Sep";}
        if(Num == 9){Month = "Oct";}
        if(Num == 10){Month = "Nov";}
        if(Num == 11){Month = "Dec";}

        return Month;
    }
     */

    public String convertStringNumtoFullStringDayOfWeek(String StrNum){
        String DayofWeek = "";

     if(StrNum.compareTo("1") == 0){ DayofWeek = "Nedjelja"; }
     if(StrNum.compareTo("2") == 0){ DayofWeek = "Ponedjeljak"; }
     if(StrNum.compareTo("3") == 0){ DayofWeek = "Utorak"; }
     if(StrNum.compareTo("4") == 0){ DayofWeek = "Srijeda"; }
     if(StrNum.compareTo("5") == 0){ DayofWeek = "Četvrtak"; }
     if(StrNum.compareTo("6") == 0){ DayofWeek = "Petak"; }
     if(StrNum.compareTo("7") == 0){ DayofWeek = "Subota"; }

        return DayofWeek;
    }

    public String convertStringNumtoShortStringDayOfWeek(String StrNum){
        String DayofWeek = "";

     if(StrNum.compareTo("1") == 0){ DayofWeek = "Ned"; }
     if(StrNum.compareTo("2") == 0){ DayofWeek = "Pon"; }
     if(StrNum.compareTo("3") == 0){ DayofWeek = "Uto"; }
     if(StrNum.compareTo("4") == 0){ DayofWeek = "Sri"; }
     if(StrNum.compareTo("5") == 0){ DayofWeek = "Cet"; }
     if(StrNum.compareTo("6") == 0){ DayofWeek = "Pet"; }
     if(StrNum.compareTo("7") == 0){ DayofWeek = "Sub"; }

        return DayofWeek;
    }

    public String convertFullStringDayOfWeektoShortStringDayOfWeek(String StrNum){
        String DayofWeek = "";

        if(StrNum.compareTo("Nedjelja") == 0){ DayofWeek = "Ned"; }
        if(StrNum.compareTo("Ponedjeljak") == 0){ DayofWeek = "Pon"; }
        if(StrNum.compareTo("Utorak") == 0){ DayofWeek = "Uto"; }
        if(StrNum.compareTo("Srijeda") == 0){ DayofWeek = "Sri"; }
        if(StrNum.compareTo("Četvrtak") == 0){ DayofWeek = "Cet"; }
        if(StrNum.compareTo("Petak") == 0){ DayofWeek = "Pet"; }
        if(StrNum.compareTo("Subota") == 0){ DayofWeek = "Sub"; }

        return DayofWeek;

    }


}
