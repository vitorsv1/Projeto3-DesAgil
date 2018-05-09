package desagil.ensino.grupo.pro.br.fabioferraoapp;

public class Dicionario  {
    Translator translator = new Translator();

    public String[] Chars() {
        String abc = "abcdefghijklmnopqrstuvwxyz1234567890";
        String[] chars = new String[36];

        for (int i = 0; i < abc.length(); i++) {
            char value = abc.charAt(i);
            chars[i] = "";
            chars[i] += value;
        }
        return chars;
    }
    public String[] Morse() {
        String abc = "abcdefghijklmnopqrstuvwxyz1234567890";
        String[] morse = new String[36];

        for (int i = 0; i < abc.length(); i++) {
            char value = abc.charAt(i);
            morse[i] = translator.charToMorse(value);
        }
        return morse;
    }
}
