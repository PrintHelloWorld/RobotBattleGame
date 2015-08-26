package grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;

public class BLOCK implements RobotProgramNode {

	private RobotProgramNode root;
	private List<STMT> children = new ArrayList<STMT>();
	private static final String LOG = "BLOCK: ";

	public BLOCK(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void execute(Robot robot) {
		for (STMT n : children) {
			n.execute(robot);
		}
	}

	@Override
	public boolean parse(Scanner s) {
		do {
			STMT statement = new STMT(getRoot());
			statement.parse(s);
			children.add(statement);
		} while (!s.hasNext(Parser.CLOSEBRACE));
		{
			if (!s.hasNext()) {
				Parser.require(Parser.CLOSEBRACE, LOG + "Expecting }", s);
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (STMT stmt : children) {
			string.append(stmt.toString());
		}
		return "BLOCK { " + string.toString() + " } ";
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
