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

package org.modelio.diagram.styles.editingsupport;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Event;
import org.modelio.diagram.styles.core.view.ISymbolViewItem;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.diagram.styles.editingsupport.checkbox.CheckboxLabelProvider;
import org.modelio.diagram.styles.editingsupport.color.ColorLabelProvider;
import org.modelio.diagram.styles.editingsupport.combo.EnumComboBoxLabelProvider;
import org.modelio.diagram.styles.editingsupport.font.FontLabelProvider;
import org.modelio.diagram.styles.editingsupport.text.TextLabelProvider;
import org.modelio.ui.UIColor;

/**
 * Style key value label provider.
 */
@objid ("85b0826d-1926-11e2-92d2-001ec947c8cc")
public class StyleCellLabelProvider extends OwnerDrawLabelProvider {
    @objid ("a4990bf7-f597-4d0c-b216-1c574d7c9179")
    private final boolean tableMode;

    /**
     * Specialized label providers.
     * <p>
     * Note: to add an owner draw label provider, do not subclass it from {@link OwnerDrawLabelProvider},
     * make it implement {@link IOwnerDrawLabelProvider} instead.
     */
    @objid ("760604ca-b586-406c-b176-6c5b6a348382")
    private Map<Class<?>, CellLabelProvider> providers = new HashMap<>();

    @objid ("719decf5-df55-496b-b0fe-4ff96287c3a3")
    private static final ColumnLabelProvider DEFAULT_PROVIDER = new ColumnLabelProvider();

    @objid ("822c1ddc-3b54-4bed-a606-a5bdaf468129")
    private final Supplier<ISymbolViewModel> modelSupplier;

    /**
     * C'tor.
     * 
     * @param treeViewer the viewer.
     */
    @objid ("85b2e4a5-1926-11e2-92d2-001ec947c8cc")
    public StyleCellLabelProvider(ColumnViewer treeViewer, boolean tableMode, Supplier<ISymbolViewModel> modelSupplier) {
        this.tableMode = tableMode;
        this.modelSupplier = modelSupplier;
        
        // Note: to add an owner draw label provider, do not subclass it from OwnerDrawLabelProvider,
        // make it implement IOwnerDrawLabelProvider instead.
        this.providers.put(Boolean.class, new CheckboxLabelProvider(treeViewer));
        this.providers.put(String.class, new TextLabelProvider(treeViewer));
        this.providers.put(Color.class, new ColorLabelProvider(treeViewer));
        this.providers.put(Integer.class, new TextLabelProvider(treeViewer));
        this.providers.put(Font.class, new FontLabelProvider(treeViewer));
        this.providers.put(Enum.class, new EnumComboBoxLabelProvider(treeViewer));
    }

    @objid ("85b2e4a8-1926-11e2-92d2-001ec947c8cc")
    @Override
    public void update(ViewerCell cell) {
        ISymbolViewItem item = (ISymbolViewItem) cell.getElement();
        CellLabelProvider provider = getLabelProviderFor(item);
        if (provider != null) {
            provider.update(cell);
        }
        
        if (this.tableMode && !this.modelSupplier.get().getChildren(item).isEmpty()) {
            // gray out cell
            cell.setBackground(UIColor.TABLE_HEADER_BG);
        }
    }

    @objid ("85b2e4ac-1926-11e2-92d2-001ec947c8cc")
    @Override
    public String getToolTipText(Object element) {
        CellLabelProvider provider = getLabelProviderFor((ISymbolViewItem) element);
        if (provider != null && provider != StyleCellLabelProvider.DEFAULT_PROVIDER) {
            String tooltip = provider.getToolTipText(element);
            if (tooltip != null) {
                return tooltip;
            }
            
            if (provider instanceof ILabelProvider) {
                return ((ILabelProvider) provider).getText(element);
            }
        }
        return super.getToolTipText(element);
    }

    /**
     * @param element Item in the symbol view model.
     * @return <ul>
     * <li>null : there is no value to edit
     * <li>{@link #DEFAULT_PROVIDER} : there is no specific provider
     * <li>anything else : the provider to use
     * </ul>
     */
    @objid ("4168f8d9-a73f-437f-bd17-ccb2d845fef2")
    private CellLabelProvider getLabelProviderFor(ISymbolViewItem element) {
        if (!element.getPossibleValues().isEmpty()) {
            return this.providers.get(Enum.class);
        } else {
            Class<?> stype = element.getType();
            if (stype == null) {
                return null;
            } else {
                CellLabelProvider columnLabelProvider = this.providers.get(stype);
                return columnLabelProvider != null ? columnLabelProvider : StyleCellLabelProvider.DEFAULT_PROVIDER;
            }
        }
    }

    @objid ("8f965a39-a342-406d-b7bb-776663fbe5f6")
    @Override
    protected void measure(Event event, Object element) {
        CellLabelProvider provider = getLabelProviderFor((ISymbolViewItem) element);
        if (provider != StyleCellLabelProvider.DEFAULT_PROVIDER && provider instanceof IOwnerDrawLabelProvider) {
            ((IOwnerDrawLabelProvider) provider).measure(event, element);
        }
    }

    @objid ("025cbc39-c851-47f0-b7b0-c449be4a41ca")
    @Override
    protected void paint(Event event, Object element) {
        CellLabelProvider provider = getLabelProviderFor((ISymbolViewItem) element);
        if (provider != StyleCellLabelProvider.DEFAULT_PROVIDER && provider instanceof IOwnerDrawLabelProvider) {
            ((IOwnerDrawLabelProvider) provider).paint(event, element);
        }
    }

}
