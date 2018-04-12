package Engine;

public class SceneryGenerator {

    // itt lesznek a függvények tárolva amelyeket majd használunk a talaj generálásához
    public static double generateGraphicsWithFirstPattern(double value){
        double width = 800.0;
        double height = 600.0;
        return (height / 2- Math.round((float)20 * Math.sin(4/10.0 * value * value +6/10.0 * value + 20)));
    }

    public static double generateGraphicsWithSecondPattern(double value){
        //TODO ha szeretnénk még másik függvényt akkor ide írjuk
        return 0.0;
    }
}
