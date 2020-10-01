/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vstore.exml.resource;

import java.util.regex.Pattern;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * Utilities.
 * @author cma
 * @since 3.6
 */
@objid ("6b8f6551-98a4-4e8f-8832-04d009913b8b")
final class GeometryUtils {
    @objid ("8aaaae54-8186-429a-a96c-7a7bf531721a")
    private static final Pattern PATH_SPLITTER = Pattern.compile("/|\\\\");

    @objid ("d4fa1501-1ebb-457f-9035-55328d310c4f")
    private GeometryUtils() {
        // no instance.
    }

    /**
     * Get the file name part of a file path.
     * 
     * @param filePath a file path.
     * @return the file name with the extension.
     */
    @objid ("c25e5147-259e-42b2-9a35-c99857762915")
    public static String getFileName(String filePath) {
        String[] ss = PATH_SPLITTER.split(filePath);
        String fname = ss[ss.length-1];
        return fname;
    }

    /**
     * Get the parent directory name.
     * 
     * @param filePath a file path
     * @return the parent directory name
     * @throws java.lang.ArrayIndexOutOfBoundsException if there is no parent directory.
     */
    @objid ("2692d693-b71f-47a1-8174-bdfce43cb69e")
    public static String getParentFileName(String filePath) throws ArrayIndexOutOfBoundsException {
        String[] ss = PATH_SPLITTER.split(filePath);
        //String fname = ss[ss.length-1];
        String fparentName = ss[ss.length-2];
        return fparentName;
    }

}
