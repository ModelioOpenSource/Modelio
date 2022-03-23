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
package org.modelio.diagram.elements.core.link.ortho.edit;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.modelio.diagram.elements.core.figures.routers.ConnectionState;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditor;
import org.modelio.diagram.elements.core.link.path.ILinkPathEditorFactory;

/**
 * Central point to get the right auto orthogonal connection editor.
 * <p>
 * Allows to change implementation at runtime for debugging purpose.
 * @since 5.0.2
 */
@objid ("456a614b-8b21-41c3-984f-4afcc21a3090")
public class AutoOrthoLinkPathEditorFactory {
    @objid ("bd4a7498-379b-4933-9966-7f27cbadb5f0")
    private  AutoOrthoLinkPathEditorFactory() {
        // no instance
    }

    /**
     * Central point to get the right auto orthogonal connection editor.
     * @return an auto orthogonal connection editor.
     */
    @objid ("6cca5277-cdf5-4980-b17d-71330c403cb9")
    public static ILinkPathEditorFactory get() {
        if (true) {
            // Return the latest stable editor
            return AutoOrthoLinkPathEditor1.INSTANCE;
        } else {
            // return debug editor
            return new EditorChooser();
        }
        
    }

    /**
     * Debug editor that may delegate to any editor
     */
    @objid ("1c44935c-c0d0-40af-ad33-20a6df2b91e3")
    private static class EditorChooser implements ILinkPathEditorFactory {
        @objid ("accaa9fa-6c7d-4d1b-b9d2-40142d2b39fe")
        @Override
        public ILinkPathEditor from(ConnectionEditPart connectionEditPart) {
            return AutoOrthoLinkPathEditor1.INSTANCE.from(connectionEditPart);
        }

        @objid ("85e37283-db17-4cef-88c5-d495b24ae7d3")
        @Override
        public ILinkPathEditor from(ConnectionEditPart connectionEditPart, ConnectionState backup) {
            return AutoOrthoLinkPathEditor1.INSTANCE.from(connectionEditPart, backup);
        }

    }

}
