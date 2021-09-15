package com.company;

import java.util.Scanner;


public class Main {

    private static final Scanner input = new Scanner(System.in);

    final static char[] ALPHABET = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å'};

    public static void main(String[] args) {


        // repeat always - until exit in case "a"
        while(true) {
            System.out.println("Velkommen til Enigma-projektet");
            System.out.println("Du kan vælge mellem følgende former for kryptering:");
            System.out.println(" - (n)umber cipher");
            System.out.println(" - (s)hift cipher");
            System.out.println(" - (p)olyalphabetic cipher");
            System.out.println(" - (a)fslut program");
            System.out.print("Skriv forbogstav for at vælge: ");
            String type = input.nextLine();

            switch (type) {
                case "n" -> {
                    System.out.println("Number cipher");
                    System.out.println("*-----------*");
                }
                case "s" -> {
                    System.out.println("Shift cipher");
                    System.out.println("*----------*");
                }
                case "p" -> {
                    System.out.println("Polyalphabetic cipher");
                    System.out.println("*-------------------*");
                }
                case "a" -> {
                    System.out.println("Programmet afslutter, tak for denne gang :)");
                    System.exit(0);
                }
            }

            // set mode
            System.out.println("Vil du (e)ncrypte eller (d)ecrypte");
            char mode = input.nextLine().charAt(0);
            if (type.equals("n") && mode == 'e') {
                numberCipherEncrypt();
            } else if (type.equals("n") && mode == 'd') {
                numberCipherDecrypt();
            } else if (type.equals("s") && mode == 'e') {
                shiftCipherEncrypt();
            } else if (type.equals("s") && mode == 'd') {
                shiftCipherDecrypt();
            } else if (type.equals("p") && mode == 'e') {
                polyalphabeticCipherEncrypt();
            } else if (type.equals("p") && mode == 'd') {
                polyalphabeticCipherDecrypt();
            }
        }
    }

    private static void numberCipherEncrypt() {
        String encoded;
        int[] ints;

        //get string from user
        System.out.println("Du har valgt encrypt");
        System.out.print("Indtast en tekst: ");
        String toEncode = input.nextLine();

        // convert string into an int array, and save it in ints
        ints = textToNumbers(toEncode.toUpperCase());

        //convert ints to a string, and return the string
        encoded = toString(ints);
        System.out.println(encoded);
        System.out.println();
    }

    private static void numberCipherDecrypt() {
        String decoded, toDecode;
        int[] ints;

        // get input from user
        System.out.println("Du har valgt decrypt ");
        System.out.print("Indtast en liste af tal: ");
        toDecode = input.nextLine();

        // convert string to array of ints
        ints = stringToInts(toDecode);

        // convert to string, from indices
        decoded = numbersToText(ints);
        System.out.println(decoded);
        System.out.println();
    }

    private static void shiftCipherEncrypt() {
        String plainText, cipherText;
        int[] plainNumbers, cipherNumbers;
        int shift;

        // get string to decrypt
        System.out.println("Du har valgt encrypt");
        System.out.print("Indtast en tekst: ");
        plainText = input.nextLine();

        // get key
        System.out.print("Indtast shift nøglen: ");
        shift = input.nextInt();
        input.nextLine(); // FIX: to prevent scanner bug

        // convert string to ints
        plainNumbers = textToNumbers(plainText.toUpperCase());

        // apply shift
        cipherNumbers = shiftNumbers(plainNumbers, shift);

        // convert ints to string
        cipherText = numbersToText(cipherNumbers);

        // output
        System.out.println(cipherText);
    }

    private static void shiftCipherDecrypt() {
        String plainText, cipherText;
        int[] plainNumbers, cipherNumbers;
        int shift;

        // get string to decrypt
        System.out.println("Du har valgt decrypt");
        System.out.print("Indtast en tekst: ");
        cipherText = input.nextLine();

        // get key
        System.out.print("Indtast shift nøglen: ");
        shift = input.nextInt();
        input.nextLine(); // FIX: to prevent scanner bug

        // invert shift
        shift = -shift;

        // convert string to ints
        cipherNumbers = textToNumbers(cipherText.toUpperCase());

        // apply anti-shift
        plainNumbers = shiftNumbers(cipherNumbers, shift);

        // convert ints to string
        plainText = numbersToText(plainNumbers);

        // output
        System.out.println(plainText);
    }

    private static void polyalphabeticCipherEncrypt() {
        String plainText, cipherText, keyword;
        int[] plainNumbers, cipherNumbers, keyInts;

        // get string to decrypt
        System.out.println("Du har valgt encrypt");
        System.out.print("Indtast en tekst: ");
        plainText = input.nextLine();

        // get keyword
        System.out.print("Indtast nøglenordet: ");
        keyword = input.nextLine();

        // convert to ints
        keyInts = textToNumbers(keyword);

        // convert string to ints
        plainNumbers = textToNumbers(plainText.toUpperCase());

        // apply shifts
        cipherNumbers = shiftNumbers(plainNumbers, keyInts);

        // convert ints to string
        cipherText = numbersToText(cipherNumbers);

        // output
        System.out.println(cipherText);
    }

    private static void polyalphabeticCipherDecrypt() {
        String plainText, cipherText, keyword;
        int[] plainNumbers, cipherNumbers, keyInts;

        // get string to decrypt
        System.out.println("Du har valgt encrypt");
        System.out.print("Indtast en tekst: ");
        plainText = input.nextLine();

        // get keyword
        System.out.print("Indtast nøglenordet: ");
        keyword = input.nextLine();

        // convert keyword to ints, and invert
        keyInts = textToNumbers(keyword);

        for (int i = 0; i < keyInts.length; i++) {
            keyInts[i] = -keyInts[i];
        }

        // convert string to ints
        plainNumbers = textToNumbers(plainText.toUpperCase());

        // apply shifts
        cipherNumbers = shiftNumbers(plainNumbers, keyInts);

        // convert ints to string
        cipherText = numbersToText(cipherNumbers);

        // output
        System.out.println(cipherText);
    }

    // run through numbers and call shiftNumber on them, and return new array with the new numbers
    public static int[] shiftNumbers(int[] numbers, int shift) {
        int[] shiftedNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            shiftedNumbers[i] = shiftNumber(numbers[i], shift);
        }
        return shiftedNumbers;
    }

    public static int[] shiftNumbers(int[] numbers, int[] shifts) {
        int[] shiftedNumbers = new int[numbers.length];
        int indexOfShift = 0;
        for (int i = 0; i < numbers.length; i++) {
            shiftedNumbers[i] = shiftNumber(numbers[i], shifts[indexOfShift]);
            indexOfShift++;
            if (indexOfShift >= shifts.length) indexOfShift = 0;
        }
        return shiftedNumbers;
    }

    // shift a number by shift amount, if larger than or equal to ALPHABET.length then subtract ALPHABET.length - 1
    // (but not with spaces)
    public static int shiftNumber(int number, int shift) {
        if (number != 0) {
            number += shift;
        }

        if (number >= ALPHABET.length) number -= ALPHABET.length - 1;

        return number;

    }

    // takes the patteren for the string of the int array, and converts it to an array of ints
    //TODO: make simplify / refactor
    public static int[] stringToInts(String text){
        // creates an array, with the length of the text, because we cannot adjust the lenght of the array later.
        // therefore, it is better to have a larger array than neccesary, and adjust later
        int[] intsPlaceholder = new int[text.length()],
                ints;

        // index for the while loop
        int i = 0;

        // finds the indexes of "{" and "}"
        int indexFirst = text.indexOf("{"),
                indexLast = text.indexOf("}"),
                workingIndex;

        // get only the numbers, needed along with commas
        String placeholder = text.substring(indexFirst + 1,indexLast);

        // find the first "," in placholder
        workingIndex = placeholder.indexOf(", ");

        // using workingIndex
        while (workingIndex != -1){
            // takes the string before the workingIndex and parses it to an int,
            // and saves it in the respective index of ints
            intsPlaceholder[i] = Integer.parseInt(placeholder.substring(0,workingIndex));

            // removes the number just found, along with the ", "
            placeholder = placeholder.substring(workingIndex + 2);

            // gets new workingIndex
            workingIndex = placeholder.indexOf(", ");
            i++;
        }
        // adds the remaining number
        intsPlaceholder[i] = Integer.parseInt(placeholder);

        // creates a new int array, of proper size
        ints = new int[i+1];

        // copies the working array to the returning array
        for (int j = 0; j < ints.length; j++) {
            ints[j] = intsPlaceholder[j];
        }

        return ints;
    }

    // converts an array of ints to the corresponding string of chars
    public static String numbersToText(int[] ints){
        // makes a StringBuilder, and appends the corresponding chars to the StringBuilder
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            result.append(ALPHABET[ints[i]]);
        }
        return result.toString();
    }

    // converts a string to an array of ints
    public static int[] textToNumbers(String text) {
        // create an array of ints, with the same size as the length of text
        int[] ints = new int[text.length()];

        // for each index of text, add the corresponding int into the respective place in the int array
        for (int i = 0; i < text.length(); i++) {
            int placeholder = charToNumber(text.charAt(i));
                if (placeholder != -1){
                    ints[i] = placeholder;
                }else{
                    ints[i] = 0;
                }
        }
        return ints;
    }

    // takes a char and returns the index of that char in ALPHABET, else return -1
    public static int charToNumber(char charater) {
        // loops through ALPHABET, until it finds the right value, and returns the index
        for (int i = 0; i < ALPHABET.length; i++) {
            if (charater == ALPHABET[i]) return i;
        }
        return -1;
    }

    // converts an array of ints to a String
    public static String toString(int[] ints){
        StringBuilder result = new StringBuilder("{" + ints[0]);
        for (int i = 1; i < ints.length; i++) {
            result.append(", ").append(ints[i]);
        }
        result.append("}");
        return result.toString();
    }

}
