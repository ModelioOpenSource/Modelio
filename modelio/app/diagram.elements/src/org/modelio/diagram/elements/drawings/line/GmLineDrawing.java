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

package org.modelio.diagram.elements.drawings.line;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.drawings.core.link.GmAbstractLinkDrawing;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Rectangle not representing any model element.
 */
@objid ("0aec4d4a-5f9a-4a0a-b8dd-61949de35f59")
public class GmLineDrawing extends GmAbstractLinkDrawing {
    @objid ("309b9c37-b27e-4e87-82fa-4588892d28a6")
     static final GmLineStyleKeys KEYS = new GmLineStyleKeys();

    /**
     * Deserialization only constructor.
     */
    @objid ("20280cc7-5d55-4161-9bf0-f0b14f79c23c")
    public GmLineDrawing() {
        super();
    }

    /**
     * Default constructor.
     * @param diagram the owner diagram.
     * @param identifier drawing identifier, must be unique in the diagram.
     */
    @objid ("b80e1074-cd61-499c-8683-72055dc619e4")
    public GmLineDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
    }

    @objid ("994a70b8-43b9-43c6-bafd-bef468ab2da7")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("9965f136-e3bf-4588-a89a-05f20db3e961")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("999e1931-99ba-4d35-a070-870f87e69427")
    @Override
    protected String getObsoleteHyperLinkStyleKey() {
        return "DRAWLINE_HYPERLINK";
    }

}
