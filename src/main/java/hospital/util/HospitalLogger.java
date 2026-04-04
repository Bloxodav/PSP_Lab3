package hospital.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HospitalLogger {

    private static final String LOG_FILE = "hospital_log.txt";

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static HospitalLogger instance;

    private HospitalLogger() {
        log("=== Журнал операций больницы запущен ===");
    }

    public static HospitalLogger getInstance() {
        if (instance == null) {
            instance = new HospitalLogger();
        }
        return instance;
    }

    public void log(String message) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        String logEntry = "[" + timestamp + "] " + message;

        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            writer.println(logEntry);
        } catch (IOException e) {
            System.err.println("Ошибка записи в журнал: " + e.getMessage());
        }

        System.out.println("[ЖУРНАЛ] " + logEntry);
    }

    public void logAdd(String role, String name) {
        log("ДОБАВЛЕН: " + role + " — " + name);
    }

    public void logDelete(String role, String name) {
        log("УДАЛЁН: " + role + " — " + name);
    }

    public void logEdit(String role, String name, String field) {
        log("ИЗМЕНЕНО: " + role + " " + name + " → поле: " + field);
    }

    public void logHospitalize(String name, String department) {
        log("ГОСПИТАЛИЗАЦИЯ: " + name + " → отделение: " + department);
    }

    public void logDischarge(String name) {
        log("ВЫПИСКА: " + name);
    }

    public void logSave() {
        log("ДАННЫЕ СОХРАНЕНЫ в файл");
    }

    public void logLoad() {
        log("ДАННЫЕ ЗАГРУЖЕНЫ из файла");
    }
}