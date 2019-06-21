package edu.hillel.homework.iostream;

import edu.hillel.homework.exceptions.FileIsDirectoryException;
import edu.hillel.homework.exceptions.NoAccessRightsException;
import edu.hillel.homework.exceptions.NotFoundFileException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListOfNumbers {
    /*1) Создать класс код которого указан выше:
      2) в классе ListOfNumbers создать метод writeContentToFile(String fileLocation) - который должен записать
      в файл содержимое списка (каждое значение на новой строке) в файл fileLocation
      если файла fileLocation не существует - создать его
      3) в классе ListOfNumbers создать метод readContentFromFile(String fileLocation)
      в котором считать из файла данные в список list из файла fileLocation (считать что в файле fileLocation каждое значение расположено на новой строке).
      - если файла fileLocation не существует - реализовать собственное исключение и бросить его
      - если файл пустой - реализовать собственное исключение и бросить его

      Создать класс ListOfNumbersDemo - где показать пример использования методов ListOfNumbers с обработкой всех возможных исключений.*/

    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers() {
        list = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++)
            list.add(new Integer(i));
    }

    public List<Integer> getList() {
        return list;
    }

    public void writeContentToFile(String fileLocation) throws FileIsDirectoryException, NotFoundFileException, IOException, NoAccessRightsException {

        File file = new File(fileLocation);
        Writer writer = null;

        try {
            if (file.isDirectory()) {
                throw new FileIsDirectoryException("File selection error!");
            }
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new NotFoundFileException("Unable to create a file!");
                }
            } else if (!file.canWrite()) {
                throw new NoAccessRightsException("Check access rights!");
            }
            writer = new FileWriter(fileLocation, false);


            for (int i = 0; i < list.size(); i++) {
                writer.write(list.get(i).toString() + System.lineSeparator());
            }
        } finally {
            writer.close();
        }
    }

    public void readContentFromFile(String fileLocation) throws IOException, FileIsDirectoryException, NotFoundFileException, NoAccessRightsException {
        File file = new File(fileLocation);
        FileInputStream inputStream = null;
        int readByte;

        try {
            inputStream = new FileInputStream(file);
            if (file.isDirectory()) {
                throw new FileIsDirectoryException("File selection error!");
            }
            if (!file.exists()) {
                throw new NotFoundFileException("Unable to create a file!");
            } else if (!file.canRead()) {
                throw new NoAccessRightsException("Check access rights!");
            }
            while ((readByte = inputStream.read()) != -1) {
                if (readByte == '\n' || readByte == '\r') {
                    continue;
                }
                getList().add(Character.getNumericValue(readByte));
            }
        } finally {
            inputStream.close();
        }
    }
}
