package blankengine;

public class Launcher {

    static Game game;

    public static void main(String[] args) {
        game = new Game("You read this? :O", 1920, 1080, 0, 0, false);
        game.start();
    }

}
// 16 x 9 = 144
