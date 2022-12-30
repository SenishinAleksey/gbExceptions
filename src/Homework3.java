import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Homework3 {

    private static Set<String> namesSet;
    private static Set<String> surnamesSet;
    private static Map<String, String> userData;

    public static void main(String[] args) {
        fillSets();
        requestUserData();
        saveUserDataToFile();
    }

    private static void saveUserDataToFile() {
        if (userData.size() != 6) {
            System.out.println("Собраны не все данные, сохранение в файл не возможно");
            return;
        }
        StringBuilder str = new StringBuilder();
        str.append(userData.get("surname")).append(" ");
        str.append(userData.get("name")).append(" ");
        str.append(userData.get("patronymic")).append(" ");
        str.append(userData.get("birthday")).append(" ");
        str.append(userData.get("phone")).append(" ");
        str.append(userData.get("gender")).append("\n");
        File file = new File(userData.get("surname"));
        try (FileWriter fr = new FileWriter(file, true)) {
            fr.write(str.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void requestUserData() {
        userData = new HashMap<>();
        System.out.println("Введите следующие данные в произвольном порядке: фамилия, имя, отчество, дата рождения, номер телефона, пол");
        Scanner scanner = new Scanner(System.in);
        List<String> userInputs;
        while (true) {
            userInputs = Arrays.asList(scanner.nextLine().split(" "));
            if (userInputs.size() < 6) {
                System.out.println("Введены не все данные, повторите ввод");
            } else if (userInputs.size() > 6) {
                System.out.println("Введены лишние данные, повторите ввод");
            } else {
                break;
            }
        }
        try {
            getBirthday(userInputs);
            getPhone(userInputs);
            getGender(userInputs);
            getFio(userInputs);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getFio(List<String> userInputs) {
        Map<String, Boolean> fio = new HashMap<>();
        userInputs.forEach(input -> {
            if (Pattern.matches("\\D{2,}", input)) {
                fio.put(input, false);
            }
        });
        if (fio.size() != 3) {
            throw new RuntimeException("Некорректные ФИО");
        }
        fio.forEach((input, used) -> {
            if (namesSet.contains(input.toLowerCase())) {
                userData.put("name", input);
                fio.put(input, true);
            }
            if (surnamesSet.contains(input.toLowerCase())) {
                userData.put("surname", input);
                fio.put(input, true);
            }
        });
        if (userData.get("name") == null) {
            System.out.println("Значение имени не опрелено, берется случайное из введенных значений");
            fio.forEach((input, used) -> {
                if (!used) {
                    userData.put("name", input);
                    fio.put(input, true);
                }
            });
        }
        if (userData.get("surname") == null) {
            System.out.println("Значение фамилии не опрелено, берется случайное из введенных значений");
            fio.forEach((input, used) -> {
                if (!used) {
                    userData.put("name", input);
                    fio.put(input, true);
                }
            });
        }
        fio.forEach((input, used) -> {
            if (!used) {
                userData.put("patronymic", input);
            }
        });
    }

    private static void getGender(List<String> userInputs) {
        userInputs.stream().filter(input -> Pattern.matches("[fm]", input))
                .forEach(input -> userData.put("gender", input));
        if (userData.get("gender") == null) {
            throw new RuntimeException("Пол должен быть задан буквами m/f");
        }
    }

    private static void getBirthday(List<String> userInputs) {
        userInputs.stream().filter(input -> Pattern.matches("[0-3]\\d\\.[01][0-2]\\.[0-2]\\d{3}", input))
                .forEach(input -> userData.put("birthday", input));
        if (userData.get("birthday") == null) {
            throw new RuntimeException("Дата рождения должна быть задана в формате дд.мм.гггг");
        }
    }

    private static void getPhone(List<String> userInputs) {
        userInputs.stream().filter(input -> Pattern.matches("\\d+", input))
                .forEach(input -> userData.put("phone", input));
        if (userData.get("phone") == null) {
            throw new RuntimeException("Номер телефона должен содержать только цифры");
        }
    }

    private static void fillSets() {
        namesSet = new HashSet<>();
        surnamesSet = new HashSet<>();
        File namesFile = new File("russian_names.csv");
        File surnamesFile = new File("russian_surnames.csv");
        if (!namesFile.exists() || !surnamesFile.exists()) {
            List<File> files = new ArrayList<>();
            files.add(namesFile);
            files.add(surnamesFile);
            prepareFiles(files);
        }
        fillSet(namesSet, namesFile);
        fillSet(surnamesSet, surnamesFile);
    }

    private static void fillSet(Set<String> set, File file) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine();
            String[] cols;
            while (reader.ready()) {
                cols = reader.readLine().split(";");
                set.add(cols[1].toLowerCase());
            }
            reader.close();
        } catch (Exception e) {

        }
    }

    private static void prepareFiles(List<File> files) {
        try {
            File namesDbZip = new File("names_db.zip");
            System.out.print("Загрузка списков имен и фамилий... ");
            URL url = new URL("https://goo.su/qxizI");
            InputStream urlStream = url.openStream();
            Files.copy(urlStream, Paths.get(namesDbZip.getPath()));
            urlStream.close();
            ZipInputStream zipStream = new ZipInputStream(new FileInputStream(namesDbZip));
            ZipEntry zipEntry;
            while ((zipEntry = zipStream.getNextEntry()) != null) {
                for (File file : files) {
                    if (zipEntry.getName().equals(file.getName())) {
                        unzipFile(zipStream, file);
                    }
                }
            }
            zipStream.close();
            namesDbZip.delete();
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("Ошибка, будем работать наобум");
        }
    }

    private static void unzipFile(ZipInputStream zipStream, File file) throws Exception {
        FileOutputStream fileStream = new FileOutputStream(file);
        for (int c = zipStream.read(); c != -1; c = zipStream.read()) {
            fileStream.write(c);
        }
        fileStream.flush();
        zipStream.closeEntry();
        fileStream.close();
    }
}
