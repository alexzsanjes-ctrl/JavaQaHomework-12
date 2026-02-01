import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    public void shouldSearchMeetingTasksByUnExistingQuery() {

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(meeting);

        Assertions.assertFalse(meeting.matches("Разработка"));
    }

    @Test
    public void shouldSearchEpicTaskUnExistingByQuery() {

        String[] subtasks = {"Провести тесты", "Найти баги", "Составить баг-репорты"};
        Epic epic = new Epic(4, subtasks);

        Todos todos = new Todos();

        todos.add(epic);

        Assertions.assertFalse(epic.matches("чек-листы"));
    }

    @Test
    public void equalsIsReflexive() {

        String[] subtasks = {"Провести тесты", "Найти баги", "Составить баг-репорты"};
        Epic epic = new Epic(4, subtasks);

        Assertions.assertTrue(epic.equals(epic));
    }

    @Test
    public void equalSymmetric() {

        SimpleTask simpleTask1 = new SimpleTask(1, "Открыть счёт в банке");
        SimpleTask simpleTask2 = new SimpleTask(2, "Откликнуться на вакансию");
        SimpleTask simpleTask3 = new SimpleTask(2, "Записать видеовизитку");

        Assertions.assertTrue(simpleTask2.equals(simpleTask3));
        Assertions.assertTrue(simpleTask3.equals(simpleTask2));

        Assertions.assertFalse(simpleTask1.equals(simpleTask2));
        Assertions.assertFalse(simpleTask2.equals(simpleTask1));

    }

    @Test
    public void equalsHandlesNull() {
        SimpleTask simpleTask1 = new SimpleTask(1, "Простая задача");
        Assertions.assertFalse(simpleTask1.equals(null));
    }

    @Test
    public void equalsComparesWithDifferentClass() {
        SimpleTask simpleTask1 = new SimpleTask(1, "Простая задача");

        String someString = "Сложная задача";

        Assertions.assertFalse(simpleTask1.equals(someString));
    }
}