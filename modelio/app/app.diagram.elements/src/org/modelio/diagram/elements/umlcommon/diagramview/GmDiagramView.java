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
package org.modelio.diagram.elements.umlcommon.diagramview;

import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.umlcommon.diagramheader.GmDiagramHeader;
import org.modelio.diagram.elements.umlcommon.diagramholder.GmDiagramHolderLink;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.platform.ui.UIColor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link AbstractDiagram} view in the diagram.
 */
@objid ("81486234-1dec-11e2-8cad-001ec947c8cc")
public class GmDiagramView extends GmCompositeNode {
    /**
     * Current version of this Gm.
     */
    @objid ("81486238-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 2;

    @objid ("8148623b-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("cc126891-34d0-4cdb-b541-0e619003a8d8")
    public static final String ROLE_HEADER = "header";

    @objid ("4b08fea7-ccd6-4028-9e71-393b31377b23")
    public static final String ROLE_BODY = "body";

    @objid ("81486237-1dec-11e2-8cad-001ec947c8cc")
    private AbstractDiagram viewedDiagram;

    @objid ("473eb64a-6e05-405f-bed9-a64a34f4fd84")
    public static final DiagramViewStyleKeys KEYS = new DiagramViewStyleKeys();

    /**
     * For deserialization only.
     */
    @objid ("8148623d-1dec-11e2-8cad-001ec947c8cc")
    public  GmDiagramView() {
        super();
    }

    /**
     * Creates a diagram model.
     * @param diagram The diagram owning this diagram view
     * @param viewedDiagram The represented diagram.
     * @param ref The represented diagram reference.
     */
    @objid ("81486240-1dec-11e2-8cad-001ec947c8cc")
    public  GmDiagramView(final IGmDiagram diagram, AbstractDiagram viewedDiagram, MRef ref) {
        super(diagram, ref);
        this.viewedDiagram = viewedDiagram;
        
        final GmDiagramHeader header = new GmDiagramHeader(diagram, ref);
        header.setRoleInComposition(GmDiagramView.ROLE_HEADER);
        addChild(header);
        
        final GmDiagramViewBody body = new GmDiagramViewBody(diagram, viewedDiagram, ref);
        body.setRoleInComposition(GmDiagramView.ROLE_BODY);
        addChild(body);
        
    }

    @objid ("8148624a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("81486252-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("814ac445-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return null;
    }

    @objid ("814ac452-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public AbstractDiagram getRepresentedElement() {
        return this.viewedDiagram;
    }

    @objid ("814ac461-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmAbstractDiagramView.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        case 2: {
            read_2(in);
            break;
        }
        default: {
            assert false : "version number not covered!";
            // reading as last handled version: 2
            read_2(in);
            break;
        }
        }
        
    }

    @objid ("814ac465-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        // Force a white background for the embedded diagram
        for (GmNodeModel body : getChildren(GmDiagramView.ROLE_BODY)) {
            if (body.getDisplayedStyle().getLocalKeys().isEmpty()) {
                body.getDisplayedStyle().setProperty(getParent().getStyleKey(MetaKey.FILLCOLOR), UIColor.WHITE);
            }
        }
        
    }

    @objid ("814ac46b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmAbstractDiagramView.", GmDiagramView.MINOR_VERSION);
        
    }

    @objid ("814ac46f-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        read_1(in);
        
        // 3.8 migration: set roles and create a GmEmbeddedDiagram
        if (getChildren().size() == 2) {
            getChildren().get(0).setRoleInComposition(GmDiagramView.ROLE_HEADER);
        }
        
    }

    @objid ("814ac472-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmDiagramView.MAJOR_VERSION;
    }

    @objid ("cd05591f-6023-4d24-80c9-812638d9d952")
    private void read_1(IDiagramReader in) {
        super.read(in);
        
        this.viewedDiagram = (AbstractDiagram) resolveRef(getRepresentedRef());
        
        // 5.0.01 migration:
        for (GmNodeModel body : getChildren(GmDiagramView.ROLE_BODY)) {
            body.delete();
        }
        
        final GmDiagramViewBody body = new GmDiagramViewBody(getDiagram(), this.viewedDiagram, getRepresentedRef());
        body.setRoleInComposition(GmDiagramView.ROLE_BODY);
        addChild(body);
        
    }

    @objid ("74cf4b1f-e3c4-4c26-8c87-e8f4c393eaf4")
    private void read_2(IDiagramReader in) {
        super.read(in);
        
        this.viewedDiagram = (AbstractDiagram) resolveRef(getRepresentedRef());
        
    }

    @objid ("92d204bd-7d36-4539-a6b3-91fd05169512")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("7798902d-9529-42e3-bcd0-cb022640ad44")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmDiagramView.KEYS.getStyleKeys();
    }

    @objid ("813c762f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmDiagramView.KEYS.getStyleKey(metakey);
    }

    @objid ("f5a14148-d722-47dc-96be-6f9d6b7eaf13")
    @Override
    public void removeEndingLink(final IGmLink gmLink) {
        boolean selfDelete = false;
        if (gmLink instanceof GmDiagramHolderLink) {
            // the removed link represents the same element (the note) as this gm: delete self as well.
            Dependency linkRelatedElement = ((GmDiagramHolderLink) gmLink).getRelatedElement();
            selfDelete = linkRelatedElement == null || !linkRelatedElement.isValid() || Objects.equals(linkRelatedElement.getDependsOn(), this.viewedDiagram);
        }
        
        super.removeEndingLink(gmLink);
        
        if (selfDelete) {
            // the removed link represents the same element (the note) as this gm: delete self as well.
            delete();
        }
        
    }

}
