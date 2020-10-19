import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String sentence){

        if (sentence.split("\\s+").length==1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> inputList = calculateFrequency(sentence);

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordInfo w : inputList) {
                    String s = w.getValue() + " " +w.getWordCount();
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
        String[] words = sentence.split("\\s+");

        List<WordInfo> inputList = new ArrayList<>();
        for (String word : words) {
            WordInfo input = new WordInfo(word, 1);
            inputList.add(input);
        }

        //get the map for the next step of sizing the same words
        Map<String, List<WordInfo>> map =getListMap(inputList);

        List<WordInfo> list = new ArrayList<>();
        for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()){
            WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        inputList = list;
        return inputList;
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
