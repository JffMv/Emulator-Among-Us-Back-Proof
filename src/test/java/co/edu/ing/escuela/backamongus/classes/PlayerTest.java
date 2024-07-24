package co.edu.ing.escuela.backamongus.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("TestPlayer", "123", "456");
    }

    @Test
    void testStateOfTask_InitialState() {
        for (int i = 0; i < 11; i++) {
            assertFalse(player.stateOfTask(i), "All tasks should be initially false");
        }
    }

    @Test
    void testStateOfTask_AfterUpdate() {
        int taskToUpdate = 5;
        player.updateTaks(taskToUpdate);
        assertTrue(player.stateOfTask(taskToUpdate), "Updated task should be true");
    }

    @Test
    void testStateOfTask_InvalidTaskId() {
        assertThrows(NullPointerException.class, () -> player.stateOfTask(11),
                "Should throw NullPointerException for invalid task ID");
    }
}