package actions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class TurnAround extends ACT implements RobotProgramNode {

	private static final String LOG = "SHIELDON: ";

	public TurnAround(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void execute(Robot robot) {
		robot.turnAround();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.TURNAROUNDPAT, LOG + "Expecting "
				+ Parser.TURNAROUNDPAT.toString(), s);
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		return true;
	}

	@Override
	public String toString() {
		return "turnAround";
	}
}
