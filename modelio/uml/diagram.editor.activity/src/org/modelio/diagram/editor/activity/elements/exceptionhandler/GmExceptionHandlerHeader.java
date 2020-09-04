/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.activity.elements.exceptionhandler;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.activityModel.ExceptionHandler;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * exception handler header displayed on the node link.
 * 
 * @author sbe
 */
@objid ("2a4c0ec8-55b6-11e2-877f-002564c97630")
public class GmExceptionHandlerHeader extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2a4c0ecc-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("2a4c0ecf-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor
     * @param diagram the diagram
     * @param relatedRef related element reference, must not be <code>null</code>.
     */
    @objid ("2a4c0ed1-55b6-11e2-877f-002564c97630")
    public GmExceptionHandlerHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("2a4c0eda-55b6-11e2-877f-002564c97630")
    public GmExceptionHandlerHeader() {
        // serialization
    }

    @objid ("2a4d9553-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        // Exception type is not editable until we have a pre-built
        // multi picking edit field.
        return null;
    }

    @objid ("2a4d955a-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getDisplayedStyle().getProperty(GmExceptionHandlerStyleKeys.NAMEVISIBLE);
    }

    @objid ("2a4d955f-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        // The label is the handled exception names.
        final ExceptionHandler edge = (ExceptionHandler) getRelatedElement();
        final StringBuilder s = new StringBuilder();
        
        for (GeneralClass c : edge.getExceptionType()) {
            if (s.length() > 0) {
                s.append(", ");
            }
        
            s.append(c.getName());
        }
        return s.toString();
    }

    @objid ("2a4d956a-55b6-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (GmExceptionHandlerStyleKeys.NAMEVISIBLE.equals(property)) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("2a4d9571-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmExceptionHandlerHeader.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
    }

    @objid ("2a4d9577-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExceptionHandlerHeader.", GmExceptionHandlerHeader.MINOR_VERSION);
    }

    @objid ("2a4f1bd9-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("2a4f1bde-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
