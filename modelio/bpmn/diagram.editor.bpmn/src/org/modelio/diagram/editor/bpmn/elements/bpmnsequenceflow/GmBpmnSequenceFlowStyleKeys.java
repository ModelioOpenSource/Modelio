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

package org.modelio.diagram.editor.bpmn.elements.bpmnsequenceflow;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmBpmnSequenceFlow}.
 */
@objid ("61a0cac8-55b6-11e2-877f-002564c97630")
public class GmBpmnSequenceFlowStyleKeys extends BpmnAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("72230391-55c1-11e2-9337-002564c97630")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("BPMNSEQUENCEFLOW_ROUTINGMODE",
            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("72230394-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = createStyleKey("BPMNSEQUENCEFLOW_LINECOLOR", MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("72230397-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = createStyleKey("BPMNSEQUENCEFLOW_LINEWIDTH", MetaKey.LINEWIDTH);

    /**
     * Line radius
     */
    @objid ("72248a29-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINERADIUS = createStyleKey("BPMNSEQUENCEFLOW_LINERADIUS",
            MetaKey.LINERADIUS);

    /**
     * Bridge
     */
    @objid ("72248a2c-55c1-11e2-9337-002564c97630")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("BPMNSEQUENCEFLOW_DRAWLINEBRIDGES",
            MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("72248a2f-55c1-11e2-9337-002564c97630")
    public static final StyleKey FONT = createStyleKey("BPMNSEQUENCEFLOW_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("72248a32-55c1-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNSEQUENCEFLOW_TEXTCOLOR", MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("72248a35-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNSEQUENCEFLOW_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("72248a38-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNSEQUENCEFLOW_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Show the guard label. public static final StyleKey GUARDVISIBLE = createStyleKey("BPMNSEQUENCEFLOW_GUARDVISIBLE", MetaKey.SHOWNAME); Show the weight label. public static final StyleKey WEIGHTVISIBLE = createStyleKey("BPMNSEQUENCEFLOW_WEIGHTVISIBLE",
     * Boolean.class); Show the main label that displays the name, the tags and the stereotypes.
     */
    @objid ("72248a3b-55c1-11e2-9337-002564c97630")
    public static final StyleKey GUARDVISIBLE = createStyleKey("BPMNSEQUENCEFLOW_GUARDVISIBLE", Boolean.class);

    /**
     * Line pattern
     */
    @objid ("72248a3e-55c1-11e2-9337-002564c97630")
    public static final StyleKey LINEPATTERN = createStyleKey("BPMNSEQUENCEFLOW_LINEPATTERN",
            MetaKey.LINEPATTERN);

}
