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

package org.modelio.diagram.editor.statik.elements.namespacelabel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.namespaceheader.NamespaceSymbolProvider;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link NameSpace} label.
 * <p>
 * Extends {@link GmModelElementLabel}.
 */
@objid ("7a12b9bf-55b6-11e2-877f-002564c97630")
public class GmNameSpaceLabel extends GmDefaultModelElementLabel {
    @objid ("35a05622-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("35a0561c-55b7-11e2-877f-002564c97630")
    private NameSpace element = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35a0561f-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    /**
     * Empty constructor needed for (de-)serialization.
     */
    @objid ("35a05624-55b7-11e2-877f-002564c97630")
    public GmNameSpaceLabel() {
        // Empty constructor needed for (de-)serialization.
    }

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param el the represented element, may be <i>null</i>.
     * @param ref a reference to the represented element.
     */
    @objid ("35a05627-55b7-11e2-877f-002564c97630")
    public GmNameSpaceLabel(IGmDiagram diagram, NameSpace el, MRef ref) {
        super(diagram, ref);
        this.element = el;
    }

    @objid ("35a36388-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("35a1dcf1-55b7-11e2-877f-002564c97630")
    @Override
    public NameSpace getRelatedElement() {
        return this.element;
    }

    @objid ("35a3636c-55b7-11e2-877f-002564c97630")
    @Override
    public NameSpace getRepresentedElement() {
        return this.element;
    }

    @objid ("35a1dccb-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParentNode() instanceof GmGroup) {
            if (metakey == MetaKey.FONT) {
                return super.getStyleKey(MetaKey.InnerGroup.INNERFONT);
            } else if (metakey == MetaKey.SHOWSTEREOTYPES) {
                return super.getStyleKey(MetaKey.InnerGroup.INNERSHOWSTEREOTYPES);
            } else if (metakey == MetaKey.SHOWTAGS) {
                return super.getStyleKey(MetaKey.InnerGroup.INNERSHOWTAGS);
            } else if (metakey == MetaKey.TEXTCOLOR) {
                return super.getStyleKey(MetaKey.InnerGroup.INNERTEXTCOLOR);
            } else if (metakey == MetaKey.SHOWVISIBILITY) {
                return super.getStyleKey(MetaKey.InnerGroup.INNERSHOWVISIBILITY);
            } else if (metakey == MetaKey.SHOWNAME) {
                return super.getStyleKey(MetaKey.InnerGroup.INNERSHOWNAME);
            }
            return null;
        } else {
            return super.getStyleKey(metakey);
        }
    }

    /**
     * Tells whether the represented element is abstract.
     * @return <i>true</i> if the namespace is abstract else <i>false</i>.
     */
    @objid ("d66920d8-7265-4669-90d0-3fd8b2e8edf2")
    public boolean isAbstract() {
        return getRelatedElement().isIsAbstract();
    }

    @objid ("35a36373-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNameSpaceLabel.");
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

    @objid ("35a3637d-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNameSpaceLabel.", GmNameSpaceLabel.MINOR_VERSION);
    }

    @objid ("35a1dcec-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return computeSignature(getRelatedElement());
    }

    @objid ("35a1dce6-55b7-11e2-877f-002564c97630")
    private String computeSignature(NameSpace att) {
        StyleKey key = getStyleKey(MetaKey.SHOWNAME);
        if (key != null) {
            final ShowNameMode nameMode = getDisplayedStyle().getProperty(key);
        
            switch (nameMode) {
                case FULLQUALIFIED:
                    return NamespaceSymbolProvider.computeFullQualifiedLabel(att, showVisibility());
                case NONE:
                    return "";
                case QUALIFIED:
                    return NamespaceSymbolProvider.computeQualifiedLabel(att, showVisibility());
                case SIMPLE:
                default:
                    return NamespaceSymbolProvider.computeSimpleLabel(att, showVisibility());
        
            }
        } else {
            return att.getName();
        }
    }

    @objid ("35a36383-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (NameSpace) resolveRef(getRepresentedRef());
    }

    @objid ("35a36379-55b7-11e2-877f-002564c97630")
    private boolean showVisibility() {
        final StyleKey showVisibilityKey = getStyleKey(MetaKey.SHOWVISIBILITY);
        return (showVisibilityKey != null && (Boolean) getDisplayedStyle().getProperty(showVisibilityKey));
    }

}
