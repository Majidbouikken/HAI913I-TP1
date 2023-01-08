package com.bouikken.tp1_2;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsVisitor extends ASTVisitor {

	private int nbClasses;
	private int nbMethods;
	private int nbPackages;
	private List<String> packagesNames = new ArrayList<>();
	private int nbLocMethods;
	private CompilationUnit cu;
	private int nbAttributs;
	private Map<String, List<String>> mapClassesMethods = new HashMap<>();
	private String tempClassName;
	private Map<String, List<String>> mapClassesAttributes = new HashMap<>();
	private Map<String, Map<String, Integer>> mapClassesMethodsLoc = new HashMap<>();
	private int nbInstrMeth;
	private int nbArgsMax;

	public StatsVisitor() {
		super();
		nbClasses = 0;
		nbMethods = 0;
		nbPackages = 0;
		nbLocMethods = 0;
		nbAttributs = 0;
		nbInstrMeth = 0;
		setNbArgsMax(0);
	}

	public StatsVisitor(boolean visitDocTags) {
		super(visitDocTags);
	}

	public void addFieldsForClass(String className, FieldDeclaration[] fields) {
		for (FieldDeclaration field : fields) {
			mapClassesAttributes.get(className).add(field.toString());
		}
	}

	@Override
	public void endVisit(MethodDeclaration md) {
		Map<String, Integer> mapMethodsLoc = mapClassesMethodsLoc.get(tempClassName);
		mapMethodsLoc.put(md.getName().toString(), nbInstrMeth);

	}

	public CompilationUnit getCu() {
		return cu;
	}

	public Map<String, List<String>> getMapClassesAttributes() {
		return mapClassesAttributes;
	}

	public Map<String, List<String>> getMapClassesMethods() {
		return mapClassesMethods;
	}

	// pour le calculer du nombre de ligne de codes de toutes les méthodes

	public Map<String, Map<String, Integer>> getMapClassesMethodsLoc() {
		return mapClassesMethodsLoc;
	}

	public int getNbArgsMax() {
		return nbArgsMax;
	}

	public int getNbAttributs() {
		return nbAttributs;
	}

	// Getters and Setters
	public int getNbClasses() {
		return nbClasses;
	}

	public int getNbLocMethods() {
		return nbLocMethods;
	}

	public int getNbMethods() {
		return nbMethods;
	}

	public int getNbPackages() {
		return nbPackages;
	}

	public void setCu(CompilationUnit cu) {
		this.cu = cu;
	}

	public void setMapClassesAttributes(Map<String, List<String>> mapClassesAttributes) {
		this.mapClassesAttributes = mapClassesAttributes;
	}

	public void setMapClassesMethods(Map<String, List<String>> mapClassesMethods) {
		this.mapClassesMethods = mapClassesMethods;
	}

	public void setMapClassesMethodsLoc(Map<String, Map<String, Integer>> mapClassesMethodsLoc) {
		this.mapClassesMethodsLoc = mapClassesMethodsLoc;
	}

	public void setNbArgsMax(int nbArgsMax) {
		this.nbArgsMax = nbArgsMax;
	}

	public void setNbAttributs(int nbAttributs) {
		this.nbAttributs = nbAttributs;
	}

	public void setNbClasses(int nbClasses) {
		this.nbClasses = nbClasses;
	}

	public void setNbLocMethods(int nbLocMethods) {
		this.nbLocMethods = nbLocMethods;
	}

	public void setNbMethods(int nbMethods) {
		this.nbMethods = nbMethods;
	}

	public void setNbPackages(int nbPackages) {
		this.nbPackages = nbPackages;
	}

	@Override
	public boolean visit(AssertStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(Block node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(BreakStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(ConstructorInvocation node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(ContinueStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(DoStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(EmptyStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(EnhancedForStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(ExpressionStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(ForStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(IfStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(LabeledStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(MethodDeclaration methodNode) {
		nbMethods++;
		mapClassesMethods.get(tempClassName).add(methodNode.getName().toString());
		nbInstrMeth = 0;
		Map<String, Integer> mapMethodsLoc = mapClassesMethodsLoc.get(tempClassName);
		mapMethodsLoc.put(methodNode.getName().toString(), nbInstrMeth);
		// Pour la question 13
		if (nbArgsMax < methodNode.parameters().size()) {
			nbArgsMax = methodNode.parameters().size();
		}
		return true;
	}

	@Override
	public boolean visit(PackageDeclaration packageNode) {
		if (!packagesNames.contains(packageNode.getName().toString())) {
			nbPackages++;
			packagesNames.add(packageNode.getName().toString());
		}
		return true;
	}

	@Override
	public boolean visit(ReturnStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(SuperConstructorInvocation node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(SwitchCase node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(SwitchStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(SynchronizedStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(ThrowStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(TryStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(TypeDeclaration typeNode) {
		tempClassName = typeNode.getName().toString();
		nbAttributs += typeNode.getFields().length;
		mapClassesAttributes.put(tempClassName, new ArrayList<String>());
		addFieldsForClass(tempClassName, typeNode.getFields());
		mapClassesMethods.put(tempClassName, new ArrayList<String>());
		nbClasses++;
		mapClassesMethodsLoc.put(tempClassName, new HashMap<String, Integer>());
		return true;
	}

	@Override
	public boolean visit(TypeDeclarationStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

	@Override
	public boolean visit(WhileStatement node) {
		// System.out.println(cu.getLineNumber(node.getStartPosition()));
		nbLocMethods++;
		nbInstrMeth++;
		return true;
	}

}
