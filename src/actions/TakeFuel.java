package actions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class TakeFuel extends ACT implements RobotProgramNode {
	private static final String LOG = "TAKEFUEL: ";

	public TakeFuel(RobotProgramNode root) {
		super(root);
	}

	@Override
	public void execute(Robot robot) {
		robot.takeFuel();
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.TAKEFUELPAT, LOG + "Expecting "
				+ Parser.TAKEFUELPAT.toString(), s);
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		return true;
	}

	@Override
	public String toString() {
		return "takeFuel";
	}

}
