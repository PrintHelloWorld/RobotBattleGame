package actions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class ACT implements RobotProgramNode {

	private RobotProgramNode root;
	private RobotProgramNode child;
	private static final String LOG = "ACT: ";

	public ACT(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void execute(Robot robot) {
		child.execute(robot);
	}

	@Override
	public boolean parse(Scanner s) {
		/* all actions extend action in order to access root */
		if (s.hasNext(Parser.MOVEPAT)) {
			child = new Move(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.TAKEFUELPAT)) {
			child = new TakeFuel(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.TURNLPAT)) {
			child = new TurnLeft(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.TURNRPAT)) {
			child = new TurnRight(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.WAITPAT)) {
			child = new Wait(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.TURNAROUNDPAT)) {
			child = new TurnAround(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.SHIELDONPAT)) {
			child = new ShieldOn(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.SHIELDOFFPAT)) {
			child = new ShieldOff(getRoot());
			return child.parse(s);
		}
		Parser.fail(LOG + "Unknown action", s);
		return false;
	}

	@Override
	public String toString() {
		return child.toString() + ";";
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
