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

package org.modelio.diagram.elements.persistence;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.persistence.IExtReferenceResolver;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.PersistenceException;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * External reference resolver to read diagrams.
 * 
 * @see IExtReferenceResolver
 * @author cmarin
 */
@objid ("8108026f-1dec-11e2-8cad-001ec947c8cc")
public class ExtReferenceResolver implements IExtReferenceResolver {
    @objid ("81080271-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IPersistent resolveReference(final String type, final String dbId, final String extId) throws PersistenceException {
        try {
            // Get the java class
            final Class<?> clazz = Class.forName(type);
        
            return resolveReference(clazz, extId);
        } catch (ClassNotFoundException e) {
            // Old migration case, diagram namespacing changed a long time ago...
            if (type.startsWith("com.modeliosoft.modelio.diagram")) {
                return resolveReference(type.replace("com.modeliosoft.modelio.diagram", "org.modelio.diagram"), dbId, extId);
            }
            throw new PersistenceException(e);
        }
    }

    @objid ("810a64c5-1dec-11e2-8cad-001ec947c8cc")
    private static IPersistent resolveReference(final Class<?> clazz, final String extId) {
        if (clazz == FactoryStyle.class) {
            return FactoryStyle.getInstance();
        } else if (clazz == NamedStyle.class) {
            return DiagramStyles.getStyleManager().getStyle(extId);
        } else {
            throw new PersistenceException("External references for '" +
                    clazz.getSimpleName() +
                    "' not handled");
        }
    }

}
