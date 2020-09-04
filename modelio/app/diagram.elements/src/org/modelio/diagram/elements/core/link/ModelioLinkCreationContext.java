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

package org.modelio.diagram.elements.core.link;

import java.util.HashMap;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation factory for link creations in the diagram.
 * <p>
 * The context contains:
 * <ul>
 * <li>the metaclass of the link to create,
 * <li>an optional existing link to unmask instead of creating it.
 * <li>an optional stereotype to apply to the new link element.
 * <li>the new link connection routing mode.
 * </ul>
 * <p>
 * The factory returns itself in {@link #getNewObject()}. Used by DefaultCreateLinkElementCommand.
 * 
 * @see DefaultCreateLinkCommand
 */
@objid ("802a47f0-1dec-11e2-8cad-001ec947c8cc")
public class ModelioLinkCreationContext implements CreationFactory {
    @objid ("91d4bebd-1e83-11e2-8cad-001ec947c8cc")
    private MClass metaclass;

    @objid ("802a47fd-1dec-11e2-8cad-001ec947c8cc")
    private StyleKey defaultRoutingMode;

    @objid ("802a47fc-1dec-11e2-8cad-001ec947c8cc")
    private MObject elementToUnmask;

    /**
     * A custom properties map.
     */
    @objid ("802a47f7-1dec-11e2-8cad-001ec947c8cc")
    private Map<String, Object> properties = new HashMap<>();

    /**
     * An optional stereotype to apply to the new element.
     */
    @objid ("802a47f5-1dec-11e2-8cad-001ec947c8cc")
    private Stereotype stereotype;

    /**
     * Create a creation context
     * 
     * @param metaclass Metaclass of the element to create
     * @param obstereotype an optional stereotype
     */
    @objid ("802caa13-1dec-11e2-8cad-001ec947c8cc")
    public ModelioLinkCreationContext(MClass metaclass, Stereotype obstereotype) {
        this.metaclass = metaclass;
        this.stereotype = obstereotype;
    }

    /**
     * Creates a {@link ModelioLinkCreationContext} that unmask an already existing MObject in the diagram.
     * 
     * @param elementToUnmask The element to unmask
     */
    @objid ("802caa18-1dec-11e2-8cad-001ec947c8cc")
    public ModelioLinkCreationContext(MObject elementToUnmask) {
        this.elementToUnmask = elementToUnmask;
        this.metaclass = elementToUnmask.getMClass();
        if (elementToUnmask instanceof ModelElement) {
            final EList<Stereotype> extension = ((ModelElement) elementToUnmask).getExtension();
            if (!extension.isEmpty()) {
                this.stereotype = extension.get(0);
            }
        }
    }

    /**
     * Convenience to get the {@link ModelioLinkCreationContext} attached to a {@link CreateRequest}.
     * <p>
     * It is recommended because safer to use this methog instead of casting
     * {@link CreateRequest#getNewObject()} to <code>ModelioLinkCreationContext</code>.
     * 
     * @param req the creation request
     * @return the creation context.
     * @throws java.lang.ClassCastException if the request is not a <code>ModelioLinkCreationContext</code> request
     */
    @objid ("9ff94138-ea6d-4927-a052-e311d9cf67c8")
    public static ModelioLinkCreationContext fromRequest(CreateRequest req) throws ClassCastException {
        return (ModelioLinkCreationContext) req.getNewObject();
    }

    /**
     * Get the style key used to get the default connection routing mode for this link.
     * 
     * @return the connection routing mode style key.
     */
    @objid ("802caa4e-1dec-11e2-8cad-001ec947c8cc")
    public StyleKey getDefaultRoutingModeKey() {
        return this.defaultRoutingMode;
    }

    /**
     * Get the element to unmask.
     * <p>
     * If <code>null</code>, the element has to be created.
     * 
     * @return The element to unmask.
     */
    @objid ("802caa1c-1dec-11e2-8cad-001ec947c8cc")
    public MObject getElementToUnmask() {
        return this.elementToUnmask;
    }

    /**
     * Get the Java interface implemented by the {@link #getMetaclass() metaclass}.
     * 
     * @return the metaclass java interface.
     */
    @objid ("19100383-e53b-4d5a-a6bf-04f2c03fa5e0")
    public Class<? extends MObject> getJavaClass() {
        return this.metaclass.getJavaInterface();
    }

    /**
     * @return the metaclass of the element to create.
     */
    @objid ("802caa21-1dec-11e2-8cad-001ec947c8cc")
    public MClass getMetaclass() {
        return this.metaclass;
    }

    /**
     * TODO : Implementation does not match GEF design.
     * <p>
     * for GEF design this should create the ModelElement and the GmXxxx.
     * More realistically this could return the MClass returned by {@link #getObjectType()}.
     * <p>
     * For potential future fix it is recommended that callers call static convenience methods
     * defined on this class.
     * @see #fromRequest(CreateRequest)
     * @see #lookRequest(CreateRequest)
     * @see #getMetaclass()
     * @see #getJavaClass()
     */
    @objid ("802caa26-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getNewObject() {
        return this;
    }

    /**
     * Implementation may not match GEF design.
     * <p>
     * This could better return 'this'.
     * <p>
     * For potential future changes it is recommended that callers call static convenience methods
     * defined on this class.
     * @see #fromRequest(CreateRequest)
     * @see #lookRequest(CreateRequest)
     * @see #getMetaclass()
     * @see #getJavaClass()
     */
    @objid ("802caa2b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Object getObjectType() {
        return this.metaclass;
    }

    /**
     * Get the creation custom properties.
     * 
     * @return the creation properties or <tt>null</tt> if no property was defined.
     */
    @objid ("802caa30-1dec-11e2-8cad-001ec947c8cc")
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    /**
     * Get the stereotype to apply, may be <tt>null</tt>.
     * 
     * @return the stereotype to apply, may be <tt>null</tt>.
     */
    @objid ("802caa38-1dec-11e2-8cad-001ec947c8cc")
    public Stereotype getStereotype() {
        return this.stereotype;
    }

    /**
     * Convenience to get the {@link ModelioLinkCreationContext} attached to a {@link CreateRequest}.
     * <p>
     * Returns <i>null</i> if the request does not own a <code>ModelioLinkCreationContext</code>.
     * <p>
     * It is recommended because safer to use this methog instead of comparing
     * {@link CreateRequest#getNewObject()} class to <code>ModelioLinkCreationContext</code>.
     * <p>
     * 
     * @param req the creation request
     * @return the creation context or <i>null</i>.
     */
    @objid ("fabaf90d-52bb-498a-89e6-e106170f17f1")
    public static ModelioLinkCreationContext lookRequest(CreateRequest req) {
        Object newObject = req.getNewObject();
        if (newObject instanceof ModelioLinkCreationContext) {
            return (ModelioLinkCreationContext) newObject;
        } else {
            return null;
        }
    }

    /**
     * Set the creation properties.
     * 
     * @param properties the creation properties.
     */
    @objid ("802caa3d-1dec-11e2-8cad-001ec947c8cc")
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * Add a custom property value.
     * 
     * @param key The property key
     * @param value The property value.
     */
    @objid ("802caa44-1dec-11e2-8cad-001ec947c8cc")
    public void setProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    /**
     * Set the style key used to get the default connection routing mode for this link.
     * 
     * @param defaultRoutingModeKey the connection routing mode style key.
     */
    @objid ("802caa49-1dec-11e2-8cad-001ec947c8cc")
    public void setRoutingModeKey(final StyleKey defaultRoutingModeKey) {
        this.defaultRoutingMode = defaultRoutingModeKey;
    }

}
