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
package org.modelio.uml.activitydiagram.editor.elements.initial;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmInitial when its representation mode is RepresentationMode.IMAGE
 */
@objid ("618e8580-7016-4598-aabb-735b7747ad60")
public class GmInitialUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("e08dd2af-9071-4916-93a7-0a37aae96be3")
    static final StyleKey REPMODE = GmInitialStructuredStyleKeys.REPMODE;

    @objid ("9c4c3013-3c70-4d18-80c9-4850c00a0778")
    static final StyleKey FONT = GmInitialStructuredStyleKeys.FONT;

    @objid ("fa9dbfe7-81d1-4657-b4e6-9fd90e310adc")
    static final StyleKey TEXTCOLOR = GmInitialStructuredStyleKeys.TEXTCOLOR;

    @objid ("faeb90fa-5883-430a-89b3-2d98ebd2e01c")
    static final StyleKey SHOWSTEREOTYPES = GmInitialStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("b77af2de-301e-4f35-a0ef-bcfac894bfbf")
    static final StyleKey SHOWTAGS = GmInitialStructuredStyleKeys.SHOWTAGS;

    /**
     * Show the label.
     */
    @objid ("7b934835-ded0-4e94-8c16-038e5c2d28d9")
    static final StyleKey SHOWLABEL = GmInitialStructuredStyleKeys.SHOWLABEL;

}
