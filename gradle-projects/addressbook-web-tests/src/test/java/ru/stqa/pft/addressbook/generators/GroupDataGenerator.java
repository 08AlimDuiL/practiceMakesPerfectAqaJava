package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Генератор тестовых данных для групп адресной книги.
 * Создает файл в формате CSV с указанным количеством тестовых групп.
 */
//description = "Folder 6.2"
public class GroupDataGenerator {
    /**
     * Точка входа в программу генерации тестовых данных.
     * Ожидает два аргумента командной строки:
     * 1. Количество групп для генерации
     * 2. Name выходного файла
     *
     * @param args аргументы командной строки
     *             args[0] - количество групп (целое число)
     *             args[1] - путь к выходному файлу
     * @throws IOException если возникает ошибка ввода-вывода при работе с файлом
     * Например: java GroupDataGenerator 5 groups.csv
     */
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<GroupData> groups = generateGroups(count);
        save(groups, file);
    }

    /**
     * Сохраняет список групп в файл в формате CSV.
     * Формат каждой строки: "название;заголовок;футер"
     *
     * @param groups список групп для сохранения
     * @param file   файл для записи данных
     * @throws IOException если возникает ошибка записи в файл
     */
    private static void save(List<GroupData> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getName(), group.getHeader(), group.getFooter()));
        }
        writer.close();
    }

    /**
     * Генерирует список тестовых групп.
     * Каждая группа имеет уникальное имя, заголовок и футер в формате:
     * - мя: "test {номер}"
     * - Заголовок: "header {номер}"
     * - Футер: "footer {номер}"
     *
     * @param count количество групп для генерации
     * @return список сгенерированных групп
     */
    private static List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData()
                    .withName(String.format("test %s", i))
                    .withHeader(String.format("header %s", i))
                    .withFooter(String.format("footer %s", i)));
        }

        return groups;
    }
}
