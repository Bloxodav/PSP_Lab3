package hospital.menu;

import hospital.manager.HospitalManager;
import hospital.model.*;
import hospital.util.InputHelper;

public class MainMenu {

    private HospitalManager manager;

    private boolean isAdmin;

    private static final String ADMIN_PASSWORD = "admin123";

    public MainMenu() {
        this.manager = new HospitalManager();
        this.isAdmin = false;
        manager.loadData();
    }

    public void start() {

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = InputHelper.readIntInRange("Выберите пункт: ", 0, isAdmin ? 20 : 15);

            switch (choice) {
                case 1:  manager.showAll(); break;
                case 2:  manager.showAllDetailed(); break;
                case 3:  searchMenu(); break;
                case 4:  filterMenu(); break;
                case 5:  sortMenu(); break;
                case 6:  manager.showStatistics(); break;
                case 7:  manager.exportCSV(); break;

                case 8:  loginMenu(); break;

                case 9:
                    if (requireAdmin()) addPersonMenu();
                    break;
                case 10:
                    if (requireAdmin()) deletePersonMenu();
                    break;
                case 11:
                    if (requireAdmin()) editMenu();
                    break;
                case 12:
                    if (requireAdmin()) {
                        String name = InputHelper.readString("Имя пациента для госпитализации: ");
                        manager.hospitalizePatient(name);
                    }
                    break;
                case 13:
                    if (requireAdmin()) {
                        String name = InputHelper.readString("Имя пациента для выписки: ");
                        manager.dischargePatient(name);
                    }
                    break;
                case 14:
                    if (requireAdmin()) manager.saveData();
                    break;
                case 15:
                    if (requireAdmin()) manager.loadData();
                    break;

                case 0:
                    System.out.println("До свидания! Данные сохранены.");
                    manager.saveData();
                    running = false;
                    break;

                default:
                    System.out.println("✗ Неверный пункт меню.");
            }
        }
    }

    private void searchMenu() {
        String keyword = InputHelper.readString("Введите имя или часть имени: ");
        manager.searchByName(keyword);
    }

    private void filterMenu() {
        System.out.println("\n── Фильтрация ──");
        System.out.println("1. По роли");
        System.out.println("2. По возрасту");
        System.out.println("3. По отделению");
        System.out.println("4. По роли + отделению");

        int choice = InputHelper.readIntInRange("Выберите: ", 1, 4);

        switch (choice) {
            case 1:
                System.out.println("Роли: Врач / Медсестра / Пациент");
                String role = InputHelper.readString("Роль: ");
                manager.filterByRole(role);
                break;
            case 2:
                int minAge = InputHelper.readInt("Минимальный возраст: ");
                int maxAge = InputHelper.readInt("Максимальный возраст: ");
                manager.filterByAge(minAge, maxAge);
                break;
            case 3:
                String dept = InputHelper.readString("Отделение: ");
                manager.filterByDepartment(dept);
                break;
            case 4:
                String r = InputHelper.readString("Роль: ");
                String d = InputHelper.readString("Отделение: ");
                manager.filterByRoleAndDepartment(r, d);
                break;
        }
    }

    private void sortMenu() {
        System.out.println("\n── Сортировка ──");
        System.out.println("1. По имени (А→Я)");
        System.out.println("2. По имени (Я→А)");
        System.out.println("3. По отделению (А→Я)");
        System.out.println("4. По отделению (Я→А)");

        int choice = InputHelper.readIntInRange("Выберите: ", 1, 4);

        switch (choice) {
            case 1: manager.sortByName(true); break;
            case 2: manager.sortByName(false); break;
            case 3: manager.sortByDepartment(true); break;
            case 4: manager.sortByDepartment(false); break;
        }
    }

    private void loginMenu() {
        if (isAdmin) {
            System.out.println("Вы уже администратор. Выйти из режима? (да/нет)");
            String answer = InputHelper.readString("> ");
            if (answer.equalsIgnoreCase("да")) {
                isAdmin = false;
                System.out.println("Вы вышли из режима администратора.");
            }
        } else {
            String password = InputHelper.readString("Введите пароль администратора: ");
            if (password.equals(ADMIN_PASSWORD)) {
                isAdmin = true;
                System.out.println("✓ Добро пожаловать, администратор!");
            } else {
                System.out.println("✗ Неверный пароль.");
            }
        }
    }

    private void addPersonMenu() {
        System.out.println("\n── Добавить ──");
        System.out.println("1. Врача");
        System.out.println("2. Медсестру");
        System.out.println("3. Пациента");

        int choice = InputHelper.readIntInRange("Выберите: ", 1, 3);

        String name = InputHelper.readString("Имя: ");
        int age = InputHelper.readIntInRange("Возраст: ", 0, 120);
        String dept = InputHelper.readString("Отделение: ");

        switch (choice) {
            case 1:
                String spec = InputHelper.readString("Специализация: ");
                double salary = InputHelper.readDouble("Зарплата: ");
                String category = InputHelper.readString("Категория (Высшая/Первая/Вторая): ");
                manager.addPerson(new Doctor(name, age, dept, spec, salary, category));
                break;

            case 2:
                String specN = InputHelper.readString("Специализация: ");
                double salaryN = InputHelper.readDouble("Зарплата: ");
                String shift = InputHelper.readString("Смена (День/Ночь/Сутки): ");
                int exp = InputHelper.readIntInRange("Опыт (лет): ", 0, 60);
                manager.addPerson(new Nurse(name, age, dept, specN, salaryN, shift, exp));
                break;

            case 3:
                int room = InputHelper.readIntInRange("Номер палаты: ", 1, 999);
                manager.addPerson(new Patient(name, age, dept, room));
                break;
        }
    }

    private void deletePersonMenu() {
        String name = InputHelper.readString("Имя для удаления: ");
        manager.deletePerson(name);
    }

    private void editMenu() {
        System.out.println("\n── Редактировать ──");
        System.out.println("1. Диагноз пациента");
        System.out.println("2. Специализацию медработника");

        int choice = InputHelper.readIntInRange("Выберите: ", 1, 2);

        switch (choice) {
            case 1:
                String patName = InputHelper.readString("Имя пациента: ");
                String diagnosis = InputHelper.readString("Новый диагноз: ");
                manager.editPatientDiagnosis(patName, diagnosis);
                break;
            case 2:
                String medName = InputHelper.readString("Имя медработника: ");
                String spec = InputHelper.readString("Новая специализация: ");
                manager.editMedicalSpecialization(medName, spec);
                break;
        }
    }

    private boolean requireAdmin() {
        if (!isAdmin) {
            System.out.println("✗ Доступ только для администратора. Войдите через пункт 8.");
            return false;
        }
        return true;
    }

    private void printMainMenu() {
        String mode = isAdmin ? "[РЕЖИМ: АДМИНИСТРАТОР]" : "[РЕЖИМ: ПОЛЬЗОВАТЕЛЬ]";
        System.out.println(mode);
        System.out.println("1.  Список всех (кратко)");
        System.out.println("2.  Список всех (подробно) ");
        System.out.println("3.  Поиск по имени");
        System.out.println("4.  Фильтрация");
        System.out.println("5.  Сортировка");
        System.out.println("6.  Статистика");
        System.out.println("7.  Экспорт в CSV");
        System.out.println("8.  " + (isAdmin ? "Выйти из режима адм." : "Войти как администратор") + " ");
        System.out.println("[ТОЛЬКО ДЛЯ АДМИНИСТРАТОРА]");
        System.out.println("9.  Добавить запись");
        System.out.println("10. Удалить запись");
        System.out.println("11. Редактировать запись");
        System.out.println("12. Госпитализировать пациента");
        System.out.println("13. Выписать пациента");
        System.out.println("14. Сохранить данные");
        System.out.println("15. Загрузить данные");
        System.out.println("0.  Выход");
    }
}