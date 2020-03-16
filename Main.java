import java.util.*;

public class Main {

    public static void main(String args[]) {
        //variables for the user to type
        Scanner input = new Scanner(System.in);
        int num1, num2;
        int depth;
        char c;
        char play_again = 'Y';

        try {
            while (play_again == 'Y') {

                do {
                    System.out.print("Enter number 0 for user and 1 for computer: "); //check if the user inserted 0,1 or rubbish
                    num1 = input.nextInt();

                    if (num1 != 0 && num1 != 1) {
                        System.out.print("No such play. Try again!!!");
                    }

                } while (num1 != 0 && num1 != 1);


                do {

                    System.out.print("Enter the depth of your search: "); //check if the user inserted positive
                    depth = input.nextInt();
                    if (depth <= 0) {
                        System.out.print("Not positive depth. Try again!!!");
                    }

                } while (depth <= 0);

                //Input data has been inserted correctly.
                boolean move = false;
                State reversi = new State(null, 0, 'B');
                reversi.tableInitialization();
                reversi.printTable();

                reversi.checkRules('B');

                //in this case the player makes a move first.
                if (num1 == 0) {
                    reversi.printWithPossibleMoves();

                    do {//here we make sure the user has chosen one of the valid tiles.
                        System.out.print("Select one of the possible row of the tile for your next move (A-H): ");
                        c = input.next().toUpperCase().charAt(0);
                        int row = c - 'A';//converting the letters to numbers,according to ASCII encoding.
                        while (row < 0 || row > 7) {
                            System.out.println("Your input was incorrect.Please insert again.");
                            c = input.next().toUpperCase().charAt(0);
                            row = c - 'A';
                        }


                        System.out.print("Select one of the possible column of the tile for your next move (0-7): ");
                        num2 = input.nextInt();
                        while (num2 < 0 || num2 > 7) {
                            System.out.println("Your input was incorrect.Please insert again.");
                            num2 = input.nextInt();
                        }

                        if (!reversi.possible_rows.isEmpty()) {
                            for (int i = 0; i < reversi.possible_rows.size(); i++) {
                                if (reversi.possible_rows.get(i) == row && reversi.possible_columns.get(i) == num2) {
                                    move = true;

                                    for (int k = 0; k < reversi.children.size(); k++) {
                                        if (reversi.children.get(k).reversi_table[row][num2] != '*') {
                                            reversi = reversi.children.get(k);
                                            reversi.printTable();
                                            break;
                                        }
                                    }

                                }

                            }
                            if (move == false) {
                                System.out.println("You Can't place a tile at this position!!! TRY AGAIN... ");
                            }
                        }
                    } while (move == false);

                }
                boolean playerLast = true;//true if the player played last in the last iteration,false otherwise.
                int done = 0;
                while (done < 2) { //here is the computer's turn to play.
                    move = false;
                    done = 0;
                    if (!reversi.skip_turn()) {
                        MiniMax_DFS.creatingDFS(reversi, depth + reversi.getCurrent_depth(), reversi.player, "max");
                        if (!reversi.getChildren().isEmpty()) {
                            reversi = reversi.getChildren().get(0);
                        }
                    } else {
                        System.out.println("There are no possible moves for the computer.");
                        done++;
                    }

                    if (done == 1 && !playerLast) {
                        break;
                    }

                    playerLast = true;

                    if (num1 == 0) {
                        reversi.checkRules('B');
                    } else {
                        reversi.checkRules('W');
                    }

                    //here we check if the player has an available move in order to play.Otherwise we skip his turn and the computer makes a move.
                    if (reversi.skip_turn()) {
                        playerLast = false;
                        System.out.println("You don't have any more available moves.");
                        done++;
                    } else {
                        do {//here we make sure the user has chosen one of the valid tiles.
                            System.out.println("Your turn!!!!");
                            reversi.printWithPossibleMoves();
                            System.out.print("Select one of the possible row of the tile for your next move (A-H): ");
                            c = input.next().toUpperCase().charAt(0);
                            int row = c - 'A';
                            while (row < 0 || row > 7) {
                                System.out.println("Your input was incorrect.Please insert again.");
                                c = input.next().toUpperCase().charAt(0);
                                row = c - 'A';
                            }


                            System.out.print("Select one of the possible column of the tile for your next move (0-7): ");
                            num2 = input.nextInt();
                            while (num2 < 0 || num2 > 7) {
                                System.out.println("Your input was incorrect.Please insert again.");
                                num2 = input.nextInt();
                            }
                            if (reversi.reversi_table[row][num2] == '*') {

                                for (int i = 0; i < reversi.possible_rows.size(); i++) {
                                    if (reversi.possible_rows.get(i) == row && reversi.possible_columns.get(i) == num2) {
                                        move = true;

                                        for (int k = 0; k < reversi.children.size(); k++) {
                                            if (reversi.children.get(k).reversi_table[row][num2] != '*') {
                                                reversi = reversi.children.get(k);
                                                reversi.printTable();
                                                break;
                                            }
                                        }

                                    }

                                }
                            }
                            if (move == false) {
                                System.out.println("You Can't place a tile at this position!!! TRY AGAIN... ");
                            }

                        } while (move == false);
                    }
                }

                if (reversi.countChar()[1] > reversi.countChar()[2]) {
                    if (num1 == 0) {
                        System.out.println("Congratulations.You won.");
                    } else {
                        System.out.println("Sorry you lost.Better luck next time.");
                    }

                } else if (reversi.countChar()[1] == reversi.countChar()[2]) {
                    System.out.println("It's a tie.");
                } else {
                    if (num1 == 0) {
                        System.out.println("Sorry you lost.Better luck next time.");
                    } else {
                        System.out.println("Congratulations.You won.");
                    }
                }
                System.out.println("**************************************** ");
                System.out.println("Do you want to play again? Press Y or N: ");
                play_again = input.next().toUpperCase().charAt(0);
                while (play_again != 'Y' && play_again != 'N') {
                    System.out.println("Your input was incorrect.Please insert again.");
                    play_again = input.next().toUpperCase().charAt(0);

                }

            }
        } catch (InputMismatchException e) {

            System.out.println("Wrong kind of input.");

        }
    }
}