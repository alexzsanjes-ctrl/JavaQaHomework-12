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
    public void shouldSearchMeetingTasksInProjectByQuery() {

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMeetingTasksInTopicByQuery() {

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(meeting);

        Task[] expected = {meeting};
        Task[] actual = todos.search("НетоБанк");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSimpleTaskByQuery() {


        SimpleTask simpleTask1 = new SimpleTask(1, "Открыть счёт в банке");
        SimpleTask simpleTask2 = new SimpleTask(2, "Откликнуться на вакансию");
        SimpleTask simpleTask3 = new SimpleTask(3, "Записать видеовизитку");

        Todos todos = new Todos();

        todos.add(simpleTask1);
        todos.add(simpleTask2);
        todos.add(simpleTask3);

        Task[] expected = {simpleTask1};
        Task[] actual = todos.search("Открыть счёт");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchEpicTaskByQuery() {

        String[] subtasks = {"Провести тесты", "Найти баги", "Составить баг-репорты"};
        Epic epic = new Epic(4, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Task[] expected = {epic};
        Task[] actual = todos.search("баг-репорты");

        Assertions.assertArrayEquals(expected, actual);
    }

}