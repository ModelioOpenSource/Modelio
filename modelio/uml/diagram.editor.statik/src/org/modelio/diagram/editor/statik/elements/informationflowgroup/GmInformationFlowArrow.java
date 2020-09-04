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

package org.modelio.diagram.editor.statik.elements.informationflowgroup;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNoStyleSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the information flow arrow.
 * <p>
 * The arrow is visible if information flows are activated and there are information flows to display.
 * 
 * @author cmarin
 */
@objid ("8167609f-1dec-11e2-8cad-001ec947c8cc")
public final class GmInformationFlowArrow extends GmNoStyleSimpleNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("816760a2-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("816760a5-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Default GmLink extension role for this kind of node.
     */
    @objid ("fa007c36-50f8-4112-90be-c79f2db1860b")
    public static final String DEFAULT_ROLE = "infoflow_arrow";

    @objid ("816760a1-1dec-11e2-8cad-001ec947c8cc")
    private MObject relatedEl;

    /**
     * Constructor for deserialization only.
     */
    @objid ("816760a7-1dec-11e2-8cad-001ec947c8cc")
    public GmInformationFlowArrow() {
    }

    /**
     * Creates a group.
     * 
     * @param diagram The diagram.
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("816760aa-1dec-11e2-8cad-001ec947c8cc")
    public GmInformationFlowArrow(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        this.relatedEl = resolveRef(relatedRef);
    }

    @objid ("8169c2d8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRelatedElement() {
        return this.relatedEl;
    }

    @objid ("8169c2dd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    /**
     * The arrow is visible if information flows are activated and there are information flows to display.
     */
    @objid ("8169c2e2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final boolean isVisible() {
        final StyleKey styleKey = getStyleKey(MetaKey.SHOWINFORMATIONFLOWS);
        if (styleKey != null) {
            boolean property = getDisplayedStyle().getProperty(styleKey);
            if (property) {
                final MObject related = getRelatedElement();
                if (related != null && related.isValid()) {
                    return !getRealizedInformationFlows().isEmpty();
                }
            }
        }
        return false;
    }

    @objid ("8169c2e8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementAdded(final MObject movedEl) {
        fireVisibilityChanged();
    }

    @objid ("8169c2ed-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementsUpdated() {
        fireVisibilityChanged();
    }

    @objid ("8169c2f0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInformationFlowArrow.");
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

    @objid ("8169c2f5-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void refreshFromObModel() {
        fireVisibilityChanged();
    }

    @objid ("8169c2f8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void styleChanged(IStyle style) {
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("8169c2fc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void styleChanged(StyleKey property, Object newValue) {
        if (property == getStyleKey(MetaKey.SHOWINFORMATIONFLOWS)) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("8169c301-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void doSetVisible(boolean visible) {
        getDisplayedStyle().setProperty(getStyleKey(MetaKey.SHOWINFORMATIONFLOWS), visible);
    }

    @objid ("8169c305-1dec-11e2-8cad-001ec947c8cc")
    protected Collection<InformationFlow> getRealizedInformationFlows() {
        return GetInformationFlowExpert.getRealizedFlows(getRelatedElement());
    }

    @objid ("8169c30b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInformationFlowArrow.", GmInformationFlowArrow.MINOR_VERSION);
    }

    @objid ("8169c30f-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        // TODO : Beurk !!!!!!!
        this.relatedEl = resolveRef((MRef) in.readProperty("relatedRef"));
    }

    @objid ("816c252f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
