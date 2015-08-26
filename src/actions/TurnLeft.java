package actions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class TurnLeft extends ACT implements RobotProgramNode {

	private static final String LOG = "TURNLEFT: ";

	public TurnLeft(RobotProgramNode root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Robot robot) {
		robot.turnLeft();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.TURNLPAT,
				LOG + "Expecting " + Parser.TURNLPAT.toString(), s);
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		return true;
	}

	@Override
	public String toString() {
		return "turnL";
	}
}
