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
package org.modelio.platform.model.ui.nattable.viewer;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.nebula.widgets.nattable.copy.command.CopyDataCommandHandler;
import org.eclipse.nebula.widgets.nattable.hideshow.ColumnHideShowLayer;
import org.eclipse.nebula.widgets.nattable.hover.HoverLayer;
import org.eclipse.nebula.widgets.nattable.hover.config.SimpleHoverStylingBindings;
import org.eclipse.nebula.widgets.nattable.layer.AbstractIndexLayerTransform;
import org.eclipse.nebula.widgets.nattable.layer.IUniqueIndexLayer;
import org.eclipse.nebula.widgets.nattable.layer.stack.DefaultBodyLayerStack;
import org.eclipse.nebula.widgets.nattable.reorder.ColumnReorderLayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.util.IClientAreaProvider;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;

/**
 * Custom Layer made from {@link DefaultBodyLayerStack}
 * It add the Hover Layer
 */
@objid ("9a3f0833-ac26-4b2b-b1e6-250ce64edb3b")
public class ModelioDefaultBodyLayerStack extends AbstractIndexLayerTransform {
    @objid ("f4bb644e-f0b5-4b18-9f3a-7adace7ac13e")
    private final ColumnReorderLayer columnReorderLayer;

    @objid ("b70bdb1f-7e1e-4ce8-bf6b-66189cf9269b")
    private final ColumnHideShowLayer columnHideShowLayer;

    @objid ("11e836c1-4f56-40f7-8bb6-de9c85c57e15")
    private final SelectionLayer selectionLayer;

    @objid ("da9486c6-3523-4c2e-9667-28dc6d2831f5")
    private final ViewportLayer viewportLayer;

    @objid ("19b323e3-8298-4144-a1cc-ca1107792d0d")
    public  ModelioDefaultBodyLayerStack(IUniqueIndexLayer underlyingLayer) {
        this.columnReorderLayer = new ColumnReorderLayer(underlyingLayer);
        this.columnHideShowLayer = new ColumnHideShowLayer(this.columnReorderLayer);
        
        HoverLayer hoverLayer = new HoverLayer(this.columnHideShowLayer, false);
        hoverLayer.addConfiguration(new SimpleHoverStylingBindings(hoverLayer));
        
        this.selectionLayer = new SelectionLayer(hoverLayer);
        this.viewportLayer = new ViewportLayer(this.selectionLayer);
        setUnderlyingLayer(this.viewportLayer);
        
        registerCommandHandler(new CopyDataCommandHandler(this.selectionLayer));
        
    }

    @objid ("db64b221-a8c9-42ca-9c69-1b26543a2918")
    @Override
    public void setClientAreaProvider(IClientAreaProvider clientAreaProvider) {
        super.setClientAreaProvider(clientAreaProvider);
    }

    @objid ("966603bd-4e02-4cbd-909e-4f7964b7c97e")
    public ColumnReorderLayer getColumnReorderLayer() {
        return this.columnReorderLayer;
    }

    @objid ("ad524930-0a12-4e89-9806-9a9bbbff7f2a")
    public ColumnHideShowLayer getColumnHideShowLayer() {
        return this.columnHideShowLayer;
    }

    @objid ("19a1663f-ffd6-453e-a976-cec4d4e67f89")
    public SelectionLayer getSelectionLayer() {
        return this.selectionLayer;
    }

    @objid ("5f5a2961-b7fe-4333-9210-158bf19ab291")
    public ViewportLayer getViewportLayer() {
        return this.viewportLayer;
    }

}
