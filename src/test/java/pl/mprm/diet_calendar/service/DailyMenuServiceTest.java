package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprm.diet_calendar.dao.DailyMenuRepository;

import java.time.DateTimeException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DailyMenuServiceTest {
    @InjectMocks
    DailyMenuService dailyMenuService;

    @Mock
    DailyMenuRepository repository;

    @Test
    void validMonthTest() {
        when(repository.findByCalendarPacjentLoginAndDate(anyString(), any())).thenReturn(Optional.empty());

        var result = dailyMenuService.findMenusForMonth(2001, 11, "userName");

        assertEquals(30, result.size());
        verify(repository, times(30)).findByCalendarPacjentLoginAndDate(anyString(), any());
    }

    @Test
    void invalidDataTest() {
        assertThrows(DateTimeException.class, () -> dailyMenuService.findMenusForMonth(2001, 13, "userName"));
        assertThrows(DateTimeException.class, () -> dailyMenuService.findMenusForMonth(2001, -13, "userName"));
        assertThrows(DateTimeException.class, () -> dailyMenuService.findMenusForMonth(2001, 0, "userName"));

    }
}