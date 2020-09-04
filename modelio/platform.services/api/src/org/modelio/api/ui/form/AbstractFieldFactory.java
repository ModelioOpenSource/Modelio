/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.api.ui.form.fields.BooleanField;
import org.modelio.api.ui.form.fields.DateField;
import org.modelio.api.ui.form.fields.ElementField;
import org.modelio.api.ui.form.fields.EnumField;
import org.modelio.api.ui.form.fields.IField;
import org.modelio.api.ui.form.fields.RichTextField;
import org.modelio.api.ui.form.fields.StringField;
import org.modelio.api.ui.form.fields.TextField;
import org.modelio.api.ui.form.fields.TimeField;
import org.modelio.api.ui.form.models.IFormFieldData;
import org.modelio.api.ui.form.models.MAttributeFieldData;
import org.modelio.api.ui.form.models.PropertyFieldData;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyType;
import org.modelio.vcore.smkernel.mapi.MAttribute;

/**
 * Base implementation of {@link IFieldFactory}.
 * <p>
 * Subclass it and implement {@link #createFields(FormToolkit, Composite, ModelElement, FormFieldPage)}
 * by using createXxxx() methods implemented here.
 */
@objid ("1a17e670-2bb1-4e93-8d26-49d6d852e4f1")
public abstract class AbstractFieldFactory implements IFieldFactory {
    @objid ("6e8ac4c2-a773-4f6d-b6df-144330c47fd4")
    private final IModuleContext moduleContext;

    /**
     * Initialize the field factory.
     * @param moduleContext The module context
     * @since 3.8 : a module context is now needed
     */
    @objid ("87fdbd9b-17a3-4f7f-9dce-7a2c03c2f1c8")
    public AbstractFieldFactory(IModuleContext moduleContext) {
        this.moduleContext = Objects.requireNonNull(moduleContext);
    }

    /**
     * Create a form field for a specific {@link PropertyDefinition}.
     * @param parent a widget which will be the parent of the new field instance (cannot be null)
     * @param input the element to build the form field for.
     */
    @objid ("e081e8e6-51ca-489f-bc51-9a2122b8df4c")
    public IField createFormField(FormToolkit toolkit, Composite parent, ModelElement input, PropertyDefinition pdef) {
        IField field;
        PropertyType type = pdef.getType();
        
        IFormFieldData model = new PropertyFieldData(getModuleContext().getModelingSession(), input, pdef);
        
        final GridData layoutData = new GridData(SWT.FILL, SWT.TOP, true, false);
        layoutData.widthHint = 600;
        switch (type.getBaseType()) {
        case TEXT:
        
            if (type.getName().equals("MultiText")) {
                field = new TextField(toolkit, parent, model, 6);
                Font defaultFont = field.getComposite().getFont();
                layoutData.heightHint = defaultFont.getFontData()[0].getHeight() * 6 + 2;
            } else {
                field = new TextField(toolkit, parent, model, 1);
            }
        
            Composite cText = field.getComposite();
            cText.setLayoutData(layoutData);
            break;
        case ENUMERATE:
            field = new EnumField(toolkit, parent, model);
            Composite cEnum = field.getComposite();
            cEnum.setLayoutData(layoutData);
            break;
        case BOOLEAN:
            field = new BooleanField(toolkit, parent, model);
            Composite cBool = field.getComposite();
            cBool.setLayoutData(layoutData);
            break;
        case DATE:
            field = new DateField(toolkit, parent, model);
            Composite cDate = field.getComposite();
            cDate.setLayoutData(layoutData);
            break;
        case TIME:
            field = new TimeField(toolkit, parent, model);
            Composite cTime = field.getComposite();
            cTime.setLayoutData(layoutData);
            break;
        case ELEMENT:
            field = new ElementField(toolkit, parent, model);
            Composite cElement = field.getComposite();
            cElement.setLayoutData(layoutData);
            break;
        case RICHTEXT:
            field = new RichTextField(getModuleContext(), toolkit, parent, model);
            Composite cRichText = field.getComposite();
            cRichText.setLayoutData(layoutData);
            break;
        case FLOAT:
        case INTEGER:
        case STRING:
        case UNSIGNED:
        default:
            field = new StringField(toolkit, parent, model);
            Composite c = field.getComposite();
            c.setLayoutData(layoutData);
            break;
        }
        
        // If the property definition has a description put it as help
        String desc = getModuleContext().getModelingSession().getMetamodelExtensions().getDescription(pdef);
        if (desc != null) {
            field.setHelpText(desc);
        }
        return field;
    }

    /**
     * Create a form field from a specific {@link Stereotype}.
     * @param parent a widget which will be the parent of the new field instance (cannot be null)
     * @param input the element to build the form field for.
     */
    @objid ("09986b9c-41c2-48d1-9837-37984d546d85")
    public List<IField> createFormFields(FormToolkit toolkit, Composite parent, ModelElement input, Stereotype stereotype) {
        List<IField> fields = new ArrayList<>();
        
        PropertyTableDefinition ptype = stereotype.getDefinedTable();
        if (ptype != null) {
            for (PropertyDefinition pdef : ptype.getOwned()) {
                fields.add(createFormField(toolkit, parent, input, pdef));
            }
        }
        
        Stereotype parentStereotype = stereotype.getParent();
        if (parentStereotype != null) {
            fields.addAll(createFormFields(toolkit, parent, input, parentStereotype));
        }
        return fields;
    }

    /**
     * Build a fully generic implementation including:
     * <ul>
     * <li>the element's name</li>
     * <li>every PropertyTableDefinition defined by its stereotypes</li>
     * </ul>
     */
    @objid ("d717c366-8419-4184-9f01-65a6639987e7")
    public List<IField> createGenericFields(FormToolkit toolkit, Composite parent, ModelElement input) {
        List<IField> fields = new ArrayList<>();
        
        // Name
        fields.add(createMAttributeField(toolkit, parent, input, input.getMClass().getAttribute("Name")));
        
        // Fields from all stereotypes
        for (Stereotype stereotype : input.getExtension()) {
            fields.addAll(createFormFields(toolkit, parent, input, stereotype));
        }
        return fields;
    }

    /**
     * Create a form field from a specific {@link MAttribute}.
     * @param parent a widget which will be the parent of the new field instance (cannot be null)
     * @param input the element to build the form field for.
     */
    @objid ("c039db35-5e3b-4e30-9299-b27371d50965")
    public IField createMAttributeField(FormToolkit toolkit, Composite parent, ModelElement input, MAttribute mAtt) {
        IFormFieldData fieldModel = new MAttributeFieldData(getModuleContext().getModelingSession(), input, mAtt);
        
        Class<?> attType = mAtt.getType();
        if (attType == UUID.class) {
            StringField stringField = new StringField(toolkit, parent, fieldModel);
            stringField.setValidator(s -> {
                try {
                    UUID.fromString(s);
                    return null;
                } catch (IllegalArgumentException  e) {
                    return e.getLocalizedMessage();
                }
            });
            setupFieldLayout(stringField);
            return stringField;
        
        } else if (attType == String.class) {
            IField stringField = new StringField(toolkit, parent, fieldModel);
            setupFieldLayout(stringField);
            return stringField;
        } else if (attType == Integer.class) {
            StringField stringField = new StringField(toolkit, parent, fieldModel);
            stringField.setValidator(s -> {
                try {
                    Integer.parseInt(s);
                    return null;
                } catch (NumberFormatException  e) {
                    return e.getLocalizedMessage();
                }
            });
            setupFieldLayout(stringField);
            return stringField;
        
        } else if (attType == Long.class) {
            StringField stringField = new StringField(toolkit, parent, fieldModel);
            stringField.setValidator(s -> {
                try {
                    Long.parseLong(s);
                    return null;
                } catch (NumberFormatException  e) {
                    return e.getLocalizedMessage();
                }
            });
            setupFieldLayout(stringField);
            return stringField;
        } else if (attType == Float.class) {
            StringField stringField = new StringField(toolkit, parent, fieldModel);
            stringField.setValidator(s -> {
                try {
                    Float.parseFloat(s);
                    return null;
                } catch (NumberFormatException  e) {
                    return e.getLocalizedMessage();
                }
            });
            setupFieldLayout(stringField);
            return stringField;
        
        } else if (attType == Double.class) {
            StringField stringField = new StringField(toolkit, parent, fieldModel);
            stringField.setValidator(s -> {
                try {
                    Double.parseDouble(s);
                    return null;
                } catch (NumberFormatException  e) {
                    return e.getLocalizedMessage();
                }
            });
            setupFieldLayout(stringField);
            return stringField;
        
        } else if (attType == Boolean.class) {
            IField booleanField = new BooleanField(toolkit, parent, fieldModel);
            setupFieldLayout(booleanField);
            return booleanField;
        } else if (attType.isEnum()) {
            IField enumField = new EnumField(toolkit, parent, fieldModel);
            setupFieldLayout(enumField);
            return enumField;
        }
        
        // Default case, return a String field
        IField stringField = new StringField(toolkit, parent, fieldModel);
        setupFieldLayout(stringField);
        return stringField;
    }

    /**
     * Create a form field page.
     * @param id the id
     * @param label the page label
     * @param image the page icon
     * @return the created page.
     * @since 3.7.1
     */
    @objid ("b8a1a88d-fdb6-4e05-b1e1-1c6b2274e9af")
    public FormFieldPage createPage(int id, String label, Image image) {
        return new BasicPage(id, label, image);
    }

    /**
     * Get the module context whose service may be used to build fields.
     * @return the module context
     * @since 3.8
     */
    @objid ("6a6ad1ab-6fab-49c7-8259-837f24fb8599")
    public IModuleContext getModuleContext() {
        return this.moduleContext;
    }

    /**
     * Setup default layout for a {@link IField}.
     * @param aField a form field
     * @since 3.7.1
     */
    @objid ("84a9f4b2-e08f-4b04-9478-6f607116a38f")
    protected void setupFieldLayout(IField aField) {
        final GridData ld_name = new GridData(SWT.FILL, SWT.CENTER, true, false);
        ld_name.widthHint = 600;
        aField.getComposite().setLayoutData(ld_name);
    }

    /**
     * Basic implementation of {@link FormFieldPage} to be instantiated directly.
     * 
     * @since 3.7.1
     */
    @objid ("9b03c6c3-4b47-49f6-ad20-dcd9338178ce")
    public static class BasicPage implements FormFieldPage {
        @objid ("0737a306-85b0-462a-90b1-9bb9bf777932")
        private final int id;

        @objid ("0a238098-a2c7-4bc7-81c6-da46f1b5de93")
        private final String label;

        @objid ("48a6d866-9c03-46ef-b1ba-a6cfb483a4c5")
        private final Image image;

        @objid ("180a4175-5472-4ae5-956f-26cd01bc5289")
        public BasicPage(int id, String label, Image image) {
            super();
            this.id = id;
            this.label = label;
            this.image = image;
        }

        @objid ("488499b3-2fc4-4c66-ab0a-4f95e442bf42")
        @Override
        public String getLabel() {
            return this.label;
        }

        @objid ("a68cb537-04b2-4bad-b790-189ec51c6d6a")
        @Override
        public Image getImage() {
            return this.image;
        }

        @objid ("3e813a4e-3a31-4d67-824e-1ff1b569a97e")
        @Override
        public int getId() {
            return this.id;
        }

    }

}
