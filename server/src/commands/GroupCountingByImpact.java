package commands;


import collections.HumanBeing;
import managers.CollectionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * group counting by impact speed
 */
@CommandInfo(name = "group_counting_by_impact", description = "group counting by impact speed")
public class GroupCountingByImpact extends AbstractCommand {
    private final CollectionManager collectionManager;

    public GroupCountingByImpact(CollectionManager collectionManager) {
        super("group_counting_by_impact", "group counting by impact speed");
        this.collectionManager = collectionManager;
    }


    @Override
    public CommandResponse execute(String[] args, Object obj) {
        HumanBeing[] elements = collectionManager.getArray();
        int[] impactSpeed = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            impactSpeed[i] = elements[i].getImpactSpeed().intValue();
        }
        Map<Integer, Integer> counter = new HashMap<>();

        for (int j : impactSpeed) {
            if (counter.containsKey(j)) {
                counter.put(j, counter.get(j) + 1);
            } else {
                counter.put(j, 1);
            }
        }
        String[] objects = new String[counter.size()];

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            objects[entry.getKey()] = "Impact speed: " + entry.getKey() + " - " + entry.getValue() + " elements";
        }
        return new CommandResponse("Grouped counting by impact speed entries", objects);


    }

}
