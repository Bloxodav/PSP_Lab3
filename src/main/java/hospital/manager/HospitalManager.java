package hospital.manager;

import hospital.model.*;
import hospital.util.FileManager;
import hospital.util.HospitalLogger;

import java.util.*;
import java.util.stream.Collectors;

public class HospitalManager {

    private List<Person> persons;
    private HospitalLogger logger;

    public HospitalManager() {
        this.persons = new ArrayList<>();
        this.logger = HospitalLogger.getInstance();
    }

    public void addPerson(Person person) {
        persons.add(person);
        logger.logAdd(person.getRole(), person.getName());
        System.out.println("✓ " + person.getRole() + " '" + person.getName() + "' добавлен.");
    }

    public boolean deletePerson(String name) {
        Optional<Person> found = persons.stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst();

        if (found.isPresent()) {
            Person p = found.get();
            persons.remove(p);
            logger.logDelete(p.getRole(), p.getName());
            System.out.println("✓ Запись '" + name + "' удалена.");
            return true;
        }

        System.out.println("✗ Запись с именем '" + name + "' не найдена.");
        return false;
    }

    public void editPatientDiagnosis(String name, String diagnosis) {
        for (Person p : persons) {
            if (p instanceof Patient && p.getName().equalsIgnoreCase(name)) {
                ((Patient) p).setDiagnosis(diagnosis);
                logger.logEdit("Пациент", name, "диагноз → " + diagnosis);
                System.out.println("✓ Диагноз пациента '" + name + "' обновлён: " + diagnosis);
                return;
            }
        }
        System.out.println("✗ Пациент '" + name + "' не найден.");
    }

    public void editMedicalSpecialization(String name, String specialization) {
        for (Person p : persons) {
            if (p instanceof MedicalPerson && p.getName().equalsIgnoreCase(name)) {
                ((MedicalPerson) p).setSpecialization(specialization);
                logger.logEdit(p.getRole(), name, "специализация → " + specialization);
                System.out.println("✓ Специализация '" + name + "' обновлена: " + specialization);
                return;
            }
        }
        System.out.println("✗ Медработник '" + name + "' не найден.");
    }

    public void showAll() {
        if (persons.isEmpty()) { // Если список пуст
            System.out.println("База данных пуста.");
            return;
        }
        System.out.println("\n══════ СПИСОК ВСЕХ ЗАПИСЕЙ (" + persons.size() + ") ══════");
        for (int i = 0; i < persons.size(); i++) {
            System.out.println((i + 1) + ". " + persons.get(i));
        }
        System.out.println("═══════════════════════════════════");
    }

    public void showAllDetailed() {
        if (persons.isEmpty()) {
            System.out.println("База данных пуста.");
            return;
        }
        System.out.println("\n══════ ПОДРОБНЫЙ СПИСОК ══════");
        for (Person p : persons) {
            p.displayInfo();
            System.out.println();
        }
    }

    public void searchByName(String keyword) {
        System.out.println("\n══════ ПОИСК ПО ИМЕНИ: \"" + keyword + "\" ══════");

        List<Person> results = persons.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            results.forEach(p -> System.out.println("• " + p));
        }
    }

    public void filterByRole(String role) {
        System.out.println("\n══════ ФИЛЬТР ПО РОЛИ: " + role + " ══════");

        List<Person> results = persons.stream()
                .filter(p -> p.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());

        printList(results);
    }

    public void filterByAge(int minAge, int maxAge) {
        System.out.println("\n══════ ФИЛЬТР ПО ВОЗРАСТУ: " + minAge + "–" + maxAge + " ══════");

        List<Person> results = persons.stream()
                .filter(p -> p.getAge() >= minAge && p.getAge() <= maxAge)
                .collect(Collectors.toList());

        printList(results);
    }

    public void filterByDepartment(String department) {
        System.out.println("\n══════ ФИЛЬТР ПО ОТДЕЛЕНИЮ: " + department + " ══════");

        List<Person> results = persons.stream()
                .filter(p -> p.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());

        printList(results);
    }

    public void filterByRoleAndDepartment(String role, String department) {
        System.out.println("\n══════ ФИЛЬТР: роль=" + role + ", отделение=" + department + " ══════");

        List<Person> results = persons.stream()
                .filter(p -> p.getRole().equalsIgnoreCase(role)
                        && p.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());

        printList(results);
    }

    public void sortByName(boolean ascending) {
        Comparator<Person> comp = Comparator.comparing(Person::getName);

        if (!ascending) {
            comp = comp.reversed();
        }

        persons.sort(comp);
        System.out.println("Список отсортирован по имени " + (ascending ? "↑ ASC" : "↓ DESC"));
        showAll();
    }

    public void sortByDepartment(boolean ascending) {
        Comparator<Person> comp = Comparator.comparing(Person::getDepartment);

        if (!ascending) {
            comp = comp.reversed();
        }

        persons.sort(comp);
        System.out.println("Список отсортирован по отделению " + (ascending ? "↑ ASC" : "↓ DESC"));
        showAll();
    }

    public void showStatistics() {
        System.out.println("\n══════════════ СТАТИСТИКА ══════════════");

        System.out.println("Всего записей: " + persons.size());

        long doctors = persons.stream().filter(p -> p instanceof Doctor).count();
        long nurses  = persons.stream().filter(p -> p instanceof Nurse).count();
        long patients = persons.stream().filter(p -> p instanceof Patient).count();

        System.out.println("  Врачей:     " + doctors);
        System.out.println("  Медсестёр:  " + nurses);
        System.out.println("  Пациентов:  " + patients);

        OptionalDouble avgAge = persons.stream()
                .mapToInt(Person::getAge)
                .average();

        System.out.printf("Средний возраст: %.1f лет%n",
                avgAge.orElse(0));

        persons.stream().mapToInt(Person::getAge).min()
                .ifPresent(min -> System.out.println("Мин. возраст: " + min));
        persons.stream().mapToInt(Person::getAge).max()
                .ifPresent(max -> System.out.println("Макс. возраст: " + max));

        System.out.println("\nРаспределение по отделениям:");
        Map<String, Long> byDept = persons.stream()
                .collect(Collectors.groupingBy(Person::getDepartment, Collectors.counting()));

        byDept.forEach((dept, count) ->
                System.out.println("  " + dept + ": " + count + " чел."));

        System.out.println("════════════════════════════════════════");
    }

    public void hospitalizePatient(String name) {
        for (Person p : persons) {
            if (p instanceof Patient && p.getName().equalsIgnoreCase(name)) {
                ((Patient) p).hospitalize();
                logger.logHospitalize(name, p.getDepartment());
                return;
            }
        }
        System.out.println("✗ Пациент '" + name + "' не найден.");
    }

    public void dischargePatient(String name) {
        for (Person p : persons) {
            if (p instanceof Patient && p.getName().equalsIgnoreCase(name)) {
                ((Patient) p).discharge();
                logger.logDischarge(name);
                return;
            }
        }
        System.out.println("✗ Пациент '" + name + "' не найден.");
    }

    public void saveData() {
        FileManager.saveToFile(persons);
        logger.logSave();
    }

    public void loadData() {
        persons = FileManager.loadFromFile();
        logger.logLoad();
    }

    public void exportCSV() {
        FileManager.exportToCSV(persons);
        logger.log("ЭКСПОРТ в CSV выполнен");
    }

    private void printList(List<Person> list) {
        if (list.isEmpty()) {
            System.out.println("Результатов не найдено.");
        } else {
            list.forEach(p -> System.out.println("• " + p));
            System.out.println("Итого: " + list.size() + " записей.");
        }
    }

    public List<Person> getPersons() {
        return persons;
    }
}