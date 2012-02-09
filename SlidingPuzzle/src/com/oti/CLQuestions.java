package com.oti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CLQuestions {
	
	Solver solver = null;
	int itert = 5;
	
	public CLQuestions() {}
	
	void askUser() throws IOException {
    	// interactive input args loop
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = null;
		int indsolv = SolverFactory.getLastIndex();
		//arg Solver instanciation
		while (s == null) {
			SolverFactory.printList();
			System.out.print("Choose a Solver ["+indsolv+"] > ");	
			while ((s = in.readLine()) == null) {				
			}
			try {
				if (s.length()>0)
					indsolv = Integer.parseInt(s);
				solver = SolverFactory.createSolver(indsolv);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();				
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (InstantiationException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			if (solver!=null) {
				System.out.println("Solver ["+solver.getClass().getName()+"]");
			} else {
				System.out.println("wrong try again.");
				s = null;
			}
		}    	
		//arg number of backsteps
		s = null;
		while (s == null) {
			System.out.print("Choose a number of backsteps for initial state creation["+itert+"] > ");	
			while ((s = in.readLine()) == null) {				
			}
			try {
				if (s.length()>0)
					itert = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();	
				s = null;
			}
			if (s!=null) {
				System.out.println("backsteps ["+itert+"]");
			} else {
				System.out.println("wrong try again.");
			}
		}    			
	}
	 
	public Solver getSolver() {
		return solver;
	}
	public int getItert() {
		return itert;
	}

}
