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
 * Proxy class to handle a {@link Dependency} with << UML2MethodReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("747f7e0d-5c8c-413d-bdbb-641dfe37801b")
public class UML2MethodReference {
    @objid ("86f83e13-1646-4d4c-9fe1-3dd6d65bb9dc")
    public static final String STEREOTYPE_NAME = "UML2MethodReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("ad532bde-afe7-4b44-b78f-1aa3baf5d72b")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2MethodReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2MethodReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bc13fb4f-3ebd-4b16-9bdf-8142f387007c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2MethodReference >> then instantiate a {@link UML2MethodReference} proxy.
     * 
     * @return a {@link UML2MethodReference} proxy on the created {@link Dependency}.
     */
    @objid ("8d3f3289-1c3e-45fc-afa6-c4f8af4e63de")
    public static UML2MethodReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2MethodReference.STEREOTYPE_NAME);
        return UML2MethodReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2MethodReference} proxy from a {@link Dependency} stereotyped << UML2MethodReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2MethodReference} proxy or <i>null</i>.
     */
    @objid ("dcde22a9-f64d-47e3-b37f-7478bbbb6f9b")
    public static UML2MethodReference instantiate(Dependency obj) {
        return UML2MethodReference.canInstantiate(obj) ? new UML2MethodReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2MethodReference} proxy from a {@link Dependency} stereotyped << UML2MethodReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2MethodReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("62790b27-f209-4bc4-b4bb-1bb04be1e6c3")
    public static UML2MethodReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2MethodReference.canInstantiate(obj))
        	return new UML2MethodReference(obj);
        else
        	throw new IllegalArgumentException("UML2MethodReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c0cc34af-6758-49bb-bc3b-3c1bfe67bac6")
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
        UML2MethodReference other = (UML2MethodReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("7c5e281e-e07d-474e-827b-718501dac66e")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("0c526209-1551-4546-8dd3-f77248be910b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ece6e39f-e0b1-4939-b700-b7fcee470d12")
    protected UML2MethodReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("de8df399-f6e6-443c-be21-019af7e50895")
    public static final class MdaTypes {
        @objid ("2886bbf6-0d14-484a-aea1-2ec6e229bf03")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("731abcca-5495-4f70-880b-cbeec130b756")
        private static Stereotype MDAASSOCDEP;

        @objid ("6ea4b72f-8500-4cfb-9d10-3ae36b96548b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8b3f8fec-25cd-4b51-90da-340b1aba3936")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "e445c33b-de99-11de-905b-001302895b2b");
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
