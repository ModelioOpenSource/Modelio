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

package org.modelio.bpmn.diagram.editor.elements.participant;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.bpmn.diagram.editor.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmBpmnParticipantPortContainer} when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("00e76b8b-737d-4e05-9beb-9e7f3376b49c")
public class ParticipantStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("6f704f89-ac76-4742-9bae-2c6868445030")
    public static final StyleKey REPMODE = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_REPMODE", MetaKey.REPMODE);

    @objid ("65af473d-3b5c-4996-a194-b5d5311210a7")
    public static final StyleKey FILLCOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("bf56671b-6426-412d-83be-133476cc86ca")
    public static final StyleKey FILLMODE = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_FILLMODE", MetaKey.FILLMODE);

    @objid ("e04c35ee-66b8-4664-9e3e-e6b55f699242")
    public static final StyleKey LINECOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("462f3823-3d39-47f6-b9ee-4981c0c9fe6e")
    public static final StyleKey LINEWIDTH = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("8fe0e126-f7bb-44a8-b000-3eb7c1550822")
    public static final StyleKey FONT = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_FONT", MetaKey.FONT);

    @objid ("b875e14b-3e14-4809-badc-b451f13816a4")
    public static final StyleKey TEXTCOLOR = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a559dcac-f834-44c4-af8c-34596ebd0412")
    public static final StyleKey SHOWSTEREOTYPES = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_SHOWSTEREOTYPES",
            MetaKey.SHOWSTEREOTYPES);

    @objid ("bf45e9ae-34f2-4c99-93dd-cd8ba64b4448")
    public static final StyleKey SHOWTAGS = BpmnAbstractStyleKeyProvider.createStyleKey("BPMNPARTICIPANT_SHOWTAGS", MetaKey.SHOWTAGS);

    /**
     * Singleton instance
     */
    @objid ("c8836c1e-3e00-46d0-9a5b-e56bb66f6e0a")
    public static final ParticipantStyleKeys INSTANCE = new ParticipantStyleKeys();

    /**
     * Private constructor, use {@link #INSTANCE}.
     */
    @objid ("137d4fcd-6bdd-40ec-9788-eec74a7eb20f")
    private ParticipantStyleKeys() {
        super();
    }

}
