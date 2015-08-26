package conditions;

import grammar.EXP;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class Lt extends COND implements RobotReturnValueNode {

	private EXP left, right;
	private Integer value;

	public Lt(RobotProgramNode root) {
		super(root);
	}

	private static final String LOG = "Lt: ";

	@Override
	public void evaluate(Robot robot) {
		left.evaluate(robot);
		right.evaluate(robot);
		if (Integer.parseInt(left.getValue()) < Integer.parseInt(right
				.getValue())) {
			value = 1;
		} else {
			value = 0;
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.LESSPAT,
				LOG + "Expecting " + Parser.LESSPAT.toString(), s);
		Parser.require(Parser.OPENPAREN, LOG + "Expecting (", s);
		left = new EXP(getRoot());
		if (!left.parse(s)) {
			return false;
		}
		Parser.require(Parser.COMMAPAT, LOG + "Expecting ,", s);
		right = new EXP(getRoot());
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
		return "lt(" + left.toString() + ", " + right.toString() + ")";
	}
}
