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
package org.modelio.api.modelio.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.api.module.IPeerModule;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.platform.ui.swt.QualifiedImage;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class provide the API to get the image of a model element.
 */
@objid ("71d0e1d2-e3cf-11dd-a49b-0014222a9f79")
public interface IImageService {
    /**
     * Get the small icon (16x16 pixels) representing the given metaclass.
     * @param metaclass Java interface of a metamodel metaclass.
     * @return The representing icon.
     * @since 3.7.1
     */
    @objid ("8ab29b6d-dcb4-4659-a4d2-9aaea25d77c1")
    Image getIcon(final Class<? extends MObject> metaclass);

    /**
     * Get the standard metamodel icon of an model object (usually 16x16 pixels).
     * <p>
     * This method return the standard icon of the model object ignoring the stereotypes owned by the object.
     * </p>
     * @param element the model object for which the icon must be displayed.
     * @return the icon corresponding to the model element.
     * @since 3.7.1
     */
    @objid ("15aca301-13e9-49cd-bb26-0b08f4e17f9b")
    Image getIcon(MObject element);

    /**
     * Get the stereotyped icon of an element (usually 16x16 pixels).
     * <p>
     * The returned icon depends on the filter:
     * <ul>
     * <li>if filter is not <code>null</code>, the first stereotype belonging to that module having an icon is used. <br>
     * If no such stereotype is found, the method returns <code>null</code></li>
     * <li>otherwise, the first stereotype with an icon is used. <br>
     * If no such stereotype is found, the method returns the UML image for this element.</li>
     * </ul>
     * </p>
     * @param element the element for which the image must be returned.
     * @param filter the module to use as filter.
     * @return the image or <code>null</code>.
     * @since 3.7.1
     */
    @objid ("c6e39b15-e880-42c5-b902-a8293860c031")
    Image getIcon(MObject element, final IPeerModule filter);

    /**
     * Get the small icon (16x16 pixels) representing the given metaclass.
     * @param metaclass A metamodel metaclass.
     * @return The representing icon.
     * @since 4.0
     */
    @objid ("4cb87607-1e7c-4918-afab-15e9ac3cf14f")
    Image getIcon(final MClass metaclass);

    /**
     * Get the image (32x32 pixels) representing the given metaclass.
     * @param metaclass Java interface of a metamodel metaclass.
     * @return The representing image.
     * @since 3.7.1
     */
    @objid ("61e8c994-2305-47fa-92da-ef6adfaa5cb0")
    Image getImage(final Class<? extends MObject> metaclass);

    /**
     * Get the standard metamodel image of an model object (usually 32x32 pixels).
     * <p>
     * This method return the standard image of the model object ignoring the stereotypes owned by the object.
     * </p>
     * @param element the model object for which the image must be displayed.
     * @return the image corresponding to the model element.
     * @since 3.7.1
     */
    @objid ("ee510910-662e-4f5f-9f23-c37b750a1a61")
    Image getImage(MObject element);

    /**
     * Get the stereotyped image of an element (usually 32x32 pixels).
     * <p>
     * The returned image depends on the filter:
     * <ul>
     * <li>if filter is not <code>null</code>, the first stereotype belonging to that module having an image is used. <br>
     * If no such stereotype is found, the method returns <code>null</code></li>
     * <li>otherwise, the first stereotype with an image is used. <br>
     * If no such stereotype is found, the method returns the UML image for this element.</li>
     * </ul>
     * </p>
     * @param element the element for which the image must be returned.
     * @param filter the module to use as filter.
     * @return the image or <code>null</code>.
     * @since 3.7.1
     */
    @objid ("0fbab508-256a-4503-9a30-a2b511cdc62c")
    Image getImage(MObject element, final IPeerModule filter);

    /**
     * Get the image (32x32 pixels) representing the given metaclass.
     * @param metaclass A metamodel metaclass.
     * @return The representing image.
     * @since 4.0
     */
    @objid ("35c773ad-d25e-4b80-b18c-3e0ba0e6740a")
    Image getImage(final MClass metaclass);

    /**
     * Get the small icon (16x16 pixels) representing the given metaclass.
     * @param metaclass Java interface of a metamodel metaclass.
     * @return The representing icon.
     * @deprecated use {@link #getIcon(Class) } instead
     */
    @objid ("a3ea61c6-0ecc-11e2-96c4-002564c97630")
    @Deprecated
    default Image getMetaclassImage(final Class<? extends MObject> metaclass) {
        return getIcon(metaclass);
    }

    /**
     * Get the stereotyped icon of an element (usually 16x16 pixels) as a {@link QualifiedImage}
     * <p>
     * The returned icon depends on the filter:
     * <ul>
     * <li>if filter is not <code>null</code>, the first stereotype belonging to that module having an icon is used. <br>
     * If no such stereotype is found, the method returns <code>null</code></li>
     * <li>otherwise, the first stereotype with an icon is used. <br>
     * If no such stereotype is found, the method returns the UML image for this element.</li>
     * </ul>
     * </p>
     * <p>
     * Note that the concept of first stereotype depends on the Modelio tool configuration especially the current workbench/expertises.
     * </p>
     * @param element the element for which the image must be returned.
     * @param filter the module to use as filter.
     * @return the image or <code>null</code>.
     * @since 4.0
     */
    @objid ("8a74c0f7-f6cb-4e9b-a414-3b259d80fe00")
    QualifiedImage getQualifiedIcon(MObject element, IPeerModule filter);

    /**
     * Get the small icon (16x16 pixels) representing the given metaclass as a {@link QualifiedImage}.
     * @param metaclass A metamodel metaclass.
     * @return The representing icon.
     * @since 4.0
     */
    @objid ("1b4cc72e-dfb3-4438-a402-f05266487b44")
    QualifiedImage getQualifiedIcon(final MClass metaclass);

    /**
     * Get the stereotyped image of an element (usually 32x32 pixels) as a {@link QualifiedImage}.
     * <p>
     * The returned image depends on the filter:
     * <ul>
     * <li>if filter is not <code>null</code>, the first stereotype belonging to that module having an image is used. <br>
     * If no such stereotype is found, the method returns <code>null</code></li>
     * <li>otherwise, the first stereotype with an image is used. <br>
     * If no such stereotype is found, the method returns the UML image for this element.</li>
     * </ul>
     * <p>
     * Note that the concept of first stereotype depends on the Modelio tool configuration especially the current workbench/expertises.
     * </p>
     * @param element the element for which the image must be returned.
     * @param filter the module to use as filter.
     * @return the image or <code>null</code>.
     * @since 4.0
     */
    @objid ("5cff3348-fce3-4ce2-9a7a-e9cabbc84e33")
    QualifiedImage getQualifiedImage(MObject element, IPeerModule filter);

    /**
     * Get the image (32x32 pixels) representing the given metaclass as a {@link QualifiedImage}.
     * @param metaclass A metamodel metaclass.
     * @return The representing image.
     * @since 4.0
     */
    @objid ("0a047982-ede3-4899-a098-86737056ced4")
    QualifiedImage getQualifiedImage(final MClass metaclass);

    /**
     * Get the icon provided by the module for a given stereotype (usually 16x16 pixels).
     * <p>
     * The life cycle of the returned image is handled by the owner module and the image should not be disposed.
     * </p>
     * @param stereotype a stereotype
     * @return the stereotype image, or <i>null</i> if the module provides none.
     * @since 3.7.1
     */
    @objid ("ee33cab4-02ca-4d5e-8413-b62aa5796e61")
    Image getStereotypeIcon(Stereotype stereotype);

    /**
     * Get the image provided by the module for a given stereotype (usually 32x32 pixels).
     * <p>
     * The life cycle of the returned image is handled by the owner module and the image should not be disposed.
     * </p>
     * @param stereotype a stereotype
     * @return the stereotype image, or <i>null</i> if the module provides none.
     * @since 3.7.1
     */
    @objid ("8de41d3b-1d48-4f27-8960-a3d445146240")
    Image getStereotypeImage(Stereotype stereotype);

    /**
     * Get the stereotyped icon of an element.
     * <p>
     * The returned icon depends on the filter:
     * <ul>
     * <li>if filter is not <code>null</code>, the first stereotype belonging to that module having an icon is used. <br>
     * If no such stereotype is found, the method returns <code>null</code></li>
     * <li>otherwise, the first stereotype with an icon is used. <br>
     * If no such stereotype is found, the method returns the UML image for this element.</li>
     * </ul>
     * </p>
     * @param element the element for which the image must be returned.
     * @param filter the module to use as filter.
     * @param useCmsDecoration this parameter is no longer used.
     * @return the image or <code>null</code>.
     * @deprecated use {@link #getIcon(MObject, IPeerModule) } instead.
     */
    @objid ("a3ea13a3-0ecc-11e2-96c4-002564c97630")
    @Deprecated
    default Image getStereotypedImage(MObject element, final IPeerModule filter, final boolean useCmsDecoration) {
        return getIcon(element, filter);
    }

    /**
     * Get the standard metamodel icon of an model object (usually 16x16 pixels).
     * <p>
     * This method return the standard icon of the model object ignoring the stereotypes owned by the object.
     * </p>
     * @param element the model object for which the icon must be displayed.
     * @param useCmsDecoration this parameter is no longer used.
     * @return the icon corresponding to the model element.
     * @deprecated use {@link #getIcon(MObject) } instead.
     */
    @objid ("a3ea3ab6-0ecc-11e2-96c4-002564c97630")
    @Deprecated
    default Image getUmlImage(MObject element, final boolean useCmsDecoration) {
        return getIcon(element);
    }

}
