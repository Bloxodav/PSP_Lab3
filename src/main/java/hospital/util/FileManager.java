package hospital.util;

import hospital.model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String DATA_FILE = "hospital_data.dat";
    private static final String CSV_FILE = "hospital_data.csv";

    public static void saveToFile(List<Person> persons) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {

            oos.writeObject(persons);
            System.out.println("Данные сохранены в файл: " + DATA_FILE);

        } catch (IOException e) {
            System.err.println("Ошибка сохранения данных: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Person> loadFromFile() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("Файл данных не найден. Начинаем с пустой базы.");
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {

            List<Person> persons = (List<Person>) ois.readObject();
            System.out.println("Данные загружены из файла: " + DATA_FILE
                    + " (" + persons.size() + " записей)");
            return persons;

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка загрузки данных: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void exportToCSV(List<Person> persons) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) {

            writer.println("Роль,Имя,Возраст,Отделение");

            for (Person p : persons) {
                writer.println(p.getRole() + "," + p.getName() + ","
                        + p.getAge() + "," + p.getDepartment());
            }

            System.out.println("Данные экспортированы в CSV: " + CSV_FILE);

        } catch (IOException e) {
            System.err.println("Ошибка экспорта в CSV: " + e.getMessage());
        }
    }
}