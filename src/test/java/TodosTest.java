import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenTaskListIsEmpty() {

        Todos todos = new Todos();

        Task[] expected = {};
        Task[] actual = todos.search("Бином");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSimpleTaskByQuery() {

        SimpleTask simpleTask = new SimpleTask(1, "Открыть счёт в банке");

        Todos todos = new Todos();

        todos.add(simpleTask);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Открыть счёт");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMultipleTuskByQuery() {
        SimpleTask simpleTask1 = new SimpleTask(5, "Установить приложение Нетология");
        SimpleTask simpleTask2 = new SimpleTask(5, "Установить приложение Тестировщик");


        String[] subtasks1 = {"Провести тесты на производительность", "Найти баги", "Составить баг-репорты"};
        String[] subtasks2 = {"Сделать ТО", "Проверить оборудование на производительность", "Составить отчет"};

        Epic epic1 = new Epic(4, subtasks1);
        Epic epic2 = new Epic(6, subtasks2);

        Meeting meeting1 = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Meeting meeting2 = new Meeting(
                555,
                "Тестирование приложения",
                "Приложение НетоБанка",
                "В среду утром"
        );

        Todos todos = new Todos();

        todos.add(simpleTask1);
        todos.add(simpleTask2);
        todos.add(epic1);
        todos.add(epic2);
        todos.add(meeting1);
        todos.add(meeting2);

        Task[] expected1 = {simpleTask1, simpleTask2};
        Task[] expected2 = {epic1, epic2};
        Task[] expected3 = {meeting1,meeting2};

        Task[] actual1 = todos.search("Установить");
        Task[] actual2 = todos.search("производительность");
        Task[] actual3 = todos.search("НетоБанк");
        Task[] actual4 = todos.search("приложения");

        Assertions.assertArrayEquals(expected1, actual1);
        Assertions.assertArrayEquals(expected2, actual2);
        Assertions.assertArrayEquals(expected3, actual3);
        Assertions.assertArrayEquals(expected3, actual4);
    }
}