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
package org.modelio.diagram.elements.drawings.core;

import java.util.Map;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.Style;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model representing a node or a link that does not represent a model element.
 * 
 * @author cmarin
 */
@objid ("807430a0-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmDrawing extends GmAbstractObject implements IGmDrawing {
    @objid ("807430a5-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Drawing identifier in the diagram.
     */
    @objid ("3e3ff493-b872-4b22-8c90-abb11961f191")
    private String identifier;

    /**
     * Current version of this Gm. Defaults to 0.
     * <li> 0 : initial version. Hyper links were stored in style properties.
     * <li> 1 : hyper link are now stored in {@link #hyperlink}
     */
    @objid ("807430a2-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 1;

    @objid ("13e87884-119d-4218-8cdf-fb96b7d148c8")
    private MRef hyperlink;

    /**
     * Default constructor.
     * @param diagram the owner diagram.
     * @param identifier the drawing identifier.
     */
    @objid ("110d2aa1-cc0b-46f6-8214-0704c4106da7")
    public  GmDrawing(IGmDiagram diagram, String identifier) {
        super(diagram);
        this.identifier = identifier;
        init();
        
    }

    /**
     * Deserialization only constructor.
     */
    @objid ("359942d3-e6ed-4612-ab94-06b04ca2c05e")
    public  GmDrawing() {
        super();
    }

    @objid ("03a05c84-9278-4da6-a745-253794a78eee")
    @Override
    public void delete() {
        if (getDiagram() != null) {
            getDiagram().removeGraphicModel(this);
        }
        
        super.delete();
        
    }

    @objid ("c12dceea-5ecb-4074-9855-47c13e2fdd12")
    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @objid ("807430b9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("807430ae-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDrawing.");
        switch (readVersion) {
            case 0: 
                read_0(in);
                read_0_hyperlink();
                break;
            case 1:
                read_0(in);
                break;
            
            default: 
                assert (false) : readVersion+" version number not covered!";
                // reading as last handled version: 0
                read_0(in);
                break;
            
        }
        
    }

    @objid ("807430b2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmDrawing.", GmDrawing.MINOR_VERSION);
        
        out.writeProperty("id", this.identifier);
        
        if (this.hyperlink != null) {
            out.writeProperty("hyperlink", this.hyperlink);
        }
        
    }

    /**
     * Instantiate the graphic model style.
     * <p>
     * Called by the standard {@link GmAbstractObject#GmAbstractObject(GmAbstractDiagram)} constructor.
     * <p>
     * Default implementation makes the style derive from the diagram style if not null, or from the factory style in
     * the other case.
     * <p>
     * Can be redefined to create another style or to return <tt>null<tt/> if
     * {@link #getStyle()} is redefined to return another style.
     * @param aDiagram the diagram where the object will be
     * @return the created style or <tt>null</tt> if the creation is postponed
     */
    @objid ("807430a7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(IGmDiagram aDiagram) {
        return new Style(aDiagram.getPersistedStyle());
    }

    /**
     * <p>
     * Initialize the object.
     * </p>
     * <p>
     * Must be called before usage by and only by:
     * <ul>
     * <li>The {@link #GmDrawing(GmAbstractDiagram, String)} constructor (but NOT by the parameter less constructor).
     * <li>and the {@link #read(IDiagramReader)} method
     * </ul>
     * </p>
     * <p>
     * The same method may exist in subclasses. In this case:
     * <ul>
     * <li>the child <em>init()</em> method must be private too,
     * <li>it must <strong>never</strong> call <em>super.init()</em>
     * <li>it must be called by the above 2 methods. they must be created if absent.
     * </ul>
     * </p>
     */
    @objid ("41eff6dc-4cee-4034-a795-9a0a55fb53dc")
    private void init() {
        // If the GmAbstractDiagram is reachable.
        if (getDiagram() != null) {
            // Register element in the diagram
            getDiagram().addGraphicModel(this);
        }
        
    }

    @objid ("807430b6-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.identifier = (String) in.readProperty("id");
        this.hyperlink = (MRef) in.readProperty("hyperlink");
        
        if (this.identifier == null) {
            this.identifier = UUID.randomUUID().toString();
        }
        
        
        init();
        
    }

    @objid ("fd7e5c7e-248f-41e5-99e8-3edf5138b27d")
    @Override
    public MRef getHyperLink() {
        return this.hyperlink;
    }

    @objid ("d94b9c1e-41b9-4568-87a6-568d65fcd84a")
    @Override
    public void setHyperLink(MRef ref) {
        this.hyperlink = ref;
    }

    @objid ("ebaa209d-8a22-42b6-b39d-12839d9c158a")
    private void read_0_hyperlink() {
        Style s = (Style) getPersistedStyle();
        Map<String, Object> obsoleteProps = s.getObsoleteProperties();
        if(! obsoleteProps.isEmpty()) {
            this.hyperlink = (MRef) obsoleteProps.get(getObsoleteHyperLinkStyleKey());
        }
        
    }

    /**
     * To be redefined by sub classes that used hyper link stored in style keys.
     * @return the style key name where hyper link was stored.
     * @since 3.7
     */
    @objid ("e91482c7-ca76-49e3-b12b-4c652413b963")
    protected String getObsoleteHyperLinkStyleKey() {
        return null;
    }

}
