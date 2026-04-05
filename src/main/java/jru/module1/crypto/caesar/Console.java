package jru.module1.crypto.caesar;

import jru.module1.crypto.caesar.exception.AlphabetException;
import jru.module1.crypto.caesar.exception.FileManagerException;

import java.util.Scanner;

public class Console {

    private final CaesarService caesarService;
    private final Scanner scanner;

    public Console() {
        this.caesarService = new CaesarService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Добро пожаловать в Caesar Cipher!");
        boolean running = true;

        while (running) {
            printMenu();
            String command = scanner.nextLine();

            try {
                switch (command) {
                    case "1":
                        encryptProcess();
                        break;
                    case "2":
                        decryptProcess();
                        break;
                    case "0":
                        running = false;
                        System.out.println("Выход из программы.");
                        break;
                    default:
                        System.out.println("Неизвестная команда. Попробуйте предложенные.");
                }
            } catch (FileManagerException | AlphabetException | NumberFormatException ex) {
                System.out.println("Ошибка: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Непредвиденная ошибка: " + ex.getMessage());
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - Шифрование");
        System.out.println("2 - Расшифровка с ключом");
        System.out.println("0 - Выход");
        System.out.print("Введите номер команды: ");
    }

    private void encryptProcess() {
        System.out.print("Введите путь к исходному файлу: ");
        String inputFile = scanner.nextLine();

        System.out.print("Введите путь к выходному файлу: ");
        String outputFile = scanner.nextLine();

        System.out.print("Введите ключ: ");
        int key = Integer.parseInt(scanner.nextLine());

        caesarService.encrypt(inputFile, outputFile, key, false);
        System.out.println("Шифрование успешно завершено.");
    }

    private void decryptProcess() {
        System.out.print("Введите путь к зашифрованному файлу: ");
        String inputFile = scanner.nextLine();

        System.out.print("Введите путь к выходному файлу: ");
        String outputFile = scanner.nextLine();

        System.out.print("Введите ключ: ");
        int key = Integer.parseInt(scanner.nextLine());

        caesarService.decrypt(inputFile, outputFile, key, true);
        System.out.println("Расшифровка успешно завершена.");
    }
}
