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

package org.modelio.diagram.elements.common.label.modelelement;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a floating ModelElement label.
 * <p>
 * Displays same as {@link GmDefaultModelElementHeader} and:<ul>
 * <li>the label may be moved
 * <li>its label may be resized
 * <li>the label try to resizes itself to fit its text
 * </ul>
 * <p>
 * <h3>History</h3>
 * This class was previously named <code>GmModelElementFlatHeader</code> and derived from <code>GmElementLabel</code>.
 */
@objid ("7e975a85-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmModelElementLabel extends GmModelElementHeader {
    @objid ("7e975a8e-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initializes a model element header.
     * 
     * @param diagram the owning diagram.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("7e975a90-1dec-11e2-8cad-001ec947c8cc")
    public GmModelElementLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Empty constructor to use only for deserialization.
     * <p>
     * Use {@link #GmModelElementFlatHeader(GmAbstractDiagram, MRef)} for regular instantiation.
     */
    @objid ("7e99bcac-1dec-11e2-8cad-001ec947c8cc")
    public GmModelElementLabel() {
        // Empty constructor to use only for deserialization.
    }

    @objid ("7e9c1f36-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("7e99bceb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // nothing to be read anymore
        super.read(in);
    }

    @objid ("7e9c1f09-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        super.styleChanged(property, newValue);
        
        GmModel parent = getParent();
        if (parent != null) {
            final StyleKey showLabelKey = parent.getStyleKey(MetaKey.SHOWLABEL);
        
            if (showLabelKey != null && showLabelKey.equals(property)) {
                fireVisibilityChanged();
            }
        }
    }

}
