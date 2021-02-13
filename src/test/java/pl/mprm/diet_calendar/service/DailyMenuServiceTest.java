package pl.mprm.diet_calendar.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprm.diet_calendar.dao.DailyMenuRepository;
import pl.mprm.diet_calendar.model.meals.DailyMenu;

import java.time.DateTimeException;
import java.time.LocalDate;
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
        when(repository.findByCalendarPatientLoginAndDate(anyString(), any())).thenReturn(Optional.empty());

        var result = dailyMenuService.findMenusForMonth(2001, 11, "userName");

        assertEquals(30, result.size());
        verify(repository, times(30)).findByCalendarPatientLoginAndDate(anyString(), any());
    }

    @Test
    void invalidDataTestHighMonth() {
        assertThrows(DateTimeException.class, () -> dailyMenuService.findMenusForMonth(2001, 13, "userName"));
    }

    @Test
    void invalidDataTestNegativeMonth() {
        assertThrows(DateTimeException.class, () -> dailyMenuService.findMenusForMonth(2001, -13, "userName"));
    }

    @Test
    void invalidDataTestNullMonth() {
        assertThrows(java.lang.NullPointerException.class, () -> dailyMenuService.findMenusForMonth(2001, null, "userName"));
    }
    @Test
    void invalidDataTestNullYear() {
        assertThrows(java.lang.NullPointerException.class, () -> dailyMenuService.findMenusForMonth(null, 1, "userName"));
    }
    @Test
    void invalidDataTestNullAll() {
        assertThrows(java.lang.NullPointerException.class, () -> dailyMenuService.findMenusForMonth(null, null, null));
    }

    @Test
    void findByDateTest() {
        when(repository.findByCalendarPatientLoginAndDate(any(), any())).thenReturn(Optional.of(new DailyMenu()));

        var date = LocalDate.now();

        var menu = dailyMenuService.findByDate(date, "userName");

        assertEquals(date, menu.getDate());

        verify(repository).findByCalendarPatientLoginAndDate(any(), any());
    }

    @Test
    void findByNullDateTest() {

        assertThrows(NullPointerException.class, () -> dailyMenuService.findByDate(null, "userName"));

        verify(repository, never()).findByCalendarPatientLoginAndDate(anyString(),isNull());
    }

    @Test
    void findNullUsersDailyMenuTest() {
        when(repository.findByCalendarPatientLoginAndDate(isNull(), any())).thenReturn(Optional.of(new DailyMenu()));
        var date = LocalDate.now();
        var menu = dailyMenuService.findByDate(date, null);

        assertEquals(date, menu.getDate());

        verify(repository, times(1)).findByCalendarPatientLoginAndDate(isNull(), any());
    }
}