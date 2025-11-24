package ru.stqa.ptf.addressbook.folderSeven.tests.db;

import org.testng.annotations.Test;
import ru.stqa.ptf.addressbook.folderOneToSix.model.GroupData;
import ru.stqa.ptf.addressbook.folderOneToSix.model.Groups;

import java.sql.*;

//Folder 7.1
public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null; // фундаментальный строительный блок для любого взаимодействия Java с базами данных, т. е. устанавливает физическое соединение с БД
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
            Statement st = conn.createStatement(); // интерфейс в JDBC, который представляет собой "контейнер" для SQL-запроса
            ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list"); //"курсор" для навигации по результатам запроса
            Groups groups = new Groups();
            while (rs.next()) { // В каждый момент времени он указывает на одну текущую строку
                groups.add(new GroupData() //Метод next() сдвигает курсор на следующую строку и возвращает true, если строки еще есть
                        .withId(rs.getInt("group_id"))
                        .withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header"))
                        .withFooter(rs.getString("group_footer")));
            }
            rs.close(); //освобождаем память от результатов
            st.close(); //завершаем работу с запросом
            conn.close(); //закрываем соединение с БД
            // !!!Предотвращает утечки памяти и блокировку соединений.

            System.out.println(groups);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}//Итог -> Получаем те же данные (группы Test 0, Test 1, Test 2...), но гораздо быстрее, чем через браузер.