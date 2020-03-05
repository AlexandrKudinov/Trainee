package gameOfLife;

import java.util.List;


public enum Location {
    DOWN {
        @Override
        public void control(int i, int j) {
            if (sc.downIsLife(i, j)) {
                surroundLife++;
            } else if (sc.downOnBorderIsLife(i, j)) {
                surroundLife++;
            }
        }
    }, UP {
        @Override
        public void control(int i, int j) {
            if (sc.upIsLife(i, j)) {
                surroundLife++;
            } else if (sc.upOnBorderIsLife(i, j)) {
                surroundLife++;
            }
        }
    }, LEFT {
        @Override
        public void control(int i, int j) {
            if (sc.leftIsLife(i, j)) {
                surroundLife++;
            }
        }
    }, RIGHT {
        @Override
        public void control(int i, int j) {
            if (sc.rightIsLife(i, j)) {
                surroundLife++;
            }
        }
    }, LEFT_UP {
        @Override
        public void control(int i, int j) {
            if (sc.leftUpIsLife(i, j)) {
                surroundLife++;
            } else if (sc.leftUpOnBorderIsLife(i, j)) {
                surroundLife++;
            }
        }
    }, LEFT_DOWN {
        @Override
        public void control(int i, int j) {
            if (sc.leftDownIsLife(i, j)) {
                surroundLife++;
            } else if (sc.leftDownOnBorderIsLife(i, j)) {
                surroundLife++;
            }
        }
    }, RIGHT_UP {
        @Override
        public void control(int i, int j) {
            if (sc.rightUpIsLife(i, j)) {
                surroundLife++;
            } else if (sc.rightUpOnBorderIsLife(i, j)) {
                surroundLife++;
            }
        }
    }, RIGHT_DOWN {
        @Override
        public void control(int i, int j) {
            if (sc.rightDownIsLife(i, j)) {
                surroundLife++;
            } else if (sc.rightDownOnBorderIsLife(i, j)) {
                surroundLife++;
            }
        }
    };

    private static SurroundControl sc;
    private static int surroundLife;

    public static int getSurroundLife() {
        int result = surroundLife;
        surroundLife = 0;
        return result;
    }

    public static void setMap(List<Boolean[][]> mapParts, int mapIndex) {
        sc = new SurroundControl();
        sc.setMap(mapParts, mapIndex);
    }

    public abstract void control(int i, int j);
}
