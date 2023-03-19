package org.example.validator;

import org.example.type.ExitStatus;

public class ArgumentValidator {

    private static final int FIRST_COLUMN_NUMBER = 0;

    private static final int LAST_COLUMN_NUMBER = 13;

    public static int checkAndGetColumnNumberOrExit(String[] args) {
        if (args.length != 1) {
            System.out.println("Не правильное количество аргументов.");
            System.exit(ExitStatus.SUCCESS.count);
        } else {
            int columnNumber = Integer.parseInt(args[FIRST_COLUMN_NUMBER]) - 1;
            if (columnNumber < FIRST_COLUMN_NUMBER || columnNumber > LAST_COLUMN_NUMBER) {
                System.out.println("Такой колонки в файле нет.");
                System.exit(ExitStatus.SUCCESS.count);
            }
            return columnNumber;
        }
        throw new RuntimeException();
    }
}
