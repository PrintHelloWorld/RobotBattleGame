package grammar;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import actions.ACT;

public class STMT implements RobotProgramNode {

	private RobotProgramNode root;
	private RobotProgramNode child;
	private static final String LOG = "STMT: ";

	public STMT(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void execute(Robot robot) {
		child.execute(robot);
	}

	@Override
	public boolean parse(Scanner s) {
		if (!s.hasNext()) {
			Parser.fail(LOG + "Empty Expression", s);
		} else if (s.hasNext(Parser.ACTIONPAT)) {
			child = new ACT(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.LOOPPAT)) {
			child = new LOOP(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.IFPAT)) {
			child = new IF(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.WHILEPAT)) {
			child = new WHILE(getRoot());
			return child.parse(s);
		}
		if (s.hasNext(Parser.VARPAT)) {
			child = new ASSGN(getRoot());
			return child.parse(s);
		}
		Parser.fail(LOG + "Unknown Statement", s);
		return false;
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
