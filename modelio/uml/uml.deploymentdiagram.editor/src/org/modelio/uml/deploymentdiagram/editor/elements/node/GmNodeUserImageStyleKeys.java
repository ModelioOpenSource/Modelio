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
package org.modelio.uml.deploymentdiagram.editor.elements.node;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.deploymentdiagram.editor.style.DeploymentAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmNode when its representation mode is RepresentationMode.IMAGE
 */
@objid ("43bb07f5-bdfc-444d-9310-e1b228447f6e")
public class GmNodeUserImageStyleKeys extends DeploymentAbstractStyleKeyProvider {
    /**
     * Representation mode.
     */
    @objid ("b455adee-c069-415d-a04b-428adfc612ff")
    public static final StyleKey REPMODE = createStyleKey("NODE_REPMODE", MetaKey.REPMODE);

    /**
     * Text font.
     */
    @objid ("87586291-5cc2-4d4f-a8a5-53e4147a358d")
    public static final StyleKey FONT = createStyleKey("NODE_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("00cac319-d699-49d7-b2cb-be94a64b555b")
    public static final StyleKey TEXTCOLOR = createStyleKey("NODE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Display name.
     */
    @objid ("53b08bbb-135e-4e1e-b146-e57dc857be9a")
    public static final StyleKey SHOWNAME = createStyleKey("NODE_SHOWNAME", MetaKey.SHOWNAME);

    /**
     * Display stereotypes.
     */
    @objid ("fc594817-8c4f-4e5f-b7c3-d9adf52d065a")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("NODE_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values.
     */
    @objid ("8e28da4b-55bf-4dfc-acf1-15b34671a619")
    public static final StyleKey SHOWTAGS = createStyleKey("NODE_SHOWTAGS", MetaKey.SHOWTAGS);

}
