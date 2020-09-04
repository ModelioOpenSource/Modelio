/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.informationitem;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.mda.Project;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the classifier header.
 * <p>
 * Contains for the moment the class name.<br>
 * Will contain in the future:<br>
 * - its visibility <br>
 * - tagged values <br>
 * - &lt;&lt;stereotypes names>> <br>
 * - a stereotype icons bar <br>
 */
@objid ("3510e79b-55b7-11e2-877f-002564c97630")
public class GmInformationItemHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3510e79f-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35126df9-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("35126dfb-55b7-11e2-877f-002564c97630")
    public GmInformationItemHeader() {
        init();
    }

    /**
     * Initialize a classifier header
     * 
     * @param diagram the owning diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("35126dfe-55b7-11e2-877f-002564c97630")
    public GmInformationItemHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        init();
    }

    @objid ("35126e23-55b7-11e2-877f-002564c97630")
    @Override
    public InformationItem getRelatedElement() {
        return (InformationItem) super.getRelatedElement();
    }

    @objid ("3513f4a2-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == InformationItemStructuredStyleKeys.SHOWNAME)
            if (updateMainLabelFromObModel())
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(property, newValue);
    }

    @objid ("3513f4a9-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle changedStyle) {
        if (updateMainLabelFromObModel())
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(changedStyle);
    }

    @objid ("3513f4af-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        ShowNameMode nameMode = getDisplayedStyle().getProperty(InformationItemStructuredStyleKeys.SHOWNAME);
        switch (nameMode) {
            case FULLQUALIFIED:
                return computeFullQualifiedLabel();
            case NONE:
                return "";
            case QUALIFIED:
                return computeQualifiedLabel();
            case SIMPLE:
            default:
                return computeSimpleLabel();
        
        }
    }

    @objid ("3513f4ba-55b7-11e2-877f-002564c97630")
    private String computeFullQualifiedLabel() {
        final StringBuilder s = new StringBuilder(100);
        final InformationItem c = this.getRelatedElement();
        
        ModelTree parent = c.getOwner();
        while (parent != null && !isRoot(parent)) {
            s.insert(0, '.');
            s.insert(0, parent.getName());
        
            parent = parent.getOwner();
        }
        
        s.append(c.getName());
        return s.toString() + computeType(c);
    }

    @objid ("3513f4be-55b7-11e2-877f-002564c97630")
    private String computeQualifiedLabel() {
        final InformationItem c = this.getRelatedElement();
        final ModelTree parent = c.getOwner();
        if (parent == null || isRoot(parent))
            return c.getName();
        else
            return parent.getName() + "." + c.getName() + computeType(c);
    }

    @objid ("3513f4c1-55b7-11e2-877f-002564c97630")
    private void init() {
        this.setStackedStereotypes(true);
    }

    /**
     * Tells whether the given element is the root package a the project.
     * 
     * @param el the element to test
     * @return true if the given element is the root package a the project, else false.
     */
    @objid ("3513f4c3-55b7-11e2-877f-002564c97630")
    private boolean isRoot(ModelTree el) {
        // Project is a root
        if (el instanceof Project)
            return true;
        
        // Root package is a root
        final MObject parent = el.getCompositionOwner();
        if (parent == null || parent instanceof Project)
            return true;
        return false;
    }

    @objid ("3513f4cb-55b7-11e2-877f-002564c97630")
    private String computeSimpleLabel() {
        final InformationItem c = this.getRelatedElement();
        return c.getName() + computeType(c);
    }

    @objid ("3513f4cf-55b7-11e2-877f-002564c97630")
    private String computeType(final InformationItem item) {
        StringBuilder s = new StringBuilder(100);
        
        final List<Classifier> types = item.getRepresented();
        boolean first = true;
        for (Classifier c : types) {
            if (first) {
                s.append(":");
                first = false;
            } else {
                s.append(", ");
            }
            s.append(c.getName());
        }
        return s.toString();
    }

    @objid ("3513f4d7-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInformationItemHeader.");
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

    @objid ("35157b3c-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInformationItemHeader.", GmInformationItemHeader.MINOR_VERSION);
    }

    @objid ("35157b42-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35157b47-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
