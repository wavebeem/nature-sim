import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Stats {
    private static HashMap<String, Integer> counts          = new HashMap<String, Integer>();
    private static HashMap<String, Integer> deathCounts     = new HashMap<String, Integer>();
    private static HashMap<String, ArrayList<Integer>> ages = new HashMap<String, ArrayList<Integer>>();

    // Death types
    public static final int STARVATION = 0;
    public static final int OLD_AGE    = 1;
    public static final int EATEN      = 2;

    public static void increaseCount(Organism organism) {
        String name = organism.getClass().getName();
        if (!counts.containsKey(name)) {
            counts.put(name, 1);
        } else {
            counts.put(name, counts.get(name) + 1);
        }
    }

    public static void decreaseCount(Organism organism) {
        String name = organism.getClass().getName();
        if (!counts.containsKey(name) || counts.get(name) <= 0) {
            Debug.echo("Trying to decrease the count of something that isn't there.");
            //counts.put(organismName, 0);
        } else {
            counts.put(name, counts.get(name) - 1);
        }
    }

    public static Integer getOrganismCount(String organismName) {
        return counts.get(organismName);
    }

    public static void recordDeath(Organism organism, Integer typeOfDeath) {
        String name = organism.getClass().getName();

        String key = name + typeOfDeath;
        if (!deathCounts.containsKey(key)) {
            deathCounts.put(key, 1);
        } else {
            deathCounts.put(key, deathCounts.get(key) + 1);
        }

        recordAge(organism);
        decreaseCount(organism);
    }

    public static Integer getStarvationDeaths(String organismName) {
        String key = organismName + STARVATION;

        return deathCounts.get(key);
    }

    public static Integer getOldAgeDeaths(String organismName) {
        String key = organismName + OLD_AGE;

        return deathCounts.get(key);
    }

    public static Integer getEatenDeaths(String organismName) {
        String key = organismName + EATEN;

        return deathCounts.get(key);
    }

    private static void recordAge(Organism organism) {
        String name = organism.getClass().getName();

        if (!ages.containsKey(name)) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(((Animal)organism).getAge());
            ages.put(name, list);
        } else {
            ages.get(name).add(((Animal)organism).getAge());
        }
    }

    public static String organismCounts() {
        String ret = "";

        ArrayList<String> keys = new ArrayList<String>(counts.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            ret += key + ": " + counts.get(key) + "\n";
        }

        return ret;
    }

    public static String deathTypeCounts() {
        String ret = "";

        ArrayList<String> keys = new ArrayList<String>(deathCounts.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            Integer count = deathCounts.get(key); 
            int type = new Integer(key.substring(key.length() - 1));
            String name = key.substring(0, key.length() - 1);
            ret += name;

            switch (type) {
                case STARVATION:
                    ret += " starved to death " + count + " times.\n";
                    break;
                case OLD_AGE:
                    ret += " died of old age " + count + " times.\n";
                    break;
                case EATEN:
                    ret += " got eaten " + count + " times.\n";
                    break;
                default:
                    break;
            }
        }

        return ret;
    }

    public static String averageAges() {
        String ret = "";

        ArrayList<String> keys = new ArrayList<String>(ages.keySet());
        Collections.sort(keys);

        for (String key : keys) {
            ArrayList<Integer> currentAges = ages.get(key);

            int sum = 0;
            for (Integer i : currentAges) {
                sum += i;
            }

            ret += key + " average age: " + (sum / currentAges.size()) + "\n";
        }

        return ret;
    }

    public static String getStats() {
        return organismCounts() + 
               "\n" + deathTypeCounts() +
               "\n" + averageAges();
    }

    public static void resetStats() {
        counts      = new HashMap<String, Integer>();
        deathCounts = new HashMap<String, Integer>();
        ages        = new HashMap<String, ArrayList<Integer>>();
    }
}
