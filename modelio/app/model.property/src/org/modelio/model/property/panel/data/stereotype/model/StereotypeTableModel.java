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

package org.modelio.model.property.panel.data.stereotype.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.core.ui.nattable.parts.data.INatValue;
import org.modelio.core.ui.nattable.parts.data.bool.DefaultBooleanNatValue;
import org.modelio.core.ui.nattable.parts.data.string.multi.DefaultMultiStringNatValue;
import org.modelio.core.ui.nattable.parts.data.string.single.DefaultStringNatValue;
import org.modelio.core.ui.nattable.viewer.config.body.PropertyAdapter;
import org.modelio.core.ui.nattable.viewer.model.AbstractPropertyModel;
import org.modelio.mda.infra.ModuleI18NService;
import org.modelio.metamodel.mda.ModuleComponent;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.mmextensions.infrastructure.IInfrastructureModelFactory;
import org.modelio.metamodel.mmextensions.standard.services.IMModelServices;
import org.modelio.metamodel.uml.infrastructure.MetaclassReference;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.model.property.plugin.ModelProperty;
import org.modelio.vcore.model.api.MTools;
import org.modelio.vcore.session.api.ICoreSession;
import org.modelio.vcore.session.impl.CoreSession;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MetaclassNotFoundException;

/**
 * Property model displaying {@link TagType} & {@link PropertyDefinition} brought by a {@link Stereotype} or a {@link ModuleComponent} for the edited element.
 * <p>
 * Rows are computed dynamically from the content of the {@link Stereotype} or {@link ModuleComponent}. Each {@link TagType} is displayed as a row, its cardinality being interpreted as the cell's type (0 = Boolean, 1 = String, * = List<String). Each
 * {@link PropertyDefinition} is displayed as a row, the property type being interpreted as the cell's type.
 * </p>
 */
@objid ("b96f5a47-4c38-4a15-a6d0-09b479a92fbc")
public class StereotypeTableModel extends AbstractPropertyModel<ModelElement> {
    @objid ("95d67493-9876-42be-bcf8-0910eea91611")
    private static final String TAG_MARKER = "{} ";

    @objid ("528257db-1c6b-48f6-9875-6084ac26a188")
    private PropertyTableHelper tableModel;

    @objid ("e8c60005-d720-4adf-b52d-026bf8971f6c")
    private TagDataHelper tagModel;

    /**
     * Create a new property model for a Stereotype.
     * 
     * @param editedElement the element currently edited in the Element's view.
     * @param stereotype the stereotype to load the {@link TagType} & {@link PropertyDefinition} list from.
     * @param modelService the model service needed to find TagTypes.
     * @param showHiddenAnnotations whether or not to show 'hidden' TagTypes.
     */
    @objid ("1c3f1397-e9ee-4108-a3ab-3a427e4c46c3")
    public StereotypeTableModel(ModelElement editedElement, Stereotype stereotype, IMModelServices modelService, boolean showHiddenAnnotations) {
        super(editedElement);
        this.tagModel = new TagDataHelper(editedElement, stereotype, modelService, showHiddenAnnotations);
        this.tableModel = new PropertyTableHelper(editedElement, stereotype.getModule(), stereotype, CoreSession.getSession(editedElement));
    }

    /**
     * Create a new property model for a ModuleComponent.
     * 
     * @param editedElement the element currently edited in the Element's view.
     * @param module the module to load the {@link TagType} & {@link PropertyDefinition} list from.
     * @param modelService the model service needed to find TagTypes.
     * @param showHiddenAnnotations whether or not to show 'hidden' TagTypes.
     */
    @objid ("c90dbb6b-5a0e-4011-9b48-88518d0adc1f")
    public StereotypeTableModel(ModelElement editedElement, ModuleComponent module, IMModelServices modelService, boolean showHiddenAnnotations) {
        super(editedElement);
        this.tagModel = new TagDataHelper(editedElement, module, modelService, showHiddenAnnotations);
        this.tableModel = new PropertyTableHelper(editedElement, module, null, CoreSession.getSession(editedElement));
    }

    @objid ("873ab9ad-02d3-4703-8064-c9d124542bec")
    @Override
    public int getColumnNumber() {
        return 2;
    }

    @objid ("5e920ead-99c4-4d01-ae9d-7facc38fc7b4")
    @Override
    public int getRowsNumber() {
        return this.tagModel.getTagTypes().size() + this.tableModel.getProperties().size() + 1;
    }

    @objid ("9ee92f73-9092-4c87-9fc5-89c1f7d92f2d")
    private Object getValue(int row, int col) {
        if (col == 0) {
            if (row == 0) {
                // Corner
                return getPropertyI18n(AbstractPropertyModel.PROPERTY_ID);
            } else if (isTaggedValue(row)) {
                TagType type = this.tagModel.getTagTypes().get(row - 1);
                String label = ModuleI18NService.getLabel(type);
                if (label == null || label.isEmpty()) {
                    label = "!" + type.getName() + "!";
                }
        
                if (type.isIsHidden()) {
                    final String hiddenString = ModelProperty.I18N.getString("AnnotationView.PropertyPanel.Hidden");
                    return StereotypeTableModel.TAG_MARKER + label + " " + hiddenString;
                } else {
                    return StereotypeTableModel.TAG_MARKER + label;
                }
            } else if (isProperty(row)) {
                // Row Header
                final PropertyDefinition pdef = this.tableModel.getProperties().get(row - this.tagModel.getTagTypes().size() - 1);
                String label = ModuleI18NService.getLabel(pdef);
                if (label == null || label.isEmpty()) {
                    label = "!" + pdef.getName() + "!";
                }
        
                return label;
            } else {
                throw new IllegalArgumentException("Invalid row number");
            }
        } else {
            if (row == 0) {
                // Col Header
                return getPropertyI18n(AbstractPropertyModel.VALUE_ID);
            } else if (isTaggedValue(row)) {
                return this.tagModel.getPropertyValue(row - 1);
            } else if (isProperty(row)) {
                // Value
                final PropertyDefinition pdef = this.tableModel.getProperties().get(row - this.tagModel.getTagTypes().size() - 1);
        
                // Get property table
                try {
                    PropertyTableDefinition propTableDef = pdef.getOwner();
        
                    String storedValue;
                    if (propTableDef.getOwnerStereotype() != null) {
                        storedValue = this.theEditedElement.getProperty(propTableDef.getOwnerStereotype(), pdef.getName());
                    } else if (propTableDef.getOwnerReference() != null) {
                        storedValue = this.theEditedElement.getProperty(propTableDef.getOwnerReference(), pdef.getName());
                    } else {
                        storedValue = null;
                    }
        
                    if (storedValue == null) {
                        storedValue = pdef.getDefaultValue();
                    }
        
                    return pdef.convertToObject(storedValue, this.theEditedElement);
                } catch (ExtensionNotFoundException e) {
                    throw new IllegalArgumentException(String.format("Invalid %s PropertyDefinition", pdef), e);
                }
        
            } else {
                throw new IllegalArgumentException("Invalid row number");
            }
        }
    }

    @objid ("a1a3ccea-76a9-4eff-8672-6b6d4e824542")
    @Override
    public void setValueAt(int row, int col, Object value) {
        if (col == 0) {
            // RowHeader
            throw new IllegalArgumentException("Header is not editable");
        } else {
            if (row == 0) {
                // Col Header
                throw new IllegalArgumentException("Header is not editable");
            } else if (isTaggedValue(row)) {
                this.tagModel.setPropertyValue(row - 1, value);
            } else if (isProperty(row)) {
                // Value
                final PropertyDefinition pdef = this.tableModel.getProperties().get(row - this.tagModel.getTagTypes().size() - 1);
                final String svalue = pdef.convertToString(value, this.theEditedElement);
                final PropertyTableDefinition propTableDef = pdef.getOwner();
        
                try {
                    if (propTableDef.getOwnerStereotype() != null) {
                        this.theEditedElement.setProperty(propTableDef.getOwnerStereotype(), pdef.getName(), svalue);
                    } else if (propTableDef.getOwnerReference() != null) {
                        this.theEditedElement.setProperty(propTableDef.getOwnerReference(), pdef.getName(), svalue);
                    } else {
                        throw new IllegalArgumentException("Invalid PropertyTableDefinition, it should belong to a Stereotype or a MetaclassReference");
                    }
                } catch (ExtensionNotFoundException e) {
                    throw new IllegalArgumentException(String.format("Invalid %s PropertyDefinition", pdef), e);
                }
            }
        }
    }

    @objid ("2c996070-429f-462b-ae94-916b5ad10fd8")
    @Override
    public boolean isEditable(int row, int col) {
        if (col == 0) {
            return false;
        }
        
        if (!this.theEditedElement.isModifiable()) {
            return false;
        }
        
        if (isProperty(row)) {
            final PropertyDefinition pdef = this.tableModel.getProperties().get(row - this.tagModel.getTagTypes().size() - 1);
            return pdef.isIsEditable();
        }
        return true;
    }

    @objid ("9498632b-5414-4921-bdf4-ad38a5bd6dcf")
    @Override
    public INatValue getValueAt(int row, int col) {
        if (col == 0) {
            if (row == 0) {
                // Corner
                return new DefaultStringNatValue((String) getValue(row, col), false);
            } else {
                // Row Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            }
        } else {
            if (row == 0) {
                // Col Header
                return new DefaultStringNatValue((String) getValue(row, col), false);
            } else if (isTaggedValue(row)) {
                return this.tagModel.getPropertyType(row - 1);
            } else if (isProperty(row)) {
                // Value
                final PropertyDefinition pdef = this.tableModel.getProperties().get(row - this.tagModel.getTagTypes().size() - 1);
                return PropertyAdapter.getNatValue(pdef, getValue(row, col));
            } else {
                throw new IllegalArgumentException("Invalid row number");
            }
        }
    }

    /**
     * @return whether or not this row corresponds to a Property edition.
     */
    @objid ("041f580f-476a-423a-8b9b-6d065db442e4")
    private boolean isProperty(int row) {
        return row > this.tagModel.getTagTypes().size();
    }

    /**
     * @return whether or not this row corresponds to a TaggedValue edition.
     */
    @objid ("c8c18a25-ff0f-4e31-a196-4bcc03c7a895")
    private boolean isTaggedValue(int row) {
        return row > 0 && row <= this.tagModel.getTagTypes().size();
    }

    @objid ("b921142f-1e19-4fe9-95ea-97f1ecebc18f")
    private static class PropertyTableHelper {
        @objid ("a3ce8075-8a2f-4437-b2fe-4dc6ea4bd603")
        private ModuleComponent module;

        @objid ("7b8a13c5-9f55-460b-aaec-748038be524b")
        private List<PropertyDefinition> properties = new ArrayList<>();

        @objid ("099d0201-281c-4b97-b89a-d5f90e5289db")
        private ICoreSession session;

        @objid ("5e306554-40db-4ab3-a51f-abd90df212dd")
        private PropertyTableHelper(ModelElement typedElement, ModuleComponent module, Stereotype stereotype, ICoreSession session) {
            this.module = module;
            this.session = session;
            
            if (stereotype != null) {
                this.properties.addAll(getModulePropertyTablesForStereotype(stereotype));
            } else {
                this.properties.addAll(getModulePropertyTablesForMetaclass(typedElement.getMClass()));
            }
        }

        @objid ("ecbff81c-1528-4e18-9302-2a6d07174ed4")
        private List<PropertyDefinition> getModulePropertyTablesForMetaclass(final MClass metaclass) {
            // Compute the properties that are defined by 'module' and applicable on 'element'
            List<PropertyDefinition> ret = new ArrayList<>();
            
            if (this.module != null) {
                for (Profile profile : this.module.getOwnedProfile()) {
                    for (MetaclassReference reference : profile.getOwnedReference()) {
                        try {
                            MClass referenceClass = getBaseClass(reference.getReferencedClassName());
                            if (metaclass.hasBase(referenceClass)) {
                                PropertyTableDefinition table = reference.getDefinedTable();
                                if (table != null) {
                                    ret.addAll(table.getOwned());
                                }
                            }
                        } catch (@SuppressWarnings ("unused") MetaclassNotFoundException e) {
                            // Ignore bad metaclass
                        }
                    }
                }
            }
            return ret;
        }

        @objid ("6587021a-46e2-408a-b571-fcf921b8df43")
        private List<PropertyDefinition> getModulePropertyTablesForStereotype(Stereotype stereotype) {
            List<PropertyDefinition> result = new ArrayList<>();
            
            PropertyTableDefinition propertyTable = stereotype.getDefinedTable();
            if (propertyTable != null) {
                result.addAll(propertyTable.getOwned());
            }
            
            for (Stereotype supperSter : getSuperStereotype(stereotype)) {
                PropertyTableDefinition superTable = supperSter.getDefinedTable();
                if (superTable != null) {
                    result.addAll(superTable.getOwned());
                }
            }
            return result;
        }

        @objid ("2f7cf840-6d4d-46ce-9dbc-45de4837fda7")
        private MClass getBaseClass(String baseName) throws MetaclassNotFoundException {
            MClass smBase = this.session.getMetamodel().getMClass(baseName);
            
            if (smBase == null) {
                throw new MetaclassNotFoundException(baseName);
            }
            return smBase;
        }

        @objid ("94680c1e-5cf7-4d68-bbe8-95cc3cda6236")
        private ModuleComponent getModule() {
            return this.module;
        }

        @objid ("fa48fa98-e0b7-473c-84c9-e196eefbd06a")
        private List<PropertyDefinition> getProperties() {
            return this.properties;
        }

        @objid ("f57b9f87-f06b-4c87-b18e-c073703cf76a")
        private List<Stereotype> getSuperStereotype(Stereotype s) {
            List<Stereotype> result = new ArrayList<>();
            
            Stereotype current = s;
            while (current.getParent() != null) {
                current = current.getParent();
                result.add(current);
            }
            return result;
        }

    }

    @objid ("b2761d0f-5d6e-4954-9dc3-68f5682d728a")
    private static class TagDataHelper {
        @objid ("85d56cf7-0a76-4741-b6ee-cea147b7ae42")
        private boolean showHiddenAnnotations;

        @objid ("075229fb-f08a-4423-aef5-c2e9fe1c360e")
        private IMModelServices modelService;

        @objid ("9f7bc83a-5d74-4ae5-9524-9562800063a7")
        private List<TagType> tagTypes = null;

        @objid ("e24b3c85-37f4-4cda-aa74-71e8f53a8c10")
        private ModelElement typedElement;

        @objid ("a3b4a960-2700-4e28-82e7-a72666a089bb")
        private TagDataHelper(ModelElement typedElement, ModuleComponent typingElement, IMModelServices modelService, boolean showHiddenAnnotations) {
            this(typedElement, modelService, showHiddenAnnotations);
            
            // Compute the tag type list
            if (typingElement == null) {
                this.tagTypes = Collections.emptyList();
            } else {
                this.tagTypes = getModuleTagTypesForMetaclass(typingElement, this.typedElement.getMClass());
            }
        }

        @objid ("840e45c6-381d-46b4-b7b0-72ae7d725d8d")
        private TagDataHelper(ModelElement typedElement, IMModelServices modelService, boolean showHiddenAnnotations) {
            this.typedElement = typedElement;
            this.modelService = modelService;
            this.showHiddenAnnotations = showHiddenAnnotations;
        }

        @objid ("21621774-5128-44fe-aeda-2135d76eb87c")
        private TagDataHelper(ModelElement typedElement, Stereotype typingElement, IMModelServices modelService, boolean showHiddenAnnotations) {
            this(typedElement, modelService, showHiddenAnnotations);
            
            // Compute the tag type list
            if (typingElement == null) {
                this.tagTypes = Collections.emptyList();
            } else {
                Stereotype s = typingElement;
                this.tagTypes = new ArrayList<>();
                while (s != null) {
                    List<TagType> currentLevelTypes = new ArrayList<>();
                    for (TagType tagType : s.getDefinedTagType()) {
                        if (displayTagType(tagType)) {
                            currentLevelTypes.add(tagType);
                        }
                    }
                    // Put tag types from parent stereotypes first...
                    this.tagTypes.addAll(0, currentLevelTypes);
                    s = s.getParent();
                }
            }
        }

        @objid ("74b2ebbc-761d-4967-bf66-4a733d29617b")
        private boolean displayTagType(TagType tagType) {
            return isShowHiddenAnnotations() || !tagType.isIsHidden();
        }

        @objid ("94517602-0221-40b8-912f-48cccaf23646")
        private List<TagType> getModuleTagTypesForMetaclass(final ModuleComponent module, final MClass metaclass) {
            // Compute the tag types that are defined by 'module' and applicable
            // on 'element'
            List<TagType> ret = new ArrayList<>();
            
            if (module != null) {
                for (TagType tagType : this.modelService.findTagTypes(module.getName(), ".*", ".*", metaclass)) {
                    if (tagType.getOwnerStereotype() == null && displayTagType(tagType)) {
                        ret.add(tagType);
                    }
                }
            }
            return ret;
        }

        @objid ("250c1d03-5196-48d1-a8cb-0d04a00ab301")
        private int getParamNumber(final TagType type) {
            int paramNumber;
            try {
                paramNumber = Integer.parseInt(type.getParamNumber());
            } catch (@SuppressWarnings ("unused") NumberFormatException e) {
                paramNumber = -1;
            }
            return paramNumber;
        }

        @objid ("0cdbb9cc-e20f-4f6f-a1af-19794e427254")
        @SuppressWarnings ("unchecked")
        private INatValue getPropertyType(final int index) {
            TagType tagType = this.tagTypes.get(index);
            int paramNumber = getParamNumber(tagType);
            if (paramNumber == 0) {
                return new DefaultBooleanNatValue((Boolean) getPropertyValue(index));
            } else if (paramNumber == 1) {
                return new DefaultStringNatValue((String) getPropertyValue(index), false);
            } else {
                return new DefaultMultiStringNatValue((List<String>) getPropertyValue(index), false);
            }
        }

        @objid ("e7c014b9-ecd8-4978-8906-7793ddcd2992")
        private Object getPropertyValue(final int index) {
            TagType tagType = this.tagTypes.get(index);
            int paramNumber = getParamNumber(tagType);
            
            TaggedValue taggedValue = null;
            for (TaggedValue v : this.typedElement.getTag()) {
                if (v.getDefinition().equals(tagType)) {
                    taggedValue = v;
                    break;
                }
            }
            
            switch (paramNumber) {
            case 0:
                return Boolean.valueOf(taggedValue != null);
            case 1:
                if (taggedValue != null) {
                    List<TagParameter> parameters = taggedValue.getActual();
                    if (parameters.isEmpty()) {
                        return "";
                    } else {
                        return parameters.get(0).getValue();
                    }
                } else {
                    return "";
                }
            
            default:
                if (taggedValue != null) {
                    List<TagParameter> parameters = taggedValue.getActual();
                    List<String> values = new ArrayList<>();
                    for (TagParameter parameter : parameters) {
                        values.add(parameter.getValue());
                    }
                    return values;
                } else {
                    return new ArrayList<>();
                }
            }
        }

        /**
         * This operation returns the tagged value with the corresponding type.
         * 
         * @param element IModelElement on which the tagged value is search for.
         * @param type The tagged value type name
         * @return The tag or null if it can't be found
         */
        @objid ("5f1f0551-353a-4b50-86fc-b0b81afc8ea0")
        private TaggedValue getTag(ModelElement element, TagType type) {
            for (TaggedValue currentTag : element.getTag()) {
                TagType tagDef = currentTag.getDefinition();
                if (tagDef != null && tagDef == type) {
                    return currentTag;
                }
            }
            return null;
        }

        @objid ("3f2a968b-20b2-44cd-9cf9-0bd86d12b25a")
        private List<TagType> getTagTypes() {
            return this.tagTypes;
        }

        @objid ("01d26cf0-0ce0-4a12-90a2-d93ed931f6a7")
        private boolean isShowHiddenAnnotations() {
            return this.showHiddenAnnotations;
        }

        /**
         * This operation sets the parameters of the tagged value with the given type on the &lt;element&gt; IModelElement.<br/>
         * The tagged value and the parameter are created if they don't exist.<br/>
         * If values is <tt>null</tt> or empty the existing tag is deleted.
         * 
         * @param element IModelElement on which the tagged value is created or updated.
         * @param tagType The tagged value type name.
         * @param value The values to store on the first tag parameter. If values is <tt>null</tt> the tag is deleted.
         */
        @objid ("607af210-575d-4b6f-b927-2f20c7e5e3b6")
        private void putTagValue(ModelElement element, TagType tagType, String value) {
            IInfrastructureModelFactory factory = MTools.get(element).getModelFactory(IInfrastructureModelFactory.class);
            
            TaggedValue tag = getTag(element, tagType);
            
            if (value == null) {
                // Delete the tag if no more value
                if (tag != null) {
                    tag.delete();
                }
                return;
            } else {
                // Create the tagged value if necessary
                if (tag == null) {
                    tag = factory.createTaggedValue(tagType, element);
                }
            
                final List<TagParameter> oldParameters = tag.getActual();
                int cpt = 0;
            
                // Replace existing parameter values and delete spare ones
                for (int i = 0; i < oldParameters.size() && i < 1; i++) {
                    oldParameters.get(i).setValue(value);
                    cpt++;
                }
            
                // Delete spare parameter
                while (oldParameters.size() > 1) {
                    oldParameters.get(oldParameters.size() - 1).delete();
                }
            
                // Add missing parameter
                if (cpt < 1) {
                    factory.createTagParameter(value, tag);
                    cpt++;
                }
            }
        }

        /**
         * This operation sets the parameters of the tagged value with the given type on the &lt;element&gt; IModelElement.<br/>
         * The tagged value and the parameter are created if they don't exist.<br/>
         * If values is <tt>null</tt> or empty the existing tag is deleted.
         * 
         * @param element IModelElement on which the tagged value is created or updated.
         * @param type The tagged value type name.
         * @param values The values to store on the tag parameters. If values is <tt>null</tt> or empty the tag is deleted.
         */
        @objid ("4d085e53-0d9c-4d8f-b9da-5c001efd0893")
        private void putTagValues(ModelElement element, TagType type, List<String> values) {
            IInfrastructureModelFactory factory = MTools.get(element).getModelFactory(IInfrastructureModelFactory.class);
            
            TaggedValue tag = getTag(element, type);
            
            if (values == null || values.isEmpty()) {
                // Delete the tag if no more value
                if (tag != null) {
                    tag.delete();
                }
                return;
            } else {
                // Create the tagged value if necessary
                if (tag == null) {
                    tag = factory.createTaggedValue(type, element);
                }
            
                final int newSize = values.size();
                final List<TagParameter> oldParameters = tag.getActual();
                int cpt = 0;
            
                // Replace existing parameter values and delete spare ones
                for (int i = 0; i < oldParameters.size() && i < newSize; i++) {
                    oldParameters.get(i).setValue(values.get(i));
                    cpt++;
                }
            
                // Delete spare parameter
                while (oldParameters.size() > newSize) {
                    oldParameters.get(oldParameters.size() - 1).delete();
                }
            
                // Add missing parameters
                while (cpt < newSize) {
                    factory.createTagParameter(values.get(cpt), tag);
                    cpt++;
                }
            }
        }

        /**
         * This operation deletes the tagged value having this type from the given element.
         * 
         * @param element IModelElement on which the tagged value is removed.
         * @param type The tagged value type name
         */
        @objid ("bd2b5831-8437-4b02-af2e-614e42124cc2")
        private void removeTag(ModelElement element, TagType type) {
            TaggedValue tag = getTag(element, type);
            if (tag != null) {
                tag.delete();
            }
        }

        @objid ("5322c6c9-d3f9-45ae-899a-a2bed4cac7ed")
        @SuppressWarnings ("unchecked")
        private void setPropertyValue(final int index, final Object value) {
            TagType tagType = this.tagTypes.get(index);
            
            int paramNumber = getParamNumber(tagType);
            
            if (paramNumber == 0) { // Boolean type
                updateBooleanTaggedValue(this.typedElement, tagType, (Boolean) value);
            } else if (paramNumber == 1) {
                updateStringTaggedValue(this.typedElement, tagType, (String) value);
            } else {
                // paramNumber > 1 and paramNumber == -1 (param number no limit)
                updateStringListTaggedValue(this.typedElement, tagType, (List<String>) value);
            }
        }

        @objid ("f2806e1a-f85d-48e6-a8b5-ae7e0c03cc49")
        private void updateBooleanTaggedValue(final ModelElement element, final TagType tagType, final Boolean value) {
            if (Boolean.TRUE.equals(value)) {
                for (TaggedValue tag : element.getTag()) {
                    if (tag.getDefinition() == tagType) {
                        return;
                    }
                }
                MTools.get(element).getModelFactory(IInfrastructureModelFactory.class).createTaggedValue(tagType, element);
            } else {
                removeTag(element, tagType);
            }
        }

        @objid ("932207af-6ce5-42b1-b5bc-927579e2319a")
        private void updateStringListTaggedValue(final ModelElement element, final TagType tagType, final List<String> values) {
            try {
                putTagValues(element, tagType, values.isEmpty() ? null : values);
            } catch (Exception e) {
                ModelProperty.LOG.error(e);
            }
        }

        @objid ("de0f7775-450e-4b69-b9d5-39c005fd8e30")
        private void updateStringTaggedValue(final ModelElement element, final TagType tagType, final String value) {
            try {
                putTagValue(element, tagType, "".equals(value) ? null : value);
            } catch (Exception e) {
                ModelProperty.LOG.error(e);
            }
        }

    }

}
