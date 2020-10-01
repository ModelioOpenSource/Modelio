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

package org.modelio.bpmn.diagram.editor.elements.bpmnsequenceflowdataassociation;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmBpmnSequenceFlowDataAssociation}.
 */
@objid ("c6ba129e-c02c-490f-88a1-20c87fad2f12")
public class GmBpmnSequenceFlowDataAssociationStyleKeys extends BpmnAbstractStyleKeyProvider {
    /**
     * Routing mode: bendpoint, orthogonal, ...
     */
    @objid ("e57161a5-eb37-4b68-ae88-fc5de5460713")
    public static final StyleKey CONNECTIONROUTER = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_ROUTINGMODE",
            MetaKey.CONNECTIONROUTER);

    /**
     * Line color
     */
    @objid ("43f964e6-0d67-4420-b370-8cb624e10884")
    public static final StyleKey LINECOLOR = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_LINECOLOR",
            MetaKey.LINECOLOR);

    /**
     * Line width
     */
    @objid ("2f3d0da6-2838-4786-aa40-20119a49fe4e")
    public static final StyleKey LINEWIDTH = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_LINEWIDTH",
            MetaKey.LINEWIDTH);

    /**
     * Line radius
     */
    @objid ("89022c7d-aec0-4fa5-aed8-90b43d84db39")
    public static final StyleKey LINERADIUS = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_LINERADIUS",
            MetaKey.LINERADIUS);

    /**
     * Bridge
     */
    @objid ("882ff023-9126-4bb7-9f28-8f46447a27a6")
    public static final StyleKey DRAWLINEBRIDGES = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_DRAWLINEBRIDGES",
            MetaKey.DRAWLINEBRIDGES);

    /**
     * Text font.
     */
    @objid ("7430673a-8888-4902-b3c0-000ccd16fe45")
    public static final StyleKey FONT = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_FONT", MetaKey.FONT);

    /**
     * Text color.
     */
    @objid ("6dbffd0a-046b-49be-a1ea-5c2fafb8eeed")
    public static final StyleKey TEXTCOLOR = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_TEXTCOLOR",
            MetaKey.TEXTCOLOR);

    /**
     * Stereotype display mode.
     */
    @objid ("2d604a39-6210-4bb3-b11a-4eb3b7e5b2d5")
    public static final StyleKey SHOWSTEREOTYPES = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    /**
     * Display tagged values
     */
    @objid ("041aa4c8-5796-4030-93b4-16a4b89692b5")
    public static final StyleKey SHOWTAGS = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Line pattern
     */
    @objid ("e2a98e75-bffb-48a7-9b14-a05020b38005")
    public static final StyleKey LINEPATTERN = createStyleKey("BPMNSEQUENCEFLOWDATAASSOCIATION_LINEPATTERN",
            MetaKey.LINEPATTERN);

}
