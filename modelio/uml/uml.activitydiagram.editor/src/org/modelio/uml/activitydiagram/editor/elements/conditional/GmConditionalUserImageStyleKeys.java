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
package org.modelio.uml.activitydiagram.editor.elements.conditional;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmConditional when its representation mode is RepresentationMode.IMAGE
 */
@objid ("ebc7784f-20c3-4851-88ed-dcece638fd99")
public class GmConditionalUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("758a6715-f8ad-4e73-987b-305207dab523")
    static final StyleKey REPMODE = GmConditionalStructuredStyleKeys.REPMODE;

    @objid ("6711be81-644a-406d-804e-6d788ab87daa")
    static final StyleKey FONT = GmConditionalStructuredStyleKeys.FONT;

    @objid ("52a25fab-f8f9-47a2-ab7e-3f5ebc58fb64")
    static final StyleKey TEXTCOLOR = GmConditionalStructuredStyleKeys.TEXTCOLOR;

    @objid ("cc74fdfc-9b09-450d-81ee-17f553bbb476")
    static final StyleKey SHOWSTEREOTYPES = GmConditionalStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("2dbf7403-5a6e-4d33-9256-d98419fee90f")
    static final StyleKey SHOWTAGS = GmConditionalStructuredStyleKeys.SHOWTAGS;

    @objid ("e76ec369-6313-4ed5-bee8-6606c5ad3894")
    static final StyleKey AUTOSHOWPINS = GmConditionalStructuredStyleKeys.AUTOSHOWPINS;

}
