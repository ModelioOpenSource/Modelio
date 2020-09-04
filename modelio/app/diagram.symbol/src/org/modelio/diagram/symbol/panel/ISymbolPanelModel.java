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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.widgets.Shell;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.viewer.StyleEditPanelSelection;

/**
 * Access to the internals of {@link SymbolPanelProvider}.
 * @author cma
 * @since 3.7
 */
@objid ("4d0d0619-7e1e-46ed-9acd-deb8e950eb80")
interface ISymbolPanelModel {
    /**
     * @return the graphic model whose style is edited or null.
     */
    @objid ("9b8bfb7d-7031-4341-8e82-9de6ccfd0b85")
    IGmObject getSelectedSymbol();

    /**
     * @return the edited style.
     */
    @objid ("5a155bcb-e84b-42b7-80f8-be2b9d596011")
    IStyle getStyleInput();

    /**
     * Contains the current selection and computations about the selection.
     * @return the symbol panel selection.
     */
    @objid ("8970a5e0-bc1d-4a23-ae04-2630d96a5ad3")
    StyleEditPanelSelection getPanelSelection();

    /**
     * @return whether the style key description is shown
     */
    @objid ("75e8cead-16a6-44df-ab6f-159e744d3011")
    boolean isShowHelp();

    /**
     * Tells from the selected graphic model and the edited style whether style extraction should create a theme or a style.
     * @return true if a theme should be created, false if a style should be created.
     */
    @objid ("c3f83512-a9d9-41d7-b2ab-4c4aa7efcc10")
    default boolean shouldCreateTheme() {
        final IGmObject selectedSymbol = getSelectedSymbol();
        
        if (selectedSymbol==null) {
            final IStyle editedStyle = getStyleInput();
            final NamedStyle parentStyle = getNamedStyle(editedStyle);
            final boolean parentIsTheme = parentStyle.isTheme();
        
            return parentIsTheme;
        } else {
            return selectedSymbol instanceof IGmDiagram;
        }
    }

    /**
     * Walk the parent style hierarchy until a NamedStyle is found.
     * @param s a style.
     * @return the named style the given style is based on.
     */
    @objid ("f5fd32cc-a125-4ae4-babe-0ac4698fdd7e")
    static NamedStyle getNamedStyle(IStyle s) {
        for (IStyle p = s; p != null; p = p.getCascadedStyle()) {
            if (p instanceof NamedStyle) {
                return (NamedStyle) p;
            }
        }
        throw new IllegalArgumentException(String.format("%s style has no named style in its parent hierarchy.", s));
    }

    /**
     * @return The panel SWT shell.
     */
    @objid ("419bfa89-4b12-4b24-8b64-2d7afa08dd66")
    Shell getSwtShell();

}
