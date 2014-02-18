package org.pvg.plasmagraph.utils;

import java.io.File;

/**
 * 
 * @author Gerardo A. Navas Morales
 *
 */
public class FileUtilities {

	/**
	 * Borrowed code from {@link http://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html} 's
	 * Utils.java file.
	 * @param f
	 * @return
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
