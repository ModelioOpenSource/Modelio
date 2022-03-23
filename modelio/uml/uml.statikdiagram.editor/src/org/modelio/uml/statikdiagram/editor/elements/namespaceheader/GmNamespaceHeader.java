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
package org.modelio.uml.statikdiagram.editor.elements.namespaceheader;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Header for {@link NameSpace} .
 * <p>
 * Same as {@link GmModelElementHeader} but adds qualified name and visibility.
 */
@objid ("96c7f7fb-55b6-11e2-877f-002564c97630")
public class GmNamespaceHeader extends GmDefaultModelElementHeader {
    @objid ("3598b4fc-55b7-11e2-877f-002564c97630")
    private boolean isAbstract = false;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3598b4fd-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3598b500-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("3598b502-55b7-11e2-877f-002564c97630")
    public  GmNamespaceHeader() {
        init();
    }

    /**
     * Initialize a classifier header
     * @param diagram the owning diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("3598b505-55b7-11e2-877f-002564c97630")
    public  GmNamespaceHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        init();
        
    }

    @objid ("359a3baf-55b7-11e2-877f-002564c97630")
    @Override
    public NameSpace getRelatedElement() {
        return (NameSpace) super.getRelatedElement();
    }

    /**
     * Delegates to the parent.
     */
    @objid ("359a3bb6-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("359a3bd3-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getStyleKey(MetaKey.SHOWNAME))
            if (updateMainLabelFromObModel())
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(property, newValue);
        
    }

    @objid ("359bc239-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle changedStyle) {
        if (updateMainLabelFromObModel())
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
        super.styleChanged(changedStyle);
        
    }

    @objid ("359bc23f-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        StyleKey showNameKey = getStyleKey(MetaKey.SHOWNAME);
        final ShowNameMode nameMode = (ShowNameMode) (showNameKey == null ? ShowNameMode.NONE
                : getDisplayedStyle().getProperty(showNameKey));
        
        switch (nameMode) {
            case FULLQUALIFIED:
                return NamespaceSymbolProvider.computeFullQualifiedLabel(getRelatedElement(),
                                                                         showVisibility());
            case NONE:
                return "";
            case QUALIFIED:
                return NamespaceSymbolProvider.computeQualifiedLabel(getRelatedElement(), showVisibility());
            case SIMPLE:
            default:
                return NamespaceSymbolProvider.computeSimpleLabel(getRelatedElement(), showVisibility());
        
        }
        
    }

    @objid ("359bc24a-55b7-11e2-877f-002564c97630")
    private void init() {
        this.setStackedStereotypes(true);
    }

    @objid ("359bc24c-55b7-11e2-877f-002564c97630")
    private boolean showVisibility() {
        final StyleKey showVisibilityKey = getStyleKey(MetaKey.SHOWVISIBILITY);
        return (showVisibilityKey != null && (Boolean) getDisplayedStyle().getProperty(showVisibilityKey));
    }

    @objid ("359bc250-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        NameSpace el = getRelatedElement();
        if (el != null && el.isValid()) {
            setAbstract(el.isIsAbstract());
        }
        
    }

    /**
     * @param isAbstract the isAbstract to set
     */
    @objid ("359bc253-55b7-11e2-877f-002564c97630")
    public void setAbstract(final boolean isAbstract) {
        if (this.isAbstract != isAbstract) {
            this.isAbstract = isAbstract;
            firePropertyChange(PROPERTY_STYLE, null, getDisplayedStyle());
        }
        
    }

    /**
     * @return the isAbstract
     */
    @objid ("359bc258-55b7-11e2-877f-002564c97630")
    public boolean isAbstract() {
        return this.isAbstract;
    }

    @objid ("359bc25d-55b7-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        out.writeProperty("abstract", isAbstract());
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNamespaceHeader.", GmNamespaceHeader.MINOR_VERSION);
        
    }

    @objid ("359bc264-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNamespaceHeader.");
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

    @objid ("359bc26b-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        final Boolean readAbs = (Boolean) in.readProperty("abstract");
        if (readAbs != null)
            this.isAbstract = readAbs;
        
    }

    @objid ("359bc271-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
