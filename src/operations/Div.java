package operations;

import grammar.EXP;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class Div extends OP implements RobotReturnValueNode {
	private EXP left, right;
	private Integer value;

	private static final String LOG = "DIV: ";

	public Div(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void evaluate(Robot robot) {
		left.evaluate(robot);
		right.evaluate(robot);
		value = Integer.parseInt(left.getValue())
				/ Integer.parseInt(right.getValue());
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.DIVPAT,
				LOG + "Expecting " + Parser.DIVPAT.toString(), s);
		Parser.require(Parser.OPENPAREN, LOG + "Expecting " + Parser.OPENPAREN,
				s);
		left = new EXP(getRoot());
		if (!left.parse(s)) {
			return false;
		}
		Parser.require(Parser.COMMAPAT, LOG + "Expecting ,", s);
		right = new EXP(getRoot());
		if (!right.parse(s)) {
			return false;
		}
		Parser.require(Parser.CLOSEPAREN, LOG + "Expecting "
				+ Parser.CLOSEPAREN, s);
		return true;
	}

	@Override
	public String getValue() {
		return value.toString();
	}

	@Override
	public String toString() {
		return "div(" + left.toString() + ", " + right.toString() + ")";
	}
}
