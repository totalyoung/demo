package ja.AutoCompleteDemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by totalyoung on 2018/11/24.
 */
public class Word {

    String prefix;
    private List<String> matchedStr;
    private Map<String, Word> childWordMap;

    public Word(String prefix, List<String> matchedStr, Map<String, Word> childWordMap) {
        this.prefix = prefix;
        this.matchedStr = matchedStr;
        this.childWordMap = childWordMap;
    }

    public List<String> findResult(String key){
        String prefixString = key.substring(0,1);
        String suffixString = key.substring(1, key.length()-1);
        Word words = childWordMap.get(prefixString);
        if(words == null){
            return new ArrayList<>();
        }else{
            return findResult(suffixString);
        }
    }



    public void initializeWord(List<String> allStr,String prefix,Word word){
        int prefixLength = prefix.length();
        Map<String, Word> childWordMap = word.getChildWordMap();
        for (String str : allStr) {
            String key = str.substring(prefixLength, prefixLength + 1);
            Word childWord = childWordMap.get(key);
            if(childWord !=null){
                childWord.getMatchedStr().add(str);
            }else{
                List<String> matchedStr = new ArrayList<>();
                matchedStr.add(str);
                childWordMap.put(key,new Word(prefix,matchedStr,new HashMap<>()));
            }
        }
        for (Map.Entry<String, Word> stringWordEntry : childWordMap.entrySet()) {
            initializeWord(stringWordEntry.getValue().getMatchedStr(),stringWordEntry.getKey(),stringWordEntry.getValue());
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public List<String> getMatchedStr() {
        return matchedStr;
    }

    public void setMatchedStr(List<String> matchedStr) {
        this.matchedStr = matchedStr;
    }

    public Map<String, Word> getChildWordMap() {
        return childWordMap;
    }

    public void setChildWordMap(Map<String, Word> childWordMap) {
        this.childWordMap = childWordMap;
    }
}
