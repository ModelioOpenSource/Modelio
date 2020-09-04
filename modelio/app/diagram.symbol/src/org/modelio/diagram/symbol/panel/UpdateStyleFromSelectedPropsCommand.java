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

package org.modelio.diagram.symbol.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.plugin.DiagramStyles;

/**
 * Update the named style from the selected style keys.
 */
@objid ("febfb90e-822a-46df-8c93-9f16cbf53219")
class UpdateStyleFromSelectedPropsCommand {
    @objid ("2c124275-22e0-4878-86be-28645b8a5a1f")
    private final ISymbolPanelModel model;

    @objid ("57cc7a28-a1e6-4131-97e2-4e957dfa010c")
    public UpdateStyleFromSelectedPropsCommand(ISymbolPanelModel model) {
        this.model = model;
    }

    @objid ("c2712cb2-34d5-48dd-9e47-99c0eb9272c9")
    public void execute() {
        final IStyle editedStyle = this.model.getStyleInput();
        final NamedStyle parentStyle = ISymbolPanelModel.getNamedStyle(editedStyle);
        final ISelection sel = this.model.getPanelSelection().getSelection();
        
        SelectionHelper
                .toStream(sel, ISymbolViewItem.class)
                .filter(item -> item.getStyleKey() != null && editedStyle.isLocal(item.getStyleKey()))
                .forEach(item -> {
                    final StyleKey styleKey = item.getStyleKey();
                    parentStyle.setProperty(styleKey, editedStyle.getProperty(styleKey));
                    editedStyle.normalize(styleKey);
                });
        
        DiagramStyles.getStyleManager().save(parentStyle);
    }

}
