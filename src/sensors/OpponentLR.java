package sensors;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class OpponentLR extends SEN implements RobotReturnValueNode {

	private Integer value;

	private static final String LOG = "OPPONENTLR: ";

	public OpponentLR(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void evaluate(Robot robot) {
		value = robot.getOpponentLR();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.OPPLRPAT,
				LOG + "Expecting " + Parser.OPPLRPAT.toString(), s);
		return true;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

	@Override
	public String toString() {
		return "oppLR";
	}

}
