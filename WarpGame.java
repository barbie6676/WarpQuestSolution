/*
 * 2014 WarpQuest game solutions.
 */

package warpquest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



/**
 * Main part of WarpQuest game solutions
 * @author zhangmian1988@gmail.com
 */
public class WarpGame {
    public static final int DIMENSION = 5;
    
    private Coordinate des;
    private Coordinate target;
    private boolean[][] obs;
    private boolean[][] pos;
    /*temperal global variables to store current status.*/
    private List<Coordinate> ships;
    private List<String> steps;
    /*queue to store statuses of BFS*/
    private Queue<Integer> lastDir;
    private Queue<Integer> lastShip;
    private Queue<List<Coordinate>> q;
    private Queue<List<String>> path;
     
    //Constructors
    public WarpGame(boolean[][] obs, Coordinate des, 
                    Coordinate target, List<Coordinate> ships) {
        this.obs = obs;
        this.des = des;
        this.target = target;
        this.ships = ships;
        steps = new ArrayList<String>();
        q = new LinkedList<List<Coordinate>>();  
        lastShip = new LinkedList<Integer>();
        lastDir = new LinkedList<Integer>();
        path = new LinkedList<List<String>>();
    }
    
    public WarpGame(WarpSetting gameCase) {
        this.obs = gameCase.obs;
        this.des = gameCase.des;
        this.target = gameCase.target;
        this.ships = gameCase.ships;
        steps = new ArrayList<String>();
        q = new LinkedList<List<Coordinate>>();  
        lastShip = new LinkedList<Integer>();
        lastDir = new LinkedList<Integer>();
        path = new LinkedList<List<String>>();
    }
    
    /**
     * Paint the grid with ship positions.
     * Initiate the queues.
     */
    public void setUp() {  
        pos = new boolean[DIMENSION][DIMENSION];
        for (Coordinate elem: ships) {
            pos[elem.x][elem.y] = true;
        }
        q.add(ships);
        lastShip.add(-1);
        lastDir.add(-1);
        path.add(new ArrayList<String>());
    }
    
    /**
     * Move the ships until find a solution.
     */
    public void move() {
        boolean found = false;
        while (!q.isEmpty() && !found) {
            // get the current positions and repaint the grid.
            ships = q.poll();
            pos = new boolean[DIMENSION][DIMENSION];
            for (Coordinate elem: ships) {
                pos[elem.x][elem.y] = true;
            }
            // first ship is always the target ship.
            target = ships.get(0);
            // remember the last movement
            int lastdir = lastDir.poll();
            int lastship = lastShip.poll();
            steps = path.poll();
            
            // for each ship, check the available movements.
            for (int i = 0; i < ships.size() && !found; i++) {
                ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
                if (i == lastship) {
                    moves = movable(i, lastdir);
                } else {
                    moves = movable(i, -1);
                }
                // for each movement, check whether it's a solution:
                // if true, stop the game
                // otherwise add the new positions to the end of the queue.
                for (int j = moves.size() - 1; j >= 0; j--) {  
                    Coordinate move = moves.get(j);
                    String step = genStep(i, ships.get(i), move);

                    if (i == 0 && atCoordi(move, des.x, des.y)) {
                        found = true;
                        steps.add(step);
                        break;
                    }
                    List<Coordinate> shipsCopy = new ArrayList<Coordinate>(ships);
                    shipsCopy.set(i, move);
                    q.add(shipsCopy);

                    List<String> stepsCopy = new ArrayList<String>(steps);
                    stepsCopy.add(step);
                    path.add(stepsCopy);

                    int dir = getDir(ships.get(i), move);
                    lastDir.add(dir);                    
                    lastShip.add(i);  
                }
            }
        }
        if (found) {
            printSteps();
        } 
    }
    
    /** 
     * Method to get a direction based on the last movement of a ship. 
     */
    private int getDir(Coordinate p1, Coordinate p2) {
        //if the ship moved down, it won't move up next time.
        if (p1.y == p2.y && p2.x > p1.x) return 0;
        if (p1.y == p2.y && p2.x < p1.x) return 1;
        if (p1.x == p2.x && p2.y > p1.y) return 2;
        if (p1.x == p2.x && p2.y < p1.y) return 3;
        return -1;
    }

    /**
     * Method to check the available movements of the ith ship.
     * Except for a certain direction.
     */
    public ArrayList<Coordinate> movable(int id, int direction) {
        Coordinate ship = ships.get(id);
        ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
        // look for other ships at four different directions 
        // 0 up 1 down 2 left 3 right
        if (direction != 0) {
            for (int i = ship.x - 1; i >= 0; i--) {
                if (obs[i][ship.y]) {  
                    break;
                } else if (pos[i][ship.y]){
                    if (i < ship.x - 1) {
                        moves.add(new Coordinate(i + 1, ship.y));
                        if (id == 0 && atCoordi(des, i + 1, ship.y)) {
                            return moves;
                        }
                    }
                    break;
                }
            }
        }
        
        if (direction != 1) {
            for (int i = ship.x + 1; i < DIMENSION; i++) {
                if (obs[i][ship.y]) {  
                    break;
                } else if (pos[i][ship.y]){
                    if (i > ship.x + 1) {
                        moves.add(new Coordinate(i - 1, ship.y));
                        if (id == 0 && atCoordi(des, i - 1, ship.y)) {
                            return moves;
                        }
                    }
                    break;
                }
            }
        }
        
        if (direction != 2) {
            for (int i = ship.y - 1; i >= 0; i--) {
                if (obs[ship.x][i]) {
                    break;
                } else if (pos[ship.x][i]) {
                    if (i < ship.y - 1) {
                        moves.add(new Coordinate(ship.x, i + 1));
                        if (id == 0 && atCoordi(des, ship.x, i + 1)) {
                            return moves;
                        }
                    }
                    break;
                }
            }
        }
        
        if (direction != 3) {
            for (int i = ship.y + 1; i < DIMENSION; i++) {
                if (obs[ship.x][i]) {
                    break;
                } else if (pos[ship.x][i]) {
                    if (i > ship.y + 1) {
                        moves.add(new Coordinate(ship.x, i - 1));
                        if (id == 0 && atCoordi(des, ship.x, i - 1)) {
                            return moves;
                        }
                    }
                    break;
                }
            }
        }
        return moves;
    }
    
    private boolean atCoordi(Coordinate c, int x, int y) {
        return c.x == x && c.y == y;
    }
    
    /**
     * Method to generate a string representing a movement for a ship.
     */
    private String genStep(int id, Coordinate a, Coordinate b) {
        String step = "Ship " + id + ": (" + a.x + ", " + a.y
                      + ") to (" + b.x + ", " + b.y + ").";       
        return step;
    }
    
    public void printSteps() {
        for (String s: steps) {
            System.out.println(s);
        }
    }
}
