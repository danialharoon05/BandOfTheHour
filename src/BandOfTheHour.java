/**
 * This program is a band management system that allows the user to add
 * and remove band members from an irregular array.
 @author Danial Haroon
 */

import java.util.Scanner;

/** Importing Scanner class to allow user input.
 @import Scanner class
 */
public class BandOfTheHour {
    // All constants used throughout the program
    private static final int MAX_ROWS = 10;
    private static final int MAX_POSITIONS = 8;
    private static final double MIN_WEIGHT = 45.0;
    private static final double MAX_WEIGHT = 200.0;
    private static final double MAX_WEIGHT_PER_POSITION = 100.0;
    private static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        int rows;
        int position;
        int index;

        System.out.println("Welcome to the Band of the Hour");
        System.out.println("-------------------------------");

        //Gets the number of rows from user
        System.out.print("Please enter number of rows               : ");
        rows = keyboard.nextInt();

        //Checks if the number of rows inputted are allowed
        while(rows >= MAX_ROWS || rows < 0){
            System.out.print("ERROR: Out of range, try again            : ");
            rows = keyboard.nextInt();
        }//end of while loop

        //Creates the irregular array
        double [][] weightOfBandMembers = new double [rows][];


        /** Gets the amount of positions in each row
         *   sets up the irregular array
         */
        for(index=0; index< rows; index++) {
            System.out.print("Please enter number of positions in row " + (char)(index+'A') + " : ");
            position = keyboard.nextInt();

            while (position<0 || position> MAX_POSITIONS) {
                System.out.print("ERROR: Out of range, try again            : ");
                position = keyboard.nextInt();
            }// end of while loop

            weightOfBandMembers[index] = new double [position];
        }//end of for loop

        /**
         * Do-while loop to allow user to add, remove, or print the irregular array Until 'X' or 'x' is entered.
         */
        char choice;
        do {
            System.out.println();
            System.out.print("(A)dd, (R)emove, (P)rint,          e(X)it : ");
            choice = Character.toUpperCase(keyboard.next().charAt(0));

            switch (choice) {
                case 'A':
                    addMusician(rows,weightOfBandMembers);
                    break;
                case 'R':
                    removeMusician(rows,weightOfBandMembers);
                    break;
                case 'P':
                    System.out.println();
                    printAssignment(rows,weightOfBandMembers);
                    break;
                case 'X':
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }//end of switch statement
        } while (choice != 'X'); //end of do-while loop



    }//end of main

    /** Method to add a band member to the jagged array
     * @param row
     * @param bandMembers (irregular array entered)
     */

    private static void addMusician(int row, double [][] bandMembers){

        //variables for the method
        char rowLetter;
        int rowPosition;
        int numberPosition;
        double weight = 0.0;
        double weightTotal = 0.0;
        double averageWeight = 0.0;
        int index;

        //Asks for row letter and checks if it is in range
        System.out.print("Please enter row letter: ");
        rowLetter = Character.toUpperCase(keyboard.next().charAt(0));;

        while(rowLetter >= ((int)('A')+ row)){
            System.out.print("ERROR: Out of range, try again ");
            rowLetter = Character.toUpperCase(keyboard.next().charAt(0));;
        }//end of while loop

        //Converts row letter to a number
        rowPosition= rowLetter -'A';

        //Asks for position number and checks if its in range
        System.out.print("Please enter position number (1 to " + bandMembers[rowPosition].length + ")      : ");
        numberPosition = keyboard.nextInt();

        while (numberPosition < 0 || numberPosition > bandMembers[rowPosition].length  ) {
            System.out.print("ERROR: Out of range, try again            : ");
            numberPosition = keyboard.nextInt();
        }//end of while loop

        //Asks the for the weight if the position is vacant
        if(bandMembers[rowPosition][numberPosition-1] == 0.0){
            System.out.print("Please enter weight (45.0 to 200.0)       : ");
            weight = keyboard.nextDouble();
        }else{
            System.out.print("ERROR: There is already a musician there.");
            return;
        }//end of if-else statement

        //Checks if the weight entered exceeds the max or min weight
        while(weight >MAX_WEIGHT || weight < MIN_WEIGHT){
            System.out.print("ERROR: Out of range, try again            : ");
            weight = keyboard.nextDouble();
        }//end of while loop

        //Enters the weight into the row and position in the irregular array
        bandMembers [rowPosition][numberPosition-1] = weight;

        //Adds the weights in each row
        for(index = 0; index < bandMembers[rowPosition].length; index++){
            weightTotal += bandMembers[rowPosition][index];
        }//end of for loop

        //Gets and checks if the average weight limit is exceeded
        averageWeight = (weightTotal/bandMembers[rowPosition].length);
        if(averageWeight > MAX_WEIGHT_PER_POSITION){
            System.out.print("ERROR: That would exceed the average weight limit.");
            bandMembers[rowPosition][numberPosition-1] = 0;
        }//end of if statement

        System.out.println("****** Musician added.");
    } // end of the addMusician method

    /** Method to print out the irregular array
     * @param rows
     * @param weight (irregular array entered)
     */
    private static void printAssignment(int rows,double[][]weight) {

        int index;
        int jindex;
        //Prints out the irregular array and gets total weight of the row
        for (index = 0; index < rows; index++) {
            System.out.print((char) ('A' + index) + ": ");
            double totalWeight = 0;
            int numOccupiedPositions = 0;

            for (jindex = 0; jindex < weight[index].length; jindex++) {
                if (weight[index][jindex] != 0) {
                    System.out.print(weight[index][jindex] + "   ");
                    totalWeight += weight[index][jindex];
                    numOccupiedPositions++;
                } else {
                    System.out.print("0.0   ");
                    numOccupiedPositions++;
                }//end of if-else statement

            }//end of inside for loop

            //Gets and prints out average weight
            double averageWeight = numOccupiedPositions == 0 ? 0 : totalWeight / numOccupiedPositions;
            System.out.println("[ " + totalWeight + ", " + averageWeight + " ]");
        }//end of outside for loop
    }//end of printAssignment method

    /** Method to remove band members irregular array
     * @param row
     * @param bandMembers (irregular array entered)
     */
    private static void removeMusician(int row,double[][] bandMembers){
        //Variables for this method
        char rowLetter;
        int rowPosition;
        int numberPosition;
        int index;

        //Asks for row letter and checks if it is in range
        System.out.print("Please enter row letter: ");
        rowLetter = Character.toUpperCase(keyboard.next().charAt(0));;

        while(rowLetter >= ((int)('A')+ row)){
            System.out.print("ERROR: Out of range, try again ");
            rowLetter = Character.toUpperCase(keyboard.next().charAt(0));;
        }//end of while loop

        //Converts row letter to a number
        rowPosition= rowLetter -'A';

        //Asks for position number and checks if its in range
        System.out.print("Please enter position number (1 to " + bandMembers[rowPosition].length + ")      : ");
        numberPosition = keyboard.nextInt();

        while (numberPosition < 0 || numberPosition > bandMembers[rowPosition].length  ) {
            System.out.print("ERROR: Out of range, try again            : ");
            numberPosition = keyboard.nextInt();
        }//end of while loop

        //Checks if position is vacant and returns back to the main method
        if(bandMembers[rowPosition][numberPosition - 1] == 0.0){
            System.out.print("ERROR: That position is vacant.");
            return;
        }//end of if statement

        //If position is not vacant it removes the band member
        bandMembers[rowPosition][numberPosition-1] = 0.0;

        System.out.print("****** Musician removed.");
    }//end of removeMusician method

}//end of BandOfTheHour class
