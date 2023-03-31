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
package org.modelio.uml.statediagram.editor.elements.region;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.style.SymbolViewContentBuilder;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.FactoryStyle;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.Style;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.uml.behavior.stateMachineModel.Region;
import org.modelio.platform.model.ui.MetamodelLabels;
import org.modelio.vcore.smkernel.mapi.MExpert;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a {@link Region}.
 */
@objid ("f568845a-55b6-11e2-877f-002564c97630")
public class GmRegion extends GmFreeZone {
    @objid ("f568845e-55b6-11e2-877f-002564c97630")
    private Region element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("f5688462-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("f5688465-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("fdfe81d9-5a5b-11e2-9e33-00137282c51b")
    private static GmRegionStructuredStyleKeys STRUCTKEYS = new GmRegionStructuredStyleKeys();

    /**
     * Empty constructor, needed for serialization.
     */
    @objid ("f5688467-55b6-11e2-877f-002564c97630")
    public  GmRegion() {
        // constructor empty for the serialization
    }

    /**
     * Default constructor.
     * @param diagram the diagram in which this gm is unmasked.
     * @param theRegion the represented region, may be null.
     * @param ref a reference to the represented clause.
     */
    @objid ("f568846a-55b6-11e2-877f-002564c97630")
    public  GmRegion(IGmDiagram diagram, final Region theRegion, MRef ref) {
        super(diagram, ref);
        this.element = theRegion;
        
    }

    @objid ("f5688477-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        MMetamodel mm = getDiagram().getModelManager().getMetamodel();
        MExpert mExpert = mm.getMExpert();
        return mExpert.canCompose(mm.getMClass(Region.class), mm.getMClass(type), null);
    }

    @objid ("f568847f-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (this.element != null && this.element.equals(el.getCompositionOwner()));
    }

    @objid ("f5688487-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmRegion.STRUCTKEYS.getStyleKey(metakey);
    }

    @objid ("f56a0afd-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmRegion.STRUCTKEYS.getStyleKeys();
    }

    /**
     * A region is always visible.
     */
    @objid ("f56a0b06-55b6-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED;
    }

    @objid ("f56a0b0c-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmRegion.");
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

    @objid ("f56a0b12-55b6-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            StyleKey key = getStyleKey(MetaKey.REPMODE);
            if (key != null) {
                getParent().getDisplayedStyle().setProperty(key, RepresentationMode.STRUCTURED);
            }
        }
        
    }

    @objid ("f56a0b16-55b6-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        final MObject childEl = node.getRelatedElement();
        return (childEl == null
                || (!childEl.isValid() && canCreate(node.getRelatedMClass().getJavaInterface()))
                || canUnmask(childEl));
        
    }

    @objid ("f56a0b1e-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("f56a0b25-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("f56a0b2c-55b6-11e2-877f-002564c97630")
    @Override
    protected IStyle createStyle(final IGmDiagram aDiagram) {
        // Own style derives from the diagram style if this.diagram is not null
        return new Style((aDiagram != null) ? aDiagram.getPersistedStyle() : FactoryStyle.getInstance());
    }

    @objid ("f56a0b37-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmRegion.", GmRegion.MINOR_VERSION);
        
    }

    @objid ("f56b919d-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Region) resolveRef(getRepresentedRef());
        
    }

    @objid ("f56b91a2-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmRegion.MAJOR_VERSION;
    }

    @objid ("45dc4885-8282-4bdd-8304-076c16b08985")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        SymbolViewContentBuilder b = new SymbolViewContentBuilder(MetamodelLabels.getString(Region.MNAME));
        b.add(b.createLabelItem(MetamodelLabels.getString(Region.MNAME))
                .add(b.createStyleItem(GmRegionStructuredStyleKeys.FILLMODE))
                .add(b.createStyleItem(GmRegionStructuredStyleKeys.FILLCOLOR)
                        .filter(GmRegionStructuredStyleKeys.FILLMODE, v -> v != StyleKey.FillMode.TRANSPARENT))
        
                );
        return b.build(getPersistedStyle(), this);
    }

}
