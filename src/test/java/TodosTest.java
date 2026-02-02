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
    public void shouldSearchDifferentTypesOfTuskByQuery() {
        SimpleTask simpleTask = new SimpleTask(5, "Открыть счёт в банке");

        String[] subtasks = {"Провести тесты", "Найти баги", "Составить баг-репорты"};
        Epic epic = new Epic(4, subtasks);

        Meeting meeting1 = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Meeting meeting2 = new Meeting(
                556,
                "Производственная планёрка",
                "Повышение производительности",
                "В понедельник утром"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting1);
        todos.add(meeting2);

        Task[] expected1 = {simpleTask};
        Task[] expected2 = {epic};
        Task[] expected3 = {meeting1};
        Task[] expected4 = {meeting2};

        Task[] actual1 = todos.search("Открыть счёт");
        Task[] actual2 = todos.search("Найти баги");
        Task[] actual3 = todos.search("НетоБанк");
        Task[] actual4 = todos.search("планёрка");

        Assertions.assertArrayEquals(expected1, actual1);
        Assertions.assertArrayEquals(expected2, actual2);
        Assertions.assertArrayEquals(expected3, actual3);
        Assertions.assertArrayEquals(expected4, actual4);
    }
}