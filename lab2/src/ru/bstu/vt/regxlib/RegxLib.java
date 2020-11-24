package ru.bstu.vt.regxlib;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegxLib {

    public static HashMap parseParametrs(String s) throws ParseException {
        HashMap<String, String> result = new HashMap<>();
        Pattern p = Pattern.compile("([^,]+)");
        Matcher m = p.matcher(s);   // get a matcher object
        int count = 0;
        while(m.find()) {
            count++;
            String subs = s.substring(m.start(), m.end());
            Pattern ps = Pattern.compile("([.ЁёА-я\\d\\w\\s]+)=([.ЁёА-я\\d\\w\\s]+)");
            Matcher ms = ps.matcher(subs);

            if(ms.find()) {
                result.put(ms.group(1).trim(), ms.group(2).trim());
            }else{
                throw new ParseException(String.format("Параметр с порядковым номером %d не был корректно распознан!", count));
            }
        }

        return result;
    }
}
