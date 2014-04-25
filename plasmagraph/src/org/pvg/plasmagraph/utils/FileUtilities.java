package org.pvg.plasmagraph.utils;

import java.io.File;

/**
 * 
 * @author Plasma Visualization Group
 *
 */
public class FileUtilities {

	/**
	 * Borrowed code from <a href = "http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html">
	 * Oracle's Utils.java</a> file.
	 * @param f
	 * @return The file type name extension.
	 */
	public static String getExtension (File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}
