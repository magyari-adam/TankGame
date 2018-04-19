package Engine;

public class SceneryGenerator {

    // itt lesznek a függvények tárolva amelyeket majd használunk a talaj generálásához
    public static double generateGraphicsWithFirstPattern(double value){
        double width = 800.0;
        double height = 600.0;
        return (height / 2- Math.round((float)100+Math.sin(1/260*value)*100));

    }

    public static double generateGraphicsWithSecondPattern(double value){
        //TODO ha szeretnénk még másik függvényt akkor ide írjuk
        double width = 800.0;
        double height = 600.0;
        return (height / 2- Math.round((float)20 * Math.sin(4/10.0 * value * value +6/10.0 * value + 20)));
    }
}
