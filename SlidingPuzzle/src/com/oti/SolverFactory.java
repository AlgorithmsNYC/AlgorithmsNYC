package com.oti;

import java.util.ArrayList;


/**
 * List of implementations of Solver, Factory/Singleton
 * */
public class SolverFactory {
	static SolverFactory m_inst=new SolverFactory();
	
	ArrayList<String> m_solversClassNames=null;
	
	private SolverFactory() {
		m_solversClassNames = new ArrayList<String>(); 
	}
	
	static void registerSolver(String name) {
		m_inst.m_solversClassNames.add(name);
	}

	static void printList() {
		for(int i =0; i<m_inst.m_solversClassNames.size(); ++i) {
			System.out.println(m_inst.m_solversClassNames.get(i) + " ["+i+"]");			
		}
	}
	
	static public Solver createSolver(int i) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return (Solver)Class.forName( m_inst.m_solversClassNames.get(i)).newInstance();
	}

	public static int getLastIndex() {
		return m_inst.m_solversClassNames.size()-1;
	}

}
