import java.io.*;
import java.util.*;

public class SalaryAnalyzer {
	static final String IN_FILE_NAME = "StateOfSC-Salary-List-04012015.txt";
	static final String OUT_FILE_NAME = "OVER250000.txt";
	static final String DELIM = "\t";

	public static void main(String[] args)
	{
		System.out.println("Let's see how many state employees make over $250,000 and work at USC.");
		analyzeEmployeeFile(IN_FILE_NAME);
		System.out.println("Results have been printed to "+OUT_FILE_NAME);
	}

    private static final String SALARY_HEADER = "TotalComp";
    private static final int SALARY_THRESHOLD = 250000;

    public static int indexOf(Object[] values, Object key) {
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(key))
                return i;
        }
        return -1;
    }

	public static void analyzeEmployeeFile(String fileName)
	{
		//TODO: Fill this in
        String ret = "";
        try {
            Scanner input = new Scanner(new File(fileName));

            if (input.hasNext()) {
                String header = input.nextLine();
                int salaryCol = indexOf(header.split("\t"), SALARY_HEADER);

                while (input.hasNext()) {
                    String line = input.nextLine();
                    if (line != "") {
                        String[] employeeInfo = line.split(DELIM);
                        if (employeeInfo.length > salaryCol) {
                            double salary = Double.parseDouble(employeeInfo[salaryCol]);

                            if (salary > SALARY_THRESHOLD) {
                                ret += line + "\n";
                            }
                        }
                    }
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }

        System.out.println(ret);
        printToSalaryFile(OUT_FILE_NAME, ret);
	}

	public static void printToSalaryFile(String fileName, String text)
	{
		//TODO: Fill this in
        try {
            PrintWriter output = new PrintWriter(new FileOutputStream(fileName), true);
            output.write(text);
            output.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
	}
}
