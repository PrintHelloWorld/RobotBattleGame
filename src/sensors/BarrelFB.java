package sensors;

import grammar.EXP;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class BarrelFB extends SEN implements RobotReturnValueNode {

	private RobotReturnValueNode expr;
	private Integer value;

	private static final String LOG = "BARRELFB: ";

	public BarrelFB(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void evaluate(Robot robot) {
		//added barrel expression
		if (expr != null) {
			expr.evaluate(robot);
			value = robot.getBarrelFB(Integer.parseInt(expr.getValue()));
		}
		value = robot.getClosestBarrelFB();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.BARRELFBPAT, LOG + "Expecting "
				+ Parser.BARRELFBPAT.toString(), s);
		if(s.hasNext(Parser.OPENPAREN)) barrelLength(s);
		return true;
	}

	public boolean barrelLength(Scanner s) {
		Parser.require(Parser.OPENPAREN, LOG + "Expecting " + Parser.OPENPAREN,
				s);
		expr = new EXP(getRoot());
		if (!expr.parse(s)) {
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
		return "barrelFB";
	}
}
