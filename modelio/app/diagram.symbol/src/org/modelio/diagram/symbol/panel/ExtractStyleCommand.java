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

package org.modelio.diagram.symbol.panel;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.modelio.core.ui.swt.SelectionHelper;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.NamedStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.diagram.symbol.plugin.DiagramSymbol;
import org.modelio.ui.swt.ColoredInputDialog;
import org.modelio.utils.i18n.BundledMessages;

@objid ("82208386-380e-4c6e-afcc-8d18581e8ef8")
class ExtractStyleCommand {
    @objid ("02753d44-f141-4e17-ab34-846d135e99dd")
    private ISymbolPanelModel model;

    @objid ("89674fcb-0429-4752-b8d8-9c49f9f9cb31")
    private Shell shell;

    @objid ("ace8a2f6-0eac-4e11-a3f2-263ce1c2e110")
    public ExtractStyleCommand(ISymbolPanelModel model) {
        this.model = model;
        this.shell = model.getSwtShell();
    }

    @objid ("5bfaf6a9-7470-47fc-943a-0bde7d17d9e7")
    public void execute(boolean allLocals) {
        final BundledMessages I18N = DiagramSymbol.I18N;
        final IStyle editedStyle = this.model.getStyleInput();
        final NamedStyle parentStyle = ISymbolPanelModel.getNamedStyle(editedStyle);
        final ISelection selection = this.model.getPanelSelection().getSelection();
        
        final boolean parentIsTheme = parentStyle.isTheme();
        final boolean createTheme = this.model.shouldCreateTheme();
        
        String suffix = createTheme ? ".Theme" : ".Style";
        String parentKind = parentIsTheme ? I18N.getMessage("SymbolPanelProvider.isTheme") : I18N.getMessage("SymbolPanelProvider.isStyle");
        
        final IInputValidator validator = (String newText) -> {
            // Check name is valid and unique
            if (!NamedStyle.isValidName(newText)) {
                return I18N.getMessage("$ExtractStyle.Error.BadStyleName" + suffix, NamedStyle.NAME_PATTERN.pattern());
            } else if (DiagramStyles.getStyleManager().findStyle(newText) != null) {
                return I18N.getMessage("ExtractStyle.Error.DuplicateStyleName", newText);
            }
            return null;
        };
        
        final ColoredInputDialog dlg = new ColoredInputDialog(this.shell,
                I18N.getMessage("$ExtractStyle.Title" + suffix, parentStyle.getName(), parentKind),
                I18N.getString("$ExtractStyle.Prompt" + suffix),
                I18N.getString("$ExtractStyle.DefaultName" + suffix), validator);
        
        dlg.open();
        final String name = dlg.getValue();
        if (name == null) {
            return;
        }
        
        final NamedStyle newStyle = DiagramStyles.getStyleManager().createStyle(name, parentStyle.getName(), createTheme);
        if (newStyle != null) {
            editedStyle.setBaseStyle(newStyle);
        
            StyleKey[] styleKeysToExtract;
            if (allLocals) {
                styleKeysToExtract = editedStyle.getLocalKeys().toArray(new StyleKey[0]);
            } else {
                styleKeysToExtract = SelectionHelper
                        .toStream(selection, ISymbolViewItem.class)
                        .map(item -> item.getStyleKey())
                        .filter(key -> key != null && editedStyle.isLocal(key))
                        .toArray(StyleKey[]::new);
            }
        
            for (StyleKey styleKey : styleKeysToExtract) {
                newStyle.setProperty(styleKey, editedStyle.getProperty(styleKey));
                editedStyle.normalize(styleKey);
            }
        
        }
    }

}
