package com.bouikken.tp1_2;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Processor {
	private final String path;
	private final ParserAST parser;
	private final StatsVisitor statVisitor;
	private final GraphVisitor graphVisitor;

	public Processor(String path) {
		super();
		this.path = path;
		parser = new ParserAST();
		statVisitor = new StatsVisitor();
		graphVisitor = new GraphVisitor();
	}

	public void display() {
		// 1
		System.out.println("1. Nombre de classes de l'application : " + statVisitor.getNbClasses());
		// 2
		System.out.println("2. Nombre de lignes de code de l'application : " + ParserAST.getNbLinesOfCodes());
		// 3
		System.out.println("3. Nombre total de méthodes de l'application : " + statVisitor.getNbMethods());
		// 4
		System.out.println("4. Nombre total de packages de l'application : " + statVisitor.getNbPackages());
		// 5
		System.out.println("5. Nombre moyen de méthodes par classe : " + (statVisitor.getNbMethods() / statVisitor.getNbClasses()));
		// 6
		System.out.println("6. Nombre moyen de lignes de code par méthode : " + (statVisitor.getNbLocMethods() / statVisitor.getNbMethods()));
		// 7
		System.out.println("7. Nombre moyen d'attributs par classe : " + (statVisitor.getNbAttributs() / statVisitor.getNbClasses()));
		// 8
		Map<String, List<String>> mapClassesMethods = statVisitor.getMapClassesMethods();
		List<String> tenPercentClassesByGtMethods = getTenPercentClassesBygreaterNb(mapClassesMethods);
		System.out.println("8. Les 10% des classes qui possèdent le plus grand nombre de méthodes : ");
		displayList(tenPercentClassesByGtMethods);
		// 9
		Map<String, List<String>> mapClassesAttributes = statVisitor.getMapClassesAttributes();
		List<String> tenPercentClassesByGtAtt = getTenPercentClassesBygreaterNb(mapClassesAttributes);
		System.out.println("9. Les 10% des classes qui possèdent le plus grand nombre d'attributs : ");
		displayList(tenPercentClassesByGtAtt);
		// 10
		Set<String> classesOfTwoCategories = getClassesOfTwoCategories(tenPercentClassesByGtAtt, tenPercentClassesByGtMethods);
		System.out.println("10. Les classes qui font partie en même temps des deux catégories précédentes : ");
		displaySet(classesOfTwoCategories);
		// 11
		System.out.print("11. Veuillez insérer la nombre des méthodes : ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();
		System.out.println("Les classes qui possèdent plus de "+x+" méthodes : ");
		List<String> classesHavingMoreThanXMeth = getClassesHavingMoreThanXMethods(mapClassesMethods, x);
		displayList(classesHavingMoreThanXMeth);
		/// 12
		System.out.println("12. Les 10% des méthodes qui possèdent le plus grand nombre de lignes de code (par classe) :");
		Map<String, Map<String, Integer>> mapClassesMethodsLoc = statVisitor.getMapClassesMethodsLoc();
		// displayMapMap(mapClassesMethodsLoc);
		Map<String, List<String>> tenPresentMethodsHavingMaxLocByClass = getTenPersentMethodsHavingMaxLocByClass(
				mapClassesMethodsLoc);
		displayMap(tenPresentMethodsHavingMaxLocByClass);
		// 13
		System.out.println("13. Le nombre maximal de paramètres par rapport à toutes les méthodes de l'application est de : " + statVisitor.getNbArgsMax());
		System.out.println();
	}

	public void displayList(List<String> list) {
		if (list.isEmpty()) {
			System.out.println("Aucun élément à afficher");
		} else {
			for (String elt : list) {
				System.out.println(MainApp.indentationFormat + "" + elt);
			}
		}
	}

	public void displayMap(Map<String, List<String>> map) {
		if (map.isEmpty()) {
			System.out.println("Aucun élément à afficher");
		} else {
			for (String className : map.keySet()) {
				System.out.println(MainApp.indentationFormat + "Nom de la classe : " + className);
				for (String methodName : map.get(className)) {
					System.out.println(MainApp.indentationFormat + MainApp.indentationFormat + methodName);
				}
			}
		}
	}

	public void displayMapMap(Map<String, Map<String, Integer>> map) {
		Map<String, Integer> methodMap;
		if (map.isEmpty()) {
			System.out.println("Aucun élément à afficher");
		} else {
			for (String className : map.keySet()) {
				System.out.println(MainApp.indentationFormat + "Nom de la classe : " + className);
				methodMap = map.get(className);
				for (String methodName : methodMap.keySet()) {
					System.out.println(MainApp.indentationFormat + MainApp.indentationFormat + "Nom de la méthode : "
							+ methodName + "  nbLine : " + methodMap.get(methodName));
				}
			}
		}
	}

	public void displaySet(Set<String> set) {
		if (set.isEmpty()) {
			System.out.println("Aucun élément à afficher");
		} else {
			for (String elt : set) {
				System.out.println(MainApp.indentationFormat + "" + elt);
			}
		}
	}

	public String exercice1() {
		return String.valueOf(statVisitor.getNbClasses());
	}

	public String exercice10() {
		return returnSetAsString(
				getClassesOfTwoCategories(getTenPercentClassesBygreaterNb(statVisitor.getMapClassesAttributes()),
						getTenPercentClassesBygreaterNb(statVisitor.getMapClassesMethods())));
	}

	public String exercice11(int x) {
		return returnListAsString(getClassesHavingMoreThanXMethods(statVisitor.getMapClassesMethods(), x));
	}

	public String exercice12() {
		return returnMapAsString(getTenPersentMethodsHavingMaxLocByClass(statVisitor.getMapClassesMethodsLoc()));
	}

	public String exercice13() {
		return String.valueOf(statVisitor.getNbArgsMax());
	}

	public String exercice2() {
		return String.valueOf(ParserAST.getNbLinesOfCodes());
	}

	public String exercice3() {
		return String.valueOf(statVisitor.getNbMethods());
	}

	public String exercice4() {
		return String.valueOf(statVisitor.getNbPackages());
	}

	public String exercice5() {
		return String.valueOf((statVisitor.getNbMethods() / statVisitor.getNbClasses()));
	}

	public String exercice6() {
		return String.valueOf((statVisitor.getNbLocMethods() / statVisitor.getNbMethods()));
	}

	public String exercice7() {
		return String.valueOf((statVisitor.getNbAttributs() / statVisitor.getNbClasses()));
	}

	public String exercice8() {
		return returnListAsString(getTenPercentClassesBygreaterNb(statVisitor.getMapClassesMethods()));
	}

	public String exercice9() {
		return returnListAsString(getTenPercentClassesBygreaterNb(statVisitor.getMapClassesAttributes()));
	}

	private List<String> getClassesHavingMoreThanXMethods(Map<String, List<String>> mapClassesMethods, int x) {
		List<String> classesChoosed = new ArrayList<>();
		for (String className : mapClassesMethods.keySet()) {
			if (mapClassesMethods.get(className).size() > x) {
				classesChoosed.add(className);
			}
		}
		return classesChoosed;
	}

	public Set<String> getClassesOfTwoCategories(List<String> tenPercentClassesByGtAtt,
			List<String> tenPercentClassesByGtMeth) {
		Set<String> result = tenPercentClassesByGtAtt.stream().distinct().filter(tenPercentClassesByGtMeth::contains)
				.collect(Collectors.toSet());
		return result;
	}

	public List<String> getJavaFiles() {
		File directory = new File(path);
		return parser.getFilesPaths(directory);
	}

	public List<String> getTenPercentClassesBygreaterNb(Map<String, List<String>> map) {
		List<String> classesChoosed = new ArrayList<>();
		SortedMap<Integer, String> tempSortedMap = new TreeMap<>();
		String[] sortedClassesTempArray;
		int nbClasses = map.keySet().size();
		int nbClassesWanted = ((10 * nbClasses) / 100) + 1;
		for (String className : map.keySet()) {
			tempSortedMap.put(map.get(className).size(), className);
		}
		sortedClassesTempArray = tempSortedMap.values().toArray(new String[0]);

		for (int i = sortedClassesTempArray.length - 1; i > (sortedClassesTempArray.length - nbClassesWanted
				- 1); i--) {
			classesChoosed.add(sortedClassesTempArray[i]);
		}
		return classesChoosed;
	}

	private Map<String, List<String>> getTenPersentMethodsHavingMaxLocByClass(
			Map<String, Map<String, Integer>> mapClassesMethodsLoc) {
		Map<String, List<String>> classAndMethodChoosed = new HashMap<>();
		SortedMap<Integer, String> tempSortedMap;
		Map<String, Integer> tempMapMethodLoc;
		String[] sortedMethodLocTempArray;
		List<String> tempListMethodChoosed;
		int tenPersentMethNb = 0;
		for (String ClassName : mapClassesMethodsLoc.keySet()) {
			tempMapMethodLoc = mapClassesMethodsLoc.get(ClassName);
			tenPersentMethNb = (int) ((tempMapMethodLoc.keySet().size() * 0.1) + 1);
			tempSortedMap = new TreeMap<>();
			for (String methodName : tempMapMethodLoc.keySet()) {
				tempSortedMap.put(tempMapMethodLoc.get(methodName), methodName);
			}
			sortedMethodLocTempArray = tempSortedMap.values().toArray(new String[0]);
			if (sortedMethodLocTempArray.length > 0) {
				tempListMethodChoosed = new ArrayList<>();
				for (int i = sortedMethodLocTempArray.length - 1; i > (sortedMethodLocTempArray.length
						- tenPersentMethNb - 1); i--) {
					tempListMethodChoosed.add(sortedMethodLocTempArray[i]);

				}
				classAndMethodChoosed.put(ClassName, tempListMethodChoosed);
			}

		}
		return classAndMethodChoosed;
	}

	public void process() throws IOException {
		List<String> javaFilesPaths = this.getJavaFiles();
		CompilationUnit ast;

		for (String filePath : javaFilesPaths) {
			ast = parser.getCompilationUnit(filePath);
			statVisitor.setCu(ast);
			ast.accept(statVisitor);
		}
	}

	public void processGraph() throws IOException {
		List<String> javaFilesPaths = this.getJavaFiles();
		CompilationUnit ast;

		for (String filePath : javaFilesPaths) {
			ast = parser.getCompilationUnit(filePath);
			graphVisitor.setCu(ast);
			ast.accept(graphVisitor);
			graphVisitor.calculateGraph();
		}

	}

	public String returnListAsString(List<String> list) {
		StringBuilder sb = new StringBuilder();
		if (list.isEmpty()) {
			sb.append("Aucun élément à afficher");
		} else {
			for (String elt : list) {
				sb.append(MainApp.indentationFormat + "" + elt);
			}
		}
		return sb.toString();
	}

	private String returnMapAsString(Map<String, List<String>> map) {
		StringBuilder sb = new StringBuilder();
		if (map.isEmpty()) {
			sb.append("Aucun élément à afficher");
		} else {
			for (String className : map.keySet()) {
				sb.append(MainApp.indentationFormat + "Nom de la classe : " + className);
				for (String methodName : map.get(className)) {
					sb.append(MainApp.indentationFormat + MainApp.indentationFormat + "" + methodName);
				}
			}
		}
		return sb.toString();
	}

	public String returnSetAsString(Set<String> set) {
		StringBuilder sb = new StringBuilder();
		if (set.isEmpty()) {
			sb.append("Aucun élément à afficher");
		} else {
			for (String elt : set) {
				sb.append(MainApp.indentationFormat + "" + elt);
			}
		}
		return sb.toString();
	}

	public String getGraph() {
		return graphVisitor.getGraph();
	}

	public void saveGraph() {
		try {
			FileWriter fw = new FileWriter("graph.dot", false);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(graphVisitor.getGraphAsDot());
			out.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Exception ecriture fichier");
			e.printStackTrace();
		}
	}

	public void saveGraphAsPNG() throws IOException {
		MutableGraph g = new Parser().read(graphVisitor.getGraphAsDot());
		Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("graph.png"));

		g.graphAttrs().add(Color.WHITE.gradient(Color.rgb("888888")).background().angle(90)).nodeAttrs()
				.add(Color.WHITE.fill()).nodes()
				.forEach(node -> node.add(Color.named(node.name().toString()), Style.lineWidth(4), Style.FILLED));
		Graphviz.fromGraph(g).width(700).render(Format.PNG).toFile(new File("graph-alternate.png"));

	}
}
