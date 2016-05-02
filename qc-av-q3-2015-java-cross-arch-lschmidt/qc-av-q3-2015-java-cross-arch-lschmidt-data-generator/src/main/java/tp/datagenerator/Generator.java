package tp.datagenerator;

public class Generator {
    public static final int TEXT = 1;
    public static final int NUMBER = 2;
    public static final int SPECIALS = 3;
    public static final int TEXT_NUMBER = 4;
    
    private Generator() {
        super();
    }

    public static String generateCharacters(int length, Integer type) {
        String sequence = "!�$%&/()=?�@''!0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzñÑÁÉÍÓÚáéíóúÂÊÎÔÛâêîôûÀÈÌÒÙàèìòùÄËÏÖÜäëïöüÇçãÃõÕ";
        if (type == TEXT_NUMBER) {
            sequence = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzñÑÁÉÍÓÚáéíóúÂÊÎÔÛâêîôûÀÈÌÒÙàèìòùÄËÏÖÜäëïöüÇçãÃõÕ";
        }
        if (type == TEXT) {
            sequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzñÑÁÉÍÓÚáéíóúÂÊÎÔÛâêîôûÀÈÌÒÙàèìòùÄËÏÖÜäëïöüÇçãÃõÕ";
        }
        if (type == NUMBER) {
            sequence = "0123456789";
        }
        if (type == SPECIALS) {
            sequence = "!�$%&/()=?�@''!";
        }
        return generateCharacters(length, sequence);
    }

    public static String generateCharacters(int length, String sequence) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += (sequence.charAt((int) (Math.random() * sequence.length())));
        }
        return result;
    }

    public static int generateNumbersBetween(int from, int to) {
        return (int) (Math.random() * (to - from + 1) + from);
    }

}
