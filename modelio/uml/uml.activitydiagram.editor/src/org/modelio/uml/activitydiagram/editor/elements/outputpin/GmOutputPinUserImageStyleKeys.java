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
package org.modelio.uml.activitydiagram.editor.elements.outputpin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmOutputPin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("7650288d-a7f2-4b33-a768-7a0999635709")
public class GmOutputPinUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("d5cd2b09-894c-421c-917d-82579adab624")
    static final StyleKey REPMODE = GmOutputPinStructuredStyleKeys.REPMODE;

    @objid ("c3eb251b-7e50-403f-b5d2-d76a6f67a1eb")
    static final StyleKey FONT = GmOutputPinStructuredStyleKeys.FONT;

    @objid ("2f910edd-8150-4f0a-ac7c-d00d4ccc5365")
    static final StyleKey TEXTCOLOR = GmOutputPinStructuredStyleKeys.TEXTCOLOR;

    @objid ("d18b436c-cf38-4ad7-80e7-f8a19d12130f")
    static final StyleKey SHOWSTEREOTYPES = GmOutputPinStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("4954cee0-ce6c-42c6-8f25-d982d5bdedab")
    static final StyleKey SHOWTAGS = GmOutputPinStructuredStyleKeys.SHOWTAGS;

}
