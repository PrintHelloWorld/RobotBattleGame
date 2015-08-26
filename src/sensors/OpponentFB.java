package sensors;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class OpponentFB extends SEN implements RobotReturnValueNode {

	private Integer value;

	private static final String LOG = "OPPONENTFB: ";

	public OpponentFB(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void evaluate(Robot robot) {
		value = robot.getOpponentFB();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.OPPFBPAT,
				LOG + "Expecting " + Parser.OPPFBPAT.toString(), s);
		return true;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

	@Override
	public String toString() {
		return "oppFB";
	}

}
