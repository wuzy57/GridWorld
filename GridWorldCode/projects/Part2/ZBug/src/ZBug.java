

import info.gridworld.actor.Bug;
public class ZBug extends Bug {
	private int steps;
    private int sideLength;
    private int turnNum;
    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
        turnNum = 0;
        sideLength = length;
        setDirection(90);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength && canMove()&&turnNum<=3)
        {
            move();
            steps++;
        }
        else if(turnNum<=3)
        {
        	//第一次转弯，方向为225度
        	if(turnNum==0)
        	{
        		setDirection(225);
                steps = 0;
                turnNum++;
        	}
        	//第二次转弯，方向为90度
        	else if(turnNum==1)
        	{
        		setDirection(90);
                steps = 0;
                turnNum++;
        	}
        }
    }
}
