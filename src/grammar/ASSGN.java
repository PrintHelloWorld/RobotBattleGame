package grammar;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import main.RobotReturnValueNode;

public class ASSGN implements RobotProgramNode {

	private PROG root;
	private String name;
	private RobotReturnValueNode expression;
	private static final String LOG = "ASSGN: ";

	public ASSGN(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void execute(Robot robot) {
		expression.evaluate(robot);
		root.setVariable(name, Integer.parseInt(expression.getValue()));
	}

	@Override
	public boolean parse(Scanner s) {
		if (s.hasNext(Parser.VARPAT)) {
			name = s.next();
		} else {
			Parser.fail(LOG + "Expecting variable name", s);
		}
		Parser.require(Parser.EQUIVPAT, LOG + "Expecting =", s);
		EXP expr = new EXP(getRoot());
		if (!expr.parse(s)) {
			return false;
		}
		Parser.require(Parser.SEMICOLONPAT, LOG + "Expecting ;", s);
		expression = expr;
		return true;
	}

	@Override
	public String toString() {
		return name + " = " + expression.toString() + ";";
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = (PROG) root;
	}
}
