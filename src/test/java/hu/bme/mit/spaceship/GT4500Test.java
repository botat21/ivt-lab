package hu.bme.mit.spaceship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class GT4500Test {

    private GT4500 ship;
    private TorpedoStore primaryMock;
    private TorpedoStore secondaryMock;

    @BeforeEach
    void init() {
        primaryMock = mock(TorpedoStore.class);
        secondaryMock = mock(TorpedoStore.class);
        ship = new GT4500(primaryMock, secondaryMock);
    }

    @Test
    void fireTorpedo_Single_Success() {
        when(primaryMock.isEmpty()).thenReturn(false);
        when(secondaryMock.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        assertTrue(result);
        verify(primaryMock).fire(1);
        verify(secondaryMock, never()).fire(1);
    }

    @Test
    void fireTorpedo_All_Success() {
        when(primaryMock.isEmpty()).thenReturn(false);
        when(secondaryMock.isEmpty()).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertTrue(result);
        verify(primaryMock).fire(1);
        verify(secondaryMock).fire(1);
    }

    @Test
    void fireTorpedo_Single_Failure() {
        when(primaryMock.isEmpty()).thenReturn(true);
        when(secondaryMock.isEmpty()).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        assertFalse(result);
        verify(primaryMock, never()).fire(1);
        verify(secondaryMock).fire(1);
    }

    @Test
    void fireTorpedo_All_Failure() {
        when(primaryMock.isEmpty()).thenReturn(true);
        when(secondaryMock.isEmpty()).thenReturn(true);

        boolean result = ship.fireTorpedo(FiringMode.ALL);

        assertFalse(result);
        verify(primaryMock, never()).fire(1);
        verify(secondaryMock, never()).fire(1);
    }

    @Test
    void fireTorpedo_Alternative_Success() {
        when(primaryMock.isEmpty()).thenReturn(true);
        when(secondaryMock.isEmpty()).thenReturn(false);

        boolean result = ship.fireTorpedo(FiringMode.SINGLE);

        assertTrue(result);
        verify(primaryMock, never()).fire(1);
        verify(secondaryMock).fire(1);
    }
}

