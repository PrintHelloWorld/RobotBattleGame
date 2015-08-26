package actions;

import grammar.EXP;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class Move extends ACT implements RobotProgramNode {

	private EXP arg;
	private static final String LOG = "MOVE: ";

	public Move(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void execute(Robot robot) {
		if (arg == null) {
			robot.move();
		} else {
			arg.evaluate(robot);
			int bound = Integer.parseInt(arg.getValue());
			for (int i = 0; i < bound; i++) {
				robot.move();
			}
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.MOVEPAT,
				"Expecting " + Parser.MOVEPAT.toString(), s);
		if (s.hasNext(Parser.OPENPAREN)) {
			Parser.require(Parser.OPENPAREN, LOG + "Expecting (", s);
			arg = new EXP(getRoot());
			if (!arg.parse(s)) {
				return false;
			}
			Parser.require(Parser.CLOSEPAREN, LOG + "Expecting )", s);
		}
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		return true;
	}

	@Override
	public String toString() {
		String s = "move";
		if (arg != null) {
			s += "(" + arg.toString() + ") ";
		}
		return s;
	}
}
