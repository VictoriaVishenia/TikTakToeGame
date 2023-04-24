import java.util.*;

class TikTakToeGame {
    private static char [][] BOARD = { {' ', '|', ' ', '|', ' '},
                                       {'-', '+', '-', '+', '-'},
                                       {' ', '|', ' ', '|', ' '},
                                       {'_', '+', '_', '+', '_'},
                                       {' ', '|', ' ', '|', ' '} };
    private static final ArrayList <Integer> PLAYER_POSITIONS = new ArrayList<>();
    private static final ArrayList <Integer> COMP_POSITIONS = new ArrayList<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    public static char[][] getBOARD() {
        return BOARD;
    }
    public static void setBOARD(char[][] BOARD) {
        TikTakToeGame.BOARD = BOARD;
    }
    public static ArrayList<Integer> getPlayersPositions(){
        return PLAYER_POSITIONS;
    }
    public static ArrayList<Integer> getCompsPositions() {
        return COMP_POSITIONS;
    }
    public static Scanner getSCANNER() {
        return SCANNER;
    }

    public static void main(String[] args) {
        printBoard(getBOARD());
         int playerInput;
        while (true) {
            {
                System.out.println("Let's enter cell's number from 1 to 9.");

                while (!SCANNER.hasNextInt()) {

                    try {
                        playerInput = Integer.parseInt(getSCANNER().nextLine());
                        if (playerInput == getSCANNER().nextInt()) {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Dear, it not an integer. Make your choice again.");
                    }
                }
                    playerInput = getSCANNER().nextInt();

                    while (getPlayersPositions().contains(playerInput) ||
                            getCompsPositions().contains(playerInput) ||
                            playerInput > 9 || playerInput < 1) {
                        System.out.println("This cell invalid or just taken. Please, make your choice again.");
                        playerInput = SCANNER.nextInt();
                    }
                }

                inputChoice(getBOARD(), playerInput, "player");
                printBoard(getBOARD());

            String resultWin =  checkWinner();

            if (resultWin.equals("Player win!") || resultWin.equals("Computer win!") ||
                    resultWin.equals("Game over. Nobody win.")) {
                System.out.println(resultWin);
                break;
            }
            Random rand = new Random();
            int cpInput = rand.nextInt(9) + 1;

            while (getCompsPositions().contains(cpInput) || getPlayersPositions().contains(cpInput) ) {
                System.out.println("Mister Computer, this ceil is just taken. Make your choice again");
                cpInput = rand.nextInt(9) + 1;
            }

            inputChoice(getBOARD(), cpInput, "comp");
            printBoard(getBOARD());

            resultWin =  checkWinner();
            if (resultWin.equals("Player win!") || resultWin.equals("Computer win!") ||
                    resultWin.equals("Game over. Nobody win.")) {
                System.out.println(resultWin);
                break;
            }
        }
    }

     static void printBoard(char [][] board) {
        for (char [] cell : board) {
            for (char c: cell) {
                System.out.print(c);
            }
            System.out.println();
        }
        System.out.println("------------------------------");
    }

     static void inputChoice(char [][] board, int input, String player){

        char  symbol = ' ';

        if (Objects.equals(player, "player")) {
            symbol = 'X';
            getPlayersPositions().add(input);
            Collections.sort(getPlayersPositions());
        } else if (Objects.equals(player, "comp")) {
            symbol = '0';
            getCompsPositions().add(input);
            Collections.sort(getCompsPositions());
        }

        switch (input) {
            case 1 -> board[0][0] = symbol;
            case 2 -> board[0][2] = symbol;
            case 3 -> board[0][4] = symbol;
            case 4 -> board[2][0] = symbol;
            case 5 -> board[2][2] = symbol;
            case 6 -> board[2][4] = symbol;
            case 7 -> board[4][0] = symbol;
            case 8 -> board[4][2] = symbol;
            case 9 -> board[4][4] = symbol;
            default -> System.out.println("Некорректный ввод, сделайте выбор еще раз");
        }
    }

     static String checkWinner() {
        List<Integer> firstSet = Arrays.asList(1, 4, 7);
        List<Integer> secondSet = Arrays.asList(2, 5, 8);
        List<Integer> thirdSet = Arrays.asList(3, 6, 9);
        List<Integer> fourthSet = Arrays.asList(1, 2, 3);
        List<Integer> fifthSet = Arrays.asList(4, 5, 6);
        List<Integer> sixthSet = Arrays.asList(7, 8, 9);
        List<Integer> seventhSet = Arrays.asList(1, 5, 9);
        List<Integer> eighthSet= Arrays.asList(3, 5, 7);

        List <List> win = new ArrayList<>();
        win.add(firstSet);
        win.add(secondSet);
        win.add(thirdSet);
        win.add(fourthSet);
        win.add(fifthSet);
        win.add(sixthSet);
        win.add(seventhSet);
        win.add(eighthSet);

        String result = "";

        for (List list :win) {
            if (getPlayersPositions().containsAll(list)){
                result = "Player win!";
            } else if (getCompsPositions().containsAll(list)) {
                result ="Computer win!";
            } else if ((getPlayersPositions().size() + getCompsPositions().size()) == 9) {
                result = "Game over. Nobody win.";
            }
        }
        return result;
    }
}
