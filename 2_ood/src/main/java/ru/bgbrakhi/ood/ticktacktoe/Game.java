package ru.bgbrakhi.ood.ticktacktoe;


import java.util.Random;

public class Game implements IGame {

    private IModel model;
    private IPrinter printer;
    private IInput input;

    public Game(IModel model, IPrinter printer, IInput input) {
        this.model = model;
        this.printer = printer;
        this.input = input;
    }

    public void set(int value, int line, int col) {
        model.set(value, line, col);
        printer.print();
    }

    @Override
    public String getWinner() {
        model.initData();

        Random rnd = new Random();
        set(1, rnd.nextInt(model.size()), rnd.nextInt(model.size()));

        do {
            Position position = getPos(input.getInput());
            if (position != null) {
                set(2, position.getLine(), position.getCol());
            } else {
                break;
            }

            if (!model.gameOver()) {
                position = model.getRandomPos();
                if (position != null) {
                    set(1, position.getLine(), position.getCol());
                }
            }
        } while (!model.gameOver());
        System.out.println(String.format("Winner is : %s !!! ", model.getWinner()));
        return model.getWinner();
    }

    private Position getPos(String input) {
        Position result = null;

        String[] data = input.split(" ");
        try {
            if (data.length != 2 || Integer.valueOf(data[0]) >= model.size() || Integer.valueOf(data[1]) >= model.size()) {
                System.out.println("Invalid input");
            } else {
                result = new Position(Integer.valueOf(data[0]), Integer.valueOf(data[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        IModel model = new Model();
        IPrinter printer = new Printer(model);
        IInput input = new Input();
        Game game = new Game(model, printer, input);
        game.getWinner();
    }
}
