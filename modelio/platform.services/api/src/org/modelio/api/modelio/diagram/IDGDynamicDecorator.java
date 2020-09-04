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

package org.modelio.api.modelio.diagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.resource.DataFormatException;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Decorator for diagram graphics in customized diagram.
 * <p>
 * With a decorator, it becomes possible to dynamically overwrite graphical property on any unmasked element.
 * </p>
 */
@objid ("7127ea44-8bc9-4ef4-9819-64312f5f611b")
public interface IDGDynamicDecorator {
    /**
     * Decorate a diagram graphic using the given access object.
     * @param context the context for a diagram graphic.
     */
    @objid ("c897f406-feda-4cbc-89c3-12c8ca514be9")
    void decorate(IOverwrittenProperties context);

    /**
     * This is a context for the decorator, grouping the currently edited element and its overwritten graphical properties.
     * <p>
     * For performance issues, no {@link IDiagramGraphic} is instantiated at first, but calling {@link #getDG(IDiagramHandle)}
     * creates one.
     * </p>
     * Some setters need a String as property value to specify a font or a color.
     * The passed value must then conform to the format used by {@link org.eclipse.jface.resource.StringConverter}.
     */
    @objid ("8360ed4c-e4ed-4dfd-ba9e-e8ca7b8a27aa")
    interface IOverwrittenProperties {
        /**
         * Instantiate an {@link IDiagramGraphic} associated with the current element.
         * <p>
         * The caller needs to open an {@link IDiagramHandle} first, in order to handle the lifecycle of the
         * graphical element.
         * </p>
         * @param diagram an opened handle to instantiate the graphical model.
         * @return an {@link IDiagramGraphic}.
         */
        @objid ("65f37cd0-147e-4f12-9283-aa0611437e92")
        IDiagramGraphic getDG(IDiagramHandle diagram);

        /**
         * @return the element graphical properties are being overwritten for.
         */
        @objid ("4198deb6-9e6e-4989-b817-752f14391ece")
        MObject getElement();

        /**
         * Sets the local property value of this graphic element for 'property'.
         * @param property The property name
         * @param value The property value
         */
        @objid ("e42b1a93-7265-413b-b60c-9b88c00a0290")
        void setProperty(final String property, final String value);

        /**
         * Indicates whereas bridge are drawn on vertical line segments that cross an horizontal one.
         * @param value The new value of the draw line bridge property.
         */
        @objid ("50440d21-7ddb-48c3-8a66-4707ec419bb6")
        void setDrawLineBridges(final boolean value);

        /**
         * Modify the current font.
         * <p>
         * This method fails if the value does not represent font data.
         * <p>
         * A valid font data representation is a string of the form
         * "<code><it>fontname</it>-<it>style</it>-<it>height</it></code>" where :<ul>
         * <li><code><it>fontname</it></code> is the name of a font,
         * <li><code><it>style</it></code> is a font style (one of
         * <code>"regular"</code>, <code>"bold"</code>,
         * <code>"italic"</code>, or <code>"bold italic"</code>)
         * <li>and <code><it>height</it></code> is an integer representing the font height.
         * </ul>
         * <p>
         * Example: "<code>Times New Roman-bold-36</code>".
         * <p>
         * You may use {@link org.eclipse.jface.resource.StringConverter#asString(org.eclipse.swt.graphics.FontData) StringConverter.asString(FontData)} if you have
         * a {@link org.eclipse.swt.graphics.Font Font} or a {@link org.eclipse.swt.graphics.FontData FontData}.
         * @param value The new font.
         * @exception DataFormatException if the given value does not represent a font data
         */
        @objid ("324a370f-ad58-44dd-84a0-d1b8aa9fc13b")
        void setFont(final String value) throws DataFormatException;

        /**
         * Modify the current line color.
         * <p>
         * You may use {@link org.eclipse.jface.resource.StringConverter#asString(org.eclipse.swt.graphics.RGB)
         * to get a color specification with the needed format.
         * @param value The new line color.
         */
        @objid ("c0e0c7f4-d709-4385-b615-726fba12f1c4")
        void setLineColor(final String value);

        /**
         * Modify the current line pattern.<br>
         * - 0 : Line drawing style for solid lines<br>
         * - 1 : Line drawing style for dashed lines<br>
         * - 2 : Line drawing style for dotted lines<br>
         * - 3 : Line drawing style for alternating dash-dot lines<br>
         * - 4 : Line drawing style for dash-dot-dot lines
         * @param value The new line pattern.
         */
        @objid ("c65377ee-ee80-4af0-b9ff-b65d60d4d801")
        void setLinePattern(final int value);

        /**
         * Modify the current line radius.
         * @param value The new line radius.
         */
        @objid ("718822a8-d937-4fd9-95c2-62feda85bbab")
        void setLineRadius(final int value);

        /**
         * Modify the current line width.
         * @param value The new line width.
         */
        @objid ("7a14739c-8d9e-4942-99e1-c6955872de7e")
        void setLineWidth(final int value);

        /**
         * Modify the current text color.
         * <p>
         * You may use {@link org.eclipse.jface.resource.StringConverter#asString(org.eclipse.swt.graphics.RGB)
         * to get a color specification with the needed format.
         * @param value The new text color.
         */
        @objid ("cbe11c2e-5537-40c8-b147-0af08d7b00e3")
        void setTextColor(final String value);

        /**
         * Modify the current fill color.
         * <p>
         * You may use {@link org.eclipse.jface.resource.StringConverter#asString(org.eclipse.swt.graphics.RGB)
         * to get a color specification with the needed format.
         * @param value The new fill color.
         */
        @objid ("c2fd708b-1dd5-4ea8-9708-d78793e88df4")
        void setFillColor(final String value);

        /**
         * Modify the current fill mode.
         * - 0 : The figure must be transparent.<br>
         * - 1 : The figure is filled with a solid color.<br>
         * - 2 : The figure is filled with a gradient.
         * @param value The new fill mode.
         */
        @objid ("de467a0c-17e5-477a-ba50-6d72b47ea89b")
        void setFillMode(final int value);

        /**
         * @return the diagram properties are being overwritten into.
         */
        @objid ("4e8afe23-e948-496c-8d3e-f8433902ddb6")
        AbstractDiagram getDiagram();

    }

}
