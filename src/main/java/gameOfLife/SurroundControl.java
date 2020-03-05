package gameOfLife;

import java.util.List;

public class SurroundControl {
    private List<Boolean[][]> mapParts;
    private Boolean[][] currentMap;
    private Boolean[][] upMap;
    private Boolean[][] downMap;
    private int mapIndex;

    public void setMap(List<Boolean[][]> mapParts, int mapIndex) {
        this.mapParts = mapParts;
        this.mapIndex = mapIndex;
        this.currentMap = mapParts.get(mapIndex);
        if (mapIndex > 0) this.upMap = mapParts.get(mapIndex - 1);
        if (mapIndex < mapParts.size() - 1) this.downMap = mapParts.get(mapIndex + 1);
    }

    public boolean rightDownIsLife(int i, int j) {
        return i < currentMap.length - 1 &&
                j < currentMap[i].length - 1 &&
                currentMap[i + 1][j + 1];
    }

    public boolean rightDownOnBorderIsLife(int i, int j) {
        return i == currentMap.length - 1 &&
                mapIndex < mapParts.size() - 1 &&
                j < currentMap[i].length - 1 &&
                downMap[0][j + 1];
    }

    public boolean rightUpIsLife(int i, int j) {
        return i > 0 && j < currentMap[i].length - 1 && currentMap[i - 1][j + 1];
    }

    public boolean rightUpOnBorderIsLife(int i, int j) {
        return i == 0 && mapIndex > 0 &&
                j < currentMap[i].length - 1 &&
                upMap[upMap.length - 1][j + 1];
    }

    public boolean leftDownIsLife(int i, int j) {
        return i < currentMap.length - 1 &&
                j > 0 && currentMap[i + 1][j - 1];
    }

    public boolean leftDownOnBorderIsLife(int i, int j) {
        return i == currentMap.length - 1 &&
                mapIndex < mapParts.size() - 1 && j > 0 &&
                downMap[0][j - 1];
    }

    public boolean leftUpIsLife(int i, int j) {
        return i > 0 && j > 0 && currentMap[i - 1][j - 1];
    }

    public boolean leftUpOnBorderIsLife(int i, int j) {
        return i == 0 && mapIndex > 0 && j > 0 &&
                upMap[upMap.length - 1][j - 1];
    }

    public boolean rightIsLife(int i, int j) {
        return j < currentMap[i].length - 1 && currentMap[i][j + 1];
    }

    public boolean leftIsLife(int i, int j) {
        return j > 0 && currentMap[i][j - 1];
    }

    public boolean upIsLife(int i, int j) {
        return i > 0 && currentMap[i - 1][j];
    }

    public boolean upOnBorderIsLife(int i, int j) {
        return i == 0 && mapIndex > 0 &&
                upMap[upMap.length - 1][j];
    }

    public boolean downIsLife(int i, int j) {
        return i < currentMap.length - 1 && currentMap[i + 1][j];
    }

    public boolean downOnBorderIsLife(int i, int j) {
        return i == currentMap.length - 1 &&
                mapIndex < mapParts.size() - 1 &&
                downMap[0][j];
    }
}
