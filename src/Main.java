import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] elements = {"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S",
                "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As",
                "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn",
                "Sb", "Te", "I", "Xe", "Cs", "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho",
                "Er", "Tm", "Yb", "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po",
                "At", "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md",
                "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Uut", "Uuq", "Uup", "Uuh", "Uus",
                "Uuo"};

        // Scans the input.
        Scanner myScanner = new Scanner(System.in);

        System.out.print("Enter a word: ");

        // Stores the user input.
        String inputWord = myScanner.next();
        String chemWord = spellWord(inputWord, elements);

        // Prints the chemistry word.
        System.out.println(chemWord);

        myScanner.close();
    }

    /**
     * Finds the chemistry word.
     */
    public static String spellWord(String word, String[] elements) {
        // Stores the chemical word.
        String chemWord = "";

        // Stores the entered word in lowercase.
        String lowercaseWord = word.toLowerCase();

        // Stores all the alphabets of lowercaseWord in a String array.
        String[] lowercaseWordArray = lowercaseWord.split("");

        // Converts all elements in elements array to lowercase.
        String[] lowercaseElementsArray = new String[elements.length];
        for (int i = 0; i < elements.length; i++) {
            lowercaseElementsArray[i] = elements[i].toLowerCase();
        }

        // Finds the chemical word.
        for (int i = 0; i < lowercaseWordArray.length; i++) {
            // Stores whether a three letter element has found.
            boolean threeLetterElementFound = false;

            // Stores whether a two letter element has found.
            boolean twoLetterElementFound = false;

            // Stores whether a single letter element has found.
            boolean singleLetterElementFound = false;

            // Stores the three letter element from lowercaseWordArray.
            if (i + 2 < lowercaseWordArray.length) {
                String threeLetterElement = lowercaseWordArray[i].concat(lowercaseWordArray[i + 1])
                        .concat(lowercaseWordArray[i + 2]);

                // Finds the threeLetterElement in lowercaseElementArray.
                int positionThreeLetterElement = getElementIndex(threeLetterElement, lowercaseElementsArray);
                if (positionThreeLetterElement != -1) {
                    // Updates status.
                    threeLetterElementFound = true;

                    // Updates the chemWord.
                    chemWord = chemWord.concat(elements[positionThreeLetterElement]);

                    // Updates the index.
                    i += 2;
                }
            }

            // Checks if three letter element has not been found.
            if (!threeLetterElementFound) {
                if (i + 1 < lowercaseWordArray.length) {
                    // Stores the two letter element from lowercaseWordArray.
                    String twoLetterElement = lowercaseWordArray[i].concat(lowercaseWordArray[i + 1]);

                    // Finds the twoLetterElement in lowercaseElementArray.
                    int positionTwoLetterElement = getElementIndex(twoLetterElement, lowercaseElementsArray);
                    if (positionTwoLetterElement != -1) {
                        // Updates status.
                        twoLetterElementFound = true;

                        // Updates the chemWord.
                        chemWord = chemWord.concat(elements[positionTwoLetterElement]);

                        // Updates the index.
                        i++;
                    }
                }
            }

            // Checks if two letter element has not been found.
            if (!twoLetterElementFound) {
                // Stores the single letter element from lowercaseWordArray.
                String singleLetterElement = lowercaseWordArray[i];

                // Finds the singleLetterElement in lowercaseElementArray.
                int positionSingleLetterElement = getElementIndex(singleLetterElement, lowercaseElementsArray);
                if (positionSingleLetterElement != -1) {
                    // Updates status.
                    singleLetterElementFound = true;

                    // Updates the chemWord.
                    chemWord = chemWord.concat(elements[positionSingleLetterElement]);
                }
            }

            // Checks if nothing has been found at current index.
            if (!threeLetterElementFound && !twoLetterElementFound && !singleLetterElementFound) {
                // Element does not exist.
                chemWord = "cannot spell word";
                break;
            }
        }
        return chemWord;
    }

    /**
     * Finds the index of element in the elements array.
     */
    public static int getElementIndex(String element, String[] elements) {
        for (int i = 0; i < elements.length; i++) {
            if (element.equals(elements[i])) {
                // Element found.
                return i;
            }
        }
        // Element not found.
        return -1;
    }
}