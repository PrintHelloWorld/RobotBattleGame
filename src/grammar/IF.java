package grammar;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.Parser;
import main.Robot;
import main.RobotProgramNode;
import conditions.COND;

public class IF implements RobotProgramNode {

	private RobotProgramNode root;
	private COND condition;
	private BLOCK block;
	//added else if blocks
	private Map<COND, BLOCK> elifBlocks = new HashMap<COND, BLOCK>();
	private BLOCK elseBlock;

	private static final String LOG = "IF: ";

	public IF(RobotProgramNode root) {
		this.setRoot(root);
	}

	@Override
	public void execute(Robot robot) {
		condition.evaluate(robot);
		if (condition.getValue().equals("1")) {
			block.execute(robot);
		} else if (elifBlocks.size() != 0) {
			boolean executed = false;
			for (COND n : elifBlocks.keySet()) {
				n.evaluate(robot);
				if (n.getValue().equals("1")) {
					elifBlocks.get(n).execute(robot);
					executed = true;
					break;
				}
			}
			if (elseBlock != null && !executed) {
				elseBlock.execute(robot);
			}
		} else if (elseBlock != null) {
			elseBlock.execute(robot);
		}
	}

	@Override
	public boolean parse(Scanner s) {
		Parser.require(Parser.IFPAT,
				LOG + "Expecting " + Parser.IFPAT.toString(), s);
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
		while (true) {
			if (s.hasNext(Parser.ELIFPAT)) {
				Parser.require(Parser.ELIFPAT, LOG + "Expecting "
						+ Parser.ELIFPAT.toString(), s);
				Parser.require(Parser.OPENPAREN, LOG + "Expecting (", s);
				COND elifCondition = new COND(getRoot());
				if (!elifCondition.parse(s)) {
					return false;
				}
				Parser.require(Parser.CLOSEPAREN, LOG + "Expecting )", s);
				Parser.require(Parser.OPENBRACE, LOG + "Expecting {", s);
				BLOCK elifBlock = new BLOCK(getRoot());
				if (!elifBlock.parse(s)) {
					return false;
				}
				Parser.require(Parser.CLOSEBRACE, LOG + "Expecting }", s);
				elifBlocks.put(elifCondition, elifBlock);
			} else {
				break;
			}
		}
		if (s.hasNext(Parser.ELSEPAT)) {
			Parser.require(Parser.ELSEPAT,
					LOG + "Expecting " + Parser.ELSEPAT.toString(), s);
			Parser.require(Parser.OPENBRACE, LOG + "Expecting {", s);
			elseBlock = new BLOCK(getRoot());
			if (!elseBlock.parse(s)) {
				return false;
			}
			Parser.require(Parser.CLOSEBRACE, LOG + "Expecting }", s);
		}
		return true;
	}

	@Override
	public String toString() {
		String s = "if(" + condition.toString() + ") " + block.toString();
		for (COND n : elifBlocks.keySet()) {
			s += "elif(" + n.toString() + ") " + elifBlocks.get(n).toString();
		}
		if (elseBlock != null) {
			s += "else " + elseBlock.toString();
		}
		return s;
	}

	public RobotProgramNode getRoot() {
		return root;
	}

	public void setRoot(RobotProgramNode root) {
		this.root = root;
	}
}
