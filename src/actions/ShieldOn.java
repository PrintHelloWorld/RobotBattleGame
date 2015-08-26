package actions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class ShieldOn extends ACT implements RobotProgramNode {

	private static final String LOG = "SHIELDON: ";

	public ShieldOn(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void execute(Robot robot) {
		robot.setShield(true);
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.SHIELDONPAT, LOG + "Expecting "
				+ Parser.SHIELDONPAT.toString(), s);
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		return true;
	}

	@Override
	public String toString() {
		return "shieldOn";
	}
}
