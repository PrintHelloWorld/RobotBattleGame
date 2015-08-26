package grammar;

import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class LOOP implements RobotProgramNode {

	private RobotProgramNode root;
	private BLOCK block;
	private static final String LOG = "LOOP: ";

	public LOOP(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void execute(Robot robot) {
		while (true) {
			block.execute(robot);
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.LOOPPAT,
				LOG + "Expecting " + Parser.LOOPPAT.toString(), s);
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
		return LOG + block.toString();
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
