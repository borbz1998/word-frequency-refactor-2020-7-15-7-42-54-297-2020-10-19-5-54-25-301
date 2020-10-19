import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACES = "\\s+";

    public String getResult(String sentence) {
        if (!isSentenceLengthEqualToOne(sentence)) {
            try {
                List<WordInfo> wordInfoList = getCalculateFrequency(sentence);

                getSortedWordInfoList(wordInfoList);

                return getStringJoiner(wordInfoList);

            } catch (Exception e) {
                return "Calculate Error";
            }
        } else {
            return sentence + " 1";
        }
    }

    private boolean isSentenceLengthEqualToOne(String sentence) {
        return sentence.split(WHITE_SPACES).length == 1;
    }

    private void getSortedWordInfoList(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
    }

    private void getWordInfoListInDescendingOrder(List<WordInfo> wordInfoList) {
        wordInfoList.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
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
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACES));
        List<WordInfo> wordInfoList = new ArrayList<>();
        for (String word : new HashSet<>(words)) {
            int frequencyCount = Collections.frequency(words, word);
            wordInfoList.add(new WordInfo(word, frequencyCount));
        }
        return wordInfoList;
    }
}
