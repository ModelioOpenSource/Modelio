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

package org.modelio.diagram.symbol.panel;

import java.util.ArrayList;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * Update the named style from all modified style keys.
 */
@objid ("20232efa-0ede-40f0-b213-143ec169cf12")
class UpdateStyleFromModifiedPropsCommand {
    @objid ("dc1626fc-2501-471b-b493-0c870757ff97")
    private ISymbolPanelModel model;

    @objid ("a38f4832-fa94-40cf-9eaf-cf345940b205")
    public UpdateStyleFromModifiedPropsCommand(ISymbolPanelModel model) {
        this.model = model;
    }

    @objid ("5794f209-647d-4097-b2e8-77cdab1b33b7")
    public void execute() {
        final IStyle editedStyle = this.model.getStyleInput();
        final NamedStyle parentStyle = ISymbolPanelModel.getNamedStyle(editedStyle);
        
        for (StyleKey styleKey : new ArrayList<>(editedStyle.getLocalKeys())) {
            parentStyle.setProperty(styleKey, editedStyle.getProperty(styleKey));
            editedStyle.normalize(styleKey);
        }
        
        DiagramStyles.getStyleManager().save(parentStyle);
    }

}
