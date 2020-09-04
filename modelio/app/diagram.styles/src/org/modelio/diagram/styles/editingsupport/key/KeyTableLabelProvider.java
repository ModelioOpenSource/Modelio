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

package org.modelio.diagram.styles.editingsupport.key;

import java.util.List;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.ui.CoreColorRegistry;
import org.modelio.ui.CoreFontRegistry;
import org.modelio.ui.UIColor;

/**
 * Key column label provider for a {@link TableViewer}.
 * 
 * @author cma
 * @since Forked KeyLabelProvider on 3.7
 */
@objid ("85a6f8ea-1926-11e2-92d2-001ec947c8cc")
public class KeyTableLabelProvider extends StyledCellLabelProvider {
    @objid ("3c20a251-26c0-4a79-895a-d39969b01c4d")
    private static final RGB heritedColor = new RGB(64, 64, 64);

    @objid ("f5a02183-fcb0-464c-bf98-fb0d76d928ea")
    private final Supplier<IStyle> inputSupplier;

    @objid ("02f19260-cdf8-4340-bf78-fcf9bef84e12")
    private static final RGB localColor = new RGB(0, 0, 0);

    @objid ("bcd6e870-98a0-44c6-b68c-7db7c72dea75")
    private Font localDynamicFont;

    @objid ("77284548-aa2a-4286-9845-fa095af7c4f3")
    private Font localFont;

    @objid ("ab1a56c4-f1ed-4792-a0cf-e155752a7be6")
    private final Supplier<ISymbolViewModel> modelSupplier;

    @objid ("85a95b3b-1926-11e2-92d2-001ec947c8cc")
    public KeyTableLabelProvider(Supplier<ISymbolViewModel> modelSupplier, Supplier<IStyle> inputSupplier) {
        this.modelSupplier = modelSupplier;
        this.inputSupplier = inputSupplier;
    }

    @objid ("85a95b48-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void dispose() {
        this.localFont = null;
        super.dispose();
    }

    @objid ("6a884317-40e3-41ae-b99f-1a1cec9f87f1")
    @Override
    public String getToolTipText(Object obj) {
        ISymbolViewItem element = (ISymbolViewItem) obj;
        String cellText = element.getLabel();
        
        if (element.getStyleKey() != null && getEditedStyle().isDynamicValue(element.getStyleKey())) {
            return DiagramStyles.I18N.getMessage("editingsupport.key.dynamic.tooltip", cellText);
        } else if (element.isLocallyModified(getEditedStyle())) {
            return DiagramStyles.I18N.getMessage("editingsupport.key.modified.tooltip", cellText);
        } else if (isOverflowing(cellText)) {
            // display tooltip only if wider than column width.
            return cellText;
        } else {
            return null;
        }
    }

    @objid ("85a95b3e-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void update(ViewerCell cell) {
        ISymbolViewItem element = (ISymbolViewItem) cell.getElement();
        ISymbolViewModel viewModel = this.modelSupplier.get();
        List<? extends ISymbolViewItem> children = viewModel.getChildren(element);
        
        String cellText = element.getLabel();
        
        int indent = 0;
        if (cellText == null) {
            cellText = "";
        } else if (!cellText.isEmpty() && viewModel.getParent(element) != null) {
            // indent child label
            for (ISymbolViewItem i = viewModel.getParent(element); i != null; i = viewModel.getParent(i)) {
                cellText = "  " + cellText;
                indent += 2;
            }
        }
        
        if (!children.isEmpty()) {
            // Item with children
        
            cell.setText(cellText);
            cell.setBackground(UIColor.TABLE_HEADER_BG);
        
            StyleRange styleRange = new StyleRange();
            styleRange.start = indent;
            styleRange.length = cellText.length() - indent;
            styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.localColor);
            // styleRange.font = getBoldFont(cell.getFont());
        
            /*
             * //set bold if any child is locally modified for (ISymbolViewItem item : children) { if (item.isLocallyModified(getEditedStyle())) { styleRange.foreground = CoreColorRegistry.getColor(localColor); styleRange.font =
             * getBoldFont(cell.getFont());
             * 
             * break; } }
             */
        
            cell.setStyleRanges(new StyleRange[] { styleRange });
        } else {
        
            StyleKey skey = element.getStyleKey();
        
            cell.setText(cellText);
        
            StyleRange styleRange = new StyleRange();
            styleRange.start = indent;
            styleRange.length = cellText.length() - indent;
        
            if (skey != null && getEditedStyle().isDynamicValue(skey)) {
                styleRange.font = getLocalDynamicFont(cell.getFont());
                styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.heritedColor);
            } else if (element.isLocallyModified(getEditedStyle())) {
                styleRange.font = getBoldFont(cell.getFont());
                styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.localColor);
            } else {
                styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.heritedColor);
                styleRange.font = null;
            }
            cell.setStyleRanges(new StyleRange[] { styleRange });
        }
        return;
    }

    @objid ("85a95b43-1926-11e2-92d2-001ec947c8cc")
    private Font getBoldFont(Font font) {
        if (this.localFont == null) {
            this.localFont = CoreFontRegistry.getModifiedFont(font, SWT.BOLD, 1.0f);
        }
        return this.localFont;
    }

    @objid ("f37ee52b-ed25-4368-a11f-63dab0095e3f")
    private IStyle getEditedStyle() {
        return this.inputSupplier.get();
    }

    @objid ("c2212c43-a829-4492-9ba0-c3df24bc9ae3")
    private Font getLocalDynamicFont(Font font) {
        if (this.localDynamicFont == null) {
            this.localDynamicFont = CoreFontRegistry.getModifiedFont(font, SWT.ITALIC | SWT.BOLD, 1.0f);
        }
        return this.localDynamicFont;
    }

    @objid ("2ef90d11-7781-42a7-9116-5a99b872b6e6")
    private boolean isOverflowing(String cellText) {
        Table t = ((TableViewer) getViewer()).getTable();
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

    @objid ("70f5b636-d6aa-4364-af50-8ae0d0bff223")
    @Override
    protected void measure(Event event, Object element) {
        super.measure(event, element);
        if (event.height < 24) {
            event.height = 24;
        }
    }

}
