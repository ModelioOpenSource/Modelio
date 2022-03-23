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
package org.modelio.diagram.elements.umlcommon.diagramholder;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.umlcommon.diagramview.GmDiagramView;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the link between the note and the annoted element.
 * <p>
 * The annoted element is the source and the destination is a {@link GmDiagramView}.
 * 
 * @author cmarin
 */
@objid ("813ed889-1dec-11e2-8cad-001ec947c8cc")
public class GmDiagramHolderLink extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("813ed88c-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("813ed88f-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("813ed88b-1dec-11e2-8cad-001ec947c8cc")
    private Dependency relatedDep;

    /**
     * Constructor that must be used for deserialization only.
     */
    @objid ("813ed891-1dec-11e2-8cad-001ec947c8cc")
    public  GmDiagramHolderLink() {
        // Nothing to do.
    }

    /**
     * Creates a new GmNoteLink
     * @param diagram The diagram containing the link.
     * @param relatedRef a reference to the related dependency.
     * @param el the related dependency
     */
    @objid ("813ed894-1dec-11e2-8cad-001ec947c8cc")
    public  GmDiagramHolderLink(IGmDiagram diagram, MRef relatedRef, final Dependency el) {
        super(diagram, relatedRef);
        this.relatedDep = el;
        
    }

    @objid ("813ed89b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Dependency getRelatedElement() {
        return this.relatedDep;
    }

    @objid ("813ed8a0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmDiagramView.KEYS.getStyleKey(metakey);
    }

    @objid ("813ed8a6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmDiagramView.KEYS.getStyleKeys();
    }

    @objid ("813ed8ad-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(IGmDiagram aDiagram) {
        return new ProxyStyle(aDiagram.getPersistedStyle());
    }

    @objid ("813ed8b3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getFromElement() {
        return getRelatedElement().getImpacted();
    }

    @objid ("813ed8b8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getToElement() {
        return getRelatedElement().getDependsOn();
    }

    /**
     * Updates the proxy style to point to the given node style.
     * @param ref the reference node, may be null.
     */
    @objid ("81413ade-1dec-11e2-8cad-001ec947c8cc")
    private void refreshStyle(final GmAbstractObject ref) {
        // Modify the style
        if (ref != null) {
            getPersistedStyle().setCascadedStyle(ref.getPersistedStyle());
        } else {
            getPersistedStyle().setCascadedStyle(getDiagram().getPersistedStyle());
        }
        
    }

    @objid ("81413ae3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setTo(final IGmLinkable to) {
        super.setTo(to);
        if (to instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) to);
        }
        
    }

    @objid ("81413ae8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void readLink(final IDiagramReader in) {
        super.readLink(in);
        
        this.relatedDep = (Dependency) resolveRef(getRepresentedRef());
        
        if (getTo() == this) {
            for (GmModel gm : getDiagram().getAllGMRelatedTo(new MRef(getToElement()))) {
                if (gm instanceof GmDiagramView) {
                    setTo((IGmLinkable) gm);
                    break;
                }
            }
        } else if (getTo() instanceof GmAbstractObject) {
            refreshStyle((GmAbstractObject) getTo());
        }
        
    }

    @objid ("81413aed-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public MObject getRepresentedElement() {
        return this.relatedDep;
    }

    @objid ("81413af2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDiagramHolderLink.", GmDiagramHolderLink.MINOR_VERSION);
        
    }

    @objid ("81413af6-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmDiagramHolderLink.MAJOR_VERSION;
    }

    @objid ("3931806f-ebaf-436a-87c9-3d4f3c25c14f")
    @Override
    protected void read_GmLinkV0_roles() {
        // nothing
    }

    @objid ("8270a98e-c37d-49de-b6ca-881fd9075b9f")
    @Override
    protected void refreshFromObModel() {
        super.refreshFromObModel();
        
        if (this.relatedDep == null || this.relatedDep.getDependsOn() == null || getTo() == this) {
            delete();
        }
        
    }

}
