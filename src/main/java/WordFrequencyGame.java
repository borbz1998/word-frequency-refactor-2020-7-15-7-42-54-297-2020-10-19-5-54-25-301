import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.io.CharArrayWriter;

import java.time.LocalDateTime;

public class WordFrequencyGame {
    public String getResult(String sentence){

        if (sentence.split("\\s+").length==1) {
            return sentence + " 1";
        } else {

            try {
                List<Input> inputList = calculateFrequency(sentence);

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : inputList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<Input> calculateFrequency(String sentence) {
        //split the input string with 1 to n pieces of spaces
        String[] words = sentence.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : words) {
            Input input = new Input(s, 1);
            inputList.add(input);
        }

        //get the map for the next step of sizing the same words
        Map<String, List<Input>> map =getListMap(inputList);

        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()){
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        inputList = list;
        return inputList;
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input :  inputList){
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
