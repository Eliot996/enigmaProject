package com.company;

import java.util.Scanner;


public class Main {

    private static final Scanner input = new Scanner(System.in);

    final static char[] ALPHABET = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Æ', 'Ø', 'Å', ',', '.', '!', '?'};

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
                numberCipherEncryptMenu();
            } else if (type.equals("n") && mode == 'd') {
                numberCipherDecryptMenu();
            } else if (type.equals("s") && mode == 'e') {
                shiftCipherEncryptMenu();
            } else if (type.equals("s") && mode == 'd') {
                shiftCipherDecryptMenu();
            } else if (type.equals("p") && mode == 'e') {
                polyalphabeticCipherEncryptMenu();
            } else if (type.equals("p") && mode == 'd') {
                polyalphabeticCipherDecryptMenu();
            }
        }
    }

    private static void numberCipherEncryptMenu() {
        String encoded;
        int[] ints;

        //get string from user
        System.out.print("Du har valgt encode - indtast en tekst: ");
        String toEncode = input.nextLine();

        // convert string into an int array, and save it in ints
        ints = toInts(toEncode.toUpperCase());

        //convert ints to a string, and return the string
        encoded = toString(ints);
        System.out.println(encoded);

    }

    private static void numberCipherDecryptMenu() {
        String decoded, toDecode;
        int[] ints;

        // get input from user
        System.out.print("Du har valgt decode - indtast en liste af tal: ");
        toDecode = input.nextLine();

        // convert string to array of ints
        ints = stringToInts(toDecode);

        // convert to string, from indices
        decoded = intsToString(ints);
        System.out.println(decoded);
    }

    private static void shiftCipherEncryptMenu() {
        //TODO:
        // get key
        // get string to decrypt
        // convert string to ints
        // apply shift
        // convert ints to string
        // output
    }

    private static void shiftCipherDecryptMenu() {
        //TODO:
        // get key
        // get string to decrypt
        // convert string to ints
        // apply anti-shift
        // convert ints to string
        // output
    }

    private static void polyalphabeticCipherEncryptMenu() {
        //TODO:
        // get keyword
        // convert to ints
        // get string to decrypt
        // convert string to ints
        // apply shift
        // convert ints to string
        // output
    }

    private static void polyalphabeticCipherDecryptMenu() {
        //TODO:
        // get keyword
        // convert to ints
        // get string to decrypt
        // convert string to ints
        // apply anti-shift
        // convert ints to string
        // output
    }



    // takes the patteren for the string of the int array, and converts it to an array of ints
    //TODO: make more consize, remove bloat
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
    public static String intsToString(int[] ints){
        // makes a StringBuilder, and appends the corresponding chars to the StringBuilder
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < ints.length; i++) {
            result.append(ALPHABET[ints[i]]);
        }
        return result.toString();
    }

    // converts a string to an array of ints
    public static int[] toInts(String text) {
        // create an array of ints, with the same size as the length of text
        int[] ints = new int[text.length()];

        // for each index of text, add the corresponding int into the respective place in the int array
        for (int i = 0; i < text.length(); i++) {
            ints[i] = charToInt(text.charAt(i));
        }
        return ints;
    }

    // takes a char and returns the index of that char in ALPHABET, else return -1
    public static int charToInt(char charater) {
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
