package edu.hillel.homework.implementation;

import edu.hillel.homework.exceptions.FileIsDirectoryException;
import edu.hillel.homework.exceptions.NoAccessRightsException;
import edu.hillel.homework.exceptions.NotFoundFileException;
import edu.hillel.homework.iostream.ListOfNumbers;

import java.io.IOException;

public class ListOfNumbersDemo {

    public static void main(String[] args) {

        //пример использования методов ListOfNumbers с обработкой всех возможных исключений

        String fileLocation = "C:\\Users\\Public\\Documents\\ForJava\\test\\file.txt";
        ListOfNumbers listOfNumbers = new ListOfNumbers();

        try {
            listOfNumbers.writeContentToFile(fileLocation);
            listOfNumbers.readContentFromFile(fileLocation);
        } catch (FileIsDirectoryException e) {
            e.printStackTrace();
        } catch (NotFoundFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoAccessRightsException e) {
            e.printStackTrace();
        }

        System.out.println("New list:\n");
        for (int i = 0; i < listOfNumbers.getList().size(); i++) {
            System.out.print(listOfNumbers.getList().get(i));
        }
    }
}
