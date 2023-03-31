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
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Table;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.plugin.DiagramStyles;
import org.modelio.platform.ui.CoreColorRegistry;
import org.modelio.platform.ui.CoreFontRegistry;
import org.modelio.platform.ui.UIColor;
import org.modelio.platform.ui.UIImages;

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

    @objid ("e84939cd-75bd-4249-a7ab-c717f4d3adcb")
    private Image sectionIcon;

    @objid ("85a95b3b-1926-11e2-92d2-001ec947c8cc")
    public  KeyTableLabelProvider(Supplier<ISymbolViewModel> modelSupplier, Supplier<IStyle> inputSupplier) {
        this.modelSupplier = modelSupplier;
        this.inputSupplier = inputSupplier;
        
    }

    @objid ("1a377a76-f0ae-4e54-acb2-0d375a8ae16e")
    @Override
    public void initialize(ColumnViewer viewer, ViewerColumn column) {
        // The section icon is a UIColor.TABLE_HEADER_BG color filled 24x24 rectangle  used for table lines that represent "sections"
        this.sectionIcon =  new Image(viewer.getControl().getDisplay(), 24, 24);
        GC gc = new GC(this.sectionIcon);
        gc.setBackground(UIColor.TABLE_HEADER_BG);
        gc.fillRectangle(0, 0, 24, 24);
        gc.dispose();
        
        super.initialize(viewer, column);
        
    }

    @objid ("85a95b48-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void dispose() {
        this.localFont = null;
        if (this.sectionIcon != null) {
            this.sectionIcon.dispose();
            this.sectionIcon = null;
        }
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
        
        // Cell text
        String cellText = element.getLabel();
        cell.setText(cellText);
        
        int indent = 0;
        // if (cellText == null) {
        // cellText = "";
        // } else if (!cellText.isEmpty() && viewModel.getParent(element) != null) {
        // // indent child label
        // for (ISymbolViewItem i = viewModel.getParent(element); i != null; i = viewModel.getParent(i)) {
        // cellText = " " + cellText;
        // indent += 2;
        // }
        // }
        
        // Cell icon
        if (!children.isEmpty()) {
            cell.setImage(this.sectionIcon);
        }
        else if (element.isLocallyModified(getEditedStyle())) {
            cell.setImage(UIImages.DOT);
        } else {
            cell.setImage(null);
        }
        
        // Cell coloring
        if (!children.isEmpty()) {
            // Item with children
            cell.setBackground(UIColor.TABLE_HEADER_BG);
            StyleRange styleRange = new StyleRange();
            styleRange.start = indent;
            styleRange.length = cellText.length() - indent;
            styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.localColor);
            cell.setStyleRanges(new StyleRange[] { styleRange });
        } else {
            StyleKey skey = element.getStyleKey();
            StyleRange styleRange = new StyleRange();
            styleRange.start = indent;
            styleRange.length = cellText.length() - indent;
            if (skey != null && getEditedStyle().isDynamicValue(skey)) {
                styleRange.font = getLocalDynamicFont(cell.getFont());
                styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.heritedColor);
            } else if (element.isLocallyModified(getEditedStyle())) {
                styleRange.font = null;
                styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.localColor);
            } else {
                styleRange.font = null;
                styleRange.foreground = CoreColorRegistry.getColor(KeyTableLabelProvider.heritedColor);
            }
            cell.setStyleRanges(new StyleRange[] { styleRange });
        }
        return;
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
