/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << UML2AssociationReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("77a332fe-d12f-47f1-abad-e85c4227ad5a")
public class UML2AssociationReference {
    @objid ("0eb10536-c860-441f-9ee2-bcf9c312674a")
    public static final String STEREOTYPE_NAME = "UML2AssociationReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6e49e65b-f3a9-45c9-8466-8615a43019cb")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2AssociationReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2AssociationReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("13514f1a-f0ef-4585-a653-869d477b28f0")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2AssociationReference >> then instantiate a {@link UML2AssociationReference} proxy.
     * 
     * @return a {@link UML2AssociationReference} proxy on the created {@link Dependency}.
     */
    @objid ("203269cc-711f-472e-8282-7dffb3fedada")
    public static UML2AssociationReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AssociationReference.STEREOTYPE_NAME);
        return UML2AssociationReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2AssociationReference} proxy or <i>null</i>.
     */
    @objid ("5a8df124-471e-42a2-b9d0-848a28e6b476")
    public static UML2AssociationReference instantiate(Dependency obj) {
        return UML2AssociationReference.canInstantiate(obj) ? new UML2AssociationReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AssociationReference} proxy from a {@link Dependency} stereotyped << UML2AssociationReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2AssociationReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("e2595fb3-2eea-4c55-9853-48d80b1bb0af")
    public static UML2AssociationReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2AssociationReference.canInstantiate(obj))
        	return new UML2AssociationReference(obj);
        else
        	throw new IllegalArgumentException("UML2AssociationReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("55bfe379-d941-47de-a3d5-c15d983ed558")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UML2AssociationReference other = (UML2AssociationReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("46634cb7-b86e-47e8-a55c-f7a538d68e39")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("ee5276ea-4e24-439f-b71b-636458b12a4f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("fa2d50db-d3fb-41fd-8350-8d16d068fccf")
    protected UML2AssociationReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b489f045-60a4-467e-9539-1f3d00766293")
    public static final class MdaTypes {
        @objid ("6d55183c-2b59-4ef3-978a-798beeb39f00")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("33578656-fc66-4ec8-9736-38fbb1bd7c92")
        private static Stereotype MDAASSOCDEP;

        @objid ("99b62ce2-d586-47d8-b8a3-ae45839807be")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("64901911-aa31-4180-8cd6-7a4134a48f9e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ee97796b-de99-11de-905b-001302895b2b");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
        }


	static {
		if(ModelerModuleModule.getInstance() != null) {
			init(ModelerModuleModule.getInstance().getModuleContext());
		}
	}
    }

}
