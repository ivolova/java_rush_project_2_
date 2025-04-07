public class Main {

    public static final String INIT_CONFIG = "src/source/init_config.json";

    public static void main(String[] args) throws Exception {
        GameMap gameMap = new GameMap();

        gameMap.init(INIT_CONFIG);

        gameMap.start();
    }
}