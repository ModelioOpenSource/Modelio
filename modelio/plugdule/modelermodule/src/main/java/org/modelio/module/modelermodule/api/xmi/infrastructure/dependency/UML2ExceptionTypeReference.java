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
 * Proxy class to handle a {@link Dependency} with << UML2ExceptionTypeReference >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("87372d24-b077-4b6a-a76e-518b2d785b07")
public class UML2ExceptionTypeReference {
    @objid ("ceb7a161-0a03-432f-86da-d205467116b0")
    public static final String STEREOTYPE_NAME = "UML2ExceptionTypeReference";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("6af0e1d1-d4c7-4d5b-b8c4-394600a76556")
    protected final Dependency elt;

    /**
     * Tells whether a {@link UML2ExceptionTypeReference proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << UML2ExceptionTypeReference >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("6001f8be-7717-4cc9-bf67-10c62cb0f587")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << UML2ExceptionTypeReference >> then instantiate a {@link UML2ExceptionTypeReference} proxy.
     * 
     * @return a {@link UML2ExceptionTypeReference} proxy on the created {@link Dependency}.
     */
    @objid ("b93bede2-6810-4763-8b65-70cec1a7729c")
    public static UML2ExceptionTypeReference create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExceptionTypeReference.STEREOTYPE_NAME);
        return UML2ExceptionTypeReference.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionTypeReference} proxy from a {@link Dependency} stereotyped << UML2ExceptionTypeReference >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link UML2ExceptionTypeReference} proxy or <i>null</i>.
     */
    @objid ("ec593e4d-b529-4a2b-b4bb-f9ac4b43fa15")
    public static UML2ExceptionTypeReference instantiate(Dependency obj) {
        return UML2ExceptionTypeReference.canInstantiate(obj) ? new UML2ExceptionTypeReference(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExceptionTypeReference} proxy from a {@link Dependency} stereotyped << UML2ExceptionTypeReference >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link UML2ExceptionTypeReference} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("976b1fbb-fb04-4b89-8230-6378a9b1c01f")
    public static UML2ExceptionTypeReference safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (UML2ExceptionTypeReference.canInstantiate(obj))
        	return new UML2ExceptionTypeReference(obj);
        else
        	throw new IllegalArgumentException("UML2ExceptionTypeReference: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6d52de94-46b5-4b9e-a419-0987e277b42c")
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
        UML2ExceptionTypeReference other = (UML2ExceptionTypeReference) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("017ad303-ecc6-4092-ab2f-b98746e3b66c")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("b67363c7-420b-45ab-ae45-3077964cce60")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("3dd9d768-2e98-4565-9255-617c4ea7220d")
    protected UML2ExceptionTypeReference(Dependency elt) {
        this.elt = elt;
    }

    @objid ("209cf20e-a9e7-4dbf-a4ff-221a53f12e81")
    public static final class MdaTypes {
        @objid ("9f514eeb-99fa-4974-9161-c5c6e8882e73")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("07558a4c-f637-4881-bc67-e9fe05551157")
        private static Stereotype MDAASSOCDEP;

        @objid ("ae1ec359-3472-447f-80e2-287b47b25963")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ca64d53e-6e87-4663-a0a0-570138594262")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4e477e48-35b4-11df-9280-001302895b2b");
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
