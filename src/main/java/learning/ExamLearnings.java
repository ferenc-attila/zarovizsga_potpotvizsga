package learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamLearnings {

    private static final int NUMBER_OF_MINUTES = 60;

    private Map<String, Integer> learnings = new HashMap<>();

    public Map<String, Integer> getLearnings() {
        return learnings;
    }

    public void readFromFile(Path path) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            getFileContent(reader);
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file.");
        }
    }

    private void getFileContent(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] cells = line.split(";");
            int learningTime = getLearningTimes(cells);
            updateLearnings(cells, learningTime);
        }
    }

    private void updateLearnings(String[] cells, Integer learningTime) {
        if (learnings.containsKey(cells[0])) {
            learnings.put(cells[0], learnings.get(cells[0]) + learningTime);
        } else {
            learnings.put(cells[0], learningTime);
        }
    }

    private int getLearningTimes(String[] cells) {
        int learningTime = 0;
        for (int i = 1; i < cells.length; i++) {
            String cellValue = cells[i].replace(",", ".");
            learningTime += ((int)(Double.parseDouble(cellValue) * NUMBER_OF_MINUTES));
        }
        return learningTime;
    }

    public double getAverageLearningInMinutes() {
        return learnings.values().stream()
                .mapToInt(Integer::intValue)
                .average().orElseThrow(() -> new IllegalArgumentException("There are no learning times."));
    }
}
