package sensors;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class SEN implements RobotReturnValueNode {

	private RobotProgramNode root;
	private RobotReturnValueNode child;

	private static final String LOG = "SEN: ";

	public SEN(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void evaluate(Robot robot) {
		child.evaluate(robot);
	}

	@Override
	public boolean parse(Scanner s) {
		if (s.hasNext(Parser.FUELLEFTPAT)) {
			child = new FuelLeft(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.OPPLRPAT)) {
			child = new OpponentLR(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.OPPFBPAT)) {
			child = new OpponentFB(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.NUMBARRELPAT)) {
			child = new NumBarrels(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.BARRELLRPAT)) {
			child = new BarrelLR(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.BARRELFBPAT)) {
			child = new BarrelFB(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.WALLDISTPAT)) {
			child = new WallDist(getRoot());
			return child.parse(s);
		}
		Parser.fail(LOG + "Unknown Sensor", s);
		return false;
	}

	@Override
	public String getValue() {
		return child.getValue();
	}

	@Override
	public String toString() {
		return child.toString();
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
