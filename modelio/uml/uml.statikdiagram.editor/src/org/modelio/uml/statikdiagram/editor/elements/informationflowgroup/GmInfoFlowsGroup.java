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
package org.modelio.uml.statikdiagram.editor.elements.informationflowgroup;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Group of information flows.
 * 
 * @author cmarin
 */
@objid ("8164fe31-1dec-11e2-8cad-001ec947c8cc")
public final class GmInfoFlowsGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("8164fe34-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("8164fe37-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Default GmLink extension role for this kind of node.
     */
    @objid ("52eb7156-a8e7-4bb7-8f54-09427250e995")
    public static final String DEFAULT_ROLE = "infoflow_group";

    @objid ("8164fe33-1dec-11e2-8cad-001ec947c8cc")
    private MObject relatedEl;

    /**
     * Creates a group.
     * @param diagram The diagram.
     * @param relatedRef The related element reference, may not be null.
     */
    @objid ("8164fe39-1dec-11e2-8cad-001ec947c8cc")
    public  GmInfoFlowsGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        this.relatedEl = resolveRef(relatedRef);
        
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("8164fe3e-1dec-11e2-8cad-001ec947c8cc")
    public  GmInfoFlowsGroup() {
        
    }

    @objid ("8164fe41-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmInformationFlowLabel.class.isAssignableFrom(nodeClass);
    }

    @objid ("8164fe49-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final boolean canCreate(Class<? extends MObject> type) {
        return InformationFlow.class.isAssignableFrom(type);
    }

    @objid ("8164fe51-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRelatedElement() {
        return this.relatedEl;
    }

    @objid ("8164fe56-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final boolean isVisible() {
        return getParent() == null || getDisplayedStyle().getBoolean(getStyleKeyStrict(MetaKey.SHOWINFORMATIONFLOWS));
    }

    @objid ("8164fe5b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void obElementsUpdated() {
        refreshFromObModel();
    }

    @objid ("8164fe5e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInfoFlowsGroup.");
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

    @objid ("8164fe63-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void refreshFromObModel() {
        super.refreshFromObModel();
        
        final MObject related = getRelatedElement();
        if (related != null) {
            for (InformationFlow part : getRealizedInformationFlows()) {
                if (getChild(new MRef(part)) == null) {
                    getDiagram().unmask(this, part, null);
                }
            }
        
        }
        
    }

    @objid ("8164fe66-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void styleChanged(StyleKey property, Object newValue) {
        if (property == getStyleKey(MetaKey.SHOWINFORMATIONFLOWS)) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
        
    }

    @objid ("8167607b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
        
    }

    @objid ("8167607f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void doSetVisible(boolean visible) {
        getDisplayedStyle().setProperty(getStyleKey(MetaKey.SHOWINFORMATIONFLOWS), visible);
    }

    @objid ("81676083-1dec-11e2-8cad-001ec947c8cc")
    protected Collection<InformationFlow> getRealizedInformationFlows() {
        return GetInformationFlowExpert.getRealizedFlows(getRelatedElement());
    }

    @objid ("81676089-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid information flow
        if (!(el instanceof InformationFlow) || el.isDeleted() || el.isShell()) {
            return false;
        }
        return (getRealizedInformationFlows().contains(el));
    }

    @objid ("8167608f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final void updateHiddenFeatures() {
        setHiddenFeature(false);
    }

    @objid ("81676092-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInfoFlowsGroup.", MINOR_VERSION);
        
    }

    @objid ("81676096-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(final IDiagramReader in) {
        super.read(in);
                
        // TODO : Beurk !!!!!!!
        this.relatedEl = resolveRef((MRef) in.readProperty("relatedRef"));
        
    }

    @objid ("8167609a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
