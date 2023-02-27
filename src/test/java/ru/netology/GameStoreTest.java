package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }
    
    @Test
    public void shouldAddSeveralGames() {

        Game game1 = store.publishGame("game1", "genre1");
        Game game2 = store.publishGame("game2", "genre2");
        Game game3 = store.publishGame("game3", "genre3");

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertTrue(store.containsGame(game3));
    }

    @Test
    public void shouldNotAddDuplicated() {

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = new Game("Нетология Баттл Онлайн", "Аркады", store);

        assertThrows(RuntimeException.class, () -> store.publishGame(game2.getTitle(), game2.getGenre()));
    }

    @Test
    public void shouldAddPlayTime() {

        store.addPlayTime("player1", 7);
        store.addPlayTime("player2", 3);
        store.addPlayTime("player3", 2);
        store.addPlayTime("player3", 6);

        String expected = "player3";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMostPlayer() {

        store.addPlayTime("player1", 2);
        store.addPlayTime("player2", 3);
        store.addPlayTime("player3", 9);
        store.addPlayTime("player4", 1);

        String expected = "player3";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotReturnAnyPlayer() {

        assertNull(store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayerIfPlayingTimeOneHour() {

        store.addPlayTime("player1", 0);
        store.addPlayTime("player4", 1);

        String expected = "player4";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test

    public void shouldSumPlayedTime() {

        store.addPlayTime("player1", 4);
        store.addPlayTime("player2", 1);
        store.addPlayTime("player3", 12);

        int expected = 17;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }
}
