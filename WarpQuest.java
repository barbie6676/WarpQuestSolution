/*
 * 2014 
 * This is a version of solution for a free online game WarpQuest.
 * https://play.google.com/store/apps/details?id=grunt.WarpQuest
 */

package warpquest;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Class to play the game
 * @author zhangmian1988@gmail.com
 */
public class WarpQuest {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("hello warp");
        
        List<WarpSetting> cases = setGameCases();
        for (int i = 0; i < cases.size(); i++) {
            System.out.println("Level " + (i + 1));
            WarpGame game = new WarpGame(cases.get(i));
            game.setUp();       
            game.move();
        }        
    }
    
    /**
     * Based on the real game:
     * generate a list of game settings based on the first 18 level.
     */
    private static List<WarpSetting> setGameCases() {
        List<WarpSetting> list = new ArrayList<WarpSetting>();
        
        list.add(new WarpSetting(new int[][] {}, new Coordinate(2, 2),  
                                 new int[][] {{0, 2}, {2, 4}, {3, 2}}));
        list.add(new WarpSetting(new int[][] {}, new Coordinate(2, 2), 
                                 new int[][] {{2, 0}, {0, 1}, {2, 1}, {2, 3}})); 
        list.add(new WarpSetting(new int[][] {{1, 2}}, new Coordinate(1, 1),
                                 new int[][] {{1, 4}, {0, 1}, {1, 0}, 
                                              {2, 0}, {3, 4}}));
        list.add(new WarpSetting(new int[][] {},  new Coordinate(2, 2), 
                                 new int[][] {{4, 2}, {0, 3}, {1, 1}, {2, 3}}));
        list.add(new WarpSetting(new int[][] {}, new Coordinate(1, 1),  
                                 new int[][] {{1, 3}, {0, 1}, {1, 4}, 
                                              {3, 0}, {4, 3}}));
        list.add(new WarpSetting(new int[][] {}, new Coordinate(1, 2),  
                                 new int[][] {{2, 3}, {0, 0}, {0, 1},
                                              {1, 3}, {3, 0}}));
        list.add(new WarpSetting(new int[][] {{3, 2}}, new Coordinate(2, 3),
                                 new int[][] {{1, 3}, {1, 0}, {1, 4},
                                              {3, 1}, {3, 4}}));
        list.add(new WarpSetting(new int[][] {{1, 1}, {2, 2}}, 
                                 new Coordinate(1, 0),
                                 new int[][] {{4, 0}, {0, 0}, {1, 4},
                                              {2, 0}, {4, 4}}));
        list.add(new WarpSetting(new int[][] {}, new Coordinate(1, 1),  
                                 new int[][] {{1, 0}, {1, 3}, {1, 4},
                                              {3, 0}, {3, 2}}));
        list.add(new WarpSetting(new int[][] {{1, 4}}, new Coordinate(1, 3),
                                 new int[][] {{2, 4}, {0, 2}, {0, 4},
                                              {3, 1}, {3, 3}}));
        list.add(new WarpSetting(new int[][] {}, new Coordinate(2, 2),  
                                 new int[][] {{2, 0}, {0, 1}, {0, 4},
                                              {4, 0}, {4, 4}}));
        list.add(new WarpSetting(new int[][] {{2, 3}}, new Coordinate(3, 3),
                                 new int[][] {{1, 0}, {1, 1}, {1, 4},
                                              {4, 1}, {4, 4}}));        
        list.add(new WarpSetting(new int[][] {{1, 3}}, new Coordinate(2, 2),
                                 new int[][] {{0, 0}, {0, 3}, {2, 4}, 
                                              {3, 0}, {4, 3}}));
        list.add(new WarpSetting(new int[][] {}, new Coordinate(2, 2),
                                 new int[][] {{1, 3}, {0, 4}, {2, 1},
                                              {4, 2}, {4, 4}}));
        list.add(new WarpSetting(new int[][] {{2, 3}}, new Coordinate(3, 2),
                                 new int[][] {{4, 2}, {0, 1}, {1, 3},
                                              {3, 0}, {3, 3}}));
        list.add(new WarpSetting(new int[][] {{0, 3}}, new Coordinate(1, 2),
                                 new int[][] {{3, 0}, {0, 1}, {0, 4},
                                              {2, 0}, {3, 3}, {3, 4}}));
        list.add(new WarpSetting(new int[][] {}, new Coordinate(2, 2),
                                 new int[][] {{0, 3}, {1, 1}, {4, 0},
                                              {4, 1}, {4, 4}}));
        //18
        list.add(new WarpSetting(new int[][] {{1, 2}, {3, 1}}, 
                                 new Coordinate(3, 2),
                                 new int[][] {{2, 1}, {0, 2}, {0, 4},
                                              {1, 4}, {4, 1}, {4, 4}}));
        return list;
    } 
}
