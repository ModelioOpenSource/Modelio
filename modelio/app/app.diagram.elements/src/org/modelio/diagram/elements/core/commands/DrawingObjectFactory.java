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
package org.modelio.diagram.elements.core.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.requests.CreationFactory;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawing;

/**
 * Creation factory for drawing creations in the diagram.
 * <p>
 * The context contains:
 * <ul>
 * <li>the class of the graphic model to create,
 * </ul>
 * <p>
 * The factory returns the class of model drawing to create in {@link #getObjectType()}.
 * {@link #getNewObject()} returns the identifier to use as the drawing identifier.
 * It generates a new identifier at each call unless an identifier has been specified at construction.
 * 
 * Used by <code>DefaultCreateGmNodeDrawingCommand</code>.
 * 
 * @see org.modelio.diagram.elements.drawings.core.policies.DefaultCreateGmNodeDrawingCommand DefaultCreateGmNodeDrawingCommand
 */
@objid ("00eeeaca-9f0a-4012-bc8a-efa6a233d78b")
public class DrawingObjectFactory implements CreationFactory {
    @objid ("0b10bb7b-88e6-46f3-92bd-39baf3afd792")
    private String drawingIdentifier;

    /**
     * A custom properties map.
     */
    @objid ("ef415bcb-9f51-4842-8e30-c33dcdbe3aff")
    private Map<String, Object> properties = new HashMap<>();

    /**
     * The name of the metaclass to create.
     */
    @objid ("e1d62940-1a34-421e-a604-7362d41b1df0")
    private Class<? extends IGmDrawing> metaclass;

    /**
     * Creates a factory that creates a {@link GmDrawing} with a random identifier.
     * @param metaclass the drawing class
     */
    @objid ("20b43e3c-87ab-4818-9151-b433cbf598f9")
    public  DrawingObjectFactory(Class<? extends GmDrawing> metaclass) {
        this.metaclass = metaclass;
        this.drawingIdentifier = null;
        
    }

    /**
     * Get the class of the drawing to create.
     * @return the drawing class.
     */
    @objid ("10bd46bc-41d1-43c9-92da-470a128a3407")
    public Class<? extends IGmDrawing> getMetaclass() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.metaclass;
    }

    /**
     * Return the identifier to give to the new drawing.
     */
    @objid ("88d8a01f-bab3-40fb-bdff-565057f13df0")
    @Override
    public Object getNewObject() {
        if (this.drawingIdentifier != null)
            return this.drawingIdentifier;
        else 
            return UUID.randomUUID().toString();
        
    }

    @objid ("ec4e71bc-2588-4e61-ab23-edffb7cfb7bd")
    @Override
    public Object getObjectType() {
        return this.metaclass;
    }

    /**
     * Get the creation custom properties.
     * @return the creation properties or <tt>null</tt> if no property was defined.
     */
    @objid ("e572b963-5226-4025-b834-411444b0f5eb")
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    /**
     * Set the creation properties.
     * @param properties the creation properties.
     */
    @objid ("52d7208b-f4bc-4548-89f7-a41dce40e6cf")
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * Add a custom property value.
     * @param key The property key
     * @param value The property value.
     */
    @objid ("341eb4d1-7710-474c-9f50-78796990b38a")
    public void setProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    /**
     * Creates a factory that creates a {@link GmDrawing}.
     * @param metaclass the drawing class
     * @param drawingIdentifier the new drawing identifier. Must be unique in the diagram.
     */
    @objid ("41156150-76ce-4560-b3ce-b5ca706c8e56")
    public  DrawingObjectFactory(Class<? extends IGmDrawing> metaclass, String drawingIdentifier) {
        this.metaclass = metaclass;
        this.drawingIdentifier = drawingIdentifier;
        
    }

}
