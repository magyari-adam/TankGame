package Engine;

import java.io.Serializable;
import java.util.Arrays;

/**
 *  This class represents a matrix, which is the backend of the game board.
 */
public class MapModel implements Serializable{

    private static final long serialVersionUID = 50L;
    private boolean[][] mapRepresentation = new boolean[800][600];

    /**
     * Default constructor, constructs a map (matrix) with default values.
     */
    public MapModel(){
        for(int i = 0;i<mapRepresentation.length;i++){
            for(int j = 0;j<mapRepresentation[0].length;j++){
                if (j > 400){
                    mapRepresentation[i][j] = true;
                }
            }
        }
    }

    /**
     * Constructor, creates a map with the given scenery generator method.
     * @param chooser To which generator pattern used by the constructor, it depends on which enumeration value passed in.
     */
    public MapModel(FunctionChooser chooser){
        switch (chooser){
            case first:
                for(int i = 0;i<mapRepresentation.length;i++){
                    int terrainBorder = (int)SceneryGenerator.generateGraphicsWithFirstPattern(i);
                    for (int j = 0;j<mapRepresentation[i].length;j++){
                        if(j > terrainBorder){
                            mapRepresentation[i][j] = true;
                        }
                    }
                }
                break;
            case second:
                for(int i = 0;i<mapRepresentation.length;i++){
                    int terrainBorder = (int)SceneryGenerator.generateGraphicsWithSecondPattern(i);
                    for (int j = 0;j<mapRepresentation[i].length;j++){
                        if(j > terrainBorder){
                            mapRepresentation[i][j] = true;
                        }
                    }
                }
                break;
        }
    }

    /**
     * Refreshes the map
     * @param position position of the bullet
     * @param radius radius of the point where the bullet hits the ground
     */
    public void refreshMap(Vec2D position,int radius){
        mapRepresentation[position.getX()][position.getY()] = false;
        for (int i = 0;i<radius;i++){
            mapRepresentation[position.getX() + i][position.getY() + i] = false;
            mapRepresentation[position.getX() + i][position.getY() - i] = false;
            mapRepresentation[position.getX() - i][position.getY() + i] = false;
            mapRepresentation[position.getX() - i][position.getY() - i] = false;
        }
    }

    /**
     *
     * @return returns the whole map, by reference
     */
    public boolean[][] getMapRepresentation(){
        return this.mapRepresentation;
    }


    /**
     * Gives the height of the map in a specific position.
     * @param horizontalPos column number
     * @return return value: height
     */
    public int getVerticalPosition(int horizontalPos){
        if (horizontalPos < 0 || horizontalPos > 800){
            return 0;
        }
        int index = 0;
        for (int i=0;i<mapRepresentation[horizontalPos].length;i++){
            index ++;
            if (mapRepresentation[horizontalPos][i] == true){
                return index;
            }
        }
        return index;
    }

    /**
     * Textual representation of the mapmodel.
     * @return returns the textual representation as string.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<mapRepresentation.length;i++){
            for(int j = 0;j<mapRepresentation[0].length;j++){
                sb.append(mapRepresentation[i][j]+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns true if two mapmodel objects are identical.
     * @param o the another mapmodel object
     * @return the value of the check, can be true or false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapModel)) return false;
        MapModel mapModel = (MapModel) o;
        return Arrays.equals(mapRepresentation, mapModel.mapRepresentation);
    }

    /**
     *
     * @return generates hash value for the mapmodel.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(mapRepresentation);
    }
}
