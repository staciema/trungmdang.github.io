/*
 * TCSS 305 � Autumn 2014 
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This program stores information about a Car object to be used in EasyStreetGUI.
 * 
 * @author Trung Dang 
 * @version October 16, 2014
 */
public class Car extends AbstractVehicle {

    /** The default death time before a car revives. */
    private static final int CAR_DEATH_TIME = 10;

    /**
     * This constructor initializes a car with given position and direction.
     * 
     * @param theX The X coordinate on the GUI
     * @param theY The Y coordinate on the GUI
     * @param theDir The initial direction of a car
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, CAR_DEATH_TIME);
    }

    /** 
     * This method decides whether a Car can pass through the given terrain and street
     * light status. A car travels on street and stops at red light.
     * 
     * @param theTerrain One of the terrain neighbors this Car
     * @param theLight A light neighbors this Car
     * @return true if the terrain street or the light is not red
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = false;
        if (theTerrain.equals(Terrain.STREET) 
                        || !theLight.equals(Light.RED)) {
            result = true;
        }
        return result;
    }

    /**
     * This method checks all the neighboring terrain to determine the preferred direction of
     * a Car object.
     * 
     * @param theNeighbors A map that contains information about neighboring terrain with 
     * <br> the following form: <Direction, Terrain>
     * @return A preferred direction of this Car object.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction result = null;
        final List<Direction> dirList = new ArrayList<Direction>();
        final Iterator<Direction> itr = theNeighbors.keySet().iterator();
        while (itr.hasNext()) {
            final Direction dir = itr.next();
            if (theNeighbors.get(dir).equals(Terrain.STREET)
                            || theNeighbors.get(dir).equals(Terrain.LIGHT)) {
                dirList.add(dir);
            }
        }
        if (dirList.contains(getDirection())) {     //go straight
            result = getDirection();
        } else if (dirList.contains(getDirection().left())) {   //go left
            result = getDirection().left();
        } else if (dirList.contains(getDirection().right())) {  //go right
            result = getDirection().right();
        } else {
            result = getDirection().reverse();      //reverse
        }
        return result;
    }
}
