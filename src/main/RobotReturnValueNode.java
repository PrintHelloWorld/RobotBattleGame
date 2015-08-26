package main;

import java.util.Scanner;

public interface RobotReturnValueNode {
	public void evaluate(Robot robot);

	public boolean parse(Scanner s);

	public String toString();
	
	public String getValue();
}
