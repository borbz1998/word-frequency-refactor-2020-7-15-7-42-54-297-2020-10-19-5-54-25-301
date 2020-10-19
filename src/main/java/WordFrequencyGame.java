import java.util.*;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfoList = getCalculateFrequency(sentence);
            getSortedWordInfoList(wordInfoList);
            return getStringJoiner(wordInfoList);

        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private void getSortedWordInfoList(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private String getStringJoiner(List<WordInfo> wordInfoList) {
        return wordInfoList.stream()
                .map(wordInfo -> format("%s %d", wordInfo.getValue(), wordInfo.getWordCount()))
                .collect(joining("\n"));
    }

    private List<WordInfo> getCalculateFrequency(String sentence) {
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACES));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int frequencyCount = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, frequencyCount));
        }
        return wordInfoList;
    }
}
