package conditions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class Not extends COND implements RobotReturnValueNode {

	private COND param;
	private Integer value;

	public Not(RobotProgramNode root) {
		super(root);
	}

	private static final String LOG = "NOT: ";

	@Override
	public void evaluate(Robot robot) {
		param.evaluate(robot);
		if (Integer.parseInt(param.getValue()) == 1) {
			value = 0;
		} else {
			value = 1;
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.NOTPAT,
				LOG + "Expecting " + Parser.NOTPAT.toString(), s);
		Parser.require(Parser.OPENPAREN, LOG + "Expecting (", s);
		param = new COND(getRoot());
		if (!param.parse(s)) {
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
		return "not(" + param.toString() + ")";
	}

}
