package sensors;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class NumBarrels extends SEN implements RobotReturnValueNode {

	private Integer numBarrels;

	private static final String LOG = "NUMBARRELS: ";

	public NumBarrels(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void evaluate(Robot robot) {
		numBarrels = robot.numBarrels();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.NUMBARRELPAT, LOG + "Expecting "
				+ Parser.NUMBARRELPAT.toString(), s);
		return true;
	}

	@Override
	public String getValue() {
		return numBarrels.toString();
	}

	@Override
	public String toString() {
		return "numBarrels";
	}

}
