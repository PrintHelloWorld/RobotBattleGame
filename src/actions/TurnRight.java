package actions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class TurnRight extends ACT implements RobotProgramNode {

	private static final String LOG = "TURNRIGHT: ";

	public TurnRight(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void execute(Robot robot) {
		robot.turnRight();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.TURNRPAT,
				LOG + "Expecting " + Parser.TURNRPAT.toString(), s);
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		return true;
	}

	@Override
	public String toString() {
		return "turnR";
	}
}