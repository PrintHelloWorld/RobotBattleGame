package operations;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class OP implements RobotReturnValueNode {

	private RobotProgramNode root;
	private static final String LOG = "OP: ";
	private RobotReturnValueNode child;

	public OP(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void evaluate(Robot robot) {
		//evaluates each expression then operands results.
		child.evaluate(robot);
	}

	@Override
	public boolean parse(Scanner s) {
		if (s.hasNext(Parser.ADDPAT)) {
			child = new Add(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.SUBPAT)) {
			child = new Sub(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.MULPAT)) {
			child = new Mul(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.DIVPAT)) {
			child = new Div(getRoot());
			return child.parse(s);
		}
		Parser.fail(LOG + "Unknown Operator", s);
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