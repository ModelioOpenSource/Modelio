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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.IToolTipProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfiguration;
import org.eclipse.nebula.widgets.nattable.coordinate.Range;
import org.eclipse.nebula.widgets.nattable.copy.command.CopyDataCommandHandler;
import org.eclipse.nebula.widgets.nattable.data.IDataProvider;
import org.eclipse.nebula.widgets.nattable.edit.config.DefaultEditBindings;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.cell.ILayerCell;
import org.eclipse.nebula.widgets.nattable.painter.cell.ICellPainter;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.selection.event.CellSelectionEvent;
import org.eclipse.nebula.widgets.nattable.selection.event.RowSelectionEvent;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.tooltip.NatTableContentTooltip;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.modelio.platform.model.ui.nattable.viewer.config.GridLayerConfiguration;
import org.modelio.platform.model.ui.nattable.viewer.config.NatTableStyleConfiguration;
import org.modelio.platform.model.ui.nattable.viewer.config.SelectionLayerConfiguration;
import org.modelio.platform.model.ui.nattable.viewer.config.body.BodyConfiguration;
import org.modelio.platform.model.ui.nattable.viewer.config.body.CellByCellOverrideLabelAccumulator;
import org.modelio.platform.model.ui.nattable.viewer.config.columnheader.ColumnHeaderConfiguration;
import org.modelio.platform.model.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyTableConfigurationProvider;
import org.modelio.platform.model.ui.nattable.viewer.model.PropertyTableDataModel;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.platform.rcp.extensionpoint.ExtensionPointContributionManager;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This method creates and configure a NatTable for property table editor. Also provides Selection management.
 */
@objid ("602e8df9-7fc9-46a9-bd3d-bb523abde06d")
public class PropertyNatTableViewer extends Viewer {
    @objid ("68daacb4-5561-49ad-aa9a-9ec867827c4a")
    private PropertyTableDataModel dataModel;

    @objid ("8afb965a-ec98-417e-b496-34f9847a9a78")
    private INatTableViewerContext context;

    @objid ("a3a15f07-d7d1-47cf-9334-c79edade2e66")
    protected final NatTable natTable;

    @objid ("63cb53c9-5bbe-4589-805c-4717eb65b6a5")
    private SelectionLayer selectionLayer;

    @objid ("1a40e77d-af1c-4532-9b03-cfdfcb1108b6")
    private DataLayer bodyDataLayer;

    /**
     * Create a new instance of the table viewer.
     * 
     * @param parent widget for the table.
     */
    @objid ("de65eee9-4d54-44dd-a101-c2cb516bf1af")
    public PropertyNatTableViewer(final Composite parent) {
        this.natTable = new NatTable(parent, false);
    }

    /**
     * Called after {@link #dataModel} has changed.
     */
    @objid ("da27472c-99f6-4288-9df9-78959507831b")
    private void setupTable() {
        final ILabelProvider elementLabelProvider = setupLabelProvider();
        
        this.natTable.setData(this.dataModel);
        
        // Data providers
        final IDataProvider bodyDataProvider = this.dataModel.getBodyDataProvider();
        final IDataProvider columnHeaderDataProvider = this.dataModel.getColumnHeaderDataProvider();
        
        this.bodyDataLayer = new DataLayer(bodyDataProvider);
        this.bodyDataLayer.setDataProvider(bodyDataProvider);
        final DataLayer columnHeaderDataLayer = new DataLayer(columnHeaderDataProvider, 40, 10);
        final ModelioDefaultBodyLayerStack bodyLayer = new ModelioDefaultBodyLayerStack(this.bodyDataLayer);
        
        // Selection
        this.selectionLayer = bodyLayer.getSelectionLayer();
        this.selectionLayer.clearConfiguration();
        this.selectionLayer.addConfiguration(new SelectionLayerConfiguration());
        
        // Column headers
        final ColumnHeaderLayer columnHeaderLayer = new ColumnHeaderLayer(columnHeaderDataLayer, bodyLayer, this.selectionLayer, false);
        columnHeaderLayer.addConfiguration(new ColumnHeaderConfiguration(this.dataModel, columnHeaderDataLayer));
        
        // General grid layer
        final CompositeLayer compositeLayer = new CompositeLayer(1, 2);
        compositeLayer.setChildLayer(GridRegion.BODY, bodyLayer, 0, 1);
        compositeLayer.setChildLayer(GridRegion.COLUMN_HEADER, columnHeaderLayer, 0, 0);
        compositeLayer.addConfiguration(new DefaultEditBindings());
        compositeLayer.addConfiguration(new GridLayerConfiguration(compositeLayer));
        
        // NOTE: Register the accumulator on the body data layer.
        final CellByCellOverrideLabelAccumulator columnLabelAccumulator = new CellByCellOverrideLabelAccumulator(this.bodyDataLayer, this.dataModel);
        this.bodyDataLayer.setConfigLabelAccumulator(columnLabelAccumulator);
        
        // Setup the global main layer on the table, here the assembly grid.
        this.natTable.setLayer(compositeLayer);
        
        // Global configuration
        this.natTable.addConfiguration(new NatTableStyleConfiguration());
        
        this.natTable.addConfiguration(new BodyConfiguration(this.dataModel, this.context, elementLabelProvider));
        
        // Redefine copy handler to handle INatValue
        final CopyDataCommandHandler cdch = new CopyDataCommandHandler(bodyLayer.getSelectionLayer());
        cdch.setCopyFormattedText(true); // This will handle NatValue
        bodyLayer.registerCommandHandler(cdch);
        bodyLayer.getSelectionLayer().registerCommandHandler(cdch);
        
        addExtensionConfigurations();
        
        this.natTable.configure();
        
        // Provides Selection management.
        this.natTable.addLayerListener(event -> {
            if (event instanceof CellSelectionEvent || event instanceof RowSelectionEvent) {
                final ISelection selection = getSelection();
                final SelectionChangedEvent e = new SelectionChangedEvent(this, selection);
                fireSelectionChanged(e);
            }
        });
        
        setupTooltip();
        
        // Set column initial sizes
        setupColumnsSize(this.bodyDataLayer);
        
        this.natTable.requestLayout();
    }

    @objid ("47446000-63dd-44bd-9363-4df4a9a82936")
    @Override
    public Control getControl() {
        return this.natTable;
    }

    @objid ("c7e404fb-fcad-453d-8a3f-92f25f104a55")
    @Override
    public PropertyTableDataModel getInput() {
        return this.dataModel;
    }

    @objid ("5806b21e-d9df-4925-b46f-4365651cda94")
    public void setInput(final IPropertyModel<? extends MObject> input) {
        setInput(new PropertyTableDataModel(input));
    }

    @objid ("cda05fc8-34a3-4158-844f-aa509cf1aa84")
    @Override
    public void refresh() {
        refresh(false);
    }

    @objid ("0ca69089-d497-4011-8d20-8769ae41a098")
    @Override
    public ISelection getSelection() {
        final List<Object> objects = new ArrayList<>();
        
        final Set<Range> rowRanges = this.selectionLayer.getSelectedRowPositions();
        for (final Range rowRange : rowRanges) {
            for (int rowPosition = rowRange.start; rowPosition < rowRange.end; rowPosition++) {
                objects.add(this.dataModel.getObjectAtRow(rowPosition));
            }
        }
        return new StructuredSelection(objects);
    }

    @objid ("21df04d2-0ce0-4f3d-bbc9-22de25bf0e3c")
    @Override
    public void setSelection(final ISelection selection, final boolean reveal) {
        final IStructuredSelection s = (IStructuredSelection) selection;
        
        this.selectionLayer.clear();
        for (final Object o : s.toList()) {
            final int row = this.dataModel.getRowIndex(o);
            if (row != -1) {
                this.selectionLayer.selectRow(0, row, false, true);
            }
        }
    }

    /**
     * @return Whether or not the editor is currently focused.
     */
    @objid ("c2807a79-42cb-421f-b448-960111fcce30")
    public boolean isEditionActive() {
        return !this.natTable.isFocusControl();
    }

    @objid ("b408c359-9cc8-4131-a253-d967b5de18cf")
    public void setContext(final INatTableViewerContext context) {
        this.context = context;
    }

    @objid ("1b60b963-1571-4c30-9c23-06fb2749bcd0")
    @Override
    public void setInput(final Object input) {
        if (input instanceof IPropertyModel) {
            setInput((IPropertyModel<?>) input);
        } else if (input instanceof PropertyTableDataModel) {
            setInput((PropertyTableDataModel) input);
        } else {
            throw new IllegalArgumentException(String.valueOf(input));
        }
    }

    @objid ("30171529-5369-4afe-a792-887ec883c26c")
    public void setInput(final PropertyTableDataModel input) {
        if (this.dataModel != null) {
            throw new UnsupportedOperationException("Changing input is not supported, dispose and recreate the viewer.");
        }
        
        this.dataModel = input;
        setupTable();
    }

    /**
     * Add {@link IConfiguration} provided by plugins extensions registered on {@link IPropertyTableConfigurationProvider#EXTENSION_POINT}.
     */
    @objid ("6b32e4ab-72af-41c7-b87f-d74ab06cd22f")
    private void addExtensionConfigurations() {
        assert this.context != null;
        assert this.dataModel != null;
        for (final IConfigurationElement configEl : new ExtensionPointContributionManager(IPropertyTableConfigurationProvider.EXTENSION_POINT).getExtensions("provider")) {
            try {
                final IPropertyTableConfigurationProvider provider = (IPropertyTableConfigurationProvider) configEl.createExecutableExtension("class");
                final IConfiguration configuration = provider.getConfiguration(this.context, this.dataModel);
        
                if (configuration == null) {
                    throw new NullPointerException(String.format("The '%s' from '%s' extension did not provide any %s .",
                            provider.getClass().getName(), configEl.getContributor().getName(), IConfiguration.class.getSimpleName()));
                }
        
                this.natTable.addConfiguration(configuration);
            } catch (CoreException | RuntimeException e) {
                CoreUi.LOG.error(e);
            }
        }
    }

    @objid ("974ec0ef-8fd1-4a96-87e0-cab6313d7343")
    protected void setupColumnsSize(final DataLayer dataLayer) {
        dataLayer.setColumnPercentageSizing(0, true);
        dataLayer.setColumnWidthPercentageByPosition(0, 20);
        
        final int columnCount = dataLayer.getColumnCount();
        if (columnCount == 2) {
            // 2 Columns
            dataLayer.setColumnPercentageSizing(1, true);
            dataLayer.setColumnWidthPercentageByPosition(1, 80);
        } else if (columnCount == 3) {
            // 3 columns
            dataLayer.setColumnPercentageSizing(1, true);
            dataLayer.setColumnWidthPercentageByPosition(1, 40);
        
            dataLayer.setColumnPercentageSizing(2, true);
            dataLayer.setColumnWidthPercentageByPosition(2, 40);
        } else {
            for (int i = 1; i < columnCount; i++) {
                dataLayer.setColumnPercentageSizing(i, true);
                dataLayer.setColumnWidthPercentageByPosition(i, 80 / (columnCount - 1));
            }
        }
    }

    /**
     * Instanciate a {@link NatTableContentTooltip} on the table for the {@link GridRegion#BODY}, {@link GridRegion#COLUMN_HEADER} and {@link GridRegion#ROW_HEADER} regions.
     * <p>
     * For a cell, delegates tooltip computing if its painter extends {@link IToolTipProvider}. Otherwise, default behavior of {@link NatTableContentTooltip} applies.
     * </p>
     * <p>
     * Sub classes might redefine this method to define a custom tooltip.
     * </p>
     */
    @objid ("61705702-ad99-4a06-8af7-5aee8d1b7bf6")
    protected void setupTooltip() {
        // Tooltip
        @SuppressWarnings ("unused")
        final
        NatTableContentTooltip tooltip = new NatTableContentTooltip(this.natTable, GridRegion.BODY, GridRegion.COLUMN_HEADER, GridRegion.ROW_HEADER) {
            @Override
            protected String getText(final Event event) {
                final int col = this.natTable.getColumnPositionByX(event.x);
                final int row = this.natTable.getRowPositionByY(event.y);
        
                final ILayerCell cell = this.natTable.getCellByPosition(col, row);
                if (cell != null) {
                    // if the registered cell painter is the PasswordCellPainter, there
                    // will be no tooltip
                    final ICellPainter painter = this.natTable.getConfigRegistry().getConfigAttribute(CellConfigAttributes.CELL_PAINTER, DisplayMode.NORMAL, cell.getConfigLabels().getLabels());
                    if (isVisibleContentPainter(painter)) {
        
                        if (painter instanceof IToolTipProvider) {
                            return ((IToolTipProvider) painter).getToolTipText(cell.getDataValue());
                        } else {
                            return super.getText(event);
                        }
                    }
                }
                return null;
            }
        };
    }

    /**
     * Instanciate a {@link UniversalLabelProvider} for the table.
     * <p>
     * Sub classes might redefine this method to define a custom label provider.
     * </p>
     */
    @objid ("a77e1143-03f3-48ff-bb9b-b4c17c10e32b")
    protected ILabelProvider setupLabelProvider() {
        return new UniversalLabelProvider();
    }

    /**
     * Refreshes this viewer completely with information freshly obtained from this
     * viewer's model.
     * 
     * @param forceConfiguration force a NatTable configuration.
     */
    @objid ("fd330ab2-9834-4e0d-b663-f5908d105edb")
    public void refresh(final boolean forceConfiguration) {
        final PropertyTableDataModel model = getInput();
        if (model == null || forceConfiguration) {
            this.natTable.configure();
        }
        
        if (model != null) {
            // Get old row/col counts
            final int nbRows = model.getRows().size();
            final int nbCols = this.natTable.getColumnCount();
        
            model.rebuildData();
        
            if (nbRows != model.getPropertyModel().getRowsNumber()) {
                // Update the data model in order to refresh correctly the Nat table if the number of rows changed
                model.getRows().clear();
                for (int i = 0; i < model.getPropertyModel().getRowsNumber(); i++) {
                    model.getRows().add(i);
                }
            }
        
            if (nbCols != model.getPropertyModel().getColumnNumber()) {
                // Col count changed, update their respective sizes
                setupColumnsSize(this.bodyDataLayer);
            }
        }
        this.natTable.refresh();
    }

}
