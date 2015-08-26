package sensors;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class FuelLeft extends SEN implements RobotReturnValueNode {

	private Integer fuelLeft;

	private static final String LOG = "FUELLEFT: ";

	public FuelLeft(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void evaluate(Robot robot) {
		fuelLeft = robot.getFuel();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.FUELLEFTPAT, LOG + "Expecting "
				+ Parser.FUELLEFTPAT.toString(), s);
		return true;
	}

	@Override
	public String getValue() {
		return fuelLeft.toString();
	}

	@Override
	public String toString() {
		return "fuelLeft";
	}
}