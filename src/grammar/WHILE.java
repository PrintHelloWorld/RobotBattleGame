package grammar;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import conditions.COND;

public class WHILE implements RobotProgramNode {

	private RobotProgramNode root;
	private COND condition;
	private BLOCK block;

	private static final String LOG = "WHILE: ";

	public WHILE(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void execute(Robot robot) {
		while (true) {
			condition.evaluate(robot);
			if (condition.getValue().equals("1")) { //true
				block.execute(robot);
			} else {
				return;
			}
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.WHILEPAT,
				LOG + "Expecting " + Parser.WHILEPAT.toString(), s);
		Parser.require(Parser.OPENPAREN, LOG + "Expecting (", s);
		condition = new COND(getRoot());
		if (!condition.parse(s)) {
			return false;
		}
		Parser.require(Parser.CLOSEPAREN, LOG + "Expecting )", s);
		Parser.require(Parser.OPENBRACE, LOG + "Expecting {", s);
		block = new BLOCK(getRoot());
		if (!block.parse(s)) {
			return false;
		}
		Parser.require(Parser.CLOSEBRACE, LOG + "Expecting }", s);
		return true;
	}

	@Override
	public String toString() {
		return "while(" + condition.toString() + ") " + block.toString();
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
