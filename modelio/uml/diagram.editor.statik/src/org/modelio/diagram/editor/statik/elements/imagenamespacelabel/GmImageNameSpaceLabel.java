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

package org.modelio.diagram.editor.statik.elements.imagenamespacelabel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.namespacelabel.GmNameSpaceLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link NameSpace} label in image mode.
 * 
 * Derives from {@link GmNameSpaceLabel}.
 * Seems to used only for being easily found with <code>instanceof</code>.
 */
@objid ("34f0dc3a-55b7-11e2-877f-002564c97630")
public class GmImageNameSpaceLabel extends GmNameSpaceLabel {
    @objid ("34f0dc44-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty constructor needed for (de-)serialization.
     */
    @objid ("34f0dc46-55b7-11e2-877f-002564c97630")
    public GmImageNameSpaceLabel() {
        // Empty constructor needed for (de-)serialization.
    }

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param el the represented element, may be <i>null</i>.
     * @param ref a reference to the represented element.
     */
    @objid ("34f0dc49-55b7-11e2-877f-002564c97630")
    public GmImageNameSpaceLabel(final IGmDiagram diagram, final NameSpace el, final MRef ref) {
        super(diagram, el, ref);
    }

    @objid ("34f3e9b0-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
