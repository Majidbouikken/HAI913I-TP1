package com.bouikken.tp1_2;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParserAST {

	private static int nbLinesOfCodes;

	public static int getNbLinesOfCodes() {
		return ParserAST.nbLinesOfCodes;
	}

	public CompilationUnit getCompilationUnit(String filePath) throws FileNotFoundException, IOException {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		char[] fileContent = this.getFileContent(filePath).toCharArray();
		parser.setSource(fileContent);
		return (CompilationUnit) parser.createAST(null);
	}

	public String getFileContent(String filePath) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		StringBuilder sb = new StringBuilder();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			sb.append(System.lineSeparator());
			line = br.readLine();
			ParserAST.nbLinesOfCodes++;
		}
		br.close();
		return sb.toString();
	}

	public List<String> getFilesPaths(File directory) {

		List<String> filesPaths = new ArrayList<>();

		for (File file : Objects.requireNonNull(directory.listFiles())) {
			if (!file.isDirectory()) {
				if (this.isJavaFile(file)) {
					filesPaths.add(file.getAbsolutePath());
				}
			} else {
				filesPaths.addAll(getFilesPaths(file));
			}
		}
		return filesPaths;
	}

	private boolean isJavaFile(File file) {
		final String extensionWanted = ".java";
		int extensionIndex = file.getName().length() - 5;
		int endFileIndex = file.getName().length();
		final String fileExtension = file.getName().substring(extensionIndex, endFileIndex);
		return fileExtension.equals(extensionWanted);
	}

}
