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

package org.modelio.diagram.editor.state.elements.deephistory;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmDeepHistory when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("02bffcbf-38c5-4de1-a22d-c14df44f211d")
public class GmDeepHistoryUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("10f91cff-6751-47a8-a444-407088e6272c")
     static final StyleKey REPMODE = GmDeepHistoryStructuredStyleKeys.REPMODE;

    @objid ("62d31d8a-7d3a-4415-a9ab-1184fe7e6e14")
     static final StyleKey FONT = GmDeepHistoryStructuredStyleKeys.FONT;

    @objid ("f4df78aa-f9cb-4032-a7ec-10e30b88ef38")
     static final StyleKey TEXTCOLOR = GmDeepHistoryStructuredStyleKeys.TEXTCOLOR;

    @objid ("89a23ee7-8171-4aa7-87d3-c31e58137bc8")
     static final StyleKey SHOWSTEREOTYPES = GmDeepHistoryStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("7401fc06-40bc-4c54-b80e-9c1cc9fbebd9")
     static final StyleKey SHOWTAGS = GmDeepHistoryStructuredStyleKeys.SHOWTAGS;

}
