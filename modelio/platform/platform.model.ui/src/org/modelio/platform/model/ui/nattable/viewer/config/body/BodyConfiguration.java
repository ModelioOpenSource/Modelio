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

package org.modelio.platform.model.ui.nattable.viewer.config.body;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.config.IEditableRule;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultDisplayConverter;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultDoubleDisplayConverter;
import org.eclipse.nebula.widgets.nattable.data.convert.DefaultIntegerDisplayConverter;
import org.eclipse.nebula.widgets.nattable.data.validate.DataValidator;
import org.eclipse.nebula.widgets.nattable.data.validate.ValidationFailedException;
import org.eclipse.nebula.widgets.nattable.edit.EditConfigAttributes;
import org.eclipse.nebula.widgets.nattable.edit.action.CellEditDragMode;
import org.eclipse.nebula.widgets.nattable.edit.action.KeyEditAction;
import org.eclipse.nebula.widgets.nattable.edit.action.MouseEditAction;
import org.eclipse.nebula.widgets.nattable.edit.editor.CheckBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.ComboBoxCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.MultiLineTextCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.editor.TextCellEditor;
import org.eclipse.nebula.widgets.nattable.edit.gui.ICellEditDialog;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.painter.cell.CheckBoxPainter;
import org.eclipse.nebula.widgets.nattable.painter.cell.ComboBoxPainter;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.HorizontalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.style.VerticalAlignmentEnum;
import org.eclipse.nebula.widgets.nattable.ui.binding.UiBindingRegistry;
import org.eclipse.nebula.widgets.nattable.ui.matcher.BodyCellEditorMouseEventMatcher;
import org.eclipse.nebula.widgets.nattable.ui.matcher.CellPainterMouseEventMatcher;
import org.eclipse.nebula.widgets.nattable.ui.matcher.KeyEventMatcher;
import org.eclipse.nebula.widgets.nattable.ui.matcher.LetterOrDigitKeyEventMatcher;
import org.eclipse.nebula.widgets.nattable.ui.matcher.MouseEventMatcher;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.modelio.platform.model.ui.MetamodelLabels;
import org.modelio.platform.model.ui.nattable.parts.data.CellTagHelper;
import org.modelio.platform.model.ui.nattable.parts.data.INatValue;
import org.modelio.platform.model.ui.nattable.parts.data.NatValueWrappingDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.bool.BooleanValueDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.bool.IBooleanNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.choice.IElementChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.IMultiElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.MultiElementDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.MultiElementPainter;
import org.modelio.platform.model.ui.nattable.parts.data.element.multi.MultiElementValueEditor;
import org.modelio.platform.model.ui.nattable.parts.data.element.multirow.IMultiRowElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.element.multirow.MultiRowElementNatValueEditor;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.ElementDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.ElementNatValueEditor;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.ElementPainter;
import org.modelio.platform.model.ui.nattable.parts.data.element.single.IElementNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.hybrid.HybridNatValueEditor;
import org.modelio.platform.model.ui.nattable.parts.data.hybrid.IHybridNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.IJavaEnumNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.javaenum.JavaEnumNatValueDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.number._float.IFloatNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._integer.IIntegerNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number._unsigned.IUnsignedNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number.date.DateDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.number.date.DateValueEditor;
import org.modelio.platform.model.ui.nattable.parts.data.number.date.IDateNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number.time.ITimeNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.number.time.TimeDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.number.time.TimeValueEditor;
import org.modelio.platform.model.ui.nattable.parts.data.string.choice.IStringChoiceNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.multi.IMultiStringNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.string.multi.MultiStringDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.string.multi.MultiStringValueEditor;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.DefaultStringDisplayConverter;
import org.modelio.platform.model.ui.nattable.parts.data.string.single.IStringNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.text.ITextNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.texticon.ITextIconNatValue;
import org.modelio.platform.model.ui.nattable.parts.data.texticon.TextIconConverter;
import org.modelio.platform.model.ui.nattable.parts.data.texticon.TextIconPainter;
import org.modelio.platform.model.ui.nattable.parts.handlers.CellPainterMouseEventMatcherWithMask;
import org.modelio.platform.model.ui.nattable.parts.handlers.SelectInExplorerAction;
import org.modelio.platform.model.ui.nattable.viewer.model.INatTableViewerContext;
import org.modelio.platform.model.ui.nattable.viewer.model.IPropertyModel;
import org.modelio.platform.model.ui.nattable.viewer.model.PropertyTableDataModel;
import org.modelio.platform.model.ui.plugin.CoreUi;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Configuration for the body layer of the Element's nat table.
 */
@objid ("cc33f440-6a2b-4774-be11-d8c1c3c7fdd5")
public class BodyConfiguration extends AbstractRegistryConfiguration {
    @objid ("877abbac-3d4d-4d51-a41c-1c372a591256")
    protected static final String EDITABLE = "Cell.editable";

    @objid ("9fa1bbfd-361b-41a9-83ab-20edc86cfecf")
    private static final VerticalAlignmentEnum DEFAULT_VERTICAL_ALIGNMENT = VerticalAlignmentEnum.MIDDLE;

    @objid ("8dbbde4b-ee06-48d3-9c51-c81a4eb49fcd")
    private static final Image EDIT_ICON = CoreUi.getImageDescriptor("icons/edit.png").createImage();

    @objid ("80860d2d-7f80-4f55-9bc6-e6c052dcb3c3")
    private final PropertyTableDataModel dataModel;

    @objid ("a6d3e1d4-b207-40ab-8a47-73c5957279be")
    private final INatTableViewerContext context;

    @objid ("cf5b3688-bd99-437c-bcfb-867f1071185b")
    private final ILabelProvider elementLabelProvider;

    /**
     * Create a new BodyConfiguration.
     * @param projectService the project service, to access preferences and model session.
     * @param pickingService the picking service, to manually choose elements in the model.
     * @param activationService the activation service, to open external editors.
     * 
     * @param dataModel the table's data model.
     * @param elementLabelProvider a label provider for MObject element
     */
    @objid ("632fda9d-a888-4c35-a89b-e86a36b8db0a")
    public BodyConfiguration(PropertyTableDataModel dataModel, INatTableViewerContext context, ILabelProvider elementLabelProvider) {
        this.context = context;
        this.dataModel = dataModel;
        this.elementLabelProvider = elementLabelProvider;
    }

    @objid ("d2999cea-e961-45dd-b72a-c6ab4bd5beab")
    @Override
    public void configureRegistry(IConfigRegistry configRegistry) {
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITABLE_RULE, IEditableRule.ALWAYS_EDITABLE,
                DisplayMode.EDIT, BodyConfiguration.EDITABLE);
        
        // Declare all standard types
        configureRegistryForBOOLEAN(configRegistry);
        configureRegistryForDATE(configRegistry);
        configureRegistryForELEMENT(configRegistry);
        configureRegistryForFLOAT(configRegistry);
        configureRegistryForHYBRID(configRegistry);
        configureRegistryForINTEGER(configRegistry);
        configureRegistryForMULTIELEMENT(configRegistry);
        configureRegistryForMULTISTRING(configRegistry);
        configureRegistryForSTRING(configRegistry);
        configureRegistryForTEXT(configRegistry);
        configureRegistryForTIME(configRegistry);
        configureRegistryForUNSIGNED(configRegistry);
        configureRegistryForTEXTICON(configRegistry);
        
        // Declare all enumerated types
        final IPropertyModel<?> propertyModel = this.dataModel.getPropertyModel();
        for (int row = 0; row < propertyModel.getRowsNumber(); row++) {
            for (int col = 0; col < propertyModel.getColumnNumber(); col++) {
                final INatValue type = propertyModel.getValueAt(row, col);
                if (type instanceof IElementChoiceNatValue) { // Enum-like
                                                              // custom
                    // declare the custom type in the registry
                    String choiceId = row + "." + col;
                    configureRegistryForELEMENTCHOICE(configRegistry, choiceId,
                            ((IElementChoiceNatValue) type).getValueChoices());
                } else if (type instanceof IStringChoiceNatValue) { // Enum-like
                                                                    // custom
                    // declare the custom type in the registry
                    String choiceId = row + "." + col;
                    configureRegistryForSTRINGCHOICE(configRegistry, choiceId,
                            ((IStringChoiceNatValue) type).getValueChoices(),
                            ((IStringChoiceNatValue) type).isEditable());
                } else if (type instanceof IJavaEnumNatValue) { // Enum type
                    // declare the enum type in the registry
                    configureRegistryForENUMERATE(configRegistry, ((IJavaEnumNatValue) type).getEnumeration());
                } else if (type instanceof IMultiRowElementNatValue) {
                    configureRegistryForMULTIROWELEMENT(configRegistry, ((IMultiRowElementNatValue) type).getTagSuffix());
                }
            }
        }
    }

    @objid ("1c7e8f6e-b7e6-40f0-b78b-af830a75de88")
    @Override
    public void configureUiBindings(UiBindingRegistry uiBindingRegistry) {
        // F2 key enters edition
        uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.NONE, SWT.F2), new KeyEditAction());
        
        // SPACE key enters edition: this is especially useful for changing the
        // value for a checkbox
        uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.NONE, SWT.SPACE), new KeyEditAction());
        
        // any letter or digit key enters edition (upper or lower case)
        uiBindingRegistry.registerKeyBinding(new LetterOrDigitKeyEventMatcher(), new KeyEditAction());
        uiBindingRegistry.registerKeyBinding(new LetterOrDigitKeyEventMatcher(SWT.SHIFT), new KeyEditAction());
        
        // Single click in a TextCellEditor editor enters edition
        uiBindingRegistry.registerSingleClickBinding(new BodyCellEditorMouseEventMatcher(TextCellEditor.class),
                new MouseEditAction());
        
        // Single click in a DateValueEditor editor enters edition
        uiBindingRegistry.registerSingleClickBinding(new BodyCellEditorMouseEventMatcher(DateValueEditor.class),
                new MouseEditAction());
        
        // Single click in a ElementValueEditor editor enters edition
        uiBindingRegistry.registerSingleClickBinding(new BodyCellEditorMouseEventMatcher(ElementNatValueEditor.class),
                new MouseEditAction());
        
        uiBindingRegistry.registerFirstSingleClickBinding(
                new CellPainterMouseEventMatcher(GridRegion.BODY, MouseEventMatcher.LEFT_BUTTON, CheckBoxPainter.class),
                new MouseEditAction());
        
        uiBindingRegistry.registerFirstMouseDragMode(
                new CellPainterMouseEventMatcher(GridRegion.BODY, MouseEventMatcher.LEFT_BUTTON, CheckBoxPainter.class),
                new CellEditDragMode());
        
        // CTRL ALT left click = select in explorer
        uiBindingRegistry.registerFirstSingleClickBinding(
                new CellPainterMouseEventMatcherWithMask(SWT.CTRL | SWT.ALT, GridRegion.BODY, MouseEventMatcher.LEFT_BUTTON, TextIconPainter.class),
                new SelectInExplorerAction(this.context.getNavigationService()));
    }

    /**
     * Boolean field. Used for 'PropertyBaseType.BOOLEAN'
     */
    @objid ("0c746ee2-c6e3-4f51-b49e-539f0b10d714")
    private void configureRegistryForBOOLEAN(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IBooleanNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new CheckBoxPainter(),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new BooleanValueDisplayConverter(), DisplayMode.NORMAL, tag);
        
        // Editor
        final CheckBoxCellEditor checkboxEditor = new CheckBoxCellEditor();
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, checkboxEditor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Date field. Used for 'PropertyBaseType.DATE'
     */
    @objid ("744ec9a5-724a-4a11-89b9-9fb114d7e770")
    private void configureRegistryForDATE(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IDateNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new DateDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        final DateValueEditor editor = new DateValueEditor();
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Element field. Used for 'PropertyBaseType.ELEMENT'
     */
    @objid ("4459595d-c753-4095-92c4-cc30c3c27cf6")
    private void configureRegistryForELEMENT(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IElementNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new ElementPainter(this.elementLabelProvider, false),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new ElementDisplayConverter(this.elementLabelProvider)), DisplayMode.NORMAL, tag);
        
        // Editor
        final ElementNatValueEditor editor = new ElementNatValueEditor(this.context.getSession(), this.context.getPickingService());
        
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    @objid ("fd9e57d5-be1c-4bbc-9bfe-229e097ef669")
    private void configureRegistryForELEMENTCHOICE(IConfigRegistry configRegistry, String choiceId, List<? extends MObject> possibleValues) {
        final String tag = CellTagHelper.getTypeTag(IElementChoiceNatValue.class, choiceId);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new ElementPainter(this.elementLabelProvider, false),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new ElementDisplayConverter(this.elementLabelProvider)), DisplayMode.NORMAL, tag);
        
        // Editor
        final ComboBoxCellEditor comboEditor = new ComboBoxCellEditor(possibleValues) {
            @Override
            public void setCanonicalValue(Object canonicalValue) {
                if (canonicalValue != null) {
                    String[] editorValues = null;
                    if (canonicalValue instanceof List<?>) {
                        List<?> temp = (List<?>) canonicalValue;
                        String[] result = new String[temp.size()];
                        for (int i = 0; i < temp.size(); i++) {
                            result[i] = this.displayConverter
                                    .canonicalToDisplayValue(this.layerCell, this.configRegistry, temp.get(i))
                                    .toString();
                        }
                        editorValues = result;
                    } else {
                        editorValues = new String[] { this.displayConverter
                                .canonicalToDisplayValue(this.layerCell, this.configRegistry, canonicalValue)
                                .toString() };
                    }
                    setEditorValue(editorValues);
                }
            }
        };
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, comboEditor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Enum field. Used for 'PropertyBaseType.ENUMERATE'
     */
    @objid ("cf461264-ae64-4e42-b152-8146fb83b507")
    private void configureRegistryForENUMERATE(IConfigRegistry configRegistry, Class<?> enumeratedPropertyType) {
        final String tag = CellTagHelper.getTypeTag(IJavaEnumNatValue.class, enumeratedPropertyType.getName());
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new ComboBoxPainter(),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new JavaEnumNatValueDisplayConverter(enumeratedPropertyType) {
                    @Override
                    public Object canonicalToDisplayValue(Object obj) {
                        if (obj == null) {
                            return "";
                        }
        
                        final Enum<?> l;
                        if (obj instanceof Enum) {
                            l = (Enum<?>) obj;
                        } else {
                            return obj;
                        }
                        return MetamodelLabels.getString(l.toString());
                    }
                }), DisplayMode.NORMAL, tag);
        
        // Editor
        final ComboBoxCellEditor comboEditor = new ComboBoxCellEditor(
                Arrays.asList(enumeratedPropertyType.getEnumConstants()));
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, comboEditor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Float field. Used for 'PropertyBaseType.FLOAT'
     */
    @objid ("f534631b-0a3b-4856-b9ad-9b3cf173b458")
    private void configureRegistryForFLOAT(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IFloatNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new DefaultDoubleDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        
        // Validator
        final DataValidator validator = new DataValidator() {
            @Override
            public boolean validate(int columnIndex, int rowIndex, Object newValue) {
                try {
                    Float.parseFloat(Objects.toString(newValue));
                    return true;
                } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                    throw new ValidationFailedException("Value must be an float");
                }
            }
        };
        configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, validator, DisplayMode.EDIT, tag);
    }

    @objid ("8fe4939a-4295-4155-b839-c35379501d35")
    private void configureRegistryForHYBRID(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IHybridNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new TextIconPainter(false),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new TextIconConverter(this.elementLabelProvider)), DisplayMode.NORMAL, tag);
        
        // Editor
        final HybridNatValueEditor editor = new HybridNatValueEditor(this.context.getSession(), this.context.getPickingService());
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Integer field. Used for 'PropertyBaseType.INTEGER'
     */
    @objid ("aa07cb1c-5e8c-4c86-a62b-3324f97b1884")
    private void configureRegistryForINTEGER(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IIntegerNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new DefaultIntegerDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        
        // Validator
        final DataValidator validator = new DataValidator() {
            @Override
            public boolean validate(int columnIndex, int rowIndex, Object newValue) {
                try {
                    Integer.decode(Objects.toString(newValue));
                    return true;
                } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                    throw new ValidationFailedException("Value must be an Integer");
                }
            }
        };
        configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, validator, DisplayMode.EDIT, tag);
    }

    /**
     * Multi-Element field. Used for 'PropertyBaseType.MULTIELEMENT'
     */
    @objid ("125b5334-4b76-4188-972b-30ed148f8851")
    private void configureRegistryForMULTIELEMENT(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IMultiElementNatValue.class);
        
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new MultiElementPainter(false),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new MultiElementDisplayConverter(this.elementLabelProvider)), DisplayMode.NORMAL, tag);
        
        // Editor
        final MultiElementValueEditor editor = new MultiElementValueEditor(this.context.getSession());
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Editor's i18n
        Map<String, Object> editDialogSettings = new HashMap<>();
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_TITLE, CoreUi.I18N.getString("MultiElementEditionDialog.Title"));
        editDialogSettings.put(ICellEditDialog.DIALOG_MESSAGE, CoreUi.I18N.getString("MultiElementEditionDialog.Message"));
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_ICON, BodyConfiguration.EDIT_ICON);
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_RESIZABLE, Boolean.TRUE);
        
        configRegistry.registerConfigAttribute(EditConfigAttributes.EDIT_DIALOG_SETTINGS, editDialogSettings,
                DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Text on one line. Used for 'PropertyBaseType.MULTISTRING'
     */
    @objid ("c59d87e6-43b1-45ab-9c5e-90961050f697")
    private void configureRegistryForMULTISTRING(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IMultiStringNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new MultiStringDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        final MultiStringValueEditor editor = new MultiStringValueEditor();
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Editor's i18n
        Map<String, Object> editDialogSettings = new HashMap<>();
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_TITLE,
                CoreUi.I18N.getString("MultiStringEditionDialog.Title"));
        editDialogSettings.put(ICellEditDialog.DIALOG_MESSAGE,
                CoreUi.I18N.getString("MultiStringEditionDialog.Message"));
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_ICON, BodyConfiguration.EDIT_ICON);
        
        configRegistry.registerConfigAttribute(EditConfigAttributes.EDIT_DIALOG_SETTINGS, editDialogSettings,
                DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Text on one line. Used for 'PropertyBaseType.STRING'
     */
    @objid ("9d951366-b361-4af1-ae61-6be78646e6bf")
    private void configureRegistryForSTRING(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IStringNatValue.class);
        
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new DefaultStringDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        final TextCellEditor textCellEditor = new TextCellEditor();
        textCellEditor.setDecorationPositionOverride(SWT.LEFT | SWT.TOP);
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, textCellEditor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    @objid ("fb5201a7-f5b2-46d7-8a6a-14e1828a80e7")
    private void configureRegistryForSTRINGCHOICE(IConfigRegistry configRegistry, String choiceId, List<?> possibleValues, boolean isEditable) {
        final String tag = CellTagHelper.getTypeTag(IStringChoiceNatValue.class, choiceId);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new ComboBoxPainter(),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new DefaultDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        final ComboBoxCellEditor comboEditor = new ComboBoxCellEditor(possibleValues);
        comboEditor.setFreeEdit(isEditable);
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, comboEditor, DisplayMode.EDIT, tag);
    }

    /**
     * Multiline Text. Used for 'PropertyBaseType.TEXT'
     */
    @objid ("e40d88fd-7e49-41d5-b2ab-234d251f6183")
    private void configureRegistryForTEXT(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(ITextNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new DefaultStringDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        final MultiLineTextCellEditor editor = new MultiLineTextCellEditor();
        editor.setDecorationPositionOverride(SWT.LEFT | SWT.TOP);
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        configRegistry.registerConfigAttribute(EditConfigAttributes.OPEN_IN_DIALOG, true, DisplayMode.EDIT, tag);
        
        // FIXME Editor's i18n
        Map<String, Object> editDialogSettings = new HashMap<>();
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_TITLE,
                CoreUi.I18N.getString("TextEditionDialog.Title"));
        editDialogSettings.put(ICellEditDialog.DIALOG_MESSAGE,
                CoreUi.I18N.getString("TextEditionDialog.Message"));
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_ICON, BodyConfiguration.EDIT_ICON);
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_SIZE, new Point(400, 400));
        editDialogSettings.put(ICellEditDialog.DIALOG_SHELL_RESIZABLE, Boolean.TRUE);
        
        configRegistry.registerConfigAttribute(EditConfigAttributes.EDIT_DIALOG_SETTINGS, editDialogSettings,
                DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Time field. Used for 'PropertyBaseType.Time'
     */
    @objid ("58a28526-9b4f-4c9d-a4d4-1eefe1a56ffe")
    private void configureRegistryForTIME(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(ITimeNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new TimeDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        final TimeValueEditor editor = new TimeValueEditor();
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * unsigned Integer field. Used for 'PropertyBaseType.UNSIGNED'
     */
    @objid ("6540d826-0da1-45e2-92df-ddf396ad6713")
    private void configureRegistryForUNSIGNED(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(IUnsignedNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new DefaultIntegerDisplayConverter()), DisplayMode.NORMAL, tag);
        
        // Editor
        
        // Validator
        final DataValidator validator = new DataValidator() {
            @Override
            public boolean validate(int columnIndex, int rowIndex, Object newValue) {
                try {
                    final Integer i = Integer.decode(Objects.toString(newValue));
                    if (i < 0) {
                        throw new ValidationFailedException("Value must be an positive");
                    }
                    return true;
                } catch (@SuppressWarnings ("unused") final NumberFormatException e) {
                    throw new ValidationFailedException("Value must be an Integer");
                }
            }
        };
        configRegistry.registerConfigAttribute(EditConfigAttributes.DATA_VALIDATOR, validator, DisplayMode.EDIT, tag);
    }

    @objid ("eee8cb6a-9592-47ba-ad2c-1e700bc884ef")
    private void configureRegistryForTEXTICON(IConfigRegistry configRegistry) {
        final String tag = CellTagHelper.getTypeTag(ITextIconNatValue.class);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new TextIconPainter(false),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new TextIconConverter(this.elementLabelProvider)), DisplayMode.NORMAL, tag);
        
        // Editor
        // final ElementNatValueEditor editor = new ElementNatValueEditor(this.context.getSession(), this.context.getPickingService());
        // configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Validator
    }

    /**
     * Element field. Used for 'PropertyBaseType.ELEMENT'
     */
    @objid ("02d586a2-02ca-4ae2-a5f5-07fc105ab5c1")
    private void configureRegistryForMULTIROWELEMENT(IConfigRegistry configRegistry, String tagSuffix) {
        final String tag = CellTagHelper.getTypeTag(IMultiRowElementNatValue.class, tagSuffix);
        // Style and painter
        Style cellStyle = new Style();
        cellStyle.setAttributeValue(CellStyleAttributes.HORIZONTAL_ALIGNMENT, HorizontalAlignmentEnum.LEFT);
        cellStyle.setAttributeValue(CellStyleAttributes.VERTICAL_ALIGNMENT, BodyConfiguration.DEFAULT_VERTICAL_ALIGNMENT);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_STYLE, cellStyle, DisplayMode.NORMAL, tag);
        configRegistry.registerConfigAttribute(CellConfigAttributes.CELL_PAINTER, new ElementPainter(this.elementLabelProvider, false),
                DisplayMode.NORMAL, tag);
        
        // Display converter
        configRegistry.registerConfigAttribute(CellConfigAttributes.DISPLAY_CONVERTER,
                new NatValueWrappingDisplayConverter(new ElementDisplayConverter(this.elementLabelProvider)), DisplayMode.NORMAL, tag);
        
        // Editor
        final MultiRowElementNatValueEditor editor = new MultiRowElementNatValueEditor(this.context.getSession());
        
        configRegistry.registerConfigAttribute(EditConfigAttributes.CELL_EDITOR, editor, DisplayMode.EDIT, tag);
        
        // Validator
    }

}
