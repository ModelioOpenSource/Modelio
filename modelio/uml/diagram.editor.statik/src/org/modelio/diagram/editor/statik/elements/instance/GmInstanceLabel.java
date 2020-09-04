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

package org.modelio.diagram.editor.statik.elements.instance;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Instance} label.
 */
@objid ("35358699-55b7-11e2-877f-002564c97630")
public class GmInstanceLabel extends GmDefaultModelElementLabel {
    @objid ("3535869d-55b7-11e2-877f-002564c97630")
    private Instance element = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("353586a0-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35370cfb-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * constructor to be used only for deserialization
     */
    @objid ("35370cfd-55b7-11e2-877f-002564c97630")
    public GmInstanceLabel() {
    }

    /**
     * Creates an instance label.
     * @param diagram the owning graphic diagram, may not be <tt>null</tt>.
     * @param el the represented instance, may be <tt>null</tt>.
     * @param ref the represented instance reference, may not be <tt>null</tt>.
     */
    @objid ("35370d00-55b7-11e2-877f-002564c97630")
    public GmInstanceLabel(IGmDiagram diagram, Instance el, MRef ref) {
        super(diagram, ref);
        
        this.element = el;
    }

    @objid ("35370d0c-55b7-11e2-877f-002564c97630")
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

    @objid ("35370d33-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getRelatedElement() {
        return this.element;
    }

    @objid ("35370d3a-55b7-11e2-877f-002564c97630")
    @Override
    public Instance getRepresentedElement() {
        return this.element;
    }

    @objid ("3538939a-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
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

    /**
     * Instance labels don't have own style key.
     * <p>
     * Everything is defined on the owner class.
     */
    @objid ("353893a4-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("353893ae-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInstanceLabel.");
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

    @objid ("353893bb-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        if (property == getStyleKey(MetaKey.SHOWNAME)) {
            if (updateMainLabelFromObModel()) {
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
            }
        }
        
        super.styleChanged(property, newValue);
    }

    @objid ("353893c4-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        if (updateMainLabelFromObModel()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        }
        
        super.styleChanged(changedStyle);
    }

    @objid ("353893cf-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInstanceLabel.", GmInstanceLabel.MINOR_VERSION);
    }

    @objid ("353893d5-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Instance) resolveRef(getRepresentedRef());
    }

    @objid ("353893da-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * An instance label is a flat label.
     */
    @objid ("58eb7751-879b-41ef-87a4-d7e9161685d7")
    @Override
    public boolean isFlat() {
        return true;
    }

}
