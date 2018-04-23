package Engine;

public class MapModel {

    private boolean[][] mapRepresentation = new boolean[800][600];

    public MapModel(){
        for(int i = 0;i<mapRepresentation.length;i++){
            for(int j = 0;j<mapRepresentation[0].length;j++){
                if (j > 400){
                    mapRepresentation[i][j] = true;
                }
            }
        }
    }

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
                //TODO ha a másik függvénnyel szeretnénk generálni azt itt fogjuk
                break;
        }
    }

    public void refreshMap(Vec2D position,int radius){
        mapRepresentation[position.getX()][position.getY()] = false;
        for (int i = 0;i<radius;i++){
            mapRepresentation[position.getX() + i][position.getY() + i] = false;
            mapRepresentation[position.getX() + i][position.getY() - i] = false;
            mapRepresentation[position.getX() - i][position.getY() + i] = false;
            mapRepresentation[position.getX() - i][position.getY() - i] = false;
        }
    }


    public int getFloorHeight(int column){
        if (column >= mapRepresentation.length) return -1; //this means, that the parameter is bigger than the size of the array
        int counter = 0;
        for (int j = 0;j<mapRepresentation[column].length;j++){
            if (mapRepresentation[column][j]){
                return ++counter;
            }else{
                counter++;
            }
        }
        return counter;
    }

    public boolean[][] getMapRepresentation(){
        // nincs másolva, referencia van visszadva
        return this.mapRepresentation;
    }

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
}
