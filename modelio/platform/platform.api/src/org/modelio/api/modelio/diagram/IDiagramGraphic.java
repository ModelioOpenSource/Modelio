/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.api.modelio.diagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.diagram.dg.IDiagramLayer;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * <p>
 * Base interface of all graphical elements.
 * </p>
 * <p>
 * A IDiagramGraphic may be a node ({@link IDiagramNode}) or a link ({@link IDiagramLink}).
 * </p>
 * <p>
 * A graphic element may be related to an {@link MObject} model.
 * The element might not exist any longer (IDiagramGraphic#getElement returns <code>null</code>), meaning the graphical element is a "ghost".
 * Therefore, the only information still available are the Name of the ghost element, and its MRef.
 * </p>
 */
@objid ("b766dc64-644a-11e0-b650-001ec947cd2a")
public interface IDiagramGraphic {
    /**
     * Get the element this {@link IDiagramGraphic} is related to.
     * <p>
     * <b>Note:</b> May return <i>null</i> if the element is not resolved.
     * or the graphic does not represent a model element, such as diagram drawings..
     * @return the represented element or <i>null</i> if the element is not resolved.
     */
    @objid ("a42e2341-0ecc-11e2-96c4-002564c97630")
    MObject getElement();

    /**
     * Return the links that are starting (ie outgoing links) from this node.
     * @return A list of links in any case, possibly an empty one. Never returns null
     */
    @objid ("6d1b014f-69b5-11e0-adf3-002564c97630")
    List<IDiagramLink> getFromLinks();

    /**
     * For graphics that may have an hyper link to a model object, get the linked object.
     * <p>
     * Returns <i>null</i> for graphics that cannot have hyper-link to a model object.
     * @return the linked model object or <i>null</i>.
     * 
     * @since 3.1
     */
    @objid ("45a04f02-9765-4276-b269-c5e1cde0e0f1")
    MObject getHyperLink();

    /**
     * Get the layer this graphic belongs to.
     * @return the layer.
     * 
     * @since 3.1
     */
    @objid ("69aefe54-3510-412d-b274-186505da80f6")
    IDiagramLayer getLayer();

    /**
     * Get the list of all properties which values have been changed from their default "style" value.
     * @return A list of property names. It might be empty, but not <code>null</code>.
     */
    @objid ("0a65b075-981f-11e0-ade9-002564c97630")
    List<String> getLocalPropertyNames();

    /**
     * Return the name of this graphic. In most cases the name is the displayed label but this can however vary for
     * different elements. No uniqueness of names across a diagram should be assumed.<br>
     * For "ghost" graphics, the name should be the last know label.
     * @return the node name
     * @since 2.1
     */
    @objid ("6d1bebb5-69b5-11e0-adf3-002564c97630")
    String getName();

    /**
     * Get a style property.<br>
     * The resolution of the property values follows this algorithm:<br>
     * <li>return the local redefinition of the property if it exists.</li>
     * <li>otherwise, go to the graphical owner and return its local redefinition of the property if it exists.</li>
     * <li>when no local redefinition is found, the default value comes from the style of the element.</li>
     * <li>when no style value is found, the extended style are checked.</li>
     * <li>lastly, if no style defines a value, Modelio gives a default "factory value".</li>
     * <br>
     * Might return <code>null</code> if the corresponding style key isn't defined.
     * @param property The property name
     * @return The property value
     */
    @objid ("6d1079ba-69b5-11e0-adf3-002564c97630")
    String getProperty(final String property);

    /**
     * Get the graphical element style.
     * <p>
     * The style contains many properties such has the foreground and background color, the font and some display
     * options. These properties are displayed and editable in the symbol view.
     * @return the graphical element style. <code>null</code> if the graphical element has no proper style but references the owner diagram's style.
     */
    @objid ("e97e5d11-861c-11e0-b2a1-002564c97630")
    IStyleHandle getStyle();

    /**
     * Return the links that are ending (ie incoming links) at this node.
     * @return A list of links in any case, possibly an empty one. Never returns null
     */
    @objid ("6d1b4f71-69b5-11e0-adf3-002564c97630")
    List<IDiagramLink> getToLinks();

    /**
     * Returns the selected state of this graphical element.
     * @return <code>true</code> if the graphical element is the primary selection of the diagram.
     */
    @objid ("6d120064-69b5-11e0-adf3-002564c97630")
    boolean isPrimarySelected();

    /**
     * Returns the selected state of this graphical element.
     * @return <code>true</code> if the graphical element is selected in the diagram.
     */
    @objid ("6d11d955-69b5-11e0-adf3-002564c97630")
    boolean isSelected();

    /**
     * Mask the current representation. The associated element is not removed from the model.
     */
    @objid ("6d10eeee-69b5-11e0-adf3-002564c97630")
    void mask();

    /**
     * Move the graphic to another layer.
     * <p>
     * The new layer must be compatible with the old one.
     * @param newLayer the new layer
     * @throws IllegalArgumentException if the new layer is not compatible with the graphic.
     */
    @objid ("4abde33e-c018-4ebd-bf35-8e3a4352c994")
    void moveToLayer(IDiagramLayer newLayer) throws IllegalArgumentException;

    /**
     * Normalizing an element's style consists in removing from its local definitions the values that are currently the same as the value in cascaded style.
     * @since 2.1.1
     */
    @objid ("c6698413-596f-11e1-8a5c-002564c97630")
    void normalizeLocalProperties();

    /**
     * Removes all local property redefinitions.
     * Does not modify the cascaded style.
     * @since 2.1.1
     */
    @objid ("c6698411-596f-11e1-8a5c-002564c97630")
    void resetLocalProperties();

    /**
     * For graphics that may have an hyper link to a model object, set the linked object.
     * <p>
     * Has no effect on a graphic that cannot have hyper-link to a model object.
     * @param obj the linked model object or <i>null</i>.
     * 
     * @since 3.1
     */
    @objid ("99668aab-ae29-4b04-8c7a-c0150b3b82f9")
    void setHyperLink(MObject obj);

    /**
     * Sets the local property value of this graphic element for 'property'.
     * @param property The property name
     * @param value The property value
     */
    @objid ("6d116420-69b5-11e0-adf3-002564c97630")
    void setProperty(final String property, final String value);

    /**
     * Set the style used to get a property value when it is not defined locally.
     * @param style The new style. Use <code>null</code> to reference the owner diagram's style.
     */
    @objid ("e97e5d15-861c-11e0-b2a1-002564c97630")
    void setStyle(final IStyleHandle style);

}
