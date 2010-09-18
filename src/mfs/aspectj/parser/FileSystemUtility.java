/**
 * AJBD (AspectJ Bug Detect) is a tool for detecting bugs  in AspecJ programs.
 * Copyright (C) 2010  Meisam Fathi Salmi <meisam.fathi[at]gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package mfs.aspectj.parser;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * utility Class For working with files
 * 
 * @author Meisam Fathi
 * @version 0.1, July 26, 2010
 */
public class FileSystemUtility {

	private static final String FileExtensionSeperator = ".";

	private static final String EMPTY_STRING = "";

	private static final String[] VALID_EXTENSIONS = { //
	".java", // *.java
			".aj" // *.aj
	};

	private static String extractFileExtension(String fileName) {
		int extensionIndex = fileName.lastIndexOf(FileExtensionSeperator);
		if (extensionIndex == -1) {
			return "";
		} else {
			return fileName.substring(extensionIndex, fileName.length());
		}
	}

	public static Collection<File> listAllFile(final File directory) {
		final Collection<File> visitedFiles = new Vector<File>(1000);
		final Queue<File> dirsToVisit = new LinkedList<File>();
		if (FileSystemUtility.isValidDirectory(directory)) {
			dirsToVisit.add(directory);
		}
		while (!dirsToVisit.isEmpty()) {
			final File currentDir = dirsToVisit.poll();
			final File[] files = currentDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (FileSystemUtility.isValidDirectory(files[i])) {
					dirsToVisit.add(files[i]);
				} else if (FileSystemUtility.isValidFile(files[i])) {
					visitedFiles.add(files[i]);
				}
			}
		}
		return visitedFiles;
	}

	private static boolean isValidDirectory(final File dir) {
		return (dir != null) && dir.isDirectory() && dir.canRead();
	}

	private static boolean isValidFile(final File file) {
		return (file != null) && file.isFile() && file.canRead()
				&& hasValidExtension(file);
	}

	private static boolean hasValidExtension(File file) {
		String extension = extractFileExtension(file.getName());
		if (extension == null)
			return false;
		for (int i = 0; i < VALID_EXTENSIONS.length; i++) {
			if (VALID_EXTENSIONS[i].equals(extension))
				return true;
		}
		return false;
	}
}
