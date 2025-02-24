package fairy.task;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void toFileString_success1() {
        String name = "New Year's Day";
        LocalDateTime startTime = LocalDateTime.of(2025, 1, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 1, 1, 23, 59);
        Event event = new Event(name, startTime, endTime);
        String actual = event.toFileString();
        String expected = "EVENT | F | New Year's Day | 20250101 0000 | 20250101 2359";
        assertEquals(expected, actual);
    }
}
