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

package org.modelio.diagram.styles.editingsupport.key;

import java.util.List;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.ui.UIColor;
import org.modelio.ui.UIFont;

/**
 * Key column label provider for a {@link TreeViewer}.
 * 
 * @author cma
 * @since Forked KeyLabelProvider on 3.7
 */
@objid ("930a4723-8bda-4eeb-a213-1933ffd9fb38")
public class KeyTreeLabelProvider extends StyledCellLabelProvider {
    @objid ("2a2f695e-dca2-42c2-9a06-acab89536829")
    private Font fromFont;

    @objid ("75ee0039-0671-4436-ae32-704ba13330c8")
    private static final Color heritedColor = UIColor.TEXT_WRITABLE_FG;

    @objid ("b6a806c7-c573-4e15-9918-04da5a930710")
    private final Supplier<IStyle> inputSupplier;

    @objid ("85a8349c-a4df-4559-8632-de15c4e21ae5")
    private static final Color localColor = UIColor.BLACK;

    @objid ("2fdf4cc5-d5e5-41c7-a278-8d382306eaec")
    private Font localDynamicFont;

    @objid ("397de429-3cbe-4953-9806-a659f1de9a76")
    private Font localFont;

    @objid ("c018093e-929c-44ac-bb58-4e3e5a662a8e")
    private final Supplier<ISymbolViewModel> modelSupplier;

    @objid ("ce285b96-845c-4d96-9e3e-b24f90915575")
    public KeyTreeLabelProvider(Supplier<ISymbolViewModel> modelSupplier, Supplier<IStyle> inputSupplier) {
        this.modelSupplier = modelSupplier;
        this.inputSupplier = inputSupplier;
    }

    @objid ("d2da10c6-627c-4d64-9600-dfa8cea4c38e")
    @Override
    public void dispose() {
        this.localFont = null;
        super.dispose();
    }

    @objid ("4cf0f300-c7ad-43dc-a7aa-e274649b3292")
    @Override
    public String getToolTipText(Object obj) {
        ISymbolViewItem element = (ISymbolViewItem) obj;
        String cellText = element.getLabel();
        
        if (element.getStyleKey() != null && getEditedStyle().isDynamicValue(element.getStyleKey())) {
            return DiagramStyles.I18N.getMessage("editingsupport.key.dynamic.tooltip", cellText);
        } else if (element.isLocallyModified(getEditedStyle())) {
            return DiagramStyles.I18N.getMessage("editingsupport.key.modified.tooltip", cellText);
        } else if (isOverflowing(cellText)) {
            // display tooltip only if wider than column width
            return cellText;
        } else {
            return null;
        }
    }

    @objid ("7a990fd7-7eb4-40fa-a04f-1aef62fb6353")
    @Override
    public void update(ViewerCell cell) {
        ISymbolViewItem element = (ISymbolViewItem) cell.getElement();
        ISymbolViewModel viewModel = this.modelSupplier.get();
        List<? extends ISymbolViewItem> children = viewModel.getChildren(element);
        
        String cellText = element.getLabel();
        if (cellText == null) {
            cellText = "";
        }
        cell.setText(cellText);
        
        StyleRange styleRange = new StyleRange();
        if (!children.isEmpty()) {
            // Item with children, set bold if any child is locally modified
            styleRange.start = 0;
            styleRange.length = cellText.length();
            styleRange.foreground = KeyTreeLabelProvider.heritedColor;
            styleRange.font = null;
        
            for (ISymbolViewItem item : children) {
                if (item.isLocallyModified(getEditedStyle())) {
                    styleRange.foreground = KeyTreeLabelProvider.localColor;
                    styleRange.font = getLocalFont(cell.getFont());
                    break;
                }
            }
        } else {
            StyleKey skey = element.getStyleKey();
        
            styleRange.start = 0;
            styleRange.length = cellText.length();
        
            if (skey != null && getEditedStyle().isDynamicValue(skey)) {
                styleRange.font = getLocalDynamicFont(cell.getFont());
                styleRange.foreground = KeyTreeLabelProvider.heritedColor;
            } else if (element.isLocallyModified(getEditedStyle())) {
                styleRange.font = getLocalFont(cell.getFont());
                styleRange.foreground = KeyTreeLabelProvider.localColor;
            } else {
                styleRange.foreground = KeyTreeLabelProvider.heritedColor;
                styleRange.font = null;
            }
        }
        
        int index = cellText.indexOf("(");
        if (index != -1) {
            // Item with children, set bold if any child is locally modified
            StyleRange fromRange = new StyleRange(styleRange);
            fromRange.start = index;
            fromRange.length = cellText.length() - index;
            fromRange.font = getFromFont(cell.getFont());
            fromRange.foreground = fromRange.foreground = UIColor.LIGHTGRAY;
        
            cell.setStyleRanges(new StyleRange[] { styleRange, fromRange });
        } else {
            cell.setStyleRanges(new StyleRange[] { styleRange });
        }
        return;
    }

    @objid ("2409f540-b578-4b8d-ac8f-17b5aa918241")
    private IStyle getEditedStyle() {
        return this.inputSupplier.get();
    }

    @objid ("637581ed-9cab-4acf-bb1f-1685a92de31d")
    private Font getFromFont(Font font) {
        if (this.fromFont == null) {
            this.fromFont = CoreFontRegistry.getModifiedFont(font, SWT.ITALIC, UIFont.NORMAL_SIZE);
        }
        return this.fromFont;
    }

    @objid ("5e29c199-f1ad-4930-aa57-8e0b49b5ae87")
    private Font getLocalDynamicFont(Font font) {
        if (this.localDynamicFont == null) {
            this.localDynamicFont = CoreFontRegistry.getModifiedFont(font, SWT.ITALIC | SWT.BOLD, UIFont.NORMAL_SIZE);
        }
        return this.localDynamicFont;
    }

    @objid ("04690cd2-bf00-43ef-9493-f7b16d1bb5f7")
    private Font getLocalFont(Font font) {
        if (this.localFont == null) {
            this.localFont = CoreFontRegistry.getModifiedFont(font, SWT.BOLD, UIFont.NORMAL_SIZE);
        }
        return this.localFont;
    }

    @objid ("7bef7d28-7e71-4cc9-a402-3390f719f4e0")
    private boolean isOverflowing(String cellText) {
        Tree t = ((TreeViewer) getViewer()).getTree();
        GC gc = new GC(t.getDisplay());
        try {
            if (gc.textExtent(cellText).x > t.getColumn(0).getWidth()) {
                return true;
            } else {
                return false;
            }
        } finally {
            gc.dispose();
        }
    }

    @objid ("14a64028-8935-4bc7-8043-47df9ee10b42")
    @Override
    protected void measure(Event event, Object element) {
        super.measure(event, element);
        if (event.height < 24) {
            event.height = 24;
        }
    }

}
