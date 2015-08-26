package conditions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class Or extends COND implements RobotReturnValueNode {

	private COND left, right;
	private Integer value;

	public Or(RobotProgramNode root) {
		super(root);
	}

	private static final String LOG = "OR: ";

	@Override
	public void evaluate(Robot robot) {
		left.evaluate(robot);
		right.evaluate(robot);
		if (Integer.parseInt(left.getValue()) == 1
				|| Integer.parseInt(right.getValue()) == 1) {
			value = 1;
		} else {
			value = 0;
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.ORPAT,
				LOG + "Expecting " + Parser.ORPAT.toString(), s);
		Parser.require(Parser.OPENPAREN, LOG + "Expecting (", s);
		left = new COND(getRoot());
		if (!left.parse(s)) {
			return false;
		}
		Parser.require(Parser.COMMAPAT, LOG + "Expecting ,", s);
		right = new COND(getRoot());
		if (!right.parse(s)) {
			return false;
		}
		Parser.require(Parser.CLOSEPAREN, LOG + "Expecting )", s);
		return true;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

	@Override
	public String toString() {
		return "or(" + left.toString() + ", " + right.toString() + ")";
	}
}
