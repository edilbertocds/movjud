package br.jus.tjsp.movjud.business.utils.helper;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * Converte um Date para String no formato dd/MM/yyyy
     *
     * @param data data.
     * @return string convertida.
     */
    public static final String dateToStringddMMyyyy(Date data) {
        String strData = "";
        if (data != null) {
            strData = new SimpleDateFormat("dd/MM/yyyy").format(data);
        }
        return strData;
    }
    
    /**
     * Obtém o ano apartir da data.
     *
     * @param date data.
     * @return ano da data.
     */
    public static String yearInDate(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }
    
    /**
     * Obtém o mës apartir da data.
     *
     * @param date data.
     * @return mës.
     */
    public static String monthInDate(Date date) {
        int intMonth = 0;
        String desc = "";
        String strMonth = new SimpleDateFormat("M").format(date);

        if (strMonth != null && !strMonth.trim().equals("")) {
            intMonth = Integer.parseInt(strMonth);

            switch (intMonth) {
                case 1:
                    desc = "Janeiro";
                case 2:
                    desc = "Fevereiro";
                case 3:
                    desc = "Março";
                    break;
                case 4:
                    desc = "Abril";
                    break;
                case 5:
                    desc = "Maio";
                    break;
                case 6:
                    desc = "Junho";
                    break;
                case 7:
                    desc = "Julho";
                    break;
                case 8:
                    desc = "Abosto";
                    break;
                case 9:
                    desc = "Setembro";
                    break;
                case 10:
                    desc = "Outubro";
                    break;
                case 11:
                    desc = "Novembro";
                    break;
                case 12:
                    desc = "Dezembro";
                    break;
            }
        }   
        return desc;
    }    
    
    public static Date removerTime(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}
}
