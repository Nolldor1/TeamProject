package ru.netology;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    //Должно суммировать время проведенное в одной игре
    @Test
    public void shouldSumGenreTimeInOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }
    @Test
    //должен суммировать сыгранное время двух игр одного жанра
    public void shouldSumGenreTwoGames() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Diablo", "RPG");
        Game game1 = store.publishGame("Diablo 2", "RPG");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.play(game, 8);
        player.play(game1, 5);
        player.play(game2, 2);

        int expected = 13;
        int actual = player.sumGenre("RPG");
        assertEquals(expected, actual);
    }

    @Test
    //должно выбрасывать исключение и показывать уведомление о неустановленной игре
    public void shouldThrowRunTimeException() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Diablo", "RPG");
        Game game1 = store.publishGame("Diablo 2", "RPG");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);


        assertThrows(RuntimeException.class, () -> {
            player.play(game2, 2);

        });
    }

    @Test
    //должен показывать жанр игры, в которую играли больше всего
    public void shouldShowTheGenreGameWasPlayedTheMost() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Diablo", "RPG");
        Game game1 = store.publishGame("Diablo 2", "RPG");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.play(game, 8);
        player.play(game1, 5);
        player.play(game2, 2);

        Game expected = game;
        Game actual = player.mostPlayerByGenre("RPG");
        assertEquals(expected, actual);
    }

    @Test
    //не должен показывать жанр игры, в которую не играли
    public void shouldNotShowTheGenreGameHasNotBeenPlayed() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Diablo", "RPG");
        Game game1 = store.publishGame("Diablo 2", "RPG");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.installGame(game1);
        player.play(game, 8);
        player.play(game1, 5);


        Game expected = null;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }


    @Test
    //должен показывать уже сыгранное время при повторном добавлении игры
    public void shouldShowAlreadyPlayedTimeWhenReAddingGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Diablo", "RPG");
        Game game1 = store.publishGame("Diablo 2", "RPG");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);
        player.installGame(game2);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }
}
