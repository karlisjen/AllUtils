package utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class StringUtills {
    
    public static <T> String join(final Iterable<T> objs, final String delimiter) {
        Iterator<T> iter = objs.iterator();
        if (!iter.hasNext()) {
            return "";
        }
        StringBuffer buffer = new StringBuffer(String.valueOf(iter.next()));
        while (iter.hasNext()) {
            buffer.append(delimiter).append(String.valueOf(iter.next()));
        }
        return buffer.toString();
    }
    
    /**
     * Replaces all tabs with 4 spaces.
     * @param str The string.
     * @return
     */
    public static String expandTabs(String str) {
        return str.replace("\t", "    ");
    }
    
    /**
     * Replaces all opening an closing tags with <code>&lt;</code> or <code>&gt;</code>.
     * @param str
     * @return
     */
    public static String htmlEntites(String str) {
        return str.replace("<", "&lt;").replace(">", "&gt;");
    }
    
    public static String normalize(String str) {
        return expandTabs(htmlEntites(str));
    }
    
    public static List<String> normalize(List<String> list) {
        List<String> result = new LinkedList<String>();
        for (String line : list) {
            result.add(normalize(line));
        }
        return result;
    }
    
    public static List<String> wrapText(List<String> list, int columnWidth) {
        List<String> result = new LinkedList<String>();
        for (String line : list) {
            result.add(wrapText(line, columnWidth));
        }
        return result;
    }
    
    /**
     * Wrap the text with the given column width 
     * @param line the text
     * @param columnWidth the given column
     * @return the wrapped text
     */
    public static String wrapText(String line, int columnWidth) {
        int lenght = line.length();
        int delimiter = "<br>".length();
        int widthIndex = columnWidth;
        
        for (int count = 0; lenght > widthIndex; count++) {
            line = line.subSequence(0, widthIndex + delimiter * count) + "<br>"
                    + line.substring(widthIndex + delimiter * count);
            widthIndex += columnWidth;
        }
        
        return line;
    }
}
