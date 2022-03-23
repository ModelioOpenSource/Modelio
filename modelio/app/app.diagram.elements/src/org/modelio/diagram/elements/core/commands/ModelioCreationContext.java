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
import java.util.List;
import java.util.Map;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.CreationFactory;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation factory for ModelElements creations in the diagram.
 * <p>
 * The context contains:
 * <ul>
 * <li>the metaclass to create,
 * <li>the parent to child dependency to use to attach the created element to its parent
 * <li>an optional stereotype to apply to the new element.
 * </ul>
 * <p>
 * The factory returns itself in {@link #getNewObject()}. Used by CreateElementCommand.
 * <p>
 * The factory returns the metaclass name for {@link #getObjectType()} .
 * 
 * @see org.modelio.diagram.elements.core.commands.DefaultCreateElementCommand CreateElementCommand
 */
@objid ("7f4303e3-1dec-11e2-8cad-001ec947c8cc")
public class ModelioCreationContext implements CreationFactory {
    /**
     * The parent to child meta dependency used to attach the new element to its owner.
     */
    @objid ("906cbbaa-1e83-11e2-8cad-001ec947c8cc")
    private MDependency dependency;

    /**
     * The name of the metaclass to create.
     */
    @objid ("906cbba4-1e83-11e2-8cad-001ec947c8cc")
    private final MClass metaclass;

    @objid ("7f4303ed-1dec-11e2-8cad-001ec947c8cc")
    private final MObject elementToUnmask;

    /**
     * A custom properties map.
     */
    @objid ("7f4303ee-1dec-11e2-8cad-001ec947c8cc")
    private Map<String, Object> properties = new HashMap<>();

    /**
     * An optional stereotype to apply to the new element.
     */
    @objid ("7f4303eb-1dec-11e2-8cad-001ec947c8cc")
    private Stereotype stereotype;

    /**
     * Creates a {@link ModelioCreationContext} that creates an MObject and a GmObject.
     * @param metaclass The metaclass name
     * @param dependency The name of the dependency
     * @param obstereotype An optional stereotype, may be null.
     */
    @objid ("7f4303f3-1dec-11e2-8cad-001ec947c8cc")
    public  ModelioCreationContext(MClass metaclass, MDependency dependency, Stereotype obstereotype) {
        this.metaclass = metaclass;
        this.dependency = dependency;
        this.stereotype = obstereotype;
        this.elementToUnmask = null;
        
    }

    /**
     * Creates a {@link ModelioCreationContext} that unmask an already existing MObject in the diagram.
     * @param elementToUnmask The element to unmask
     */
    @objid ("7f4565f9-1dec-11e2-8cad-001ec947c8cc")
    public  ModelioCreationContext(MObject elementToUnmask) {
        this.elementToUnmask = elementToUnmask;
        this.metaclass = elementToUnmask.getMClass();
        
        // Try to find the dependency
        MObject parent = elementToUnmask.getCompositionOwner();
        if (parent != null) { // Roots do not have a parent...
            for (MDependency dep : parent.getMClass().getDependencies(true)) {
                if (parent.mGet(dep).contains(elementToUnmask)) {
                    this.dependency = dep;
                    break;
                }
            }
        }
        
        // Look for a stereotype on the element to unmask to define it in the context
        if (elementToUnmask instanceof ModelElement) {
            final List<Stereotype> extension = ((ModelElement) elementToUnmask).getExtension();
            if (!extension.isEmpty()) {
                this.stereotype = extension.get(0);
            }
        }
        
    }

    /**
     * Convenience to get the {@link ModelioCreationContext} attached to a {@link CreateRequest}.
     * <p>
     * It is recommended because safer to use this methog instead of casting
     * {@link CreateRequest#getNewObject()} to <code>ModelioCreationContext</code>.
     * @param req the creation request
     * @return the creation context.
     * @throws ClassCastException if the request is not a <code>ModelioCreationContext</code> request
     */
    @objid ("f4243304-450b-48cb-a9e0-ddf9e53b8752")
    public static ModelioCreationContext fromRequest(CreateRequest req) throws ClassCastException {
        return (ModelioCreationContext) req.getNewObject();
    }

    /**
     * Get the name of the dependency used to add the element to its parent.
     * @return The name of the dependency.
     */
    @objid ("7f4565fd-1dec-11e2-8cad-001ec947c8cc")
    public MDependency getDependency() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.dependency;
    }

    /**
     * Get the {@link MObject} to unmask.
     * <p>
     * If null, the MObject must be created.
     * @return The model element to unmask.
     */
    @objid ("7f456602-1dec-11e2-8cad-001ec947c8cc")
    public MObject getElementToUnmask() {
        return this.elementToUnmask;
    }

    /**
     * Get the Java interface implemented by the {@link #getMetaclass() metaclass}.
     * @return the metaclass java interface.
     */
    @objid ("8c74170a-991a-481b-8ebe-a0a66e6d87bf")
    public Class<? extends MObject> getJavaClass() {
        return this.metaclass.getJavaInterface();
    }

    /**
     * Get the metaclass name of the MObject to create.
     * @return the metaclass name.
     */
    @objid ("7f456607-1dec-11e2-8cad-001ec947c8cc")
    public MClass getMetaclass() {
        // Automatically generated method. Please delete this comment before entering specific code.
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
    @objid ("7f45660c-1dec-11e2-8cad-001ec947c8cc")
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
    @objid ("7f456611-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public String getObjectType() {
        return this.metaclass.getName();
    }

    /**
     * Get the creation custom properties.
     * @return the creation properties or <tt>null</tt> if no property was defined.
     */
    @objid ("7f456616-1dec-11e2-8cad-001ec947c8cc")
    public Map<String, Object> getProperties() {
        return this.properties;
    }

    /**
     * Get the stereotype to apply to the created element.
     * @return the stereotype to apply.
     */
    @objid ("7f45661e-1dec-11e2-8cad-001ec947c8cc")
    public Stereotype getStereotype() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.stereotype;
    }

    /**
     * Convenience to get the {@link ModelioCreationContext} attached to a {@link CreateRequest}.
     * <p>
     * Returns <i>null</i> if the request does not own a <code>ModelioCreationContext</code>.
     * <p>
     * It is recommended because safer to use this methog instead of comparing
     * {@link CreateRequest#getNewObject()} class to <code>ModelioCreationContext</code>.
     * <p>
     * @param req the creation request
     * @return the creation context or <i>null</i>.
     */
    @objid ("a14312be-0898-4776-85c4-35ae1d4d9ad5")
    public static ModelioCreationContext lookRequest(CreateRequest req) {
        Object newObject = req.getNewObject();
        if (newObject instanceof ModelioCreationContext) {
            return (ModelioCreationContext) newObject;
        } else {
            return null;
        }
        
    }

    /**
     * Set the creation properties.
     * @param properties the creation properties.
     */
    @objid ("7f456623-1dec-11e2-8cad-001ec947c8cc")
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    /**
     * Add a custom property value.
     * @param key The property key
     * @param value The property value.
     */
    @objid ("7f45662a-1dec-11e2-8cad-001ec947c8cc")
    public void setProperty(String key, Object value) {
        this.properties.put(key, value);
    }

    /**
     * Get the {@link #getDependency() dependency} name if a dependency is defined.
     * Return null in the other case.
     * <p>
     * This is a convenience to avoid testing {@link #getDependency()} against <i>null</i>
     * for code that use the dependency name.
     * @return the dependency name or <i>null</i>.
     */
    @objid ("5c52029c-def1-4d7e-b54c-43c788e53278")
    public String getDependencyName() {
        if (getDependency() == null) {
            return null;
        } else {
            return getDependency().getName();
        }
        
    }

}
