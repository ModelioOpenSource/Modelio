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
package org.modelio.diagram.elements.common.header;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.elements.core.node.GmSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagParameter;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTable;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.TypedPropertyTable;
import org.modelio.platform.mda.infra.ModuleI18NService;
import org.modelio.platform.model.ui.swt.images.MetamodelImageService;
import org.modelio.vcore.smkernel.mapi.MRef;
import org.modelio.vcore.smkernel.mapi.MStatus;

/**
 * Represents a ModelElement header.
 * <p>
 * Displays:
 * <li>the ModelElement main label, computed with <tt>computeMainLabel()</tt>
 * <li>tagged values<br>
 * <li>the stereotypes<br>
 * <li>the stereotype icons<br>
 * <li>the metaclass icon
 * <li>the metaclass keyword
 */
@objid ("7e62e6b4-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmModelElementHeader extends GmSimpleNode {
    @objid ("7e6548f1-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * The cached main label.
     */
    @objid ("8e6c220c-1e83-11e2-8cad-001ec947c8cc")
    private String label;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7e6548ee-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7e6548ed-1dec-11e2-8cad-001ec947c8cc")
    private boolean showLabel = true;

    @objid ("7e62e6b8-1dec-11e2-8cad-001ec947c8cc")
    private boolean showMetaclassIcon = false;

    @objid ("7e62e6b9-1dec-11e2-8cad-001ec947c8cc")
    private boolean showMetaclassKeyword = false;

    @objid ("7e62e6ba-1dec-11e2-8cad-001ec947c8cc")
    private boolean stackedStereotypes = false;

    /**
     * Empty constructor to use only for deserialization.
     */
    @objid ("7e6548f3-1dec-11e2-8cad-001ec947c8cc")
    public  GmModelElementHeader() {
        // Empty constructor to use only for deserialization.
    }

    /**
     * Initializes a model element header.
     * @param diagram the owning diagram.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("7e6548f6-1dec-11e2-8cad-001ec947c8cc")
    public  GmModelElementHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        init();
        
    }

    /**
     * This method can be used to filter the stereotypes that must be actually displayed. Return an empty list when no stereotypes are to be displayed. Return the passed parameter to implement a nop filter.
     * @param stereotypes the stereotypes that can be displayed
     * @return the stereotypes to display
     */
    @objid ("7e6548fb-1dec-11e2-8cad-001ec947c8cc")
    public abstract List<Stereotype> filterStereotypes(List<Stereotype> stereotypes);

    /**
     * This method can be used to filter the tags that must be actually displayed.
     * <p>
     * Return an empty list when no tag are to be displayed. Return the passed parameter to implement a nop filter.
     * @param taggedValues the tags that can be displayed. Do <b>not</b> modify this list.
     * @return the tags to display
     */
    @objid ("7e654903-1dec-11e2-8cad-001ec947c8cc")
    public abstract List<TaggedValue> filterTags(List<TaggedValue> taggedValues);

    @objid ("7e65490b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public IEditableText getEditableText() {
        if (getRelatedElement() == null) {
            return null;
        }
        return new IEditableText() {
        
                    @Override
                    public String getText() {
                        ModelElement relatedElement = getRelatedElement();
                        if (relatedElement == null) {
                            return "?";
                        }
                        return relatedElement.getName();
                    }
        
                    @Override
                    public void setText(String text) {
                        getRelatedElement().setName(text);
                    }
        
                };
        
    }

    /**
     * Get the main label.
     * <p>
     * The main label usually contains the element name with possibly its signature.
     * @return The main label.
     */
    @objid ("7e67ab90-1dec-11e2-8cad-001ec947c8cc")
    public String getMainLabel() {
        if (this.label == null) {
            updateMainLabelFromObModel();
        }
        return this.label;
    }

    @objid ("7e6a0db9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmModelElementHeader.MAJOR_VERSION;
    }

    /**
     * @return the metaclass icon that may be displayed on the left side of the header.
     */
    @objid ("7e6a0db2-1dec-11e2-8cad-001ec947c8cc")
    public Image getMetaclassIcon() {
        return MetamodelImageService.getIcon(getRelatedElement().getMClass());
    }

    @objid ("7e6a0da4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public ModelElement getRelatedElement() {
        return (ModelElement) super.getRelatedElement();
    }

    @objid ("7e62e693-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        if (getParent() == null) {
            return RepresentationMode.STRUCTURED;
        }
        return getParent().getRepresentationMode();
    }

    /**
     * Get the stereotype icons to display.
     * <p>
     * Look for the element stereotypes and filter them by calling {@link #filterStereotypes(List)}.
     * @return the stereotype icons to display.
     */
    @objid ("7e654910-1dec-11e2-8cad-001ec947c8cc")
    public List<Image> getStereotypeIcons() {
        if (getRelatedElement() != null) {
            final List<Stereotype> stereotypes = filterStereotypes(getRelatedElement().getExtension());
            if (!stereotypes.isEmpty()) {
                final List<Image> ret = new ArrayList<>(stereotypes.size());
        
                for (Stereotype s : stereotypes) {
                    final Image im = ModuleI18NService.getIcon(s);
                    if (im != null) {
                        ret.add(im);
                    }
                }
        
                return ret;
            }
        }
        return Collections.emptyList();
    }

    /**
     * Get the stereotypes labels.
     * @return an array of labels.
     */
    @objid ("87b82c54-94f2-42f1-a82f-2064e2bd786b")
    public List<String> getStereotypesLabel() {
        if (getRelatedElement() != null) {
            List<Stereotype> stereotypes = filterStereotypes(getRelatedElement().getExtension());
            if (!stereotypes.isEmpty()) {
                List<String> labels = new ArrayList<>(stereotypes.size());
        
                for (Stereotype s : stereotypes) {
                    // if there is no label, use the name
                    // question: should we filter out hidden stereotypes ? if(!s.isIsHidden())
                    labels.add(ModuleI18NService.getLabel(s));
                }
                return labels;
            }
        }
        return Collections.emptyList();
    }

    /**
     * Get all tagged value labels to display.
     * <p>
     * Look for the element tagged values and filter them by calling {@link #filterTags(List)}.
     * @return the tagged value labels to display.
     */
    @objid ("7e65491e-1dec-11e2-8cad-001ec947c8cc")
    public List<String> getTaggedValueLabels() {
        ModelElement element = getRelatedElement();
        
        if (element != null) {
            List<TaggedValue> tags = filterTags(element.getTag());
            List<String> labels = null;
            if (!tags.isEmpty()) {
                labels = new ArrayList<>(tags.size());
                for (TaggedValue tag : tags) {
                    // Ignore hidden tags
                    final TagType tagType = tag.getDefinition();
                    if (tagType == null || !tagType.isIsHidden()) {
                        labels.add(GmModelElementHeader.makeTagLabel(tag));
                    }
                }
            }
        
            List<PropertyTable> tablesToDisplay = filterPropertyTables(element.getProperties());
            if (!tablesToDisplay.isEmpty()) {
                if (labels == null) {
                    labels = new ArrayList<>(tablesToDisplay.size());
                }
                
                HashMap<String, Stereotype> mapStereotypes = new HashMap<>();
                for (Stereotype stereo : element.getExtension()) {
                    mapStereotypes.put(stereo.getUuid(), stereo);
                }
        
                for (PropertyTable propertyTable : tablesToDisplay) {
                    MStatus status = propertyTable.getStatus();
                    if (status.isVisible()) {
                        String ptName = propertyTable.getName();
                        Stereotype matchingStereo = mapStereotypes.get(ptName);
                        PropertyTableDefinition ptd = null;
                        
                        if (matchingStereo != null) {
                            ptd = matchingStereo.getDefinedTable();
                            
                        } 
                        for (Entry<Object, Object> entry : propertyTable.toProperties().entrySet()) {
                            if (ptd != null && ptd.getOwned(entry.getKey().toString()) != null) {
                                labels.add(GmModelElementHeader.makePropertyTableLabel(propertyTable, entry.getKey().toString(), ptd.getOwned(entry.getKey().toString()).computeLabel(entry.getValue().toString())));
                            }
                            else {
                                labels.add(GmModelElementHeader.makePropertyTableLabel(propertyTable, entry.getKey().toString(), entry.getValue().toString()));
                            }
                        }
                    }
                }
            }
        
            if (labels != null) {
                return labels;
            }
        }
        return Collections.emptyList();
    }

    /**
     * Get whether each stereotype is in its own &lt;&lt; >> or all are in the same &lt;&lt;a, b, c ...>>.
     * @return
     * <li><i>true</i>: each stereotype is in its &lt;&lt; >>.<br>
     * <li><i>false</i>: all stereotypes will be in a single &lt;&lt;a, b, c ...>> label
     */
    @objid ("97465298-c7cd-444c-9334-05d73ddabfde")
    public boolean isDisplayStereotypesAsStack() {
        return this.stackedStereotypes;
    }

    /**
     * Tells whether this label must be displayed on one line or many ones.
     * @return true to display the label on only one line.
     */
    @objid ("c5307c84-1cc3-49f7-b20e-941bb3f4e612")
    public boolean isFlat() {
        return false;
    }

    /**
     * Tells whether the element's name is shown.
     * @return true if the element's name is shown.
     */
    @objid ("7e6a0da9-1dec-11e2-8cad-001ec947c8cc")
    public boolean isShowLabel() {
        return this.showLabel;
    }

    /**
     * Tells whether the metaclass icon is shown.
     * @return true if the metaclass icon is shown.
     */
    @objid ("7e65492b-1dec-11e2-8cad-001ec947c8cc")
    public boolean isShowMetaclassIcon() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.showMetaclassIcon;
    }

    /**
     * Tells whether the metaclass keyword is shown.
     * @return true if the metaclass keyword is shown.
     */
    @objid ("7e654930-1dec-11e2-8cad-001ec947c8cc")
    public boolean isShowMetaclassKeyword() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.showMetaclassKeyword;
    }

    /**
     * The label visibility may be toggled with {@link MetaKey#SHOWLABEL} style metakey.
     */
    @objid ("b56f58f0-0094-41a3-bf02-138bcebeaf89")
    @Override
    public boolean isVisible() {
        GmModel parent = getParent();
        
        // No parent ==> invisible
        if (parent == null) {
            return false;
        }
        
        // Parent invisible ==> invisible
        if (parent instanceof GmNodeModel && !((GmNodeModel) parent).isVisible()) {
            return false;
        }
        
        // Visibility depends on "Show label" meta-key if defined
        StyleKey key = parent.getStyleKey(MetaKey.SHOWLABEL);
        if (key != null) {
            return getDisplayedStyle().getProperty(key);
        } else {
            return true;
        }
        
    }

    @objid ("7e67ab4f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmModelElementHeader.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("7e67ab53-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        updateMainLabelFromObModel();
        
        // whatever has changed, fire a label change, the header will update everything.
        firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        
    }

    /**
     * Set whether the element's name must be shown.
     * @param value whether the element's name must be shown.
     */
    @objid ("7e6a0dae-1dec-11e2-8cad-001ec947c8cc")
    public void setShowLabel(boolean value) {
        this.showLabel = value;
    }

    /**
     * Set whether the metaclass icon must be shown.
     * @param value whether the metaclass icon must be shown.
     */
    @objid ("7e67ab56-1dec-11e2-8cad-001ec947c8cc")
    public void setShowMetaclassIcon(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.showMetaclassIcon = value;
        
    }

    /**
     * Set whether the metaclass keyword must be shown .
     * @param value whether the metaclass keyword must be shown.
     */
    @objid ("7e67ab5a-1dec-11e2-8cad-001ec947c8cc")
    public void setShowMetaclassKeyword(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.showMetaclassKeyword = value;
        
    }

    /**
     * Set whether all stereotypes must be displayed on a one line label or many stacked labels.
     * @param value if true, stereotypes will be displayed one per line. If false all on one line.
     */
    @objid ("7e67ab5e-1dec-11e2-8cad-001ec947c8cc")
    public void setStackedStereotypes(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.stackedStereotypes = value;
        
    }

    @objid ("fcac2129-eb8d-48f6-83fd-924e1e8e05b7")
    @Override
    public void styleChanged(IStyle changedStyle) {
        fireVisibilityChanged();
        super.styleChanged(changedStyle);
        
    }

    @objid ("cc80b0c2-2c8b-46f2-b8b1-073622eaba71")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        // Copied from GmModelelementFlatHeader
        
        super.styleChanged(property, newValue);
        
        // Some keys might change the content of the label
        GmModel parent = getParent();
        if (parent != null) {
            final StyleKey showStereotypesKey = parent.getStyleKey(MetaKey.SHOWSTEREOTYPES);
            final StyleKey showTagKey = parent.getStyleKey(MetaKey.SHOWTAGS);
            final StyleKey showLabelKey = parent.getStyleKey(MetaKey.SHOWLABEL);
        
            if ((showStereotypesKey != null && showStereotypesKey.equals(property))
                    || (showTagKey != null && showTagKey.equals(property))) {
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
            }
        
            if (showLabelKey != null && showLabelKey.equals(property)) {
                fireVisibilityChanged();
            }
        
        }
        
    }

    @objid ("7e67ab8c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        out.writeProperty("show_icon", this.showMetaclassIcon);
        out.writeProperty("show_keyword", this.showMetaclassKeyword);
        out.writeProperty("stack_stereo", this.stackedStereotypes);
        out.writeProperty("show_label", this.showLabel);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmModelElementHeader.", GmModelElementHeader.MINOR_VERSION);
        
    }

    /**
     * Computes the main label of the header.
     * <p>
     * The main label is often the element name and the default implementation returns it.
     * <p>
     * This method may be redefined by subclasses.
     * @return The main label of the header.
     */
    @objid ("7e67ab67-1dec-11e2-8cad-001ec947c8cc")
    protected String computeMainLabel() {
        return getRelatedElement().getName();
    }

    @objid ("daf6ae16-f5ef-4356-85a2-0c22d8cf1c82")
    protected String getMetaclassKeyword() {
        return getRelatedElement().getMClass().getName();
    }

    /**
     * Updates the cached main label.
     * <p>
     * To be called from {@link #refreshFromObModel()} or <tt>styleChanged(*)</tt> methods when the label needs to be updated.
     * <p>
     * Fires no property change notification. It is to the caller to decide depending on the returned value and other conditions whether a property change event must be fired.
     * @return true if the label was changed, false if it is still the same.
     */
    @objid ("7e67ab6c-1dec-11e2-8cad-001ec947c8cc")
    protected boolean updateMainLabelFromObModel() {
        if (getRelatedElement() != null) {
            final String newName = computeMainLabel();
            if (this.label == null || !this.label.equals(newName)) {
                this.label = newName;
                return true;
            }
        }
        return false;
    }

    /**
     * To be called by the constructor and the {@link #read(IDiagramReader)} methods.
     */
    @objid ("7e67ab83-1dec-11e2-8cad-001ec947c8cc")
    private void init() {
        if (getRelatedElement() != null) {
            this.label = computeMainLabel();
        }
        
    }

    /**
     * Build the label for a tagged value.
     * @param tag a tagged value
     * @return the tag label
     */
    @objid ("7e67ab86-1dec-11e2-8cad-001ec947c8cc")
    private static String makeTagLabel(TaggedValue tag) {
        final StringBuilder buf = new StringBuilder();
        final TagType tagType = tag.getDefinition();
        
        String tagLabel = ModuleI18NService.getLabel(tagType);
        if (tagLabel == null || tagLabel.isEmpty()) {
            tagLabel = tagType.getName();
        }
        
        Stereotype typeStereotype = tagType.getOwnerStereotype();
        
        buf.append("{");
        if (typeStereotype != null) {
            String stereotypeLabel = ModuleI18NService.getLabel(typeStereotype);
            if (stereotypeLabel == null || stereotypeLabel.isEmpty()) {
                stereotypeLabel = typeStereotype.getName();
            }
            buf.append(stereotypeLabel);
            buf.append(".");
        }
        buf.append(tagLabel);
        
        final List<TagParameter> params = tag.getActual();
        if (!params.isEmpty()) {
            buf.append("(");
            boolean first = true;
            for (TagParameter param : params) {
                if (first) {
                    first = false;
                } else {
                    buf.append(", ");
                }
        
                buf.append(param.getValue());
            }
            buf.append(")");
        }
        
        TagParameter qual = tag.getQualifier();
        if (qual != null) {
            buf.append(":");
            buf.append(qual.getValue());
        }
        
        buf.append("}");
        return buf.toString();
    }

    @objid ("7e6a0db6-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.showMetaclassIcon = (Boolean) in.readProperty("show_icon");
        this.showMetaclassKeyword = (Boolean) in.readProperty("show_keyword");
        this.stackedStereotypes = (Boolean) in.readProperty("stack_stereo");
        this.showLabel = (Boolean) in.readProperty("show_label");
        
        init();
        
    }

    /**
     * This method can be used to filter the property tables that must be actually displayed.
     * <p>
     * Return an empty list when no property tables are to be displayed. Return the passed parameter to implement a no-op filter.
     * @param tables the property tables that can be displayed. Do <b>not</b> modify this list.
     * @return the property tables to display
     */
    @objid ("aea4bd37-ce7d-43af-82d1-a250bcc68609")
    protected List<PropertyTable> filterPropertyTables(List<PropertyTable> tables) {
        return tables;
    }

    @objid ("61d63929-2902-42f3-b825-459edc019091")
    private static String makePropertyTableLabel(PropertyTable propertyTable, String key, String value) {
        final StringBuilder buf = new StringBuilder();
        buf.append("{");
        
        String propLabel = null;
        if (propertyTable instanceof TypedPropertyTable) {
            PropertyTableDefinition type = ((TypedPropertyTable) propertyTable).getType();
            PropertyDefinition propDef = type.getOwned(key);
            if (propDef != null) {
                propLabel = ModuleI18NService.getLabel(propDef);
            }
        }
        if (propLabel == null) {
            propLabel = key;
        }
        
        buf.append(propLabel);
        
        buf.append(" = ");
        buf.append(value);
        buf.append("}");
        return buf.toString();
    }

}
