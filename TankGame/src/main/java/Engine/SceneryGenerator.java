package Engine;

/**
 * A class which provides methods do generate scenery.
 */
public class SceneryGenerator {

    private static double helper = 0;
    private static double helper2 = 0;

    /**
     * First method to generate scenery.
     * @param value x value of the position.
     * @return returns the height for the specific x position.
     */
    public static double generateGraphicsWithFirstPattern(double value){
        double width = 800.0;
        double height = 600.0;
        return (height / 2- Math.round((float)100+Math.sin(1/260*value)*100));

    }

    /**
     * Second method to generate scenery
     * @param value x value of the position.
     * @return returns the height for the specific x position.
     */
    public static double generateGraphicsWithSecondPattern(double value){
        double width = 800.0;
        double height = 600.0;
        if (value < 100){
            if (value == 99){
                helper = value + 100;
            }
            return value + 100;
        }else if(value < 200) {
            return helper;
        }else if(value <= 300){
            return 250;
        }else if(value > 300 && value < 450){
            return 290;
        }else if (value >= 450 && value <= 600){
            if (value == 600){
                helper2 = 220;
            }
            return 220;
        }else if (value > 600 && value < 630){
                helper2 += 2;
                return helper2;
        }else{
            return 300;
        }
    }
}
