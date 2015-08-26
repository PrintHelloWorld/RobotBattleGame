package actions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class ShieldOff extends ACT implements RobotProgramNode {
	private static final String LOG = "SHIELDOFF: ";

	public ShieldOff(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void execute(Robot robot) {
		robot.setShield(false);
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.SHIELDOFFPAT, LOG + "Expecting "
				+ Parser.SHIELDOFFPAT.toString(), s);
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		return true;
	}

	@Override
	public String toString() {
		return "shieldOff";
	}
}
