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
package org.modelio.diagram.elements.drawings.ellipse;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.node.GmNodeDrawing;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Rectangle not representing any model element.
 */
@objid ("029fbd1c-e60d-4340-84b8-886625c10970")
public class GmEllipseDrawing extends GmNodeDrawing {
    @objid ("e8064284-a378-4f59-9865-f0f555b4d892")
    static final GmEllipseStyleKeys KEYS = new GmEllipseStyleKeys();

    /**
     * Deserialization only constructor.
     */
    @objid ("daa32cba-d7ee-47a2-9363-cae0b1b91fd7")
    public  GmEllipseDrawing() {
        super();
    }

    /**
     * Default constructor.
     * @param diagram the owner diagram.
     * @param identifier drawing identifier, must be unique in the diagram.
     */
    @objid ("dc897a04-918f-4306-8409-0ac5cddf5bd1")
    public  GmEllipseDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
    }

    @objid ("21160af7-52f9-4d04-b606-8d86b493a481")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("8488de09-3fcd-4f4c-9393-ea0fd80da4e1")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("0e487f1e-7712-40e9-b9df-f3b32aaa4efd")
    @Override
    protected String getObsoleteHyperLinkStyleKey() {
        return "ELLIPSE_HYPERLINK";
    }

}
