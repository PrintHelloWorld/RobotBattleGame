package sensors;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class WallDist extends SEN implements RobotReturnValueNode {

	private Integer distance;

	private static final String LOG = "WALLDIST: ";

	public WallDist(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void evaluate(Robot robot) {
		distance = robot.getDistanceToWall();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.WALLDISTPAT, LOG + "Expecting "
				+ Parser.WALLDISTPAT.toString(), s);
		return true;
	}

	@Override
	public String getValue() {
		return distance.toString();
	}

	@Override
	public String toString() {
		return "wallDist";
	}

}
