package numbers;

import java.util.List;

public class RisingNumbers {

    public int getNumberOfSixDigitRisingNumbers(List<Integer> numbers) {
        validateListOfNumbers(numbers);
        return (int) numbers.stream()
                .filter(number -> Integer.toString(number).length() == 6)
                .filter(this::areRisingNumbers)
                .count();
    }

    private boolean areRisingNumbers(Integer number) {
        char[] digits = Integer.toString(number).toCharArray();
        for (int i = 1; i < digits.length - 1; i++) {
            if (digits[i-1] >= digits[i] || digits[i+1] <= digits[i]) {
                return false;
            }
        }
        return true;
    }

    private void validateListOfNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("There are no numbers!");
        }
    }
}
