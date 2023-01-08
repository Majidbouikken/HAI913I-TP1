package com.bouikken.tp1_2;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

public class GraphVisitor extends ASTVisitor {

	CompilationUnit cu;
	List<MethodDeclaration> methodsDeclaration = new ArrayList<>();
	List<MethodInvocation> methodsInvocation = new ArrayList<>();
	List<SuperMethodInvocation> superMethodsInvocation = new ArrayList<>();
	ArrayList<Method> methodsHavingReferences = new ArrayList<>();

	public void calculateGraph() {
		calculateAllMethods();
		calculateReferences();
	}
	
	private void calculateAllMethods() {
		for (MethodDeclaration methodDeclaration : methodsDeclaration) {
			// init
			ArrayList<String> references = new ArrayList<>();
			GraphVisitor graphVisitor2 = new GraphVisitor();
			graphVisitor2.setCu(cu);
			// calcul
			methodDeclaration.accept(graphVisitor2);
			String methodName = methodDeclaration.getName().toString();
			if (!methodContainedInCollection(methodName, methodsHavingReferences)) {
				references = graphVisitor2.getMethodsCalls();
				Method methodToAdd = new Method(methodName, references);
				methodsHavingReferences.add(methodToAdd);
			}
		}
	}

	private boolean methodContainedInCollection(String methodName, ArrayList<Method> methodsWithReferences2) {
		for (Method method : methodsWithReferences2) {
			if (method.getMethodName().equals(methodName)) {
				return true;
			}
		}
		return false;
	}

	private void calculateReferences() {
		for (int i = 0; i < this.methodsHavingReferences.size(); i++) {
			for (int j = 0; j < this.methodsHavingReferences.size(); j++) {
				if (i != j)
					this.methodsHavingReferences.get(i).addIfNotContained(this.methodsHavingReferences.get(j));
			}
		}
	}

	private ArrayList<String> getMethodsCalls() {
		ArrayList<String> res = new ArrayList<>();
		String methodName;
		for (MethodInvocation methodsInvocation : methodsInvocation) {
			methodName = methodsInvocation.getName().toString();
			if (!res.contains(methodName)) {
				res.add(methodName);
			}
		}
		return res;
	}

	public void setCu(CompilationUnit ast) {
		cu = ast;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		if (node.isConstructor()) {
			return false;
		}
		methodsDeclaration.add(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(MethodInvocation node) {
		methodsInvocation.add(node);
		return super.visit(node);
	}

	@Override
	public boolean visit(SuperMethodInvocation node) {
		superMethodsInvocation.add(node);
		return super.visit(node);
	}

	public String getGraph() {
		StringBuilder res = new StringBuilder("");
		for (Method method : methodsHavingReferences) {
			res.append(method);
		}
		if (res.toString().equals("")) {
			res.append("Il n'y a aucun appel de methodes !");
		}
		return res.toString();
	}

	public String getGraphAsDot() {
		StringBuilder res = new StringBuilder("digraph G {\n");
		for (Method method : methodsHavingReferences) {
			res.append(method.getMethodWithCallsLinks());
		}
		res.append("\n}");
		return res.toString();
	}

}
