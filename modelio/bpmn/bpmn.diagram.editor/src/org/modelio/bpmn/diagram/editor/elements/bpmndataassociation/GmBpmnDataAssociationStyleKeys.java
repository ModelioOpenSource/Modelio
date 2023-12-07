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
package org.modelio.bpmn.diagram.editor.elements.bpmndataassociation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.common.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmBpmnDataAssociation}.
 */
@objid ("60ab1ff8-55b6-11e2-877f-002564c97630")
public class GmBpmnDataAssociationStyleKeys extends BpmnAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("71474991-55c1-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("BPMNDATAASSOCIATION_ROUTINGMODE",
                        MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("71474994-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("BPMNDATAASSOCIATION_LINECOLOR",
                        MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("71474997-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("BPMNDATAASSOCIATION_LINEWIDTH",
                        MetaKey.LINEWIDTH);

    /**
     * Line radius
     */
    @objid ("7147499a-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("BPMNDATAASSOCIATION_LINERADIUS",
                        MetaKey.LINERADIUS);

    /**
     * Bridge
     */
    @objid ("7148d02b-55c1-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("BPMNDATAASSOCIATION_DRAWLINEBRIDGES",
                        MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("7148d02e-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("BPMNDATAASSOCIATION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("7148d031-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNDATAASSOCIATION_TEXTCOLOR",
                        MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("7148d034-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNDATAASSOCIATION_SHOWSTEREOTYPES",
                        MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("7148d037-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNDATAASSOCIATION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("7148d03a-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("BPMNDATAASSOCIATION_LINEPATTERN",
                        MetaKey.LINEPATTERN);

    /**
     * Display Show label.
     */
    @objid ("3ebdab27-69d2-477f-8b1b-97af1f80fa96")
    public static final StyleKey SHOWLABEL = createStyleKey("BPMNDATAASSOCIATION_SHOWLABEL", MetaKey.SHOWLABEL);

}
