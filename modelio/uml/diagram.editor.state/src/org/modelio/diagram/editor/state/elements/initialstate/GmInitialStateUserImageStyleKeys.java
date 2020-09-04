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

package org.modelio.diagram.editor.state.elements.initialstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmInitialState} when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("b9f0b5e5-3218-4f4e-a56d-9496a1ef6305")
public class GmInitialStateUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("80ccfee2-4cbe-4379-9fec-82fc836a1edf")
     static final StyleKey REPMODE = GmInitialStateStructuredStyleKeys.REPMODE;

    @objid ("0b4a38bf-9785-453b-a2b4-e546b138f724")
     static final StyleKey FONT = GmInitialStateStructuredStyleKeys.FONT;

    @objid ("a58556a9-31f3-4740-92b4-76da13a664d4")
     static final StyleKey TEXTCOLOR = GmInitialStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("74b23a8b-7b73-4fa6-a515-329cad00895d")
     static final StyleKey SHOWSTEREOTYPES = GmInitialStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a736be4d-82a2-401a-b225-09ddbbf368b3")
     static final StyleKey SHOWTAGS = GmInitialStateStructuredStyleKeys.SHOWTAGS;

    @objid ("ea37e55f-11ff-4fa5-a037-cc49f580ec52")
     static final StyleKey SHOWLABEL = GmInitialStateStructuredStyleKeys.SHOWLABEL;

}
