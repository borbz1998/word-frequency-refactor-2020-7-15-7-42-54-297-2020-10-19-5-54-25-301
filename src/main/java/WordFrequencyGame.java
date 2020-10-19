import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";

    public String getResult(String sentence){

        if (sentence.split(WHITE_SPACES).length==1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> inputList = calculateFrequency(sentence);

                inputList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo wordInfo : inputList) {
                    String s = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<WordInfo> calculateFrequency(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split(WHITE_SPACES);

        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : words) {
            WordInfo wordInfo = new WordInfo(word, 1);
            wordInfoList.add(wordInfo);
        }

        //get the map for the next step of sizing the same words
        Map<String, List<WordInfo>> wordInfoMap =getListMap(wordInfoList);

        List<WordInfo> distinctInfoWords = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()){
            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
            distinctInfoWords.add(wordInfo);
        }
        wordInfoList = distinctInfoWords;
        return wordInfoList;
    }


    private Map<String,List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input :  inputList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())){
                ArrayList words = new ArrayList<>();
                words.add(input);
                map.put(input.getValue(), words);
            }

            else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }


}
