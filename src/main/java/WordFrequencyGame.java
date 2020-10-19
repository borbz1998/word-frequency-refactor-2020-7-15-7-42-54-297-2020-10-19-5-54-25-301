import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";

    public String getResult(String sentence) {

        if (sentence.split(WHITE_SPACES).length == 1) {
            return sentence + " 1";
        } else {

            try {
                List<WordInfo> wordInfoList = getCalculateFrequency(sentence);

                wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());

//                StringJoiner joiner = getStringJoiner(wordInfoList);
//                return joiner.toString();

                return getStringJoiner(wordInfoList);

            } catch (Exception e) {
                return "Calculate Error";
            }
        }


    }

    private String getStringJoiner(List<WordInfo> wordInfoList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (WordInfo wordInfo : wordInfoList) {
            String wordInfoLine = String.format("%s %d", wordInfo.getValue(), wordInfo.getWordCount());
            joiner.add(wordInfoLine);
        }
        return joiner.toString();
    }

    private List<WordInfo> getCalculateFrequency(String sentence) {
//        //split the input string with 1 to n pieces of spaces
//        String[] words = sentence.split(WHITE_SPACES);
//
//        List<WordInfo> wordInfoList = new ArrayList<>();
//        for (String word : words) {
//            WordInfo wordInfo = new WordInfo(word, 1);
//            wordInfoList.add(wordInfo);
//        }
//
//        //get the map for the next step of sizing the same words
//        Map<String, List<WordInfo>> wordInfoMap =getListMap(wordInfoList);
//
//        List<WordInfo> distinctInfoWords = new ArrayList<>();
//        for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()){
//            WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
//            distinctInfoWords.add(wordInfo);
//        }
//        wordInfoList = distinctInfoWords;
//        return wordInfoList;

        List<String> words = Arrays.asList(sentence.split(WHITE_SPACES));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int frequencyCount = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, frequencyCount));
        }
        return wordInfoList;
    }


    private Map<String, List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList words = new ArrayList<>();
                words.add(input);
                map.put(input.getValue(), words);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }


}
