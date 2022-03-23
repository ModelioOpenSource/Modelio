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
package org.modelio.uml.statikdiagram.editor.elements.instance;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Instance} label for the image mode.
 * <p>
 * Extends {@link GmModelElementLabel}.
 */
@objid ("352de550-55b7-11e2-877f-002564c97630")
public class GmImageInstanceLabel extends GmDefaultModelElementLabel {
    @objid ("352de554-55b7-11e2-877f-002564c97630")
    private MObject element = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("352de557-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("352de55a-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * constructor to be used only for deserialization
     */
    @objid ("352de55c-55b7-11e2-877f-002564c97630")
    public  GmImageInstanceLabel() {
        
    }

    /**
     * Creates an instance label.
     * @param diagram the owning graphic diagram, may not be <tt>null</tt>.
     * @param el the represented instance, may be <tt>null</tt>.
     * @param ref the represented instance reference, may not be <tt>null</tt>.
     */
    @objid ("352de55f-55b7-11e2-877f-002564c97630")
    public  GmImageInstanceLabel(final IGmDiagram diagram, final Instance el, final MRef ref) {
        super(diagram, ref);
        
        this.element = el;
        
    }

    @objid ("352de56e-55b7-11e2-877f-002564c97630")
    @Override
    public String computeMainLabel() {
        final Instance inst = getRelatedElement();
        
        final ShowNameMode nameMode = getDisplayedStyle().getProperty(GmInstanceStructuredStyleKeys.SHOWNAME);
        
        switch (nameMode) {
            case FULLQUALIFIED:
                return InstanceSymbolProvider.computeFullQualifiedLabel(inst);
            case NONE:
                return "";
            case QUALIFIED:
                return InstanceSymbolProvider.computeQualifiedLabel(inst);
            case SIMPLE:
            default:
                return InstanceSymbolProvider.computeSimpleLabel(inst);
        
        }
        
    }

    @objid ("352f6bf7-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getRelatedElement() {
        return (Instance) super.getRelatedElement();
    }

    @objid ("352f6bfe-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("352f6c05-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        if (metakey == MetaKey.FONT) {
            return super.getStyleKey(MetaKey.InternalGroup.INTFONT);
        } else if (metakey == MetaKey.SHOWSTEREOTYPES) {
            return super.getStyleKey(MetaKey.InternalGroup.INTSHOWSTEREOTYPES);
        } else if (metakey == MetaKey.SHOWTAGS) {
            return super.getStyleKey(MetaKey.InternalGroup.INTSHOWTAGS);
        } else if (metakey == MetaKey.TEXTCOLOR) {
            return super.getStyleKey(MetaKey.InternalGroup.INTTEXTCOLOR);
        }
        return null;
    }

    @objid ("352f6c1a-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmImageInstanceLabel.");
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

    @objid ("3530f285-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        if (property == getStyleKey(MetaKey.SHOWNAME))
            if (updateMainLabelFromObModel())
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(property, newValue);
        
    }

    @objid ("3530f28e-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        if (updateMainLabelFromObModel())
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(changedStyle);
        
    }

    @objid ("3530f299-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmImageInstanceLabel.", GmImageInstanceLabel.MINOR_VERSION);
        
    }

    @objid ("3530f29f-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.element = resolveRef(this.getRepresentedRef());
        
    }

    @objid ("3530f2a5-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
