package gameOfLife;

public enum Location {
    DOWN {
        @Override
        public void control(int i, int j) {
            if (i < map.length - 1 && map[i + 1][j]) {
                surroundLife++;
            }
        }
    }, UP {
        @Override
        public void control(int i, int j) {
            if (i > 0 && map[i - 1][j]) {
                surroundLife++;
            }
        }
    }, LEFT {
        @Override
        public void control(int i, int j) {
            if (j > 0 && map[i][j - 1]) {
                surroundLife++;
            }
        }
    }, RIGHT {
        @Override
        public void control(int i, int j) {
            if (j < map[i].length - 1 && map[i][j + 1]) {
                surroundLife++;
            }
        }
    }, LEFT_UP {
        @Override
        public void control(int i, int j) {
            if (i > 0 && j > 0 && map[i - 1][j - 1]) {
                surroundLife++;
            }
        }
    }, LEFT_DOWN {
        @Override
        public void control(int i, int j) {
            if (i < map.length - 1 && j > 0 && map[i + 1][j - 1]) {
                surroundLife++;
            }
        }
    }, RIGHT_UP {
        @Override
        public void control(int i, int j) {
            if (i > 0 && j < map[i].length - 1 && map[i - 1][j + 1]) {
                surroundLife++;
            }
        }
    }, RIGHT_DOWN {
        @Override
        public void control(int i, int j) {
            if (i < map.length - 1 && j < map[i].length - 1 && map[i + 1][j + 1]) {
                surroundLife++;
            }
        }
    };

    private static boolean[][] map;
    private static int surroundLife = 0;


    public static int getSurroundLife() {
        int result =  surroundLife;
        surroundLife = 0;
        return result;
    }

    public static void setMap(boolean[][] map) {
        Location.map = map;
    }

    public abstract void control(int i, int j);
}
