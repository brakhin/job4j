package ru.bgbrakhi.ood.ticktacktoe;

public class Printer implements IPrinter {

    private IModel model;
    public static final String LN = System.getProperty("line.separator");

    public Printer(IModel model) {
        this.model = model;
    }

    @Override
    public void print() {
        int value;

        StringBuilder builder = new StringBuilder();
        builder.append("  ");
        for (int col = 0; col < model.size(); col++) {
            builder.append(col).append(" ");
        }
        builder.append(LN);
        for (int line = 0; line < model.size(); line++) {
            builder.append(line).append(" ");
            for (int col = 0; col < model.size(); col++) {
                value = model.get(line, col);
                builder.append(value == 0 ? "*" : value == 1 ? "X" : "O").append(" ");
            }
            builder.append(LN);
        }
        System.out.println(builder.toString());
    }
}
