package maomorn.com.pingyindemo;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtils {

    /**
     * 将字符串中的中文转化为拼音,英文字符不变
     * @param inputString 汉字
     * @return 汉语拼音
     */
    public static String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String output = "";
        if (inputString != null && inputString.length() > 0
                && !"null".equals(inputString)) {
            char[] input = inputString.trim().toCharArray();
            try {
                for (int i = 0; i < input.length; i++) {
                    if (java.lang.Character.toString(input[i]).matches(
                            "[\\u4E00-\\u9FA5]+")) {
                        String[] temp = PinyinHelper.toHanyuPinyinStringArray(
                                input[i], format);
                        output += temp[0];
                    } else
                        output += java.lang.Character.toString(input[i]);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            return "*";
        }
        return output;
    }

    /**
     * 一字多音
     * @param chines 汉字
     * @return 所有的发音，用空格间隔
     */
    public static String getMorePinyin(char chines) {
        String pinyinName = "";
        String[] pinyinArray=PinyinHelper.toHanyuPinyinStringArray(chines);
        pinyinName+=pinyinArray[0];
        for(int i=1;i<pinyinArray.length;i++){
            pinyinName+=" "+pinyinArray[i];
        }
        return pinyinName;
    }

    public static String getWithTone(char chines){
        HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        String pinyinName="";
        try{
            String[] one=PinyinHelper.toHanyuPinyinStringArray(chines,format);
            pinyinName+=one[0]+" ";
            format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
            String[] two=PinyinHelper.toHanyuPinyinStringArray(chines,format);
            pinyinName+=two[0];
        }catch(BadHanyuPinyinOutputFormatCombination e){
            e.printStackTrace();
        }
        return pinyinName;
    }
}
