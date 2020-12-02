package Components;

import java.util.*;

public class SplitCommandString {
    public static ArrayList<String> split(String str, char sp){
        ArrayList<String> tmpResult = new ArrayList<String>();
        String tmp = "";
        char pc,pcb = 'n',pca = 'n';
        char splitChar = sp, quoteChar = '"',specialChar = '\\';
        boolean isInString = false, openingQ = true;

        for(int i = 0; i<str.length();i++){ //Loop
            pc = str.charAt(i);
            if(i != 0){ pcb = str.charAt(i-1);}
            if(i+1 < str.length()){ pca = str.charAt(i+1);}

            if(pc == splitChar && !isInString){ //Splitter ==============
                tmpResult.add(tmp);
                tmp = "";

            }else if(pc == quoteChar){ //String ===============
                if(pcb == specialChar) {
                    tmp+= pc;
                }else if(openingQ && pcb != specialChar){
                    isInString = true;
                    openingQ = false;
                }else if(!openingQ && pcb != specialChar){
                    isInString = false;
                    openingQ = true;
                }

            }else{ //Other ==================
                if(pc == specialChar && pca == quoteChar){
                    break;
                }else{
                    tmp += pc;
                }
            }
        }
        tmpResult.add(tmp);

        return tmpResult;
    }

}
