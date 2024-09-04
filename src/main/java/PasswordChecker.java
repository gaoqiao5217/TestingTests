public class PasswordChecker {
    private int minLength;
    private int maxRepeats;
    private boolean isMinLengthSet;
    private boolean isMaxRepeatsSet;

    public void setMinLength(int minLength) {
        if (minLength < 0) {
            throw new IllegalArgumentException();
        }
        this.minLength = minLength;
        this.isMinLengthSet = true;
    }

    public void setMaxRepeats(int maxRepeats) {
        if (maxRepeats <= 0) {
            throw new IllegalArgumentException();
        }
        this.maxRepeats = maxRepeats;
        this.isMaxRepeatsSet = true;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxRepeats() {
        return maxRepeats;
    }

    public boolean isMinLengthSet() {
        return isMinLengthSet;
    }

    public void setMinLengthSet(boolean minLengthSet) {
        isMinLengthSet = minLengthSet;
    }

    public boolean isMaxRepeatsSet() {
        return isMaxRepeatsSet;
    }

    public void setMaxRepeatsSet(boolean maxRepeatsSet) {
        isMaxRepeatsSet = maxRepeatsSet;
    }

    public boolean verify(String password) {
        if (!isMinLengthSet || !isMaxRepeatsSet) {
            throw new IllegalStateException("Настройки не заданы корректно.");
        }
        char current = password.charAt(0);
        int count = 1;

        for (int i = 1; i < password.length() - 1; i++) {
            char c = password.charAt(i);
            if (c == current) {
                count++;
            } else {
                current = c;
                count = 1;
            }
            if (count > maxRepeats) {
                return false;
            }

        }

        return password.length() >= minLength;


    }
}