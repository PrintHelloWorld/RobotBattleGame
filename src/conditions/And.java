package conditions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class And extends COND implements RobotReturnValueNode {

	private COND left, right;
	private Integer value;

	public And(RobotProgramNode root) {
		super(root);
	}

	private static final String LOG = "AND: ";

	@Override
	public void evaluate(Robot robot) {
		left.evaluate(robot);
		right.evaluate(robot);
		if (Integer.parseInt(left.getValue()) == 1
				&& Integer.parseInt(right.getValue()) == 1) {
			value = 1;
		} else {
			value = 0;
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.ANDPAT,
				LOG + "Expecting " + Parser.ANDPAT.toString(), s);
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
		return "and(" + left.toString() + ", " + right.toString() + ")";
	}

}
