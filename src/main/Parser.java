package main;

import grammar.PROG;
import grammar.STMT;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

/**
 * The parser and interpreter. The top level parse function, a main method for
 * testing, and several utility methods are provided. You need to implement
 * parseProgram and all the rest of the parser.
 */

public class Parser {

	/**
	 * Top level parse method, called by the World
	 */
	protected static RobotProgramNode parseFile(File code) {
		Scanner scan = null;
		try {
			scan = new Scanner(code);

			// the only time tokens can be next to each other is
			// when one of them is one of (){},;
			scan.useDelimiter(TOKENIZE); // tokenize input

			RobotProgramNode n = parseProgram(scan); // You need to implement
														// this!!!
			scan.close();
			return n;
		} catch (FileNotFoundException e) {
			System.out.println("Robot program source file not found");
		} catch (ParserFailureException e) {
			System.out.println("Parser error:");
			System.out.println(e.getMessage());
			scan.close();
		}
		return null;
	}

	/** For testing the parser without requiring the world */

	public static void main(String[] args) {
		if (args.length > 0) {
			for (String arg : args) {
				File f = new File(arg);
				if (f.exists()) {
					System.out.println("Parsing '" + f + "'");
					RobotProgramNode prog = parseFile(f);
					System.out.println("Parsing completed ");
					if (prog != null) {
						System.out.println("================\nProgram:");
						System.out.println(prog);
					}
					System.out.println("=================");
				} else {
					System.out.println("Can't find file '" + f + "'");
				}
			}
		} else {
			while (true) {
				JFileChooser chooser = new JFileChooser(".");// System.getProperty("user.dir"));
				int res = chooser.showOpenDialog(null);
				if (res != JFileChooser.APPROVE_OPTION) {
					break;
				}
				RobotProgramNode prog = parseFile(chooser.getSelectedFile());
				System.out.println("Parsing completed");
				if (prog != null) {
					System.out.println("Program: \n" + prog);
				}
				System.out.println("=================");
			}
		}
		System.out.println("Done");
	}

	// Useful Patterns
	private static final Pattern TOKENIZE = Pattern
			.compile("\\s+|(?=[{}(),;])|(?<=[{}(),;])");
	public static final Pattern NUMPAT = Pattern.compile("-?\\d+"); // ("-?(0|[1-9][0-9]*)");
	public static final Pattern OPENPAREN = Pattern.compile("\\(");
	public static final Pattern CLOSEPAREN = Pattern.compile("\\)");
	public static final Pattern OPENBRACE = Pattern.compile("\\{");
	public static final Pattern CLOSEBRACE = Pattern.compile("\\}");
	public static final Pattern SEMICOLONPAT = Pattern.compile("\\;");
	public static final Pattern COMMAPAT = Pattern.compile("\\,");
	public static final Pattern EQUIVPAT = Pattern.compile("\\=");
	public static final Pattern VARPAT = Pattern
			.compile("\\$[A-Za-z][A-Za-z0-9]*");

	// Terminals
	public static final Pattern MOVEPAT = Pattern.compile("move");
	public static final Pattern TAKEFUELPAT = Pattern.compile("takeFuel");
	public static final Pattern TURNLPAT = Pattern.compile("turnL");
	public static final Pattern TURNRPAT = Pattern.compile("turnR");
	public static final Pattern WAITPAT = Pattern.compile("wait");
	public static final Pattern LOOPPAT = Pattern.compile("loop");
	public static final Pattern WHILEPAT = Pattern.compile("while");
	public static final Pattern IFPAT = Pattern.compile("if");
	public static final Pattern ELIFPAT = Pattern.compile("elif");
	public static final Pattern ELSEPAT = Pattern.compile("else");
	public static final Pattern TURNAROUNDPAT = Pattern.compile("turnAround");
	public static final Pattern SHIELDONPAT = Pattern.compile("shieldOn");
	public static final Pattern SHIELDOFFPAT = Pattern.compile("shieldOff");
	public static final Pattern LESSPAT = Pattern.compile("lt");
	public static final Pattern GREATERPAT = Pattern.compile("gt");
	public static final Pattern EQUALPAT = Pattern.compile("eq");
	public static final Pattern ANDPAT = Pattern.compile("and");
	public static final Pattern ORPAT = Pattern.compile("or");
	public static final Pattern NOTPAT = Pattern.compile("not");
	public static final Pattern FUELLEFTPAT = Pattern.compile("fuelLeft");
	public static final Pattern OPPLRPAT = Pattern.compile("oppLR");
	public static final Pattern OPPFBPAT = Pattern.compile("oppFB");
	public static final Pattern NUMBARRELPAT = Pattern.compile("numBarrels");
	public static final Pattern BARRELLRPAT = Pattern.compile("barrelLR");
	public static final Pattern BARRELFBPAT = Pattern.compile("barrelFB");
	public static final Pattern WALLDISTPAT = Pattern.compile("wallDist");
	public static final Pattern ADDPAT = Pattern.compile("add");
	public static final Pattern SUBPAT = Pattern.compile("sub");
	public static final Pattern MULPAT = Pattern.compile("mul");
	public static final Pattern DIVPAT = Pattern.compile("div");

	// Non-Terminals
	public static final Pattern ACTIONPAT = Pattern
			.compile("move|takeFuel|turnL|turnR|wait|turnAround|shieldOn|shieldOff");
	public static final Pattern SENSORPAT = Pattern
			.compile("fuelLeft|oppLR|oppFB|numBarrels|barrelLR|barrelFB|wallDist");
	public static final Pattern CONDITIONPAT = Pattern
			.compile("and|or|not|lt|gt|eq");
	public static final Pattern OPERATIONPAT = Pattern
			.compile("add|sub|mul|div");

	/**
	 * PROG ::= STMT+
	 */
	static RobotProgramNode parseProgram(Scanner s) {
		// THE PARSER GOES HERE!
		//list of statements
		List<STMT> statements = new ArrayList<STMT>();
		//map of variables
		Map<String, Integer> vars = new HashMap<String, Integer>();
		RobotProgramNode program = new PROG(statements, vars);
		if (program.parse(s)) {
			return program;
		} else {
			//easy for error checking
			return null;
		}
	}

	// utility methods for the parser
	/**
	 * Report a failure in the parser.
	 */
	public static void fail(String message, Scanner s) {
		String msg = message + "\n   @ ...";
		for (int i = 0; i < 5 && s.hasNext(); i++) {
			msg += " " + s.next();
		}
		throw new ParserFailureException(msg + "...");
	}

	public static String require(String pat, String msg, Scanner s) {
		if (s.hasNext(pat)) {
			return s.next();
		} else {
			fail(msg, s);
			return null;
		}
	}

	public static String require(Pattern pat, String msg, Scanner s) {
		if (s.hasNext(pat)) {
			return s.next();
		} else {
			fail(msg, s);
			return null;
		}
	}

	/**
	 * If the next token in the scanner matches the specified pattern, consume
	 * the token and return true. Otherwise return false without consuming
	 * anything. Useful for dealing with the syntactic elements of the language
	 * which do not have semantic content, and are there only to make the
	 * language parsable.
	 */
	public static boolean gobble(String p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

	public static boolean gobble(Pattern p, Scanner s) {
		if (s.hasNext(p)) {
			s.next();
			return true;
		} else {
			return false;
		}
	}

}

// You could add the node classes here, as long as they are not declared public
// (or private)
