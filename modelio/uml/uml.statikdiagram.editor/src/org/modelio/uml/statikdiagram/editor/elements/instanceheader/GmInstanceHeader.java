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

package org.modelio.uml.statikdiagram.editor.elements.instanceheader;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.uml.statikdiagram.editor.elements.instance.GmInstanceStructuredStyleKeys;
import org.modelio.uml.statikdiagram.editor.elements.instance.InstanceSymbolProvider;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the {@link Instance} header.
 */
@objid ("3547d5da-55b7-11e2-877f-002564c97630")
public class GmInstanceHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3547d5de-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3547d5e1-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("3547d5e3-55b7-11e2-877f-002564c97630")
    public GmInstanceHeader() {
        init();
    }

    /**
     * Initialize a classifier header
     * 
     * @param diagram the owning diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("3547d5e6-55b7-11e2-877f-002564c97630")
    public GmInstanceHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        init();
    }

    @objid ("3547d5ef-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == GmInstanceStructuredStyleKeys.SHOWNAME)
            if (updateMainLabelFromObModel())
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(property, newValue);
    }

    @objid ("3547d5f6-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle changedStyle) {
        if (updateMainLabelFromObModel())
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(changedStyle);
    }

    @objid ("3547d5fc-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        final Instance c = this.getRelatedElement();
        final ShowNameMode nameMode = getDisplayedStyle().getProperty(GmInstanceStructuredStyleKeys.SHOWNAME);
        switch (nameMode) {
            case FULLQUALIFIED:
                return InstanceSymbolProvider.computeFullQualifiedLabel(c);
            case NONE:
                return "";
            case QUALIFIED:
                return InstanceSymbolProvider.computeQualifiedLabel(c);
            case SIMPLE:
            default:
                return InstanceSymbolProvider.computeSimpleLabel(c);
        
        }
    }

    @objid ("3547d600-55b7-11e2-877f-002564c97630")
    private void init() {
        this.setStackedStereotypes(true);
    }

    @objid ("3547d602-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getRelatedElement() {
        return (Instance) super.getRelatedElement();
    }

    @objid ("35495c79-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        Instance instance = getRelatedElement();
        if (instance != null && instance.isValid()) {
            setShowMetaclassIcon(instance.getBase() != null);
        } else {
            setShowMetaclassIcon(false);
        }
        
        super.refreshFromObModel();
    }

    /**
     * For an instance, the metaclass icon is the one from its base.
     */
    @objid ("35495c7c-55b7-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        Instance instance = getRelatedElement();
        NameSpace base = instance.getBase();
        if (base != null) {
            return ElementImageService.getIcon(base);
        } else {
            return ElementImageService.getIcon(instance);
        }
    }

    @objid ("35495c81-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInstanceHeader.");
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

    @objid ("35495c87-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInstanceHeader.", GmInstanceHeader.MINOR_VERSION);
    }

    @objid ("35495c8d-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35495c92-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
