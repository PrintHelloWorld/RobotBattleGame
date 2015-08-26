package grammar;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import main.Robot;
import main.RobotProgramNode;

public class PROG implements RobotProgramNode {

	private List<STMT> statements;
	private Map<String, Integer> vars;

	private static final String LOG = "PROG: ";

	public PROG(List<STMT> statements, Map<String, Integer> vars) {
		this.statements = statements;
		this.vars = vars;
	}

	@Override
	public void execute(Robot robot) {
		for (STMT statement : statements) {
			statement.execute(robot);
		}
	}

	@Override
	public boolean parse(Scanner s) {
		while (s.hasNext()) {
			STMT statement = new STMT(this);
			if (!statement.parse(s)) {
				return false;
			}
			statements.add(statement);
		}
		return true;
	}

	@Override
	public String toString() {
		String s = LOG + "\n";
		for (STMT n : statements) {
			s += n.toString() + "\n";
		}
		return s;
	}

	public Integer getVariable(String name) {
		return vars.containsKey(name) ? vars.get(name) : 0;
	}

	public void setVariable(String name, int value) {
		vars.put(name, value);
	}
}
