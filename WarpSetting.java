package warpquest;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to initate the setting of a warpquest game.
 * @author zhangmian1988@gmail.com
 */
public class WarpSetting {
    public Coordinate des;
    public Coordinate target;
    public List<Coordinate> ships;
    public boolean[][] obs;
       
    public WarpSetting (int[][] obs, Coordinate des, int[][] ships){
        
        this.obs = new boolean[WarpGame.DIMENSION][WarpGame.DIMENSION];
        for (int[] ob : obs) {
            this.obs[ob[0]][ob[1]] = true;
        }
        this.des = des;
        target = new Coordinate(ships[0][0], ships[0][1]);
        this.ships = new ArrayList<Coordinate>();
        for (int[] ship : ships) {
            this.ships.add(new Coordinate(ship[0], ship[1]));
        }
    }
}
