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

package org.modelio.diagram.editor.state.elements.finalstate;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.state.style.StateAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmFinalstate when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("a3e900fc-0c39-47d2-b7cd-e95da476abb5")
public class GmFinalStateUserImageStyleKeys extends StateAbstractStyleKeyProvider {
    @objid ("41a4c8e8-00dc-4583-917f-398bbe8f2e40")
     static final StyleKey REPMODE = GmFinalStateStructuredStyleKeys.REPMODE;

    @objid ("2b87c4b1-e55f-4bb3-9fab-8fe1f1098dec")
     static final StyleKey FONT = GmFinalStateStructuredStyleKeys.FONT;

    @objid ("57967dc7-bd5d-46ab-8237-c65012e05596")
     static final StyleKey TEXTCOLOR = GmFinalStateStructuredStyleKeys.TEXTCOLOR;

    @objid ("a79e7548-268b-4c9a-b77f-56be8b7da3f8")
     static final StyleKey SHOWSTEREOTYPES = GmFinalStateStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("9ee580aa-8e4c-4c88-94d2-667b5088146c")
     static final StyleKey SHOWTAGS = GmFinalStateStructuredStyleKeys.SHOWTAGS;

    @objid ("38bb7a74-6f40-42bc-95ab-dd332efe093c")
     static final StyleKey SHOWLABEL = createStyleKey("FINALSTATE_SHOWLABEL", MetaKey.SHOWLABEL);

}
