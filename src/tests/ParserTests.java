package tests;

import static org.junit.Assert.assertNull;

import java.io.File;

import main.Parser;
import main.RobotProgramNode;

import org.junit.Test;

public class ParserTests extends Parser {

	@Test
	public void s0_bad1() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s0_bad1.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s0_bad2() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s0_bad2.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s0_bad3() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s0_bad3.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s0_bad4() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s0_bad4.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s1_bad1() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s1_bad1.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s1_bad2() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s1_bad2.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s1_bad3() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s1_bad3.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s1_bad4() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s1_bad4.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s1_bad5() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s1_bad5.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s1_bad6() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s1_bad6.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s1_bad7() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s1_bad7.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s2_bad1() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s2_bad1.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s2_bad2() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s2_bad2.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s2_bad3() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s2_bad3.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s2_bad4() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s2_bad4.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s2_bad5() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s2_bad5.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s2_bad6() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s2_bad6.prog"));
		System.out.println("=================");
		assertNull(node);
	}

	@Test
	public void s2_bad7() {
		RobotProgramNode node = Parser.parseFile(new File(
				"TestPrograms/s2_bad7.prog"));
		System.out.println("=================");
		assertNull(node);
	}
}
