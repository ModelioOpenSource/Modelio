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

package org.modelio.patterns.api;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.patterns.model.RuntimePattern;

/**
 * Defines services offered by the Patterns plugin.
 */
@objid ("6cff23d5-b25d-4b03-91fd-00cd4b7711e1")
public interface IPatternService {
    /**
     * Export a pattern.
     * 
     * @param pattern the pattern to export.
     * @throws javax.xml.bind.JAXBException when the pattern metadatas are invalid.
     * @throws java.io.IOException when the pattern can't be read.
     */
    @objid ("196087f9-a115-4c62-b919-6733aae3e06b")
    void exportPattern(RuntimePattern pattern) throws IOException, JAXBException;

    /**
     * Get the project's pattern catalog.
     * 
     * @return the pattern catalog. Should be <code>null</code> when no project is opened.
     */
    @objid ("b21e3d18-54cf-4992-8c9e-f81720eea83e")
    IPatternRepository getCatalog();

}
