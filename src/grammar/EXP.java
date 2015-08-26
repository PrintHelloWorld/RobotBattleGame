package grammar;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;
import operations.OP;
import sensors.SEN;

public class EXP implements RobotReturnValueNode {

	private RobotProgramNode root;
	private RobotReturnValueNode child;
	private static final String LOG = "EXP: ";

	public EXP(RobotProgramNode root) {
		this.root = root;
	}

	@Override
	public void evaluate(Robot robot) {
		child.evaluate(robot);
	}

	@Override
	public boolean parse(Scanner s) {
		if (s.hasNext(Parser.NUMPAT)) {
			child = new NUM(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.SENSORPAT)) {
			child = new SEN(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.OPERATIONPAT)) {
			child = new OP(getRoot());
			return child.parse(s);
		}
		/* Checks to see if variable is in map, if not its an invalid expression */ //moved to VAR Class
		if (s.hasNext(Parser.VARPAT)) {
			child = new VAR(getRoot(), s.next(Parser.VARPAT));
			return true;
		}
		Parser.fail(LOG + "Unknown Expression", s);
		return false;
	}

	@Override
	public String toString() {
		return child.toString();
	}

	public String getValue() {
		return child.getValue();
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
