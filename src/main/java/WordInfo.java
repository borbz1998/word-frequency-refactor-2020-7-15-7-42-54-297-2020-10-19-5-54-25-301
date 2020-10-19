public class WordInfo {
    private String value;
    private int count;

    public WordInfo(String wordValue, int frequencyCount) {
        this.value = wordValue;
        this.count = frequencyCount;
    }


    public String getValue() {
        return this.value;
    }

    public int getWordCount() {
        return this.count;
    }


}
