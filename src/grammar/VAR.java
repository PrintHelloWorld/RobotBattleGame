package grammar;

import java.util.Scanner;

import main.ParserFailureException;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class VAR implements RobotReturnValueNode {
	private PROG root;
	private String variableName;

	private static final String LOG = "VARS: ";

	public VAR(RobotProgramNode root, String name) {
		this.setRoot(root);
		this.variableName = name;
	}

	@Override
	public void evaluate(Robot robot) {
		//throw exception is variable is undeclared
		if (root.getVariable(variableName) == null) {
			throw new ParserFailureException(LOG
					+ "Variable must be declared before usage");
		}
	}

	@Override
	public boolean parse(Scanner s) {
		return true;
	}

	@Override
	public String getValue() {
		Integer value = root.getVariable(variableName);
		return value.toString();
	}

	@Override
	public String toString() {
		return variableName;
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = (PROG) root;
	}
}
