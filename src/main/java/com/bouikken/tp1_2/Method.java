package com.bouikken.tp1_2;

import java.util.ArrayList;

public class Method {

	String methodName;
	ArrayList<String> calls = new ArrayList<>();
	ArrayList<String> entries = new ArrayList<>();

	public Method(String name, ArrayList<String> calls) {
		super();
		this.methodName = name;
		this.calls = calls;
	}

	public void addIfNotContained(Method method) {
		if (method.getCalls().contains(this.getMethodName())) {
			this.getEntries().add(method.getMethodName());
		}
	}

	public ArrayList<String> getCalls() {
		return calls;
	}

	public ArrayList<String> getEntries() {
		return entries;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getMethodWithCallsLinks() {
		StringBuilder res = new StringBuilder("");
		for (String string : calls) {
			res.append(methodName).append("->").append(string).append(" ");
		}
		return res.toString();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Methode : ").append(this.getMethodName()).append("\nMethode(s) sortie : ")
				.append(this.getCalls().toString()).append("\nMethode(s) entree : ")
				.append(this.getEntries().toString()).append("\n");
		return res.toString();
	}

}
