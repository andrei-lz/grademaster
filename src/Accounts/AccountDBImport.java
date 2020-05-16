package Accounts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import Objects.Account;

public class AccountDBImport {
	
	public static LinkedList<Account> accounts = new LinkedList<Account>();
	public static String fileName;
	
	public AccountDBImport(String fileName) {
		
		importFile("res/"+fileName+".csv");
		
	}
	
	private void importFile(String fileName) {
		AccountDBImport.fileName = fileName;
		try {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		String line = br.readLine();//Reading the headings
		line = br.readLine();//Reading the first line
		while (line != null) {//Loop until the end of the document
			
			String[] temp = line.split(",");// Split into an array at the "," means it splits information based on the excel boxes (csv file format)
			
			// admin check
			boolean admin = false;
			if(Integer.parseInt(temp[4])> 0) {
				admin = true;
			}
			
			accounts.add(new Account(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3], admin));
			line = br.readLine();
		}
		
		br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(accounts);
		
	}

	public static LinkedList<Account> getAccounts() {
		return accounts;
	}

	public static String getFileName() {
		return fileName;
	}
	
	
	
}
