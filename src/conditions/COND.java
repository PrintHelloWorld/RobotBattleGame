package conditions;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class COND implements RobotReturnValueNode {

	private RobotProgramNode root;
	private RobotReturnValueNode child;

	private static final String LOG = "COND: ";

	public COND(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void evaluate(Robot robot) {
		child.evaluate(robot);
	}

	@Override
	public boolean parse(Scanner s) {
		if (s.hasNext(Parser.LESSPAT)) {
			child = new Lt(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.GREATERPAT)) {
			child = new Gt(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.EQUALPAT)) {
			child = new Eq(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.ANDPAT)) {
			child = new And(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.ORPAT)) {
			child = new Or(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.NOTPAT)) {
			child = new Not(getRoot());
			return child.parse(s);
		}
		Parser.fail(LOG + "Unknown Conditional", s);
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
