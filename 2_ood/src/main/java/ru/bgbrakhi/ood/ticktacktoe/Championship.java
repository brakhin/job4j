package ru.bgbrakhi.ood.ticktacktoe;

public class Championship {

    private IGame game;

    public Championship(IGame game) {
        this.game = game;
    }

    public String getChampion() {
        int crossContiniousWins = 0;
        int zeroContiniousWins = 0;
        int roundNum = 1;
        String result;

        do {
            System.out.println(String.format("------------ ROUND %d ------------", roundNum++));
            switch (game.getWinner()) {
                case "CROSS" :
                    crossContiniousWins++;
                    zeroContiniousWins = 0;
                    break;
                case "ZERO" :
                    crossContiniousWins = 0;
                    zeroContiniousWins++;
                    break;
                default:
                    crossContiniousWins = 0;
                    zeroContiniousWins = 0;
            }
            System.out.println(String.format("CROSS continious wins : %d, ZERO continious wins : %d", crossContiniousWins, zeroContiniousWins));
        } while (crossContiniousWins < 5 && zeroContiniousWins < 5);
        if (crossContiniousWins == 5) {
            result = "CROSS";
        } else {
            result = "ZERO";
        }
        return result;
    }

    public static void main(String[] args) {
        IModel model = new Model();
        IPrinter printer = new Printer(model);
        IInput input = new Input();
        IGame game = new Game(model, printer, input);
        Championship championship = new Championship(game);
        System.out.println(String.format("The champion is %s", championship.getChampion()));
    }
}
