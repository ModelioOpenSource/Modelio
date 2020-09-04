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
 * Proxy class to handle a {@link Dependency} with << UML2EndCreationDataReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("2bb9e3da-9089-4006-a6a4-4b50ae303353")
public class UML2EndCreationDataReference {
    @objid ("fb621767-db41-4865-9c7f-f278ba967076")
    public static final String STEREOTYPE_NAME = "UML2EndCreationDataReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("2ba3bba2-c63f-4175-b16c-435164178f34")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2EndCreationDataReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2EndCreationDataReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("28ae8a74-ea48-4ea0-b5da-275a070bbfff")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2EndCreationDataReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2EndCreationDataReference >> then instantiate a {@link UML2EndCreationDataReference} proxy.
     * 
     * @return a {@link UML2EndCreationDataReference} proxy on the created {@link Dependency}.
     */
    @objid ("950053e7-aa45-46da-97bb-a4a27ebe1c54")
    public static UML2EndCreationDataReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2EndCreationDataReference.STEREOTYPE_NAME);
        return UML2EndCreationDataReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2EndCreationDataReference} proxy from a {@link Dependency} stereotyped << UML2EndCreationDataReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2EndCreationDataReference} proxy or <i>null</i>.
     */
    @objid ("f38abb24-32b3-4bb5-912c-6364271ae155")
    public static UML2EndCreationDataReference instantiate(Dependency obj) {
        return UML2EndCreationDataReference.canInstantiate(obj) ? new UML2EndCreationDataReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2EndCreationDataReference} proxy from a {@link Dependency} stereotyped << UML2EndCreationDataReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2EndCreationDataReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("3d250e5e-c343-47d6-9457-43426255cffc")
    public static UML2EndCreationDataReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2EndCreationDataReference.canInstantiate(obj))
        	return new UML2EndCreationDataReference(obj);
        else
        	throw new IllegalArgumentException("UML2EndCreationDataReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("604d2399-f4ce-416d-ad61-4a990fa7eb8d")
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
        UML2EndCreationDataReference other = (UML2EndCreationDataReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("0a9a1851-7499-4dcf-ac52-b708efdd97e8")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("33c3a2fa-8de6-4dac-b112-f894bb1bf816")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("eb783047-3cd7-450a-8212-161c533c3fd9")
    protected UML2EndCreationDataReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("ce8685fd-e624-48a1-a3ab-352efe352774")
    public static final class MdaTypes {
        @objid ("ef646d00-fac0-4000-ab8e-16c9b0229d56")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("08478a22-8709-4756-b1fc-56e05fb26252")
        private static Stereotype MDAASSOCDEP;

        @objid ("48564207-4cca-4661-b03a-035307de2965")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("25a80573-aa56-46e4-a4d0-37bf8a28a27a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "ee06097f-de99-11de-905b-001302895b2b");
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
