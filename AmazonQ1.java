import java.util.*;

public class AmazonQ1 {

    // Let's sort the hashmap
    private static HashMap<String, Integer> sortByValue(SortedMap<String, Integer> map)
    {
        List<Map.Entry<String, Integer> > list =
                new LinkedList<Map.Entry<String, Integer> >(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public ArrayList<String> topNCompetitors(int numCompetitors,
                                             int topNCompetitors,
                                             List<String> competitors,
                                             int numReviews,
                                             List<String> reviews) {
        if (topNCompetitors <= 1) {
            return new ArrayList<String>();
        }

        // WRITE YOUR CODE HERE
        SortedMap<String, Integer> compMap = new TreeMap<String, Integer>();
        for (String competitor : competitors) {
            for (String review : reviews) {
                String revLower = review.toLowerCase();
                if (revLower.indexOf(competitor) != -1) {
                    Integer count = compMap.get(competitor);
                    if (count == null) {
                        compMap.put(competitor, 1);
                    } else {
                        compMap.put(competitor, count + 1);
                    }
                }
            }
        }

        Map<String, Integer> sortedMap = sortByValue(compMap);

        // Compose final answer
        int index = 0;
        ArrayList<String> topComp = new ArrayList<>(topNCompetitors);
        for (String compName : sortedMap.keySet()) {
            topComp.add(index, compName);
            index++;

            if (index == topNCompetitors) {
                break;
            }
        }

        return topComp;
    }
    // METHOD SIGNATURE ENDS
}

