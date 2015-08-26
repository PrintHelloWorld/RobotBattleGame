package grammar;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class NUM implements RobotReturnValueNode {

	private RobotProgramNode root;
	private String value;
	private static final String LOG = "NUM: ";

	public NUM(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void evaluate(Robot robot) {
		if (value == null) {
			value = "0";
		}
	}

	@Override
	public boolean parse(Scanner s) {
		if (s.hasNext(Parser.NUMPAT)) {
			value = s.next();
			return true;
		} else {
			Parser.fail(LOG + "Expecting a number", s);
			return false;
		}
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
